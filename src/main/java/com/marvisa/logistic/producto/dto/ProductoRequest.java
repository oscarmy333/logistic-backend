package com.marvisa.logistic.producto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ProductoRequest {

    @NotBlank(message = "El codigo es obligatorio")
    private String codigo;

    private String nombre;

    private String descripcion;

    private TipoProductoRequest tipoProductoRequest;

    //@NotNull(message = "El precio es obligatorio")
    //@DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    //private BigDecimal precio;

    //@NotNull(message = "El stock es obligatorio")
    //@Min(value = 0, message = "El stock no puede ser negativo")
    //private Integer stock;

    private Boolean activo;
}