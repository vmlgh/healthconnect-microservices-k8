package com.healthconnect.user.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.healthconnect.user.model.util.ApiResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //TODO add log
		
		  ApiResponse<Void> apiResponse = new
		  ApiResponse<Void>(HttpStatus.UNAUTHORIZED, authException.getMessage());
		  response.sendError(HttpServletResponse.SC_UNAUTHORIZED, new
		  Gson().toJson(apiResponse));
		 
    }

}