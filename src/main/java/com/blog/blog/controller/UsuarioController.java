package com.blog.blog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    private static boolean validation(String string){
        if (string == null || string.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Usuario usuario){
        if (!validation(usuario.getSenha())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O usuário precisa de uma senha cadastrada");
        }else if (!validation(usuario.getNome())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O usuário precisa de um nome cadastrado");
        }else if (!validation(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O usuário precisa de um email cadastrado");
        }
        return ResponseEntity.ok(service.create(usuario));
    }

    @GetMapping("/list/all")
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Usuario usuario) {
        if (!validation(usuario.getSenha())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A senha não pode ser alterada para algo vazio");
        }else if (!validation(usuario.getNome())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome não pode ser alterado para algo vazio");
        }else if (!validation(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O email não pode ser alterad para algo vazio");
        }
        return ResponseEntity.ok(service.update(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
    
}
