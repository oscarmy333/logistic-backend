package com.marvisa.logistic.envio.service;

import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.envio.dto.EnvioRequest;
import com.marvisa.logistic.envio.entity.Envio;
import com.marvisa.logistic.envio.repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvioService {

    private final EnvioRepository envioRepository;

    public List<Envio> listar() {
        return envioRepository.findAll();
    }

    public Envio obtener(Long id) {
        return envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envío no encontrado con id: " + id));
    }

    public Envio crear(EnvioRequest request) {
        Envio envio = Envio.builder()
                .codigo(request.getCodigo())
                .direccionOrigen(request.getDireccionOrigen())
                .direccionDestino(request.getDireccionDestino())
                .estado(request.getEstado())
                .fechaEnvio(request.getFechaEnvio())
                .build();

        return envioRepository.save(envio);
    }

    public Envio actualizar(Long id, EnvioRequest request) {
        Envio envio = obtener(id);
        envio.setCodigo(request.getCodigo());
        envio.setDireccionOrigen(request.getDireccionOrigen());
        envio.setDireccionDestino(request.getDireccionDestino());
        envio.setEstado(request.getEstado());
        envio.setFechaEnvio(request.getFechaEnvio());

        return envioRepository.save(envio);
    }

    public void eliminar(Long id) {
        Envio envio = obtener(id);
        envioRepository.delete(envio);
    }
}