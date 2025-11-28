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
public class VehiculoDTO {
    private Long id;

    @NotBlank(message = "La placa no puede estar vacía")
    @Size(max = 10, message = "La placa no puede superar los 10 caracteres")
    private String placa;

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(max = 50, message = "La marca no puede superar los 50 caracteres")
    private String marca;

    @NotBlank(message = "El modelo no puede estar vacío")
    @Size(max = 50, message = "El modelo no puede superar los 50 caracteres")
    private String modelo;

    private BigDecimal capacidadM3;
}