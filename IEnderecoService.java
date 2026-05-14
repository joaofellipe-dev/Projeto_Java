package com.fatec.sigvs.servico;

import java.util.Optional;

import com.fatec.sigvs.model.Endereco;

public interface IEnderecoService {
    public Optional<Endereco> obtemLogradouroPorCep(String cep);

}
