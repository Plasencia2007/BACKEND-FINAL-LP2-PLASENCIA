package com.example.bdv1.mappers;

import com.example.bdv1.dto.RolDTO;
import com.example.bdv1.entity.security.Rol;
import com.example.bdv1.mappers.base.BaseMappers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper extends BaseMappers<Rol, RolDTO> {
}
