package com.marvisa.logistic.liquidacion.service;

import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.liquidacion.dto.LiquidacionRequest;
import com.marvisa.logistic.liquidacion.dto.LiquidacionResponse;
import com.marvisa.logistic.liquidacion.entity.Liquidacion;
import com.marvisa.logistic.liquidacion.mapper.LiquidacionMapper;
import com.marvisa.logistic.liquidacion.repository.LiquidacionRepository;
import com.marvisa.logistic.vendedor.entity.Vendedor;
import com.marvisa.logistic.vendedor.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LiquidacionService {

    private final LiquidacionRepository liquidacionRepository;
    private final VendedorRepository vendedorRepository;
    private final LiquidacionMapper liquidacionMapper;

    public List<LiquidacionResponse> listar() {
        return liquidacionMapper.toResponseList(liquidacionRepository.findAll());
    }

    public LiquidacionResponse obtener(Long id) {
        Liquidacion entity = liquidacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Liquidación no encontrada con id: " + id));
        return liquidacionMapper.toResponse(entity);
    }

    public LiquidacionResponse crear(LiquidacionRequest request) {
        Vendedor vendedor = vendedorRepository.findById(request.getVendedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor no encontrado con id: " + request.getVendedorId()));

        Liquidacion liquidacion = Liquidacion.builder()
                .fechaLiquidacion(request.getFechaLiquidacion())
                .montoTotal(request.getMontoTotal())
                .estado(request.getEstado())
                .vendedor(vendedor)
                .build();

        return liquidacionMapper.toResponse(liquidacionRepository.save(liquidacion));
    }

    public LiquidacionResponse actualizar(Long id, LiquidacionRequest request) {
        Liquidacion liquidacion = liquidacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Liquidación no encontrada - sin actualizar: " + id));

        Vendedor vendedor = vendedorRepository.findById(request.getVendedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor no encontrado con id: " + request.getVendedorId()));

        liquidacion.setFechaLiquidacion(request.getFechaLiquidacion());
        liquidacion.setMontoTotal(request.getMontoTotal());
        liquidacion.setEstado(request.getEstado());
        liquidacion.setVendedor(vendedor);

        return liquidacionMapper.toResponse(liquidacionRepository.save(liquidacion));
    }

    public void eliminar(Long id) {
        Liquidacion liquidacion = liquidacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Liquidación no encontrada - sin eliminar: " + id));
        liquidacionRepository.delete(liquidacion);
    }
}