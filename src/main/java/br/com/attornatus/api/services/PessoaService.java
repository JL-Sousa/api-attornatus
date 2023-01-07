package br.com.attornatus.api.services;

import br.com.attornatus.api.dto.PessoaCadastradaDTO;
import br.com.attornatus.api.dto.PessoaDTO;
import br.com.attornatus.api.entities.Pessoa;

import java.util.List;

public interface PessoaService {

    List<Pessoa> findAll();

    PessoaDTO findById(Long id);

    PessoaCadastradaDTO insert(PessoaDTO pessoaDTO);
}
