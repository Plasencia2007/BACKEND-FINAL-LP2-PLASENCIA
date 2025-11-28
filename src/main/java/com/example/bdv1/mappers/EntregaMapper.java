package com.example.bdv1.mappers;

import com.example.bdv1.dto.EntregaDTO;
import com.example.bdv1.entity.Entrega;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntregaMapper extends BaseMappers<Entrega, EntregaDTO> {
    @Mapping(source = "idEntrega", target = "id")
    @Mapping(source = "vehiculo.idVehiculo", target = "idVehiculo")
    @Mapping(source = "chofer.idChofer", target = "idChofer")
    @Mapping(source = "cliente.idCliente", target = "idCliente")
    @Mapping(source = "estado.idEstado", target = "idEstado")
    EntregaDTO toDTO(Entrega entrega);

    @InheritInverseConfiguration
    @Mapping(target = "vehiculo", ignore = true)
    @Mapping(target = "chofer", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "destinos", ignore = true)
    @Mapping(target = "detalleIncidencias", ignore = true)
    @Mapping(target = "detalleGastosAdicionales", ignore = true)
    Entrega toEntity(EntregaDTO entregaDTO);
}