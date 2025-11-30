package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Chofer")
public class Chofer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chofer")
    private Long idChofer;

    @Column(name = "nombres", nullable = true, length = 100)
    private String nombres;

    @Column(name = "dni", nullable = true, length = 8)
    private String dni;

    @Column(name = "licencia", nullable = true, length = 100)
    private String licencia;

    @Column(name = "telefono", nullable = true, length = 15)
    private String telefono;

    // Relación inversa
    @OneToMany(mappedBy = "chofer", fetch = FetchType.LAZY)
    private List<Entrega> entregas;
}