package com.marvisa.logistic.envio.controller;


import com.marvisa.logistic.common.response.ApiResponse;
import com.marvisa.logistic.envio.dto.EnvioRequest;
import com.marvisa.logistic.envio.entity.Envio;
import com.marvisa.logistic.envio.service.EnvioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService envioService;

    @GetMapping
    public ApiResponse<List<Envio>> listar() {
        return new ApiResponse<>(true, "Lista de envíos", envioService.listar());
    }

    @GetMapping("/{id}")
    public ApiResponse<Envio> obtener(@PathVariable Long id) {
        return new ApiResponse<>(true, "Envío encontrado", envioService.obtener(id));
    }

    @PostMapping
    public ApiResponse<Envio> crear(@Valid @RequestBody EnvioRequest request) {
        return new ApiResponse<>(true, "Envío creado", envioService.crear(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<Envio> actualizar(@PathVariable Long id, @Valid @RequestBody EnvioRequest request) {
        return new ApiResponse<>(true, "Envío actualizado", envioService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable Long id) {
        envioService.eliminar(id);
        return new ApiResponse<>(true, "Envío eliminado", null);
    }
}