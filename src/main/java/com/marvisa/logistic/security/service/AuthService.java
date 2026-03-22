package com.marvisa.logistic.security.service;

import com.marvisa.logistic.common.exception.BadRequestException;
import com.marvisa.logistic.security.dto.*;
import com.marvisa.logistic.security.entity.RefreshToken;
import com.marvisa.logistic.security.entity.Role;
import com.marvisa.logistic.security.entity.RoleName;
import com.marvisa.logistic.security.entity.User;
import com.marvisa.logistic.security.jwt.JwtService;
import com.marvisa.logistic.security.mapper.UserMapper;
import com.marvisa.logistic.security.repository.RoleRepository;
import com.marvisa.logistic.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String ROLES = "roles";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final RefreshTokenService refreshTokenService;
    private final LoginAttemptService loginAttemptService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("El usuario ya existe");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("El email ya existe");
        }

        RoleName rol = parseRole(request.getRole());

        Role role = roleRepository.findByRol(rol)
                .orElseThrow(() -> new BadRequestException("Rol no encontrado"));

        User user = User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .failedLoginAttempts(0)
                .roles(Set.of(role))
                .build();

        userRepository.save(user);

        String accessToken = jwtService.generateAccessToken(
                user,
                Map.of(ROLES, user.getRoles().stream().map(r -> r.getRol().name()).toList())
        );

        RefreshTokenService.TokenPair tokenPair = refreshTokenService.createRefreshToken(user);

        return new AuthResponse(accessToken, tokenPair.rawToken(), userMapper.toResponse(user));
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

        if (loginAttemptService.isLocked(user)) {
            throw new LockedException("Usuario bloqueado temporalmente");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception ex) {
            loginAttemptService.loginFailed(user);
            throw ex;
        }

        loginAttemptService.loginSucceeded(user);

        String accessToken = jwtService.generateAccessToken(
                user,
                Map.of(ROLES, user.getRoles().stream().map(r -> r.getRol().name()).toList())
        );

        RefreshTokenService.TokenPair tokenPair = refreshTokenService.createRefreshToken(user);

        return new AuthResponse(accessToken, tokenPair.rawToken(), userMapper.toResponse(user));
    }

    public RefreshTokenResponse refresh(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.verify(request.getRefreshToken());
        User user = refreshToken.getUser();

        String newAccessToken = jwtService.generateAccessToken(
                user,
                Map.of(ROLES, user.getRoles().stream().map(r -> r.getRol().name()).toList())
        );

        RefreshTokenService.TokenPair newPair = refreshTokenService.createRefreshToken(user);
        refreshTokenService.revoke(refreshToken);

        return new RefreshTokenResponse(newAccessToken, newPair.rawToken());
    }

    public void logout(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.verify(request.getRefreshToken());
        refreshTokenService.revoke(refreshToken);
    }

    public UserMeResponse me(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userMapper.toMeResponse(user);
    }

    private RoleName parseRole(String rawRole) {
        return switch (rawRole.trim().toUpperCase()) {
            case "ROLE_ADMIN", "ADMIN" -> RoleName.ROLE_ADMIN;
            case "ROLE_OPERADOR", "OPERADOR" -> RoleName.ROLE_OPERADOR;
            case "ROLE_VENDEDOR", "VENDEDOR" -> RoleName.ROLE_VENDEDOR;
            case "ROLE_REPARTIDOR", "REPARTIDOR" -> RoleName.ROLE_REPARTIDOR;
            default -> throw new BadRequestException("Rol inválido");
        };
    }
}