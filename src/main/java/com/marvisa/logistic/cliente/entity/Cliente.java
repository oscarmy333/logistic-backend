package com.marvisa.logistic.cliente.entity;

import com.marvisa.logistic.common.audit.AuditableEntity;
import com.marvisa.logistic.reparto.entity.Reparto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    @Column(length = 11)
    private String codigo;

    @Column(nullable = false, length = 75)
    private String nombres;

    @Column(nullable = false, length = 75)
    private String apellidos;

    @Column(length = 255)
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @Column(length = 150)
    private String nombreComercial;

    @Column(length = 20)
    private String dni;

    @Column(length = 11)
    private String ruc;

    @Column(length = 120)
    private String email;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;

    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private List<Reparto> repartos = new ArrayList<>();
}