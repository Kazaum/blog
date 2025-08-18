package com.blog.blog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog.entity.Publicacao;
import com.blog.blog.repository.PublicacaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublicacaoService {
    private final PublicacaoRepository repository;

    // Cria uma nova publicação
    public Publicacao create(Publicacao publicacao){
        return repository.save(publicacao);
    }

    // Lista todas as publicações em páginas
    public Page<Publicacao> listarPaginado(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dataCriacao").descending());
        return repository.findAll(pageable);
    }

    // Lista todas as publicações de um usuário em páginas
    public Page<Publicacao> listarPorUsuario(Long usuarioId, int paginaInicial, int tamanhoPagina) {
        Pageable pageable = PageRequest.of(paginaInicial, tamanhoPagina, Sort.by("dataCriacao").descending());
        return repository.findByUsuarioId(usuarioId, pageable);
    }

    // Altera uma publicação
    public Publicacao update(Publicacao publicacaoAtualizada) {
        Publicacao existente = repository.findById(publicacaoAtualizada.getId())
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada"));

        publicacaoAtualizada.setDataCriacao(existente.getDataCriacao());
        publicacaoAtualizada.setDataAlteracao(LocalDateTime.now());
        publicacaoAtualizada.setId(publicacaoAtualizada.getId());
        if (publicacaoAtualizada.getUsuario() == null) {
            publicacaoAtualizada.setUsuario(existente.getUsuario());
        }

        return repository.save(publicacaoAtualizada);
    }

    // Deleta uma publicação
    public Page<Publicacao> delete(Long id){
        repository.deleteById(id);
        return  listarPaginado(0, 10);
    }
}
