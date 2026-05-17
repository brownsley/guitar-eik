package com.chord.server.controllers.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chord.server.dto.request.auth.UserCreateDto;
import com.chord.server.dto.request.auth.UserLoginDto;
import com.chord.server.services.auth.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    // @PreAuthorize("hasAuthority('user:create')")
    public String register(@Valid @RequestBody UserCreateDto createDto) {
        return authService.register(createDto);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginDto loginDto) {
        return authService.login(loginDto);
    }

}
