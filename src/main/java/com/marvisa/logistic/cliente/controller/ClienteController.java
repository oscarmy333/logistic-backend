package com.marvisa.logistic.cliente.controller;

import com.marvisa.logistic.cliente.dto.ClienteRequest;
import com.marvisa.logistic.cliente.entity.Cliente;
import com.marvisa.logistic.cliente.service.ClienteService;
import com.marvisa.logistic.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ApiResponse<List<Cliente>> listar() {
        return new ApiResponse<>(true, "Lista de clientes", clienteService.listar());
    }

    @GetMapping("/{id}")
    public ApiResponse<Cliente> obtener(@PathVariable Long id) {
        return new ApiResponse<>(true, "Cliente encontrado", clienteService.obtener(id));
    }

    @PostMapping
    public ApiResponse<Cliente> crear(@Valid @RequestBody ClienteRequest request) {
        return new ApiResponse<>(true, "Cliente creado", clienteService.crear(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<Cliente> actualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
        return new ApiResponse<>(true, "Cliente actualizado", clienteService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return new ApiResponse<>(true, "Cliente eliminado", null);
    }
}