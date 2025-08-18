package com.blog.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog.entity.Usuario;
import com.blog.blog.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    
    // Cria um novo usuario
    public Page<Usuario> create(Usuario usuario){
        repository.save(usuario);
        return listPage(0, 10);
    }

    // Retorna todos os usuarios em páginas
    public Page<Usuario> listPage(int paginaInicial, int tamanhoPagina){
        Pageable pageable = PageRequest.of(paginaInicial, tamanhoPagina, Sort.by("nome").descending());
        return repository.findAll(pageable);
    }

    // Retorna os dados de um usuário
    public Usuario findUser(Long idUsuario) {
        return repository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + idUsuario));
    }

    // Atualiza os dados de um usuário
    public Usuario update(Usuario usuario){
        return repository.save(usuario);
    }

    // Deleta um usuário
    public Page<Usuario> delete(Long id){
        repository.deleteById(id);
        return listPage(0, 10);
    }
}
