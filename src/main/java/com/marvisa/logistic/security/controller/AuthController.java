package com.marvisa.logistic.security.controller;

import com.marvisa.logistic.common.response.ApiResponse;
import com.marvisa.logistic.security.dto.*;
import com.marvisa.logistic.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return new ApiResponse<>(true, "Usuario registrado correctamente", authService.register(request));
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return new ApiResponse<>(true, "Login exitoso", authService.login(request));
    }

    @PostMapping("/refresh")
    public ApiResponse<RefreshTokenResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return new ApiResponse<>(true, "Token refrescado correctamente", authService.refresh(request));
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@Valid @RequestBody RefreshTokenRequest request) {
        authService.logout(request);
        return new ApiResponse<>(true, "Logout exitoso", null);
    }

    @GetMapping("/me")
    public ApiResponse<UserMeResponse> me(Authentication authentication) {
        return new ApiResponse<>(true, "Usuario autenticado", authService.me(authentication));
    }
}