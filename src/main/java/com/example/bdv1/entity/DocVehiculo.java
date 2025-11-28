package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DOC_Vehiculo")
public class DocVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doc_vehiculo")
    private Long idDocVehiculo;

    @Column(name = "codigo", nullable = true, length = 50)
    private String codigo;

    @Column(name = "fecha_emision", nullable = true)
    private LocalDate fechaEmision;

    @Column(name = "fecha_vencimiento", nullable = true)
    private LocalDate fechaVencimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_doc_vehiculo", nullable = false)
    private TipoDocVehiculo tipoDocVehiculo;
}