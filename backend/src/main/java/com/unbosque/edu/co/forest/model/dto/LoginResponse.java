package com.unbosque.edu.co.forest.model.dto;

import java.util.List;

public class LoginResponse {
    private String token;
    private String refreshToken;
    private boolean mfaRequired;
    private List<String> roles;
    private UserDTO user;
    private String errorMessage;

    public LoginResponse(String token, boolean mfaRequired) {
        this.token = token;
    }

    public LoginResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isMfaRequired() {
        return mfaRequired;
    }

    public void setMfaRequired(boolean mfaRequired) {
        this.mfaRequired = mfaRequired;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
