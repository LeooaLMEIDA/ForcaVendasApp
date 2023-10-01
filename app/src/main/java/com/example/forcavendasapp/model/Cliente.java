package com.example.forcavendasapp.model;

public class Cliente {

    private int codigo;
    private String nome;
    private String cpf;
    private String dtNasc;
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String dtNasc, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.endereco = endereco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public Endereco getCodEndereco() {
        return endereco;
    }

    public void setCodEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
