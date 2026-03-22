package com.marvisa.logistic.cliente.controller;

import com.marvisa.logistic.cliente.dto.ClienteRequest;
import com.marvisa.logistic.cliente.dto.ClienteResponse;
import com.marvisa.logistic.cliente.service.ClienteService;
import com.marvisa.logistic.common.pagination.PageResponse;
import com.marvisa.logistic.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ApiResponse<PageResponse<ClienteResponse>> listar(
            @RequestParam(required = false) String nombres,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean activo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return new ApiResponse<>(
                true,
                "Lista de clientes",
                //clienteService.listar(nombres, activo, page, size, sortBy, direction)
                clienteService.listar(nombres, email, activo, page, size, sortBy, direction)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ClienteResponse> obtener(@PathVariable Long id) {
        return new ApiResponse<>(true, "Cliente encontrado", clienteService.obtener(id));
    }

    @PostMapping
    public ApiResponse<ClienteResponse> crear(@Valid @RequestBody ClienteRequest request) {
        return new ApiResponse<>(true, "Cliente creado", clienteService.crear(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ClienteResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
        return new ApiResponse<>(true, "Cliente actualizado", clienteService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return new ApiResponse<>(true, "Cliente eliminado", null);
    }
}