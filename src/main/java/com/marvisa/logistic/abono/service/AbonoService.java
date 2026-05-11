package com.marvisa.logistic.abono.service;

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
    private final DocumentoCobranzaService documentoService;
    private final AbonoMapper abonoMapper;

    @Transactional
    public AbonoResponse registrar(Long documentoId, AbonoRequest request, String username) {
        DocumentoCobranza documento = documentoRepository.findById(documentoId)
                .orElseThrow(() -> new ResourceNotFoundException("Documento no encontrado: " + documentoId));

        if (request.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El abono debe ser mayor a cero");
        }

        if (documento.getSaldoPendiente().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("El documento ya está pagado");
        }

        documentoService.recalcularEstadoYSaldo(documento);

        if (request.getMonto().compareTo(documento.getSaldoPendiente()) > 0) {
            throw new BadRequestException("El abono no puede exceder el saldo pendiente");
        }

        Abono abono = Abono.builder()
                .documento(documento)
                .codigo(request.getCodigo())
                .monto(request.getMonto())
                .fechaAbono(request.getFechaAbono() == null ? LocalDateTime.now() : request.getFechaAbono())
                .medioPago(request.getMedioPago())
                .referencia(request.getReferencia())
                .observacion(request.getObservacion())
                .build();

        Abono guardado = abonoRepository.save(abono);

        documentoService.recalcularEstadoYSaldo(documento);
        documentoRepository.save(documento);

        return abonoMapper.toResponse(guardado);
    }

    public List<AbonoResponse> listarPorDocumento(Long documentoId) {
        return abonoRepository.findByDocumentoId(documentoId).stream().map(this.abonoMapper::toResponse).toList();
    }

}
