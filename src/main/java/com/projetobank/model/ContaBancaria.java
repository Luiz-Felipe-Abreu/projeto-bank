package com.projetobank.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titular;
    private String cpf;
    private double saldo;
    private boolean ativa = true;

    

    private String numeroConta;  // Número da conta
    private String agencia;  // Agência da conta

    @Temporal(TemporalType.DATE)
    private LocalDate dataAbertura;  // Data de abertura da conta

    @Enumerated(EnumType.STRING)
    private TipoConta tipo;  // Tipo da conta (Corrente, Poupança, Salário)

    public ContaBancaria(Long id, String numeroConta, String agencia, String titular, String cpf, LocalDate dataAbertura, double saldo, boolean ativa, String tipo) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.titular = titular;
        this.cpf = cpf;
        this.dataAbertura = dataAbertura;
        this.saldo = saldo;
        this.ativa = ativa;
        this.tipo = TipoConta.valueOf(tipo);
    }
    
    // Construtor vazio (necessário para o JPA)
    public ContaBancaria() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }
}
