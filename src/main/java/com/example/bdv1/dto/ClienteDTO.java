package com.example.bdv1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO {
    private Long id;

    @NotBlank(message = "La razón social no puede estar vacía")
    @Size(max = 100, message = "La razón social no puede superar los 100 caracteres")
    private String razonSocial;

    @NotBlank(message = "El RUC no puede estar vacío")
    @Size(min = 11, max = 11, message = "El RUC debe tener 11 caracteres")
    private String ruc;

}