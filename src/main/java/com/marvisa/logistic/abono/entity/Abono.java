package com.marvisa.logistic.abono.entity;

import com.marvisa.logistic.documento.entity.DocumentoCobranza;
import com.marvisa.logistic.common.audit.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(schema = "public", name = "abono")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE abono SET deleted = true, updated_at = now() WHERE id = ?")
@SQLRestriction("deleted = false")
public class Abono extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 11)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "documento_cobranza_id", nullable = false)
    private DocumentoCobranza documento;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal monto;

    @Column(name = "fecha_abono", nullable = false)
    private LocalDateTime fechaAbono;

    @Column(length = 50)
    private String medioPago;

    @Column(length = 100)
    private String referencia;

    @Column(length = 255)
    private String observacion;
}