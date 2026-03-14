package com.marvisa.logistic.reparto.service;

import com.marvisa.logistic.cliente.entity.Cliente;
import com.marvisa.logistic.cliente.repository.ClienteRepository;
import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.envio.entity.Envio;
import com.marvisa.logistic.envio.repository.EnvioRepository;
import com.marvisa.logistic.reparto.dto.RepartoRequest;
import com.marvisa.logistic.reparto.entity.Reparto;
import com.marvisa.logistic.reparto.repository.RepartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepartoService {

    private final RepartoRepository repartoRepository;
    private final ClienteRepository clienteRepository;
    private final EnvioRepository envioRepository;

    public List<Reparto> listar() {
        return repartoRepository.findAll();
    }

    public Reparto obtener(Long id) {
        return repartoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reparto no encontrado con id: " + id));
    }

    public Reparto crear(RepartoRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + request.getClienteId()));

        Envio envio = envioRepository.findById(request.getEnvioId())
                .orElseThrow(() -> new ResourceNotFoundException("Envío no encontrado con id: " + request.getEnvioId()));

        Reparto reparto = Reparto.builder()
                .fechaReparto(request.getFechaReparto())
                .estado(request.getEstado())
                .observacion(request.getObservacion())
                //.clienteId(request.getClienteId())
                .cliente(cliente)
                .envio(envio)
                .build();

        return repartoRepository.save(reparto);
    }

    public Reparto actualizar(Long id, RepartoRequest request) {
        Reparto reparto = obtener(id);

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + request.getClienteId()));

        Envio envio = envioRepository.findById(request.getEnvioId())
                .orElseThrow(() -> new ResourceNotFoundException("Envío no encontrado con id: " + request.getEnvioId()));

        reparto.setFechaReparto(request.getFechaReparto());
        reparto.setEstado(request.getEstado());
        reparto.setObservacion(request.getObservacion());
        //reparto.setClienteId(request.getClienteId());
        //reparto.setEnvioId(request.getEnvioId());
        reparto.setCliente(cliente);
        reparto.setEnvio(envio);

        return repartoRepository.save(reparto);
    }

    public void eliminar(Long id) {
        //Reparto reparto = obtener(id);
        repartoRepository.delete(obtener(id));
    }
}