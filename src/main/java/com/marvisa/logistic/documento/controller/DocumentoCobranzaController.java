package com.marvisa.logistic.documento.controller;


import com.marvisa.logistic.documento.dto.DocumentoRequest;
import com.marvisa.logistic.documento.dto.DocumentoResponse;
import com.marvisa.logistic.documento.service.DocumentoCobranzaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@RequiredArgsConstructor
public class DocumentoCobranzaController {

    private final DocumentoCobranzaService service;

    @PostMapping
    public DocumentoResponse crear(@Valid @RequestBody DocumentoRequest request) {
        return service.crear(request);
    }

    @GetMapping
    public List<DocumentoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public DocumentoResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<DocumentoResponse> listarPorCliente(@PathVariable Long clienteId) {
        return service.listarPorCliente(clienteId);
    }

    @PutMapping("/{id}")
    public DocumentoResponse actualizar(@PathVariable Long id, @Valid @RequestBody DocumentoRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
