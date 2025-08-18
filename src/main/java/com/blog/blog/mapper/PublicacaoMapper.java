package com.blog.blog.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.blog.blog.dto.PublicacaoDto;
import com.blog.blog.dto.UsuarioDto;
import com.blog.blog.entity.Publicacao;
import com.blog.blog.entity.Usuario;

@Component
public class PublicacaoMapper {

    private final UsuarioMapper usuarioMapper;

    // Injetando o UsuarioMapper para mapear o usuário dentro da publicação
    public PublicacaoMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public PublicacaoDto toDto(Publicacao publicacao) {
        if (publicacao == null) return null;

        PublicacaoDto dto = new PublicacaoDto();
        BeanUtils.copyProperties(publicacao, dto); // copia id, descricao, curtidas, datas
        
        // mapeia o usuário dentro do DTO
        UsuarioDto usuarioDto = usuarioMapper.toDto(publicacao.getUsuario());
        dto.setUsuario(usuarioDto);

        return dto;
    }

    public Publicacao toEntity(PublicacaoDto dto) {
        if (dto == null) return null;

        Publicacao publicacao = new Publicacao();
        BeanUtils.copyProperties(dto, publicacao);

        // mapeia o usuário do DTO para a entidade
        Usuario usuario = usuarioMapper.toEntity(dto.getUsuario());
        publicacao.setUsuario(usuario);

        return publicacao;
    }
}