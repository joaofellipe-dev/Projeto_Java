package com.fatec.sigvs.model;

public record ClienteDTO(String cpf, String nome, String cep, String endereco, String bairro, String cidade,
        String complemento, String email) {

}
