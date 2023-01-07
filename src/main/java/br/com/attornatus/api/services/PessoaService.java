package br.com.attornatus.api.services;

import br.com.attornatus.api.dto.PessoaCadastradaDTO;
import br.com.attornatus.api.entities.Pessoa;

import java.util.List;

public interface PessoaService {

    List<Pessoa> findAll();

    Pessoa findById(Long id);

    PessoaCadastradaDTO insert(Pessoa pessoa);
}
