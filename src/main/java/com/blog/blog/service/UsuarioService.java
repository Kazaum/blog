package com.blog.blog.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog.entity.Usuario;
import com.blog.blog.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    
    public List<Usuario> create(Usuario usuario){
        repository.save(usuario);
        return listAll();
    }

    public List<Usuario> listAll(){
        Sort sort = Sort.by("dataCriacao").descending();
        return repository.findAll(sort);
    }

    public Usuario update(Usuario usuario){
        return repository.save(usuario);
    }

    public List<Usuario> delete(Long id){
        repository.deleteById(id);
        return listAll();
    }
}
