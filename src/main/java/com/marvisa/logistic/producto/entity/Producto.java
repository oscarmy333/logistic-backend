package com.marvisa.logistic.producto.entity;

import com.marvisa.logistic.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "public", name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    private String nombre;

    private String descripcion;

    private String presentacion;

    @Column(nullable = false)
    private Boolean activo;
}