package com.rltw.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerSample {
    @GetMapping("")
    ResponseEntity<?> home(@AuthenticationPrincipal Object principal){
        return ResponseEntity.ok(principal);
    }
}
