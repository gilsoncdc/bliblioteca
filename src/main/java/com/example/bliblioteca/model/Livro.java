/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bliblioteca.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Gilson
 */
@Entity
@Table(name = "livro")
public class Livro implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_livro;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "ano_publicacao")
    private LocalDate ano_publicacao;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "disponibilidade")
    private String disponibilidade;
    
    @ManyToOne()
    @JoinColumn(name="id_autor")
    private Autor autor;
    
    
    public Livro(){

    }

    public Livro(String titulo, LocalDate ano_publicacao, String isbn, String disponibilidade) {
        super();
        this.titulo = titulo;
        this.ano_publicacao = ano_publicacao;
        this.isbn = isbn;
        this.disponibilidade = disponibilidade;
    }

    public Long getIdLivro() {
        return id_livro;
    }
    public void setIdLivro(long id) {
        this.id_livro = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public LocalDate getAnoPublicacao() {
        return ano_publicacao;
    }
    public void setAnoPublicacao(LocalDate ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }
    
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }
    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
}
