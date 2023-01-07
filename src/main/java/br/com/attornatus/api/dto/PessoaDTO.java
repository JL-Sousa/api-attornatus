package br.com.attornatus.api.dto;

import br.com.attornatus.api.entities.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PessoaDTO {

        private String nome;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataNascimento;

        private List<EnderecoDTO> enderecos = new ArrayList<EnderecoDTO>();

        public PessoaDTO() {}

        public PessoaDTO(Pessoa pessoa) {
                this.nome = pessoa.getNome();
                this.dataNascimento = pessoa.getDataNascimento();
                this.enderecos.addAll(pessoa.getEnderecos().stream().map(EnderecoDTO::new).collect(Collectors.toList()));

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

        public List<EnderecoDTO> getEnderecos() {
                return enderecos;
        }

        public void setEnderecos(List<EnderecoDTO> enderecos) {
                this.enderecos = enderecos;
        }
}

