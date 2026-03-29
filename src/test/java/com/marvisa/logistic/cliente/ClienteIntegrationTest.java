package com.marvisa.logistic.cliente;

import com.marvisa.logistic.config.TestcontainersConfig;
import com.marvisa.logistic.security.entity.Role;
import com.marvisa.logistic.security.entity.RoleName;
import com.marvisa.logistic.security.entity.User;
import com.marvisa.logistic.security.jwt.JwtService;
import com.marvisa.logistic.security.repository.RoleRepository;
import com.marvisa.logistic.security.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteIntegrationTest extends TestcontainersConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    private String adminToken;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        Role adminRole = roleRepository.findByRol(RoleName.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(Role.builder().rol(RoleName.ROLE_ADMIN).build()));

        User admin = User.builder()
                .fullName("Admin Test")
                .username("admin_test")
                .email("admin_test@mail.com")
                .password(passwordEncoder.encode("Admin123*"))
                .enabled(true)
                .failedLoginAttempts(0)
                .roles(Set.of(adminRole))
                .build();

        userRepository.save(admin);

        adminToken = jwtService.generateAccessToken(
                admin,
                Map.of("roles", admin.getRoles().stream().map(r -> r.getRol().name()).toList())
        );
    }

    @Test
    void shouldCreateClienteWhenAdmin() throws Exception {
        String requestBody = """
                {
                  "nombres": "Oscar",
                  "apellidos": "Meneses",
                  "email": "oscar@mail.com",
                  "telefono": "999999999",
                  "direccion": "Ayacucho",
                  "activo": true
                }
                """;

        mockMvc.perform(post("/api/clientes")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.email").value("oscar@mail.com"));
    }

    @Test
    void shouldRejectClienteDeleteWhenNotAdmin() throws Exception {
        // aquí un token con ROLE_VENDEDOR
        // luego llamar DELETE /api/clientes/{id}
        // y esperar 403 Forbidden
    }
}