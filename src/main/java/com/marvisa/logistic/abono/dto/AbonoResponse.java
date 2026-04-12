package com.marvisa.logistic.abono.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbonoResponse {
    private Long id;
    private Long documentoId;
    private BigDecimal monto;
    private LocalDateTime fechaAbono;
}