package br.com.attornatus.api.dto;

import br.com.attornatus.api.entities.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PessoaDTO {

        private Long id;

        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataNascimento;

        @NotNull
        @Valid
        private List<EnderecoDTO> enderecos = new ArrayList<EnderecoDTO>();

        public PessoaDTO() {}

        public PessoaDTO(Pessoa pessoa) {
                this.id = pessoa.getId();
                this.nome = pessoa.getNome();
                this.dataNascimento = pessoa.getDataNascimento();
                this.enderecos.addAll(pessoa.getEnderecos().stream().map(EnderecoDTO::new).collect(Collectors.toList()));

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

        public List<EnderecoDTO> getEnderecos() {
                return enderecos;
        }

        public void setEnderecos(List<EnderecoDTO> enderecos) {
                this.enderecos = enderecos;
        }

}

