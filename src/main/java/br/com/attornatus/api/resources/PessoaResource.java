package br.com.attornatus.api.resources;

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
        System.out.println(pessoas);
        return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pessoa> insert(@RequestBody Pessoa pessoa, UriComponentsBuilder uriBuilder) {
        Pessoa pessoaCadastrada = service.insert(pessoa);

        var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoaCadastrada.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoaCadastrada);
    }
}
