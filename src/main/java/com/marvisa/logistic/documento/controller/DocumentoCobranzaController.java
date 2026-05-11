package com.marvisa.logistic.documento.controller;


import com.marvisa.logistic.documento.dto.DocumentoRequest;
import com.marvisa.logistic.documento.dto.DocumentoResponse;
import com.marvisa.logistic.documento.enums.EstadoDocumento;
import com.marvisa.logistic.documento.service.DocumentoCobranzaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@RequiredArgsConstructor
public class DocumentoCobranzaController {

    private final DocumentoCobranzaService documentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentoResponse crear(@Valid @RequestBody DocumentoRequest request) {
        return documentoService.crear(request);
    }

    @GetMapping
    public List<DocumentoResponse> listar(
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) EstadoDocumento estado
    ) {
        return documentoService.listar(clienteId, estado);
    }

    @GetMapping("/{id}")
    public DocumentoResponse obtener(@PathVariable Long id) {
        return documentoService.obtener(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<DocumentoResponse> listarPorCliente(@PathVariable Long clienteId) {
        return documentoService.listarPorCliente(clienteId);
    }

    @PutMapping("/{id}")
    public DocumentoResponse actualizar(@PathVariable Long id, @Valid @RequestBody DocumentoRequest request) {
        return documentoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        documentoService.eliminar(id);
    }
}
