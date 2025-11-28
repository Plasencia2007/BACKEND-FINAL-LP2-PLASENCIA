package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Destinos")
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_destino")
    private Long idDestino;

    @Column(name = "volumen_m3", precision = 8, scale = 2)
    private BigDecimal volumenM3;

    @Column(name = "vh_inicio", nullable = true)
    private LocalDateTime vhInicio;

    @Column(name = "vh_final", nullable = true)
    private LocalDateTime vhFinal;

    @Column(name = "almacen", nullable = true, length = 100)
    private String almacen;

    @Column(name = "psex", nullable = true, length = 50)
    private String psex;

    @Column(name = "canal", nullable = true, length = 50)
    private String canal;

    @Column(name = "observaciones", nullable = true, length = 200)
    private String observaciones;

    @Column(name = "hora_entrega", nullable = true)
    private LocalDateTime horaEntrega;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrega", nullable = false)
    private Entrega entrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tienda", nullable = false)
    private Tienda tienda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "destino", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DocServicio> docServicios;
}