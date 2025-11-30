package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Gastos_Adicionales")
public class GastoAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gastos_adicionales")
    private Long idGastosAdicionales;

    @Column(name = "tipo_gastos_adicionales", nullable = true, length = 100)
    private String tipoGastosAdicionales;

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "detalles", nullable = true, length = 100)
    private String detalles;

    @Column(name = "evidencia", nullable = true, length = 200)
    private String evidencia;

    @OneToMany(mappedBy = "gastoAdicional", fetch = FetchType.LAZY)
    private List<DetalleGastoAdicional> detalleGastos;
}