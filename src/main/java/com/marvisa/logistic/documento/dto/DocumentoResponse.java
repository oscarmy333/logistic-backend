package com.marvisa.logistic.documento.dto;

import com.marvisa.logistic.documento.enums.EstadoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoResponse {
    private Long id;
    private Long clienteId;
    private String codigo;
    private String clienteNombre;
    private String glosa;
    private BigDecimal montoOriginal;
    private BigDecimal saldoPendiente;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private EstadoDocumento estado;
    private String observacion;
    private boolean activo;
}