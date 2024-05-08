package com.example.springboot.controllers;

import com.example.springboot.dtos.LivroRecordDto;
import com.example.springboot.models.LivroModel;
import com.example.springboot.repositories.LivroRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class LivroController {

    @Autowired
    LivroRepository livroRepository;

    @PostMapping("/livro")
    public ResponseEntity<LivroModel> cadastrarLivro(@RequestBody @Valid LivroRecordDto livroRecordDto) {
        var livroModel = new LivroModel();
        BeanUtils.copyProperties(livroRecordDto, livroModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livroModel));
    }
    
    @GetMapping("/livro")
    public ResponseEntity<List<LivroModel>> consultarLivros() {
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.findAll());
    }

    @GetMapping("/livro/{id}")
    public ResponseEntity<Object> consultarUmLivro(@PathVariable(value = "id") UUID id) {
        Optional<LivroModel> livroUm = livroRepository.findById(id);
        if (livroUm.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(livroUm.get());
    }
    
    @PutMapping("/livro/{id}")
    public ResponseEntity<Object> alterarCadastro(@PathVariable (value = "id")UUID id, @RequestBody @Valid LivroRecordDto livroRecordDto) {
    
        Optional<LivroModel> livroUm= livroRepository.findById(id);
        if(livroUm.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontardo");
        }
        var livroModel = livroUm.get();
        BeanUtils.copyProperties(livroRecordDto, livroModel);
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.save(livroModel));
    }
   
    @DeleteMapping("/livro/{id}")
    public ResponseEntity<Object> deletarLivro(@PathVariable(value = "id") UUID id){
        Optional<LivroModel> livroUm = livroRepository.findById(id);
        if(livroUm.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado.");
        }
        livroRepository.delete(livroUm.get());
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso.");
    }
    
}
