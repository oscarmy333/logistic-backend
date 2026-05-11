package com.marvisa.logistic.seed;

import com.marvisa.logistic.security.entity.Role;
import com.marvisa.logistic.security.entity.RoleName;
import com.marvisa.logistic.security.entity.User;
import com.marvisa.logistic.security.repository.RoleRepository;
import com.marvisa.logistic.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.existsByUsername("admin")) {
            return;
        }

        Role adminRol = roleRepository.findByRol(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new IllegalStateException("No existe el rol ROLE_ADMIN"));

        String inputString = System.getenv("SECRET");

        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode(inputString))
                .fullName("Administrador")
                .email("admin@marvisa.com")
                .enabled(true)
                .roles(Set.of(adminRol))
                .failedLoginAttempts(0)
                .build();

        userRepository.save(admin);

    }
}