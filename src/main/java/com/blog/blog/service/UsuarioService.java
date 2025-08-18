package com.blog.blog.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog.dto.UsuarioDto;
import com.blog.blog.entity.Usuario;
import com.blog.blog.mapper.UsuarioMapper;
import com.blog.blog.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    
    // Cria um novo usuario
    public UsuarioDto create(Usuario usuario){
        return mapper.toDto(repository.save(usuario));
    }

    // Retorna todos os usuarios em páginas
    public Page<UsuarioDto> listPage(int paginaInicial, int tamanhoPagina){
        Pageable pageable = PageRequest.of(paginaInicial, tamanhoPagina, Sort.by("nome").descending());
        Page<Usuario> usuarios = repository.findAll(pageable);
        Page<UsuarioDto> newUsuarios = usuarios.map(usuario -> 
            mapper.toDto(usuario)
        );
        return newUsuarios;
    }

    // Retorna os dados de um usuário
    public UsuarioDto findUser(Long idUsuario) {
        Usuario usuario = repository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + idUsuario));

        return mapper.toDto(usuario);
    }

    // Atualiza os dados de um usuário
    public UsuarioDto update(Usuario usuario){
        Usuario existingUser = repository.findById(usuario.getId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + usuario.getId()));
        
        BeanUtils.copyProperties(usuario, existingUser, "id");

        Usuario updatedUser = repository.save(existingUser);
        return mapper.toDto(updatedUser);
    }

    // Deleta um usuário
    public Page<UsuarioDto> delete(Long id){
        repository.deleteById(id);
        return listPage(0, 10);
    }
}
