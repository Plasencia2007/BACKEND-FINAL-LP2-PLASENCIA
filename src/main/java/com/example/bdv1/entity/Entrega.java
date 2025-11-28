package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_entrega")
    private Long idEntrega;

    @Column(name = "tipo_ruta", nullable = true, length = 50)
    private String tipoRuta;

    @Column(name = "fecha_programacion", nullable = false)
    private LocalDate fechaProgramacion;

    @Column(name = "fecha_fin", nullable = true)
    private LocalDate fechaFin;

    @Column(name = "monto_base", precision = 10, scale = 2)
    private BigDecimal montoBase;

    @Column(name = "monto_gastos", precision = 10, scale = 2)
    private BigDecimal montoGastos;

    @Column(name = "monto_total", precision = 10, scale = 2)
    private BigDecimal montoTotal;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chofer", nullable = false)
    private Chofer chofer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Destino> destinos;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleIncidencia> detalleIncidencias;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleGastoAdicional> detalleGastosAdicionales;
}