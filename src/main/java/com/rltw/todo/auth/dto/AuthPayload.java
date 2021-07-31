package com.rltw.todo.auth.dto;

import com.rltw.todo.auth.model.User;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;


@Component
public class AuthPayload {
    @NotEmpty(message = "Username is required")
    @Size(min=3,max=255,message = "Username must be between 3-255 chars")
    private String username;

    @NotEmpty(message="Password is required")
    @Size(min=5,max=255,message = "Password must be between 5-255 chars")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser(){
        return new User(this.getUsername(),this.getPassword());
    }
}
