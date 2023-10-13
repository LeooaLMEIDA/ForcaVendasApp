package com.example.forcavendasapp.controller;

import android.content.Context;

import com.example.forcavendasapp.dao.ClienteDao;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Endereco;

import java.util.ArrayList;

public class ClienteController {
    private Context context;

    public ClienteController(Context context) {
        this.context = context;
    }

    public long salvarCliente(String nome, String cpf, String dtNasc, Endereco endereco) {
        Cliente cliente = new Cliente(nome, cpf, dtNasc, endereco);
        return ClienteDao.getInstance(context).insert(cliente);
    }

    public long atualizaAluno(String nome, String cpf, String dtNasc, Endereco endereco) {
        Cliente cliente = new Cliente(nome, cpf, dtNasc, endereco);
        return ClienteDao.getInstance(context).update(cliente);
    }

    public long apagarAluno(Cliente cliente) {
        return ClienteDao.getInstance(context).update(cliente);
    }

    public ArrayList<Cliente> retornarTodosClientes() {
        return ClienteDao.getInstance(context).getAll();
    }

    public Cliente retornarCliente(String cpf) {
        return ClienteDao.getInstance(context).getByCpf(cpf);
    }

    public String validaCliente(String nome, String cpf, String dtNasc) {
        StringBuilder mensagem = new StringBuilder();
        if (isEmpty(nome)) {
            mensagem.append("Nome do cliente deve ser informado.\n");
        }
        if (isEmpty(cpf)) {
            mensagem.append("CPF do cliente deve ser informado.\n");
        }
        if (isEmpty(dtNasc)) {
            mensagem.append("Data de Nascimento do cliente deve ser informado.\n");
        }
        return mensagem.toString();
    }


    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
