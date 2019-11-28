package com.mall.jiuzhenbao.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mall.jiuzhenbao.security.exception.BeanValidationErrorCode;

import javax.validation.constraints.NotNull;

public class LoginRequest {
    @JsonProperty(value = "username", required = true)
    @NotNull(message = BeanValidationErrorCode.NOT_BLANK)
    private String username;

    @JsonProperty(value = "password", required = true)
    @NotNull(message = BeanValidationErrorCode.NOT_BLANK)
    private String password;

    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Return username from the loginRequest
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return Password from the loginRequest
     * @return
     */
    public String getPassword() {
        return password;
    }
}
