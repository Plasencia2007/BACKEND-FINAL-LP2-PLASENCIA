package com.example.bdv1.mappers;

import com.example.bdv1.dto.ClienteDTO;
import com.example.bdv1.entity.Cliente;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends BaseMappers<Cliente, ClienteDTO> {
    @Mapping(source = "idCliente", target = "id")
    ClienteDTO toDTO(Cliente cliente);

    @InheritInverseConfiguration
    @Mapping(target = "entregas", ignore = true)
    Cliente toEntity(ClienteDTO clienteDTO);
}