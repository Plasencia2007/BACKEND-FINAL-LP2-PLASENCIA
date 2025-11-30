package com.example.bdv1.mappers;

import com.example.bdv1.dto.DocVehiculoDTO;
import com.example.bdv1.entity.DocVehiculo;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocVehiculoMapper extends BaseMappers<DocVehiculo, DocVehiculoDTO> {
    @Mapping(source = "idDocVehiculo", target = "id")
    @Mapping(source = "vehiculo.idVehiculo", target = "idVehiculo")
    @Mapping(source = "tipoDocVehiculo.idTipoDocVehiculo", target = "idTipoDocVehiculo")
    DocVehiculoDTO toDTO(DocVehiculo docVehiculo);

    @InheritInverseConfiguration
    @Mapping(target = "vehiculo", ignore = true)
    @Mapping(target = "tipoDocVehiculo", ignore = true)
    DocVehiculo toEntity(DocVehiculoDTO docVehiculoDTO);
}