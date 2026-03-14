package com.marvisa.logistic.liquidacion.service;

import com.marvisa.logistic.common.exception.ResourceNotFoundException;
import com.marvisa.logistic.liquidacion.dto.LiquidacionRequest;
import com.marvisa.logistic.liquidacion.entity.Liquidacion;
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

    public List<Liquidacion> listar() {
        return liquidacionRepository.findAll();
    }

    public Liquidacion obtener(Long id) {
        return liquidacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Liquidación no encontrada con id: " + id));
    }

    public Liquidacion crear(LiquidacionRequest request) {
        Vendedor vendedor = vendedorRepository.findById(request.getVendedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor no encontrado con id: " + request.getVendedorId()));

        Liquidacion liquidacion = Liquidacion.builder()
                .fechaLiquidacion(request.getFechaLiquidacion())
                .montoTotal(request.getMontoTotal())
                .estado(request.getEstado())
                //.vendedorId(request.getVendedorId())
                .vendedor(vendedor)
                .build();

        return liquidacionRepository.save(liquidacion);
    }

    public Liquidacion actualizar(Long id, LiquidacionRequest request) {
        Liquidacion liquidacion = obtener(id);

        Vendedor vendedor = vendedorRepository.findById(request.getVendedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor no encontrado con id: " + request.getVendedorId()));

        liquidacion.setFechaLiquidacion(request.getFechaLiquidacion());
        liquidacion.setMontoTotal(request.getMontoTotal());
        liquidacion.setEstado(request.getEstado());
        //liquidacion.setVendedorId(request.getVendedorId());
        liquidacion.setVendedor(vendedor);

        return liquidacionRepository.save(liquidacion);
    }

    public void eliminar(Long id) {
        //Liquidacion liquidacion = obtener(id);
        liquidacionRepository.delete(obtener(id));
    }
}