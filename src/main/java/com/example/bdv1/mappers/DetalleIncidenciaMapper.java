package com.example.bdv1.mappers;

import com.example.bdv1.dto.DetalleIncidenciaDTO;
import com.example.bdv1.entity.DetalleIncidencia;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleIncidenciaMapper extends BaseMappers<DetalleIncidencia, DetalleIncidenciaDTO> {
    @Mapping(source = "idDetalleIncidencias", target = "id")
    @Mapping(source = "incidencia.idIncidencias", target = "idIncidencia")
    @Mapping(source = "entrega.idEntrega", target = "idEntrega")
    DetalleIncidenciaDTO toDTO(DetalleIncidencia detalleIncidencia);

    @InheritInverseConfiguration
    @Mapping(target = "incidencia", ignore = true)
    @Mapping(target = "entrega", ignore = true)
    DetalleIncidencia toEntity(DetalleIncidenciaDTO detalleIncidenciaDTO);
}