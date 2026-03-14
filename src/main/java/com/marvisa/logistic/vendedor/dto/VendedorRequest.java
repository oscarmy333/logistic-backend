package com.marvisa.logistic.vendedor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VendedorRequest {

    @NotBlank(message = "EL código es único")
    private String codigo;

    private String dni;

    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;

    private String licencia;

    private String email;

    private String celular;

    private String direccion;

    private String usuario;

    private Boolean activo;
}