package com.marvisa.logistic.dashboard;

import com.marvisa.logistic.cliente.repository.ClienteRepository;
import com.marvisa.logistic.dashboard.dto.DashboardResumenResponse;
import com.marvisa.logistic.documento.enums.EstadoDocumento;
import com.marvisa.logistic.documento.repository.DocumentoCobranzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ClienteRepository clienteRepository;
    private final DocumentoCobranzaRepository documentoRepository;

    public DashboardResumenResponse resumen() {
        var documentos = documentoRepository.findAll();

        BigDecimal totalPorCobrar = documentos.stream()
                .map(d -> d.getSaldoPendiente())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalVencido = documentos.stream()
                .filter(d -> d.getEstado() == EstadoDocumento.VENCIDO)
                .map(d -> d.getSaldoPendiente())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalNoVencido = documentos.stream()
                .filter(d -> d.getEstado() == EstadoDocumento.NO_VENCIDO)
                .map(d -> d.getSaldoPendiente())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPagado = documentos.stream()
                .filter(d -> d.getEstado() == EstadoDocumento.PAGADO)
                .map(d -> d.getMontoOriginal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return DashboardResumenResponse.builder()
                .totalClientes(clienteRepository.count())
                .totalDocumentos(documentoRepository.count())
                .totalPorCobrar(totalPorCobrar)
                .totalVencido(totalVencido)
                .totalNoVencido(totalNoVencido)
                .totalPagado(totalPagado)
                .build();
    }
}