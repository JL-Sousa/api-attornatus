package br.com.attornatus.api.services.impl;

import br.com.attornatus.api.dto.PessoaAtualizarDTO;
import br.com.attornatus.api.dto.PessoaCadastradaDTO;
import br.com.attornatus.api.dto.PessoaDTO;
import br.com.attornatus.api.entities.Endereco;
import br.com.attornatus.api.entities.Pessoa;
import br.com.attornatus.api.repository.PessoaRepository;
import br.com.attornatus.api.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<PessoaDTO> findAll() {

        List<Pessoa> pessoas = repository.findAll();
        return pessoas.stream().map(PessoaDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        Pessoa pessoa = repository.getReferenceById(id);
        return new PessoaDTO(pessoa);
    }

    @Override
    @Transactional
    public PessoaCadastradaDTO insert(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa(pessoaDTO);
        pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
        repository.save(pessoa);
        return new PessoaCadastradaDTO(pessoa.getId(), pessoa.getNome());
    }

    @Override
    @Transactional
    public Pessoa update(Pessoa pessoa) {

        pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
        Pessoa usuarioSalvo = repository.save(pessoa);
        return usuarioSalvo;
    }
}
