package com.fatec.sigvs.model;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataEmissao;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    @Column(nullable = false, length = 100)
    private String servicoContratado;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private boolean cancelada;

    @Column(nullable = false)
    private boolean paga;

    public Vendas() {
    }

    public Vendas(String cpf, LocalDate dataVencimento, String servicoContratado, String valor) {
        setCpf(cpf);
        setDataVencimento(dataVencimento);
        setServicoContratado(servicoContratado);
        setValor(valor);
        this.dataEmissao = LocalDate.now();
        setCancelada(false);
        setPaga(false);
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String c) {
        ValidaCpf vCpf = new ValidaCpf(c);

        if (c == null || c.isBlank() || !vCpf.isValido())
            throw new IllegalArgumentException("CPF invalido");
        else {
            this.cpf = c;
        }

    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        // 1. Validação atributo obrigatorio
        if (dataVencimento == null) {
            throw new IllegalArgumentException("Data de vencimento é obrigatória.");
        }

        // 2. Validação de Regra: Data Retroativa
        if (isAnteriorDataAtual(dataVencimento)) {
            throw new IllegalArgumentException("Data de vencimento não pode ser anterior à data atual.");
        }

        // 3. Validação de Regra: Fim de semana (Negócio)
        if (isDomingo(dataVencimento)) {
            throw new IllegalArgumentException("Data de vencimento não pode cair em um domingo.");
        }

        this.dataVencimento = dataVencimento;

    }

    public String getServicoContratado() {
        return servicoContratado;
    }

    public void setServicoContratado(String servico) {
        if (servico.isEmpty()) {
            throw new IllegalArgumentException("Descricao do servico invalido");
        } else {
            this.servicoContratado = servico;
        }
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(String v) {

        try {
            BigDecimal temp = new BigDecimal(v);

            // BigDecimal val = new BigDecimal(v.replace(",", "."));
            this.valor = temp;
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O valor da fatura deve ser maior que zero.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor da fatura invalido");
        }
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean c) {
        this.cancelada = c;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean p) {
        this.paga = p;
    }

    public boolean isDomingo(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isAnteriorDataAtual(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

}