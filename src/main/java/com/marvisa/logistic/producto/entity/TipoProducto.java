package com.marvisa.logistic.producto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(schema = "public", name = "tipo_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    private String descripcion;

    @Column(nullable = false)
    private Boolean activo;

    //@OneToMany
    //private Set<Producto> allProductos;
}