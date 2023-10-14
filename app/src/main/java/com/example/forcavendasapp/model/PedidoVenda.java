package com.example.forcavendasapp.model;

public class PedidoVenda {

    private int codigo;
    private int codCliente;
    private String condPagto;
    private int qtdParcela;
    private double vlFrete;
    private int qtdItens;
    private double vlTotItens;
    private double vlTotPedido;
    private int codEndereco;

    public PedidoVenda() {
    }

    public PedidoVenda(int codigo, int codCliente, String condPagto,
                       int qtdParcela, double vlFrete, int qtdItens, double vlTotItens,
                       double vlTotPedido, int codEndereco) {
        this.codigo = codigo;
        this.codCliente = codCliente;
        this.condPagto = condPagto;
        this.qtdParcela = qtdParcela;
        this.vlFrete = vlFrete;
        this.qtdItens = qtdItens;
        this.vlTotItens = vlTotItens;
        this.vlTotPedido = vlTotPedido;
        this.codEndereco = codEndereco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public double getVlFrete() {
        return vlFrete;
    }

    public void setVlFrete(double vlFrete) {
        this.vlFrete = vlFrete;
    }

    public int getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(int qtdItens) {
        this.qtdItens = qtdItens;
    }

    public String getCondPagto() {
        return condPagto;
    }

    public void setCondPagto(String condPagto) {
        this.condPagto = condPagto;
    }

    public int getQtdParcela() {
        return qtdParcela;
    }

    public void setQtdParcela(int qtdParcela) {
        this.qtdParcela = qtdParcela;
    }

    public double getVlTotItens() {
        return vlTotItens;
    }

    public void setVlTotItens(double vlTotItens) {
        this.vlTotItens = vlTotItens;
    }

    public double getVlTotPedido() {
        return vlTotPedido;
    }

    public void setVlTotPedido(double vlTotPedido) {
        this.vlTotPedido = vlTotPedido;
    }

    public int getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }
}
