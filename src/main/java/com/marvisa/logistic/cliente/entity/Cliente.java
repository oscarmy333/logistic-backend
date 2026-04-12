package com.marvisa.logistic.cliente.entity;

import com.marvisa.logistic.common.audit.AuditableEntity;
import com.marvisa.logistic.reparto.entity.Reparto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE cliente SET deleted = true, updated_at = now() WHERE id = ?")
@SQLRestriction("deleted = false")
public class Cliente extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    private String direccion;

    private String telefono;

    private String nombreComercial;

    private String dni;

    private String ruc;

    private String email;

    @Column(nullable = false)
    private Boolean activo = true;

    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private List<Reparto> repartos = new ArrayList<>();
}