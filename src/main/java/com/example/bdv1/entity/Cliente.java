package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "razon_social", nullable = true, length = 100)
    private String razonSocial;

    @Column(name = "ruc", nullable = true, length = 11)
    private String ruc;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Entrega> entregas;
}