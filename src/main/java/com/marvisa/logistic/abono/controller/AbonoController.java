package com.marvisa.logistic.abono.controller;

import com.marvisa.logistic.abono.dto.AbonoRequest;
import com.marvisa.logistic.abono.dto.AbonoResponse;
import com.marvisa.logistic.abono.service.AbonoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abonos")
@RequiredArgsConstructor
public class AbonoController {

    private final AbonoService abonoService;

    @PostMapping("/documento/{documentoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AbonoResponse registrar(@PathVariable Long documentoId,
                                   @Valid @RequestBody AbonoRequest request,
                                   Authentication authentication) {
        return abonoService.registrar(documentoId, request, authentication.getName());
    }

    @GetMapping("/documento/{documentoId}")
    public List<AbonoResponse> listarPorDocumento(@PathVariable Long documentoId) {
        return abonoService.listarPorDocumento(documentoId);
    }
}