package br.com.attornatus.api.services;

import br.com.attornatus.api.dto.PessoaCadastradaDTO;
import br.com.attornatus.api.dto.PessoaDTO;
import br.com.attornatus.api.entities.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaService {

    Page<PessoaDTO> findAll(Pageable pageable);

    PessoaDTO findById(Long id);

    PessoaCadastradaDTO insert(PessoaDTO pessoaDTO);

    Pessoa update(Pessoa Pessoa);
}
