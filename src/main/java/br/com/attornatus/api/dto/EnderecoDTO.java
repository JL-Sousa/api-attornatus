package br.com.attornatus.api.dto;

import br.com.attornatus.api.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(

        Long id,

        @NotBlank
        String logradouro,

        @NotBlank
        @Pattern(regexp = "\\d{8}", message = "Endereco deve conter 8 digitos!")
        String cep,

        long numero,

        @NotBlank
        String cidade,

        boolean enderecoPrincipal) {

    public EnderecoDTO(Endereco endereco) {
        this(endereco.getId(), endereco.getLogradouro(), endereco.getCep(), endereco.getNumero(), endereco.getCidade(), endereco.isEnderecoPrincipal());
    }
}
