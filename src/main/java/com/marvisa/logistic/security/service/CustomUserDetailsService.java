package com.marvisa.logistic.security.service;

import com.marvisa.logistic.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        /*var usuario = usuarioRepository.findByUsernameAndEnabledTrue(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        var authorities = usuario.getRoles().stream()
            .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getNombre()))
            .toList();

        return new User(usuario.getUsername(), usuario.getPassword(), authorities);*/
    }
}