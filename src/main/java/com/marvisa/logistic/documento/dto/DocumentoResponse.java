package com.marvisa.logistic.documento.dto;

import com.marvisa.logistic.common.enums.EstadoDocumento;
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
    private String clienteNombre;
    private String glosa;
    private BigDecimal montoOriginal;
    private BigDecimal saldoPendiente;
    private LocalDate fechaVencimiento;
    private EstadoDocumento estado;
}