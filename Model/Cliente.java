package com.fatec.sigvs.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String cpf;
    private String nome;
    private String cep;
    private String endereco;
    private String bairro;
    private String cidade;
    private String complemento;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataCadastro;

    public Cliente() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String c) {
        if (c == null || c.isBlank())
            throw new IllegalArgumentException("CPF invalido");
        else {
            ValidaCpf vCpf = new ValidaCpf(c);
            if (!vCpf.isValido())
                throw new IllegalArgumentException("CPF invalido");
            else
                this.cpf = c;
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("O nome não deve estar em branco");
        else
            this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    /*
     * a api viacep trata dados invalidos
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        if (complemento == null || complemento.isBlank())
            throw new IllegalArgumentException("O complemento não deve estar em branco");
        else
            this.complemento = complemento;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro() {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAtual.format(pattern);
        this.dataCadastro = dataFormatada;

    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, cpf, email, nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(cep, other.cep) && Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
                && Objects.equals(nome, other.nome);
    }

}