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
    public List<Pessoa> findAll() {return repository.findAll();}

    @Override
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
    public PessoaDTO update(Long id, PessoaAtualizarDTO pessoaAtualizarDTO) {
        Pessoa pessoa = repository.getReferenceById(id);
        pessoa.setNome(pessoaAtualizarDTO.getNome());
        pessoa.setDataNascimento(pessoaAtualizarDTO.getDataNascimento());
        pessoa.setEnderecos(pessoaAtualizarDTO.getEnderecos().stream().map(Endereco::new).collect(Collectors.toList()));
        pessoa = repository.save(pessoa);
        return new PessoaDTO(pessoa);
    }
}
