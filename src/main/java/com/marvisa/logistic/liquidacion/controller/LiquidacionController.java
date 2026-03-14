package com.marvisa.logistic.liquidacion.controller;

import com.marvisa.logistic.common.response.ApiResponse;
import com.marvisa.logistic.liquidacion.dto.LiquidacionRequest;
import com.marvisa.logistic.liquidacion.entity.Liquidacion;
import com.marvisa.logistic.liquidacion.service.LiquidacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/liquidaciones")
@RequiredArgsConstructor
public class LiquidacionController {

    private final LiquidacionService liquidacionService;

    @GetMapping
    public ApiResponse<List<Liquidacion>> listar() {
        return new ApiResponse<>(true, "Lista de liquidaciones", liquidacionService.listar());
    }

    @GetMapping("/{id}")
    public ApiResponse<Liquidacion> obtener(@PathVariable Long id) {
        return new ApiResponse<>(true, "Liquidación encontrada", liquidacionService.obtener(id));
    }

    @PostMapping
    public ApiResponse<Liquidacion> crear(@Valid @RequestBody LiquidacionRequest request) {
        return new ApiResponse<>(true, "Liquidación creada", liquidacionService.crear(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<Liquidacion> actualizar(@PathVariable Long id, @Valid @RequestBody LiquidacionRequest request) {
        return new ApiResponse<>(true, "Liquidación actualizada", liquidacionService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Long id) {
        liquidacionService.eliminar(id);
        return new ApiResponse<>(true, "Liquidación eliminada", null);
    }
}