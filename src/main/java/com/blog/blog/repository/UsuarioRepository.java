package com.blog.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
