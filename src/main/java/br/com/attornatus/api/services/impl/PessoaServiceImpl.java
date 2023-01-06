package br.com.attornatus.api.services.impl;

import br.com.attornatus.api.entities.Pessoa;
import br.com.attornatus.api.repository.PessoaRepository;
import br.com.attornatus.api.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Override
    public List<Pessoa> findAll() {
        return repository.findAll();
    }
}
