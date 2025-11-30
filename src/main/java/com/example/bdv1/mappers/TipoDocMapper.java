package com.example.bdv1.mappers;

import com.example.bdv1.dto.TipoDocDTO;
import com.example.bdv1.entity.TipoDoc;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TipoDocMapper extends BaseMappers<TipoDoc, TipoDocDTO> {
    @Mapping(source = "idTipoDoc", target = "id")
    TipoDocDTO toDTO(TipoDoc tipoDoc);

    @InheritInverseConfiguration
    @Mapping(target = "documentosServicio", ignore = true)
    TipoDoc toEntity(TipoDocDTO tipoDocDTO);
}