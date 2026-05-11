package com.marvisa.logistic.cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequest {

    @Size(max = 30)
    private String codigo;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 75)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 75)
    private String apellidos;

    @Size(max = 255)
    private String direccion;

    @Size(max = 20)
    private String telefono;

    @Size(max = 150)
    private String nombreComercial;

    @Size(max = 20)
    private String dni;

    @Size(max = 11)
    private String ruc;

    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    @Size(max = 120)
    private String email;

    private Boolean activo;
}
