package com.example.bdv1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GastoAdicionalDTO {
    private Long id;

    @NotBlank(message = "El tipo de gasto no puede estar vacío")
    @Size(max = 100, message = "El tipo no puede superar los 100 caracteres")
    private String tipoGastosAdicionales;

    private BigDecimal monto;
    private String detalles;
    private String evidencia;
}