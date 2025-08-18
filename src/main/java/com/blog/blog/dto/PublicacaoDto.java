package com.blog.blog.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicacaoDto {
    private Long id;
    private String descricao;
    private int curtidas;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private UsuarioDto usuario;
}
