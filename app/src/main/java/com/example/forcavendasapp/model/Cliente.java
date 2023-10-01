package com.example.forcavendasapp.model;

public class Cliente {

    private int codigo;
    private String nome;
    private String cpf;
    private String dtNasc;
    private String codEndereco;

    public Cliente() {
    }

    public Cliente(int codigo, String nome, String cpf, String dtNasc, String codEndereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.codEndereco = codEndereco;
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

    public String getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(String codEndereco) {
        this.codEndereco = codEndereco;
    }
}
