package com.example.bdv1.mappers;

import com.example.bdv1.dto.TallerDTO;
import com.example.bdv1.entity.Taller;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TallerMapper extends BaseMappers<Taller, TallerDTO> {
    @Mapping(source = "idTaller", target = "id")
    TallerDTO toDTO(Taller taller);

    @InheritInverseConfiguration
    @Mapping(target = "mantenimientos", ignore = true)
    Taller toEntity(TallerDTO tallerDTO);
}