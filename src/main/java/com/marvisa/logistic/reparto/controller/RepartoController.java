package com.marvisa.logistic.reparto.controller;

import com.marvisa.logistic.common.response.ApiResponse;
import com.marvisa.logistic.reparto.dto.RepartoRequest;
import com.marvisa.logistic.reparto.dto.RepartoResponse;
import com.marvisa.logistic.reparto.service.RepartoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repartos")
@RequiredArgsConstructor
public class RepartoController {

    private final RepartoService repartoService;

    @GetMapping
    public ApiResponse<List<RepartoResponse>> listar() {
        return new ApiResponse<>(true, "Lista de repartos", repartoService.listar());
    }

    @GetMapping("/{id}")
    public ApiResponse<RepartoResponse> obtener(@PathVariable Long id) {
        return new ApiResponse<>(true, "Reparto encontrado", repartoService.obtener(id));
    }

    @PostMapping
    public ApiResponse<RepartoResponse> crear(@Valid @RequestBody RepartoRequest request) {
        return new ApiResponse<>(true, "Reparto creado", repartoService.crear(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<RepartoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody RepartoRequest request) {
        return new ApiResponse<>(true, "Reparto actualizado", repartoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Long id) {
        repartoService.eliminar(id);
        return new ApiResponse<>(true, "Reparto eliminado", null);
    }
}