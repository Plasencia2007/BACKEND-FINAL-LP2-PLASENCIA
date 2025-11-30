package com.example.bdv1.mappers;
import com.example.bdv1.dto.UsuarioDTO;
import com.example.bdv1.entity.security.Usuario;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMappers<Usuario, UsuarioDTO> {

    @Override
    @Mapping(target = "rol", expression = "java(entity.getRol().getNombre())")
    UsuarioDTO toDTO(Usuario entity);

    @Override
    @Mapping(target = "rol", ignore = true)  // se asigna en el servicio
    Usuario toEntity(UsuarioDTO dto);
}

