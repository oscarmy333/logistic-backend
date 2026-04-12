package com.marvisa.logistic.abono.controller;

import com.marvisa.logistic.abono.dto.AbonoRequest;
import com.marvisa.logistic.abono.dto.AbonoResponse;
import com.marvisa.logistic.abono.service.AbonoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abonos")
@RequiredArgsConstructor
public class AbonoController {

    private final AbonoService service;

    @PostMapping
    public AbonoResponse registrar(@Valid @RequestBody AbonoRequest request) {
        return service.registrar(request);
    }

    @GetMapping("/documento/{documentoId}")
    public List<AbonoResponse> listarPorDocumento(@PathVariable Long documentoId) {
        return service.listarPorDocumento(documentoId);
    }
}