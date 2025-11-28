package com.example.bdv1.mappers;

import com.example.bdv1.dto.ChoferDTO;
import com.example.bdv1.entity.Chofer;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChoferMapper extends BaseMappers<Chofer, ChoferDTO> {
    @Mapping(source = "idChofer", target = "id")
    ChoferDTO toDTO(Chofer chofer);

    @InheritInverseConfiguration
    @Mapping(target = "entregas", ignore = true)
    Chofer toEntity(ChoferDTO choferDTO);
}