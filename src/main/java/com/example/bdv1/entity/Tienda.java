package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tiendas")
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tienda")
    private Long idTienda;

    @Column(name = "codigo", nullable = true, length = 50)
    private String codigo;

    @Column(name = "nombre", nullable = true, length = 100)
    private String nombre;

    @Column(name = "direccion", nullable = true, length = 200)
    private String direccion;

    @Column(name = "distrito", nullable = true, length = 100)
    private String distrito;

    @OneToMany(mappedBy = "tienda", fetch = FetchType.LAZY)
    private List<Destino> destinos;
}