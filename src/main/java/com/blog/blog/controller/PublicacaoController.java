package com.blog.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.entity.Publicacao;
import com.blog.blog.service.PublicacaoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("publicacao")
@RequiredArgsConstructor
public class PublicacaoController {
    private final PublicacaoService service;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Publicacao publicacao){
        return ResponseEntity.ok(service.create(publicacao));
    }

    @GetMapping("/all")
    public ResponseEntity<?> listAll(
        @RequestParam(defaultValue = "0") int paginaInicial,
        @RequestParam(defaultValue = "10") int tamanhoPagina)
    {
        return ResponseEntity.ok(service.listarPaginado(paginaInicial, tamanhoPagina));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> listByUserId(
        @PathVariable("userId") Long userId,
        @RequestParam(defaultValue = "0") int paginaInicial,
        @RequestParam(defaultValue = "10") int tamanhoPagina)
    {
        return ResponseEntity.ok(service.listarPorUsuario(userId, paginaInicial, tamanhoPagina));
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Publicacao publicacao) {
        return ResponseEntity.ok(service.update(publicacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }
    
}
