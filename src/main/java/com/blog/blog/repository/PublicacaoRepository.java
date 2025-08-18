package com.blog.blog.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entity.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>{
    Page<Publicacao> findAll(Pageable pageable);

    Page<Publicacao> findByUsuarioId(Long usuarioId, Pageable pageable);
}
