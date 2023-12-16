/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bliblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bliblioteca.model.Autor;
import com.example.bliblioteca.model.Livro;
import com.example.bliblioteca.repository.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Gilson
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class LivroController {
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @GetMapping("/livro")
    public List<Livro> getAllLivro(){
            return livroRepository.findAll();
    }
    
    @PostMapping("/livro")
    public ResponseEntity<?> createLivro(@RequestBody ObjectNode json) {
        
        String titulo = json.get("titulo").textValue();
        String anoPublicacao = json.get("anoPublicacao").textValue();
        String isbn = json.get("isbn").textValue();
        String disponibilidade = json.get("disponibilidade").textValue();
        String idAutor = json.get("id_autor").textValue();
        
        if(titulo.isEmpty() || idAutor.isEmpty() || anoPublicacao.isEmpty() || isbn.isEmpty() || disponibilidade.isEmpty())
            return ResponseEntity.badRequest().body("\"Todos os campos são obrigatorio e não podem ser vazio ou nulo.\"");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate anoPublicacaoFormat = LocalDate.parse(anoPublicacao, formatter);      
        
        Autor autor = autorRepository.findById(Long.parseLong(idAutor)).orElse(null);
        if(autor != null){
        
            Livro livro = new Livro();
            livro.setTitulo(titulo);
            livro.setAnoPublicacao(anoPublicacaoFormat);
            livro.setIsbn(isbn);
            livro.setDisponibilidade(disponibilidade);
            livro.setAutor(autor);
            livroRepository.save(livro);

            return ResponseEntity.ok(livro);
            
        }else{
            return ResponseEntity.badRequest().body("\"O Autor inserido não foi encontrado.\"");
        }
        
    }
    
    @GetMapping("/livro/{id}")
    public ResponseEntity<?> getLivroById(@PathVariable Long id) {
            Livro livro = livroRepository.findById(id).orElse(null);
            
            if(livro == null)
                return ResponseEntity.badRequest().body("\"O Livro não foi encontrado\"");
            
            return ResponseEntity.ok(livro);
            
    }
    
    @PutMapping("/livro/{id}")
    public ResponseEntity<?> updateLivro(@PathVariable Long id, @RequestBody Livro livroDetalhe){
            Livro livro = livroRepository.findById(id).orElse(null);
            
            if(livro == null)
                return ResponseEntity.badRequest().body("\"O Livro não foi encontrado\"");
            
            livro.setTitulo(livroDetalhe.getTitulo());
            livro.setAnoPublicacao(livroDetalhe.getAnoPublicacao());
            livro.setIsbn(livroDetalhe.getIsbn());
            livro.setDisponibilidade(livroDetalhe.getDisponibilidade());

            Livro updatedLivro = livroRepository.save(livro);
            return ResponseEntity.ok(updatedLivro);
            
    }
    
    @DeleteMapping("/livro/{id}")
    public ResponseEntity<Map<String, Object>> deleteLivro(@PathVariable Long id){
            Livro livro = livroRepository.findById(id).orElse(null);

            if (livro == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "O Livro não foi encontrado");
                response.put("deleted", Boolean.FALSE);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            
            livroRepository.delete(livro);
            Map<String, Object> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
            
    }

}
