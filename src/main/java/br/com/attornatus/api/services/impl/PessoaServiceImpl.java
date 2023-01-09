package br.com.attornatus.api.services.impl;

import br.com.attornatus.api.dto.PessoaCadastradaDTO;
import br.com.attornatus.api.dto.PessoaDTO;
import br.com.attornatus.api.entities.Pessoa;
import br.com.attornatus.api.repository.PessoaRepository;
import br.com.attornatus.api.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<PessoaDTO> findAll(Pageable pageable) {
        Page<Pessoa> pessoas = repository.findAll(pageable);
        return pessoas.map(x -> new PessoaDTO(x));
    }

    @Override
    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        Pessoa pessoa = repository.findById(id).get();
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
