package br.com.attornatus.api.entities;

import br.com.attornatus.api.dto.PessoaAtualizarDTO;
import br.com.attornatus.api.dto.PessoaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    public Pessoa() {}

    public Pessoa(Long id, String nome, LocalDate dataNascimento, List<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
    }

    public Pessoa(PessoaDTO pessoaDTO) {
        this.nome = pessoaDTO.getNome();
        this.dataNascimento = pessoaDTO.getDataNascimento();
        this.enderecos.addAll(pessoaDTO.getEnderecos().stream().map(Endereco::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa pessoa = (Pessoa) o;

        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    public void atualizarDadosPessoa(PessoaAtualizarDTO pessoaAtualizarDTO) {
        this.nome = pessoaAtualizarDTO.getNome();
        this.dataNascimento = pessoaAtualizarDTO.getDataNascimento();
        this.enderecos.clear();
        this.enderecos = pessoaAtualizarDTO.getEnderecos().stream().map(Endereco::new).collect(Collectors.toList());
    }
}
