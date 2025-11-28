package com.example.bdv1.mappers;

import com.example.bdv1.dto.TiendaDTO;
import com.example.bdv1.entity.Tienda;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TiendaMapper extends BaseMappers<Tienda, TiendaDTO> {
    @Mapping(source = "idTienda", target = "id")
    TiendaDTO toDTO(Tienda tienda);

    @InheritInverseConfiguration
    @Mapping(target = "destinos", ignore = true)
    Tienda toEntity(TiendaDTO tiendaDTO);
}