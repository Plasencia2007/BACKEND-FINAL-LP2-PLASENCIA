package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tipo_Doc_Vehiculo")
public class TipoDocVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_doc_vehiculo")
    private Long idTipoDocVehiculo;

    @Column(name = "descripcion", nullable = true, length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "tipoDocVehiculo", fetch = FetchType.LAZY)
    private List<DocVehiculo> documentos;
}