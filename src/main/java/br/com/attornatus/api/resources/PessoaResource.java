package br.com.attornatus.api.resources;

import br.com.attornatus.api.dto.PessoaCadastradaDTO;
import br.com.attornatus.api.entities.Pessoa;
import br.com.attornatus.api.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> pessoas = service.findAll();
        return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        var pessoa = service.findById(id);
        return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<PessoaCadastradaDTO> insert(@RequestBody Pessoa pessoa, UriComponentsBuilder uriBuilder) {
        PessoaCadastradaDTO pessoaCadastrada = service.insert(pessoa);

        var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoaCadastrada.id()).toUri();
        return ResponseEntity.created(uri).body(pessoaCadastrada);
    }
}
