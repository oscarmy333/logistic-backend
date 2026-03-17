package com.marvisa.logistic.envio.entity;

import com.marvisa.logistic.common.audit.AuditableEntity;
import com.marvisa.logistic.reparto.entity.Reparto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "envio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Envio extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String direccionOrigen;

    @Column(nullable = false)
    private String direccionDestino;

    private String codigoVendedor;

    private String placa;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDate fechaEnvio;

    @OneToMany(mappedBy = "envio")
    @Builder.Default
    private List<Reparto> repartos = new ArrayList<>();
}