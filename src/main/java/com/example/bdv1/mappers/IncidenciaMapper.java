package com.example.bdv1.mappers;

import com.example.bdv1.dto.IncidenciaDTO;
import com.example.bdv1.entity.Incidencia;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncidenciaMapper extends BaseMappers<Incidencia, IncidenciaDTO> {

    @Mapping(source = "idIncidencias", target = "id")
    IncidenciaDTO toDTO(Incidencia entity);

    @InheritInverseConfiguration
    @Mapping(target = "detalleIncidencias", ignore = true) // ← Ignora la lista
    @Mapping(target = "idIncidencias", ignore = true)
    Incidencia toEntity(IncidenciaDTO dto);
}