package com.rltw.todo.auth.controller;

import com.rltw.todo.auth.dto.AuthPayload;
import com.rltw.todo.auth.dto.AuthResponse;
import com.rltw.todo.auth.model.User;
import com.rltw.todo.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("login")
    AuthResponse login(@RequestBody @Valid AuthPayload payload){
       User user = userService.getUser(payload);

       String token = userService.encodeJWT(user.getId());
       return new AuthResponse(token);
    }

    @PostMapping("signup")
    AuthResponse signup(@RequestBody @Valid AuthPayload payload){
        if(userService.doesUsernameExist(payload.getUsername())){
            throw new RuntimeException("test");
        }

        User user = userService.createUser(payload);
        String token = userService.encodeJWT(user.getId());

        return new AuthResponse(token);
    }

    @GetMapping("")
    User getUser(@AuthenticationPrincipal User user){

        return user;
    }
}
