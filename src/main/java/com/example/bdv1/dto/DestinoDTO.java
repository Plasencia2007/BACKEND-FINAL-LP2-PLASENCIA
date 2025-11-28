package com.example.bdv1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DestinoDTO {
    private Long id;

    private BigDecimal volumenM3;
    private LocalDateTime vhInicio;
    private LocalDateTime vhFinal;
    private String almacen;
    private String psex;
    private String canal;
    private String observaciones;
    private LocalDateTime horaEntrega;

    private Long idEntrega;
    private Long idTienda;
    private Long idEstado;
}