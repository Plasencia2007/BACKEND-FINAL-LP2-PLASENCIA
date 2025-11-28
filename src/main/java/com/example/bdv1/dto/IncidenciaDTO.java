package com.example.bdv1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncidenciaDTO {
    private Long id;

    @NotBlank(message = "El tipo de incidencia no puede estar vacío")
    @Size(max = 100, message = "El tipo no puede superar los 100 caracteres")
    private String tipoIncidencia;

    @NotBlank(message = "La prioridad no puede estar vacía")
    @Size(max = 50, message = "La prioridad no puede superar los 50 caracteres")
    private String prioridad;

    private String detalles;
    private String evidencia;
}