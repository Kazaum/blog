package com.blog.blog.service;

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

    public Publicacao create(Publicacao publicacao){
        return repository.save(publicacao);
    }

    public List<Publicacao> listAll(){
        Sort sort = Sort.by("dataCriacao").descending();
        return repository.findAll(sort);
    }

    public Publicacao update(Publicacao publicacao){
        return repository.save(publicacao);
    }

    public List<Publicacao> delete(Long id){
        repository.deleteById(id);
        return  listAll();
    }
}
