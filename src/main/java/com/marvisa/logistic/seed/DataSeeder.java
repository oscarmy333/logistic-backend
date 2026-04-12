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
        Role adminRole = roleRepository.findByRol(RoleName.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(Role.builder().rol(RoleName.ROLE_ADMIN).build()));

        Role cobranzaRole = roleRepository.findByRol(RoleName.ROLE_COBRANZA)
                .orElseGet(() -> roleRepository.save(Role.builder().rol(RoleName.ROLE_COBRANZA).build()));

        Role lectorRole = roleRepository.findByRol(RoleName.ROLE_LECTOR)
                .orElseGet(() -> roleRepository.save(Role.builder().rol(RoleName.ROLE_LECTOR).build()));


    }
}