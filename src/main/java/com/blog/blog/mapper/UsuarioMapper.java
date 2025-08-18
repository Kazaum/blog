package com.blog.blog.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.blog.blog.dto.UsuarioDto;
import com.blog.blog.entity.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioDto toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDto dto = new UsuarioDto();
        BeanUtils.copyProperties(usuario, dto);
        return dto;
    }

    public Usuario toEntity(UsuarioDto dto) {
        if (dto == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario);
        return usuario;
    }
}
