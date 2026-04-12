package com.marvisa.logistic.abono.service;

import com.marvisa.logistic.abono.calculator.DocumentoEstadoCalculator;
import com.marvisa.logistic.abono.dto.AbonoRequest;
import com.marvisa.logistic.abono.dto.AbonoResponse;
import com.marvisa.logistic.abono.entity.Abono;
import com.marvisa.logistic.abono.mapper.AbonoMapper;
import com.marvisa.logistic.abono.repository.AbonoRepository;
import com.marvisa.logistic.common.exception.BadRequestException;
import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.documento.entity.DocumentoCobranza;
import com.marvisa.logistic.documento.repository.DocumentoCobranzaRepository;
import com.marvisa.logistic.documento.service.DocumentoCobranzaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbonoService {

    private final AbonoRepository abonoRepository;
    private final DocumentoCobranzaRepository documentoRepository;
    private final DocumentoEstadoCalculator estadoCalculator;
    private final DocumentoCobranzaService documentoService;
    private final AbonoMapper abonoMapper;

    @Transactional
    public AbonoResponse registrar(AbonoRequest request) {
        DocumentoCobranza documento = documentoRepository.findById(request.getDocumentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Documento no encontrado"));

        if (request.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El abono debe ser mayor a cero");
        }

        if (documento.getSaldoPendiente().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("El documento ya está pagado");
        }

        if (request.getMonto().compareTo(documento.getSaldoPendiente()) > 0) {
            throw new BadRequestException("El abono no puede exceder el saldo pendiente");
        }

        Abono abono = Abono.builder()
                .documento(documento)
                .monto(request.getMonto())
                .fechaAbono(LocalDateTime.now())
                .build();

        documento.setSaldoPendiente(documento.getSaldoPendiente().subtract(request.getMonto()));
        documento.setEstado(estadoCalculator.calcular(documento.getSaldoPendiente(), documento.getFechaVencimiento()));
        documentoService.recalcularEstadoYSaldo(documento);

        abonoRepository.save(abono);
        documentoRepository.save(documento);

        return abonoMapper.toResponse(abono);
    }

    public List<AbonoResponse> listarPorDocumento(Long documentoId) {
        return abonoRepository.findByDocumentoId(documentoId).stream().map(this.abonoMapper::toResponse).toList();
    }

}
