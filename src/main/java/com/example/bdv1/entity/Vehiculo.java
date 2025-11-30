package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Long idVehiculo;

    @Column(name = "placa", nullable = true, length = 10)
    private String placa;

    @Column(name = "marca", nullable = true, length = 50)
    private String marca;

    @Column(name = "modelo", nullable = true, length = 50)
    private String modelo;

    @Column(name = "capacidad_m3", precision = 8, scale = 2)
    private BigDecimal capacidadM3;

    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<Entrega> entregas;

    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<DocVehiculo> documentosVehiculo;

    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private List<Mantenimiento> mantenimientos;
}