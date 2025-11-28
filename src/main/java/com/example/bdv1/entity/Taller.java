package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Taller")
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_taller")
    private Long idTaller;

    @Column(name = "nombre_taller", nullable = true, length = 100)
    private String nombreTaller;

    @Column(name = "direccion", nullable = true, length = 200)
    private String direccion;

    @OneToMany(mappedBy = "taller", fetch = FetchType.LAZY)
    private List<Mantenimiento> mantenimientos;
}