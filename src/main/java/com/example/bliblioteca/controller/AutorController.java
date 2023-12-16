/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bliblioteca.controller;

import com.example.bliblioteca.exception.ResourceNotFoundException;
import com.example.bliblioteca.model.Autor;
import com.example.bliblioteca.repository.AutorRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

/**
 *
 * @author Gilson
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class AutorController {
    
    @Autowired
    private AutorRepository autorRepository;
    
    @GetMapping("/autor")
    public List<Autor> getAllAutor(){
            return autorRepository.findAll();
    }
    
    @PostMapping("/autor")
    public ResponseEntity<?> createAutor(@RequestBody ObjectNode json) {
        
        String nome = json.get("nome").textValue();
        
        if(nome.isEmpty())
            return ResponseEntity.badRequest().body("\"O campo 'nome' não pode ser vazio ou nulo.\"");
        
        Autor autor = new Autor();
        autor.setNome(json.get("nome").textValue());
        autorRepository.save(autor);
        
        return ResponseEntity.ok(autor);
                
    }
    
    @GetMapping("/autor/{id}")
    public ResponseEntity<?> getAutorById(@PathVariable Long id) {
            Autor autor = autorRepository.findById(id).orElse(null);
            
            if(autor == null)
                return ResponseEntity.badRequest().body("\"O Autor não foi encontrado\"");
            
            return ResponseEntity.ok(autor);
    }
    
    @PutMapping("/autor/{id}")
    public ResponseEntity<?> updateAutor(@PathVariable Long id, @RequestBody Autor autorDetalhe){
            Autor autor = autorRepository.findById(id).orElse(null);
            
            if(autor == null)
                return ResponseEntity.badRequest().body("\"O Autor não foi encontrado\"");

            autor.setNome(autorDetalhe.getNome());
            Autor updatedAutor = autorRepository.save(autor);
            
            return ResponseEntity.ok(updatedAutor);
    }
    
    @DeleteMapping("/autor/{id}")
    public ResponseEntity<Map<String, Object>> deleteAutor(@PathVariable Long id) {
    Autor autor = autorRepository.findById(id).orElse(null);

    if (autor == null) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "O Autor não foi encontrado");
        response.put("deleted", Boolean.FALSE);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    autorRepository.delete(autor);
    Map<String, Object> response = new HashMap<>();
    response.put("message", "Autor excluído com sucesso");
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
}
    
}
