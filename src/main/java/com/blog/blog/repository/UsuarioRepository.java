package com.blog.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entity.Publicacao;
import com.blog.blog.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Page<Usuario> findAll(Pageable pageable);
}
