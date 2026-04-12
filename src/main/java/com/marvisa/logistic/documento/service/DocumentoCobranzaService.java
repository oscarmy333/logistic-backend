package com.marvisa.logistic.documento.service;

import com.marvisa.logistic.cliente.entity.Cliente;
import com.marvisa.logistic.cliente.repository.ClienteRepository;
import com.marvisa.logistic.common.enums.EstadoDocumento;
import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.documento.dto.DocumentoRequest;
import com.marvisa.logistic.documento.dto.DocumentoResponse;
import com.marvisa.logistic.documento.entity.DocumentoCobranza;
import com.marvisa.logistic.documento.mapper.DocumentoMapper;
import com.marvisa.logistic.documento.repository.DocumentoCobranzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoCobranzaService {

    private final DocumentoCobranzaRepository repository;
    private final ClienteRepository clienteRepository;
    private final DocumentoMapper documentoMapper;

    public DocumentoResponse crear(DocumentoRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        DocumentoCobranza doc = DocumentoCobranza.builder()
                .cliente(cliente)
                .glosa(request.getGlosa())
                .montoOriginal(request.getMontoOriginal())
                .saldoPendiente(request.getMontoOriginal())
                .fechaVencimiento(request.getFechaVencimiento())
                .estado(EstadoDocumento.NO_VENCIDO)
                .build();

        recalcularEstadoYSaldo(doc);
        return documentoMapper.toResponse(repository.save(doc));
    }

    public List<DocumentoResponse> listar() {
        return repository.findAll().stream().map(this.documentoMapper::toResponse).toList();
    }

    public DocumentoResponse obtener(Long id) {
        return documentoMapper.toResponse(findEntity(id));
    }

    public List<DocumentoResponse> listarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId).stream().map(this.documentoMapper::toResponse).toList();
    }

    public DocumentoResponse actualizar(Long id, DocumentoRequest request) {
        DocumentoCobranza doc = findEntity(id);

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        doc.setCliente(cliente);
        doc.setGlosa(request.getGlosa());
        doc.setMontoOriginal(request.getMontoOriginal());

        if (doc.getSaldoPendiente().compareTo(request.getMontoOriginal()) > 0) {
            doc.setSaldoPendiente(request.getMontoOriginal());
        }

        doc.setFechaVencimiento(request.getFechaVencimiento());
        recalcularEstadoYSaldo(doc);

        return documentoMapper.toResponse(repository.save(doc));
    }

    public void eliminar(Long id) {
        repository.delete(findEntity(id));
    }

    public void recalcularEstadoYSaldo(DocumentoCobranza documento) {
        if (documento.getSaldoPendiente().compareTo(BigDecimal.ZERO) <= 0) {
            documento.setSaldoPendiente(BigDecimal.ZERO);
            documento.setEstado(EstadoDocumento.PAGADO);
            return;
        }

        if (documento.getFechaVencimiento().isBefore(LocalDate.now())) {
            documento.setEstado(EstadoDocumento.VENCIDO);
        } else {
            documento.setEstado(EstadoDocumento.NO_VENCIDO);
        }
    }

    private DocumentoCobranza findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento no encontrado con id: " + id));
    }

}
