package com.consumption.crud.controllers;

import com.consumption.crud.models.PessoaModel;
import com.consumption.crud.repositories.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin("*")
@AllArgsConstructor
public class PessoaController {
    private PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<List<PessoaModel>> pegaTodasPessoas(){
        //Recuperando todas as entradas
        return new ResponseEntity<>(pessoaRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PessoaModel> criarNovaPessoa(@RequestBody PessoaModel pessoa){
        //Garantindo que a nova pessoa está sem Id para que o JPA entenda que é para criar uma nova entrada
        pessoa.setId(null);
        return new ResponseEntity<>(pessoaRepository.save(pessoa), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PessoaModel> alterarPessoa(@RequestBody PessoaModel pessoa){
        //Atualizando uma pessoa existente
        return new ResponseEntity<>(pessoaRepository.save(pessoa), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PessoaModel> excluirPessoa(@PathVariable Integer id){
        //Remove uma pessoa por id
        pessoaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
