package com.example.forcavendasapp.controller;

import android.content.Context;
import com.example.forcavendasapp.dao.ItemDao;
import com.example.forcavendasapp.model.Item;

import java.util.ArrayList;

public class ItemController {

    private Context context;

    public ItemController(Context context) {
        this.context = context;
    }

    public long salvarItem(int codigo, String descricao, double vlrUnit, String unMedida) {
        Item item = new Item(codigo,descricao,vlrUnit,unMedida);
        return ItemDao.getInstance(context).insert(item);
    }

    public long atualizaItem(Item item) {
        return ItemDao.getInstance(context).update(item);
    }

    public long apagarItem(Item item) {
        return ItemDao.getInstance(context).update(item);
    }

    public ArrayList<Item> retornarTodosItens() {
        return ItemDao.getInstance(context).getAll();
    }

    public Item retornarItem(int codigo) {
        return ItemDao.getInstance(context).getById(codigo);
    }

    public String validaItem(String codigo, String descricao, String vlrUnit, String unMedida) {
        StringBuilder mensagem = new StringBuilder();

        if (isEmpty(codigo)) {
            mensagem.append("O Código deve ser informado.\n");
        }else {
            int codigoItem = Integer.parseInt(codigo);
            Item itemExistente = ItemDao.getInstance(context).getById(codigoItem);
            if (itemExistente != null) {
                mensagem.append("Já existe um item com o Código informado.\n");
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
