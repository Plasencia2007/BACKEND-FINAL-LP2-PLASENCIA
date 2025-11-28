package com.example.bdv1.mappers;

import com.example.bdv1.dto.EstadoDTO;
import com.example.bdv1.entity.Estado;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstadoMapper extends BaseMappers<Estado, EstadoDTO> {
    @Mapping(source = "idEstado", target = "id")
    EstadoDTO toDTO(Estado estado);

    @InheritInverseConfiguration
    @Mapping(target = "entregas", ignore = true)
    @Mapping(target = "destinos", ignore = true)
    Estado toEntity(EstadoDTO estadoDTO);
}