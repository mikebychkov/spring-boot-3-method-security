package com.mikebychkov.methodsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
public class MyController {

    @GetMapping("/some")
    public ResponseEntity<String> getSome() {

        return ResponseEntity.ok("some");
    }

    @GetMapping("/secret")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<String> getSecret() {

        return ResponseEntity.ok("secret");
    }

    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {

        return ResponseEntity.ok(getUsername());
    }

    public String getUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getName();
    }
}
