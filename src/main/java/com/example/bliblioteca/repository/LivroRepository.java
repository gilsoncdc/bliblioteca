/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bliblioteca.repository;

import com.example.bliblioteca.model.Livro;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Gilson
 */
public interface LivroRepository extends JpaRepository<Livro, Long>{
    
}
