package com.marvisa.logistic.reparto.entity;

import com.marvisa.logistic.cliente.entity.Cliente;
import com.marvisa.logistic.envio.entity.Envio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "reparto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reparto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaReparto;

    @Column(nullable = false)
    private String estado;

    private String observacion;
    /*
        @Column(nullable = false)
        private Long clienteId;

        @Column(nullable = false)
        private Long envioId;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "envio_id", nullable = false)
    private Envio envio;
}