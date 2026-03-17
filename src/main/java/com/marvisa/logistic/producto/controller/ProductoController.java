package com.marvisa.logistic.producto.controller;


import com.marvisa.logistic.common.response.ApiResponse;
import com.marvisa.logistic.producto.dto.ProductoRequest;
import com.marvisa.logistic.producto.dto.ProductoResponse;
import com.marvisa.logistic.producto.entity.Producto;
import com.marvisa.logistic.producto.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ApiResponse<List<ProductoResponse>> listar() {
        return new ApiResponse<>(true, "Lista de productos", productoService.listar());
    }

    @GetMapping("/{id}")
    public ApiResponse<Producto> obtener(@PathVariable Long id) {
        return new ApiResponse<>(true, "Producto encontrado", productoService.obtener(id));
    }

    @PostMapping
    public ApiResponse<Producto> crear(@Valid @RequestBody ProductoRequest request) {
        return new ApiResponse<>(true, "Producto creado", productoService.crear(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<Producto> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        return new ApiResponse<>(true, "Producto actualizado", productoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return new ApiResponse<>(true, "Producto eliminado", null);
    }
}