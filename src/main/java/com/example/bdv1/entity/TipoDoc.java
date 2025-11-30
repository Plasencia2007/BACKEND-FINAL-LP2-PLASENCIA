package com.example.bdv1.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tipo_Doc")
public class TipoDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_doc")
    private Long idTipoDoc;

    @Column(name = "descripcion", nullable = true, length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "tipoDoc", fetch = FetchType.LAZY)
    private List<DocServicio> documentosServicio;
}