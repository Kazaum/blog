package com.blog.blog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.entity.Usuario;
import com.blog.blog.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Usuario usuario){
        return ResponseEntity.ok(service.create(usuario));
    }

    @GetMapping("/all")
    public ResponseEntity<?> listAll(
        @RequestParam(defaultValue = "0") int paginaInicial,
        @RequestParam(defaultValue = "10") int tamanhoPagina)
    {
        return ResponseEntity.ok(service.listPage(paginaInicial, tamanhoPagina));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findUser(id));
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(service.update(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
    
}
