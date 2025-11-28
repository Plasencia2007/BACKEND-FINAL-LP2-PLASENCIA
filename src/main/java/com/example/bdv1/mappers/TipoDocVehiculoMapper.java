package com.example.bdv1.mappers;

import com.example.bdv1.dto.TipoDocVehiculoDTO;
import com.example.bdv1.entity.TipoDocVehiculo;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TipoDocVehiculoMapper extends BaseMappers<TipoDocVehiculo, TipoDocVehiculoDTO> {
    @Mapping(source = "idTipoDocVehiculo", target = "id")
    TipoDocVehiculoDTO toDTO(TipoDocVehiculo tipoDocVehiculo);

    @InheritInverseConfiguration
    @Mapping(target = "documentos", ignore = true)
    TipoDocVehiculo toEntity(TipoDocVehiculoDTO tipoDocVehiculoDTO);
}