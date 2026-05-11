package com.marvisa.logistic.documento.service;

import com.marvisa.logistic.abono.calculator.DocumentoEstadoCalculator;
import com.marvisa.logistic.abono.repository.AbonoRepository;
import com.marvisa.logistic.cliente.entity.Cliente;
import com.marvisa.logistic.cliente.repository.ClienteRepository;
import com.marvisa.logistic.documento.enums.EstadoDocumento;
import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.documento.dto.DocumentoRequest;
import com.marvisa.logistic.documento.dto.DocumentoResponse;
import com.marvisa.logistic.documento.entity.DocumentoCobranza;
import com.marvisa.logistic.documento.mapper.DocumentoMapper;
import com.marvisa.logistic.documento.repository.DocumentoCobranzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoCobranzaService {

    private final DocumentoCobranzaRepository repository;
    private final ClienteRepository clienteRepository;
    private final AbonoRepository abonoRepository;
    private final DocumentoMapper documentoMapper;
    private final DocumentoEstadoCalculator estadoCalculator;

    public DocumentoResponse crear(DocumentoRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + request.getClienteId()));

        DocumentoCobranza documento = DocumentoCobranza.builder()
                .cliente(cliente)
                .codigo(request.getCodigo())
                .glosa(request.getGlosa())
                .montoOriginal(request.getMontoOriginal())
                .fechaEmision(request.getFechaEmision())
                .fechaVencimiento(request.getFechaVencimiento())
                .observacion(request.getObservacion())
                .saldoPendiente(request.getMontoOriginal())
                .estado(EstadoDocumento.NO_VENCIDO)
                .activo(request.getActivo() == null || request.getActivo())
                .build();

        recalcularEstadoYSaldo(documento);
        return documentoMapper.toResponse(repository.save(documento));
    }

    public List<DocumentoResponse> listar(Long clienteId, EstadoDocumento estado) {
        List<DocumentoCobranza> documentos;

        if (clienteId != null) {
            documentos = repository.findByClienteId(clienteId);
        } else if (estado != null) {
            documentos = repository.findByEstado(estado);
        } else {
            documentos = repository.findAll();
        }

        documentos.forEach(this::recalcularEstadoYSaldo);
        return documentos.stream().map(this.documentoMapper::toResponse).toList();
    }

    public DocumentoResponse obtener(Long id) {
        DocumentoCobranza documento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento no encontrado: " + id));

        recalcularEstadoYSaldo(documento);

        return documentoMapper.toResponse(documento);
    }

    public List<DocumentoResponse> listarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId).stream().map(this.documentoMapper::toResponse).toList();
    }

    public DocumentoResponse actualizar(Long id, DocumentoRequest request) {
        DocumentoCobranza doc = findEntity(id);

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + request.getClienteId()));

        doc.setCliente(cliente);
        doc.setGlosa(request.getGlosa());
        doc.setMontoOriginal(request.getMontoOriginal());
        doc.setFechaEmision(request.getFechaEmision());
        doc.setObservacion(request.getObservacion());
        doc.setActivo(request.getActivo() == null || request.getActivo());

        doc.setFechaVencimiento(request.getFechaVencimiento());
        recalcularEstadoYSaldo(doc);

        return documentoMapper.toResponse(repository.save(doc));
    }

    public void eliminar(Long id) {
        repository.delete(findEntity(id));
    }

    public void recalcularEstadoYSaldo(DocumentoCobranza documento) {
        BigDecimal totalAbonos = documento.getId() == null
                ? BigDecimal.ZERO
                : abonoRepository.sumMontoByDocumentoId(documento.getId()).orElse(BigDecimal.ZERO);

        BigDecimal saldo = documento.getMontoOriginal().subtract(totalAbonos);

        if (saldo.compareTo(BigDecimal.ZERO) < 0) {
            saldo = BigDecimal.ZERO;
        }

        documento.setSaldoPendiente(saldo);

        documento.setEstado(estadoCalculator.calcular(documento.getSaldoPendiente(), documento.getFechaVencimiento()));
    }

    private DocumentoCobranza findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento no encontrado con id: " + id));
    }

}
