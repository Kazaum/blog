package com.blog.blog.entity;

import java.security.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="publicacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publicacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private int curtidas;

    @CreationTimestamp
    private Timestamp dataCriacao;

    @UpdateTimestamp
    private Timestamp dataAlteracao;
}
