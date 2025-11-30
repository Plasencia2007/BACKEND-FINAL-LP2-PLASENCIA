package com.example.bdv1.mappers;

import com.example.bdv1.dto.VehiculoDTO;
import com.example.bdv1.entity.Vehiculo;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehiculoMapper extends BaseMappers<Vehiculo, VehiculoDTO> {
    @Mapping(source = "idVehiculo", target = "id")
    VehiculoDTO toDTO(Vehiculo vehiculo);

    @InheritInverseConfiguration
    @Mapping(target = "entregas", ignore = true)
    @Mapping(target = "documentosVehiculo", ignore = true)
    @Mapping(target = "mantenimientos", ignore = true)
    Vehiculo toEntity(VehiculoDTO vehiculoDTO);
}