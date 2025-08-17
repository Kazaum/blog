package com.blog.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entity.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>{
    
}
