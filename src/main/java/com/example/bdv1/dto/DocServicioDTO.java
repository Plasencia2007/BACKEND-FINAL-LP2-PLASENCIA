package com.example.bdv1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocServicioDTO {
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    @Size(max = 50, message = "El código no puede superar los 50 caracteres")
    private String codigo;

    private Long idDestino; // FK a Destino
    private Long idTipoDoc;
}