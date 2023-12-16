/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bliblioteca.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Gilson
 */
@Entity
@Table(name = "autor")
public class Autor implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_autor;
    
    @Column(name = "nome")
    private String nome;
    
    public Autor() {
    }

    public Autor(Long id_autor, String nome) {
        this.id_autor = id_autor;
        this.nome = nome;
    }
   

    public Long getIdAutor() {
        return id_autor;
    }

    public void setIdAutor(Long id_autor) {
        this.id_autor = id_autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Livro> livros;
    
    
}
