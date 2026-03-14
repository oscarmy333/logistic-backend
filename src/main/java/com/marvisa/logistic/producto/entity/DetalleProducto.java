package com.marvisa.logistic.producto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(schema = "public", name = "detalle_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    //private Producto producto;

    //private Semana semana;

    private Date fecha;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVentaMin;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVentaMax;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioDevolucionMin;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioDevolucionMax;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Boolean activo;
}