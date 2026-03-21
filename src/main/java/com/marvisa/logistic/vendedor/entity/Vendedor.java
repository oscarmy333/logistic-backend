package com.marvisa.logistic.vendedor.entity;


import com.marvisa.logistic.common.audit.AuditableEntity;
import com.marvisa.logistic.liquidacion.entity.Liquidacion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "vendedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE vendedor SET deleted = true, updated_at = now() WHERE id = ?")
@SQLRestriction("deleted = false")
public class Vendedor extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column
    private String dni;

    @Column(nullable = false)
    private String nombres;

    @Column
    private String licencia;

    @Column
    private String email;

    private String celular;

    @Column
    private String direccion;

    @Column
    private String usuario;

    @Column(nullable = false)
    private Boolean activo;

    @OneToMany(mappedBy = "vendedor")
    @Builder.Default
    private List<Liquidacion> liquidaciones = new ArrayList<>();
}