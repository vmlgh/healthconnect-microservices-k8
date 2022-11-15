package com.healthconnect.user.security;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.enums.UserStatus;
import com.healthconnect.user.model.util.ApiResponse;
import com.healthconnect.user.model.util.HealthConnectUtility;
import com.healthconnect.user.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SIGNING_KEY = "aws342345rty";
    public static final String TOKEN_EXPIRED = "Token has been expired.";
    public static final String UN_SUPPORTED = "Token is unsupported.";
    public static final String MALFORMED = "Token is Malformed.";
    public static final String SIGNATURE_MISMATCH = "Token signature is mismatched.";
    public static final String ILLEGAL_ARG = "IllegalArgument.";
    public static final String SCOPES = "scopes";
    
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            LOGGER.info("Entry to JWTAuthenticationFilter doFilterInternal method from ip - " + getIpAdressFromRequest(req));
            //LOGGER.info(extractPostRequestBody(req));
            String header = req.getHeader(HEADER_STRING);
            String userId = null;
            String authToken = null;
            List<String> roles = null;
            if (header != null && header.startsWith(TOKEN_PREFIX)) {
                authToken = new String(Base64.getDecoder().decode(header.replace(TOKEN_PREFIX, "")));
            }else if(req.getParameter("token") != null) {
                authToken = new String(Base64.getDecoder().decode(req.getParameter("token")));
            }else {
                logger.warn("couldn't find bearer string, will ignore the header.");
            }
            if(authToken != null) {
                LOGGER.info("token - " + authToken);
                try {
                    Jws<Claims> claims = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(authToken);
                    roles = claims.getBody().get(SCOPES, List.class);
                    userId = claims.getBody().getSubject();
                } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                        | IllegalArgumentException e) {
                    if (ExpiredJwtException.class.isInstance(e)) {
                        throw new SecurityException(UNAUTHORIZED, TOKEN_EXPIRED);
                    } else if (UnsupportedJwtException.class.isInstance(e)) {
                        throw new SecurityException(UNAUTHORIZED.value(), UN_SUPPORTED);
                    } else if (MalformedJwtException.class.isInstance(e)) {
                        throw new SecurityException(UNAUTHORIZED.value(), MALFORMED);
                    } else if (SignatureException.class.isInstance(e)) {
                        throw new SecurityException(UNAUTHORIZED.value(), SIGNATURE_MISMATCH);
                    } else if (IllegalArgumentException.class.isInstance(e)) {
                        throw new SecurityException(UNAUTHORIZED.value(), ILLEGAL_ARG);
                    }
                }
            }
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                long id = HealthConnectUtility.extractRecordIdFromUserId(userId);
                User dbUser = userRepository.findById(id).get();
                if (dbUser == null) {
                    throw new SecurityException(BAD_REQUEST.value(), "Invalid token.");
                }
                validToken(dbUser);
                Set<GrantedAuthority> authorities = new HashSet<>();
                roles.forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role));
                });
                AuthenticatedUser authentication = new AuthenticatedUser(userId, authorities, dbUser);
                logger.info("authenticated user " + userId + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(req, res);
        }catch(Exception e){
            ApiResponse<Void> apiResponse = new ApiResponse<>(HttpStatus.UNAUTHORIZED, e.getMessage(), null);
            res.getOutputStream().println(new Gson().toJson(apiResponse));
        }
    }

    String extractPostRequestBody(HttpServletRequest request) throws IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }

    private String getIpAdressFromRequest(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public void validToken(User dbUser) throws SecurityException {
        if(dbUser.isDeleted() || dbUser.getStatus() == UserStatus.BLOCKED){
            throw new SecurityException(UNAUTHORIZED, "User is disabled.Reach out to admin to enable account.");
        }
        if(dbUser.getToken() == null){
            throw new SecurityException(BAD_REQUEST, "Can not recognise any valid auth token. Try login again.");
        }
    }


}