package com.example.bdv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetalleGastoAdicionalDTO {
    private Long id;

    private LocalDateTime fechaRegistro;
    private Long idGastoAdicional;
    private Long idEntrega;
}