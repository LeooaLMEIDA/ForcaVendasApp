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

    public long atualizaAluno(Cliente cliente) {
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

    public String validaCliente(String nome, String cpf, String dtNasc){
        String mensagem = "";
        if(nome == null || nome.isEmpty()){
            mensagem += "Nome do cliente deve ser informado.\n";
        }else if(cpf == null || cpf.isEmpty()) {
            mensagem += "CPF do cliente deve ser informado.\n";
        }else if(dtNasc == null || dtNasc.isEmpty()) {
            mensagem += "Data de Nascimento do cliente deve ser informado.\n";
        }
        return mensagem;
    }


}
