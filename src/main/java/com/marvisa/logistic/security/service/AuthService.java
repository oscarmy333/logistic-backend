package com.marvisa.logistic.security.service;

import com.marvisa.logistic.security.dto.AuthRequest;
import com.marvisa.logistic.security.dto.AuthResponse;
import com.marvisa.logistic.security.dto.RegisterRequest;
import com.marvisa.logistic.security.entity.Role;
import com.marvisa.logistic.security.entity.RoleName;
import com.marvisa.logistic.security.entity.User;
import com.marvisa.logistic.security.jwt.JwtService;
import com.marvisa.logistic.security.repository.RoleRepository;
import com.marvisa.logistic.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El username ya existe");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya existe");
        }

        RoleName roleName = RoleName.valueOf(request.getRole().toUpperCase());
        Role role = roleRepository.findByRol(roleName)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        User user = User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .roles(Set.of(role))
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getUsername());
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getUsername());
    }
}