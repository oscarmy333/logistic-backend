package com.marvisa.logistic.producto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(schema = "public", name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    private String nombre;

    private String descripcion;

    private String presentacion;

    //private TipoProducto tipoProducto;

    @Column(nullable = false)
    private Boolean activo;
}