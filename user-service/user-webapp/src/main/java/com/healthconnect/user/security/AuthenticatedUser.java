package com.healthconnect.user.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.healthconnect.user.model.core.User;


public class AuthenticatedUser implements Authentication {

	private static final long serialVersionUID = 6861381095901879822L;
	private String userId;
    private boolean authenticated = true;
    private Set<GrantedAuthority> authorities;
    private User user;

    public AuthenticatedUser(String userId, Set<GrantedAuthority> authorities, User user){
        this.userId = userId;
        this.authorities = authorities;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return this.user;
    }

    @Override
    public Object getPrincipal() {
        return this.userId;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return this.userId;
    }
}