package br.com.attornatus.api.resources;
import br.com.attornatus.api.dto.PessoaCadastradaDTO;
import br.com.attornatus.api.dto.PessoaDTO;
import br.com.attornatus.api.entities.Pessoa;
import br.com.attornatus.api.services.PessoaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<PessoaDTO>> findAll() {
        List<PessoaDTO> pessoas = service.findAll();
        return new ResponseEntity<List<PessoaDTO>>(pessoas, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        var pessoa = service.findById(id);
        return new ResponseEntity<PessoaDTO>(pessoa, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<PessoaCadastradaDTO> insert(@RequestBody @Valid PessoaDTO pessoaDTO, UriComponentsBuilder uriBuilder) {

        PessoaCadastradaDTO pessoaCadastrada = service.insert(pessoaDTO);
        var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoaCadastrada.id()).toUri();
        return ResponseEntity.created(uri).body(pessoaCadastrada);
    }

    @PutMapping
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa) {
        Pessoa pessoaCad = service.update(pessoa);
        return ResponseEntity.ok().body(pessoaCad);
    }
}
