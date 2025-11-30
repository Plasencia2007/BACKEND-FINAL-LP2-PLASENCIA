package com.example.bdv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetalleIncidenciaDTO {
    private Long id;

    private LocalDateTime fechaRegistro;
    private Long idIncidencia;
    private Long idEntrega;
}