package com.blog.blog.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog.dto.UsuarioDto;
import com.blog.blog.entity.Usuario;
import com.blog.blog.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    private static UsuarioDto convertUsuario(Usuario usuario){
        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
    
    // Cria um novo usuario
    public Page<UsuarioDto> create(Usuario usuario){
        repository.save(usuario);
        return listPage(0, 10);
    }

    // Retorna todos os usuarios em páginas
    public Page<UsuarioDto> listPage(int paginaInicial, int tamanhoPagina){
        Pageable pageable = PageRequest.of(paginaInicial, tamanhoPagina, Sort.by("nome").descending());
        Page<Usuario> usuarios = repository.findAll(pageable);
        Page<UsuarioDto> newUsuarios = usuarios.map(usuario -> 
            convertUsuario(usuario)
        );
        return newUsuarios;
    }

    // Retorna os dados de um usuário
    public UsuarioDto findUser(Long idUsuario) {
        Usuario usuario = repository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + idUsuario));

        return convertUsuario(usuario);
    }

    // Atualiza os dados de um usuário
    public UsuarioDto update(Usuario usuario){
        Usuario existingUser = repository.findById(usuario.getId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + usuario.getId()));
        
        BeanUtils.copyProperties(usuario, existingUser, "id");

        Usuario updatedUser = repository.save(existingUser);
        return convertUsuario(updatedUser);
    }

    // Deleta um usuário
    public Page<UsuarioDto> delete(Long id){
        repository.deleteById(id);
        return listPage(0, 10);
    }
}
