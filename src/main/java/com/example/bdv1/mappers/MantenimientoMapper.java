package com.example.bdv1.mappers;

import com.example.bdv1.dto.MantenimientoDTO;
import com.example.bdv1.entity.Mantenimiento;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MantenimientoMapper extends BaseMappers<Mantenimiento, MantenimientoDTO> {
    @Mapping(source = "idMantenimiento", target = "id")
    @Mapping(source = "vehiculo.idVehiculo", target = "idVehiculo")
    @Mapping(source = "taller.idTaller", target = "idTaller")
    MantenimientoDTO toDTO(Mantenimiento mantenimiento);

    @InheritInverseConfiguration
    @Mapping(target = "vehiculo", ignore = true)
    @Mapping(target = "taller", ignore = true)
    Mantenimiento toEntity(MantenimientoDTO mantenimientoDTO);
}