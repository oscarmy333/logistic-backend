package com.marvisa.logistic.security.controller;

import com.marvisa.logistic.common.exception.BadRequestException;
import com.marvisa.logistic.common.response.ApiResponse;
import com.marvisa.logistic.security.dto.*;
import com.marvisa.logistic.security.service.AuthService;
import com.marvisa.logistic.security.service.RateLimitService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RateLimitService rateLimitService;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return new ApiResponse<>(true, "Usuario registrado correctamente", authService.register(request));
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody AuthRequest request, HttpServletRequest httpRequest) {
        String key = "login:" + httpRequest.getRemoteAddr();

        if (!rateLimitService.allow(key)) {
            throw new BadRequestException("Demasiados intentos de login. Intenta nuevamente en un minuto.");
        }

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