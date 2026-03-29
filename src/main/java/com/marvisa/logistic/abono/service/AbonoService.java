package com.marvisa.logistic.abono.service;

import com.marvisa.logistic.abono.calculator.DocumentoEstadoCalculator;
import com.marvisa.logistic.abono.entity.Abono;
import com.marvisa.logistic.abono.repository.AbonoRepository;
import com.marvisa.logistic.cobranza.entity.DocumentoCobranza;
import com.marvisa.logistic.cobranza.repository.DocumentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AbonoService {

    private final AbonoRepository abonoRepository;
    private final DocumentoRepository documentoRepository;
    private final DocumentoEstadoCalculator estadoCalculator;

    @Transactional
    public void registrarAbono(Long documentoId, BigDecimal monto) {
        DocumentoCobranza doc = documentoRepository.findById(documentoId)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El abono debe ser mayor a cero");
        }

        if (doc.getSaldoPendiente().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("El documento ya está pagado");
        }

        if (monto.compareTo(doc.getSaldoPendiente()) > 0) {
            throw new IllegalArgumentException("El abono no puede exceder el saldo pendiente");
        }

        Abono abono = new Abono();
        abono.setDocumento(doc);
        abono.setMonto(monto);
        abono.setFechaAbono(LocalDateTime.now());
        abonoRepository.save(abono);

        doc.setSaldoPendiente(doc.getSaldoPendiente().subtract(monto));
        doc.setEstado(estadoCalculator.calcular(doc.getSaldoPendiente(), doc.getFechaVencimiento()));
        documentoRepository.save(doc);
    }
}