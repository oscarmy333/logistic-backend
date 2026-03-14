package com.marvisa.logistic.security.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "public", name = "perfil_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName rol;
}