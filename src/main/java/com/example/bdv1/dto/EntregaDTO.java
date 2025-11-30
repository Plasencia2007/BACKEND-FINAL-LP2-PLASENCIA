package com.example.bdv1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntregaDTO {
    private Long id;

    private String tipoRuta;

    @NotNull(message = "La fecha de programación es obligatoria")
    private LocalDate fechaProgramacion;

    private LocalDate fechaFin;
    private BigDecimal montoBase;
    private BigDecimal montoGastos;
    private BigDecimal montoTotal;

    private Long idVehiculo;
    private Long idChofer;
    private Long idCliente;
    private Long idEstado;
}