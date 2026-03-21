package com.marvisa.logistic.security.service;

import com.marvisa.logistic.common.exception.BadRequestException;
import com.marvisa.logistic.security.entity.RefreshToken;
import com.marvisa.logistic.security.entity.User;
import com.marvisa.logistic.security.jwt.JwtService;
import com.marvisa.logistic.security.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Value("${app.jwt.refresh-expiration}")
    private long refreshExpiration;

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(jwtService.generateRefreshTokenValue())
                .expiryDate(LocalDateTime.now().plus(refreshExpiration, ChronoUnit.MILLIS))
                .revoked(false)
                .user(user)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verify(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByTokenAndDeletedFalse(token)
                .orElseThrow(() -> new BadRequestException("Refresh token inválido"));

        if (Boolean.TRUE.equals(refreshToken.getRevoked())) {
            throw new BadRequestException("Refresh token revocado");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Refresh token expirado");
        }

        return refreshToken;
    }

    public void revoke(RefreshToken refreshToken) {
        refreshToken.setRevoked(true);
        refreshToken.setDeleted(true);
        refreshTokenRepository.save(refreshToken);
    }

    public void revokeAllByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
}