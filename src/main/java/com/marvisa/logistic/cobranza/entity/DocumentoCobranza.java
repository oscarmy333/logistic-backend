package com.marvisa.logistic.cobranza.entity;

import com.marvisa.logistic.cliente.entity.Cliente;
import com.marvisa.logistic.common.audit.AuditableEntity;
import com.marvisa.logistic.common.enums.EstadoDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "documentos_cobranza")
@Getter
@Setter
@SQLDelete(sql = "UPDATE documentos_cobranza SET deleted = true, updated_at = now() WHERE id = ?")
@SQLRestriction("deleted = false")
public class DocumentoCobranza extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(nullable = false)
    private String glosa;

    @Column(name = "monto_original", nullable = false, precision = 14, scale = 2)
    private BigDecimal montoOriginal;

    @Column(name = "saldo_pendiente", nullable = false, precision = 14, scale = 2)
    private BigDecimal saldoPendiente;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoDocumento estado;

    @Column(length = 255)
    private String observacion;

    @Column(nullable = false)
    private Boolean activo = true;
}