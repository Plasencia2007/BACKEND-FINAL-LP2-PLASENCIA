package com.example.bdv1.mappers;

import com.example.bdv1.dto.DocServicioDTO;
import com.example.bdv1.entity.DocServicio;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocServicioMapper extends BaseMappers<DocServicio, DocServicioDTO> {
    @Mapping(source = "idDocServicio", target = "id")
    @Mapping(source = "destino.idDestino", target = "idDestino")
    @Mapping(source = "tipoDoc.idTipoDoc", target = "idTipoDoc")
    DocServicioDTO toDTO(DocServicio docServicio);

    @InheritInverseConfiguration
    @Mapping(target = "destino", ignore = true)
    @Mapping(target = "tipoDoc", ignore = true)
    DocServicio toEntity(DocServicioDTO docServicioDTO);
}