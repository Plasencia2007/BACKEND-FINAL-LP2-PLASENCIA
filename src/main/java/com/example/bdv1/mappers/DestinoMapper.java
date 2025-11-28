package com.example.bdv1.mappers;

import com.example.bdv1.dto.DestinoDTO;
import com.example.bdv1.entity.Destino;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DestinoMapper extends BaseMappers<Destino, DestinoDTO> {
    @Mapping(source = "idDestino", target = "id")
    @Mapping(source = "entrega.idEntrega", target = "idEntrega")
    @Mapping(source = "tienda.idTienda", target = "idTienda")
    @Mapping(source = "estado.idEstado", target = "idEstado")
    DestinoDTO toDTO(Destino destino);

    @InheritInverseConfiguration
    @Mapping(target = "entrega", ignore = true)
    @Mapping(target = "tienda", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "docServicios", ignore = true)
    Destino toEntity(DestinoDTO destinoDTO);
}