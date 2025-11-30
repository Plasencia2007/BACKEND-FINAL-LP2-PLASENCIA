package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Incidencias")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidencias")
    private Long idIncidencias;

    @Column(name = "tipo_incidencia", nullable = true, length = 100)
    private String tipoIncidencia;

    @Column(name = "prioridad", nullable = true, length = 50)
    private String prioridad;

    @Column(name = "detalles", nullable = true, length = 100)
    private String detalles;

    @Column(name = "evidencia", nullable = true, length = 200)
    private String evidencia;

    @OneToMany(mappedBy = "incidencia", fetch = FetchType.LAZY)
    private List<DetalleIncidencia> detalleIncidencias;
}