package com.marvisa.logistic.vendedor.controller;


import com.marvisa.logistic.common.response.ApiResponse;
import com.marvisa.logistic.vendedor.dto.VendedorRequest;
import com.marvisa.logistic.vendedor.entity.Vendedor;
import com.marvisa.logistic.vendedor.service.VendedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
@RequiredArgsConstructor
public class VendedorController {

    private final VendedorService vendedorService;

    @GetMapping
    public ApiResponse<List<Vendedor>> listar() {
        return new ApiResponse<>(true, "Lista de vendedores", vendedorService.listar());
    }

    @GetMapping("/{id}")
    public ApiResponse<Vendedor> obtener(@PathVariable Long id) {
        return new ApiResponse<>(true, "Vendedor encontrado", vendedorService.obtener(id));
    }

    @PostMapping
    public ApiResponse<Vendedor> crear(@Valid @RequestBody VendedorRequest request) {
        return new ApiResponse<>(true, "Vendedor creado", vendedorService.crear(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<Vendedor> actualizar(@PathVariable Long id, @Valid @RequestBody VendedorRequest request) {
        return new ApiResponse<>(true, "Vendedor actualizado", vendedorService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Long id) {
        vendedorService.eliminar(id);
        return new ApiResponse<>(true, "Vendedor eliminado", null);
    }
}
