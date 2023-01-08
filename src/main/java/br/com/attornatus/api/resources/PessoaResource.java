package br.com.attornatus.api.resources;

import br.com.attornatus.api.dto.PessoaAtualizarDTO;
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
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> pessoas = service.findAll();
        return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaAtualizarDTO pessoaAtualizarDTO) {
        PessoaDTO pessoaDTO = service.update(id, pessoaAtualizarDTO);
        return ResponseEntity.ok().body(pessoaDTO);
    }
}
