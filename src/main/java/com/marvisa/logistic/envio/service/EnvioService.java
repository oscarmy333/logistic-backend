package com.marvisa.logistic.envio.service;

import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.envio.dto.EnvioRequest;
import com.marvisa.logistic.envio.dto.EnvioResponse;
import com.marvisa.logistic.envio.entity.Envio;
import com.marvisa.logistic.envio.mapper.EnvioMapper;
import com.marvisa.logistic.envio.repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvioService {

    private final EnvioRepository envioRepository;
    private final EnvioMapper envioMapper;

    public List<EnvioResponse> listar() {
        return envioMapper.toResponseList(envioRepository.findAll());
    }

    public EnvioResponse obtener(Long id) {
         Envio entity = envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envío no encontrado con id: " + id));
        return envioMapper.toResponse(entity);
    }

    public EnvioResponse crear(EnvioRequest request) {
        Envio envio = Envio.builder()
                .codigo(request.getCodigo())
                .direccionOrigen(request.getDireccionOrigen())
                .direccionDestino(request.getDireccionDestino())
                .estado(request.getEstado())
                .fechaEnvio(request.getFechaEnvio())
                .build();

        return envioMapper.toResponse(envioRepository.save(envio));
    }

    public EnvioResponse actualizar(Long id, EnvioRequest request) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envío no encontrada con id: " + id));
        envio.setCodigo(request.getCodigo());
        envio.setDireccionOrigen(request.getDireccionOrigen());
        envio.setDireccionDestino(request.getDireccionDestino());
        envio.setEstado(request.getEstado());
        envio.setFechaEnvio(request.getFechaEnvio());

        return envioMapper.toResponse(envioRepository.save(envio));
    }

    public void eliminar(Long id) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envío no encontrada con id: " + id));
        envioRepository.delete(envio);
    }
}