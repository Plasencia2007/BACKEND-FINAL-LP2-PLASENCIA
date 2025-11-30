package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long idEstado;

    @Column(name = "descripcion", nullable = true, length = 100)
    private String descripcion;

    @Column(name = "tipo", nullable = true, length = 20)
    private String tipo;

    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Entrega> entregas;

    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Destino> destinos;
}