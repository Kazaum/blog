package com.blog.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entity.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>{
    List<Publicacao> findByUsuarioIdOrderByDataCriacaoDesc(Long usuarioId);
}
