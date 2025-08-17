package com.blog.blog.service;

import java.time.LocalDateTime;
import java.util.List;

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

    // Lista todas as publicações
    public List<Publicacao> listAll(){
        Sort sort = Sort.by("dataCriacao").descending();
        return repository.findAll(sort);
    }

    // Lista todas as publicações de um usuário
    public List<Publicacao> listByUserId(Long usuarioId) {
        return repository.findByUsuarioIdOrderByDataCriacaoDesc(usuarioId);
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
    public List<Publicacao> delete(Long id){
        repository.deleteById(id);
        return  listAll();
    }
}
