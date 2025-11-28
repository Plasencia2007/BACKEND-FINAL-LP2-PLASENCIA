package com.example.bdv1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocVehiculoDTO {
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    @Size(max = 50, message = "El código no puede superar los 50 caracteres")
    private String codigo;

    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;

    private Long idVehiculo;
    private Long idTipoDocVehiculo;
}