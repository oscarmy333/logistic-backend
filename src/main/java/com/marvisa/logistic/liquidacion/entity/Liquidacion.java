package com.marvisa.logistic.liquidacion.entity;

import com.marvisa.logistic.vendedor.entity.Vendedor;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "liquidacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Liquidacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaLiquidacion;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal montoTotal;

    @Column(nullable = false)
    private String estado;

    //@Column(nullable = false)
    //private Long vendedorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;
}