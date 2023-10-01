package com.example.forcavendasapp.controller;

import android.content.Context;
import com.example.forcavendasapp.dao.ClienteDao;
import com.example.forcavendasapp.model.Cliente;

import java.util.ArrayList;

public class ClienteController {
    private Context context;

    public ClienteController(Context context) {
        this.context = context;
    }

    public long salvarCliente(Cliente cliente) {
        return ClienteDao.getInstance(context).insert(cliente);
    }

    public long atualizaAluno(Cliente cliente) {
        return ClienteDao.getInstance(context).update(cliente);
    }

    public long apagarAluno(Cliente cliente) {
        return ClienteDao.getInstance(context).update(cliente);
    }

    public ArrayList<Cliente> retornarTodosClientes() {
        return ClienteDao.getInstance(context).getAll();
    }

    public Cliente retornarCliente(int codigo) {
        return ClienteDao.getInstance(context).getById(codigo);
    }

}
