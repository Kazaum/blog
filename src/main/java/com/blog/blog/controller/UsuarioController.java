package com.blog.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.entity.Usuario;
import com.blog.blog.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;


    @PostMapping("/create")
    public List<Usuario> create(@RequestBody Usuario usuario){
        return service.create(usuario);
    }

    @GetMapping("/listAll")
    public List<Usuario> listAll() {
        return service.listAll();
    }

    @PutMapping("/update")
    public Usuario update(@RequestBody Usuario usuario) {
        return service.update(usuario);
    }

    @DeleteMapping("{id}")
    public List<Usuario> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
    
}
