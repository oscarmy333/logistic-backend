package com.marvisa.logistic.security.repository;


import com.marvisa.logistic.security.entity.RefreshToken;
import com.marvisa.logistic.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenAndDeletedFalse(String token);
    void deleteByUser(User user);
}