package com.example.bdv1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TiendaDTO {
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    @Size(max = 50, message = "El código no puede superar los 50 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 200, message = "La dirección no puede superar los 200 caracteres")
    private String direccion;

    @NotBlank(message = "El distrito no puede estar vacío")
    @Size(max = 100, message = "El distrito no puede superar los 100 caracteres")
    private String distrito;
}