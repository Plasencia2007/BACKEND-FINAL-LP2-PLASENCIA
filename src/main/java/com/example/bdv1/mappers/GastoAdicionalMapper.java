package com.example.bdv1.mappers;

import com.example.bdv1.dto.GastoAdicionalDTO;
import com.example.bdv1.entity.GastoAdicional;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GastoAdicionalMapper extends BaseMappers<GastoAdicional, GastoAdicionalDTO> {

    @Mapping(source = "idGastosAdicionales", target = "id")
    GastoAdicionalDTO toDTO(GastoAdicional entity);

    @InheritInverseConfiguration
    @Mapping(target = "detalleGastos", ignore = true) // Ignora la relación
    @Mapping(target = "idGastosAdicionales", ignore = true)
    GastoAdicional toEntity(GastoAdicionalDTO dto);
}