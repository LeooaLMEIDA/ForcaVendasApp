package com.example.forcavendasapp.controller;

import android.content.Context;

import com.example.forcavendasapp.dao.PedidoVendaDao;
import com.example.forcavendasapp.model.PedidoVenda;

import java.util.ArrayList;

public class PedidoController {

    private Context context;

    public PedidoController(Context context) {
        this.context = context;
    }

    public long salvarPedido(int codigo, String descricao, double vlrUnit, String unMedida) {
        PedidoVenda pedidoVenda = new PedidoVenda();
        return PedidoVendaDao.getInstance(context).insert(pedidoVenda);
    }

    public long atualizaPedido(PedidoVenda item) {
        return PedidoVendaDao.getInstance(context).update(item);
    }

    public long apagarPedido(PedidoVenda item) {
        return PedidoVendaDao.getInstance(context).update(item);
    }

    public ArrayList<PedidoVenda> retornarTodosPedidos() {
        return PedidoVendaDao.getInstance(context).getAll();
    }

    public PedidoVenda retornarPedido(int codigo) {
        return PedidoVendaDao.getInstance(context).getById(codigo);
    }

    public String validaPedido(String codigo, String codCliente, String condPagto,
                               String qtdParcela, String vlFrete, String qtdItens, String vlTotItens,
                               String vlTotPedido, String codEndereco) {
        StringBuilder mensagem = new StringBuilder();

        if (isEmpty(codigo)) {
            mensagem.append("O Código deve ser informado.\n");
        } else {
            int codigoPedido = Integer.parseInt(codigo);
            PedidoVenda pedidoExistente = PedidoVendaDao.getInstance(context).getById(codigoPedido);
            if (pedidoExistente != null) {
                mensagem.append("Já existe um Pedido com o Código informado.\n");
            }
        }

        if (isEmpty(codCliente)) {
            mensagem.append("O Código do Cliente deve ser informado.\n");
        }

        if (isEmpty(condPagto)) {
            mensagem.append("A Condição de Pagamento deve ser informada.\n");
        }

        if (isEmpty(qtdParcela)) {
            mensagem.append("A Quantidade de Parcelas deve ser informada.\n");
        }

        if (isEmpty(vlFrete)) {
            mensagem.append("O Valor do Frete deve ser informado.\n");
        }

        if (isEmpty(qtdItens)) {
            mensagem.append("A Quantidade de Itens deve ser informada.\n");
        }

        if (isEmpty(vlTotItens)) {
            mensagem.append("O Valor Total dos Itens deve ser informado.\n");
        }

        if (isEmpty(vlTotPedido)) {
            mensagem.append("O Valor Total do Pedido deve ser informado.\n");
        }

        if (isEmpty(codEndereco)) {
            mensagem.append("O Código do Endereço deve ser informado.\n");
        }

        return mensagem.toString();
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

}
