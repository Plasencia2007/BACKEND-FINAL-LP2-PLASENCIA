package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Detalle_Gastos_Adicionales")
public class DetalleGastoAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_gastos_adicionales")
    private Long idDetalleGastosAdicionales;

    @Column(name = "fecha_registro", nullable = true)
    private LocalDateTime fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gastos_adicionales", nullable = false)
    private GastoAdicional gastoAdicional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrega", nullable = false)
    private Entrega entrega;
}