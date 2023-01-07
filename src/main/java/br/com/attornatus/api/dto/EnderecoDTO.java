package br.com.attornatus.api.dto;

import br.com.attornatus.api.entities.Endereco;

public record EnderecoDTO(
        Long id,
        String logradouro,
        String cep,
        long numero,
        String cidade,
        boolean enderecoPrincipal) {

    public EnderecoDTO(Endereco endereco) {
        this(endereco.getId(), endereco.getLogradouro(), endereco.getCep(), endereco.getNumero(), endereco.getCidade(), endereco.isEnderecoPrincipal());
    }
}
