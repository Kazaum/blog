package com.blog.blog.service;

import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog.dto.PublicacaoDto;
import com.blog.blog.entity.Publicacao;
import com.blog.blog.mapper.PublicacaoMapper;
import com.blog.blog.repository.PublicacaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublicacaoService {
    private final PublicacaoRepository repository;
    private final PublicacaoMapper mapper;

    // Cria uma nova publicação
    public PublicacaoDto create(Publicacao publicacao){
        return mapper.toDto(repository.save(publicacao));
    }

    // Lista todas as publicações em páginas
    public Page<PublicacaoDto> listarPaginado(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dataCriacao").descending());
        Page<Publicacao> publicacoes = repository.findAll(pageable);
        Page<PublicacaoDto> newPublicacoes = publicacoes.map(publicacao -> 
            mapper.toDto(publicacao)
        );

        return newPublicacoes;
    }

    // Lista todas as publicações de um usuário em páginas
    public Page<PublicacaoDto> listarPorUsuario(Long usuarioId, int paginaInicial, int tamanhoPagina) {
        Pageable pageable = PageRequest.of(paginaInicial, tamanhoPagina, Sort.by("dataCriacao").descending());
        Page<Publicacao> publicacoes = repository.findByUsuarioId(usuarioId, pageable);
        Page<PublicacaoDto> newPublicacoes = publicacoes.map(publicacao -> 
            mapper.toDto(publicacao)
        );

        return newPublicacoes;
    }

    // Altera uma publicação
    public PublicacaoDto update(Publicacao publicacaoAtualizada) {
        Publicacao existente = repository.findById(publicacaoAtualizada.getId())
        .orElseThrow(() -> new RuntimeException("Publicação não encontrada"));
        BeanUtils.copyProperties(publicacaoAtualizada, existente, "id", "dataCriacao", "usuario");
        existente.setDataAlteracao(LocalDateTime.now());

        return mapper.toDto(repository.save(existente));
    }

    // Deleta uma publicação
    public Page<PublicacaoDto> delete(Long id){
        repository.deleteById(id);

        return  listarPaginado(0, 10);
    }
}
