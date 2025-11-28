package com.example.bdv1.mappers;

import com.example.bdv1.dto.DetalleGastoAdicionalDTO;
import com.example.bdv1.entity.DetalleGastoAdicional;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleGastoAdicionalMapper extends BaseMappers<DetalleGastoAdicional, DetalleGastoAdicionalDTO> {
    @Mapping(source = "idDetalleGastosAdicionales", target = "id")
    @Mapping(source = "gastoAdicional.idGastosAdicionales", target = "idGastoAdicional")
    @Mapping(source = "entrega.idEntrega", target = "idEntrega")
    DetalleGastoAdicionalDTO toDTO(DetalleGastoAdicional detalleGastoAdicional);

    @InheritInverseConfiguration
    @Mapping(target = "gastoAdicional", ignore = true)
    @Mapping(target = "entrega", ignore = true)
    DetalleGastoAdicional toEntity(DetalleGastoAdicionalDTO detalleGastoAdicionalDTO);
}