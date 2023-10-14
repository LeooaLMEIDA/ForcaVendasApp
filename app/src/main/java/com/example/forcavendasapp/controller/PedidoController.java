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

    public ArrayList<PedidoVenda> retornarTodosItens() {
        return PedidoVendaDao.getInstance(context).getAll();
    }

    public PedidoVenda retornarPedido(int codigo) {
        return PedidoVendaDao.getInstance(context).getById(codigo);
    }
    //*****PAREI AQUI
    public String validaPedido(String codigo, String codCliente, String condPagto,
                               String qtdParcela, String vlFrete, String qtdItens, String vlTotItens,
                               String vlTotPedido, String codEndereco) {
        StringBuilder mensagem = new StringBuilder();

        if (isEmpty(codigo)) {
            mensagem.append("O Código deve ser informado.\n");
        }else {
            int codigoPedido = Integer.parseInt(codigo);
            PedidoVenda itemExistente = PedidoVendaDao.getInstance(context).getById(codigoPedido);
            if (itemExistente != null) {
                mensagem.append("Já existe um Pedido com o Código informado.\n");
            }
        }
        if (isEmpty(descricao)) {
            mensagem.append("A Descrição deve ser informada.\n");
        }
        if (isEmpty(vlrUnit)) {
            mensagem.append("O Valor Unitário deve ser informado.\n");
        }
        if (isEmpty(unMedida)) {
            mensagem.append("A Unidade de Medida deve ser informada.\n");
        }

        return mensagem.toString();
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
