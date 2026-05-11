package com.marvisa.logistic.abono.calculator;

import com.marvisa.logistic.documento.enums.EstadoDocumento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DocumentoEstadoCalculator {

    public EstadoDocumento calcular(BigDecimal saldoPendiente, LocalDate fechaVencimiento) {
        if (saldoPendiente.compareTo(BigDecimal.ZERO) <= 0) {
            return EstadoDocumento.PAGADO;
        }
        return fechaVencimiento.isBefore(LocalDate.now())
                ? EstadoDocumento.VENCIDO
                : EstadoDocumento.NO_VENCIDO;
    }
}