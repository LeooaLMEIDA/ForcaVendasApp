package com.example.forcavendasapp.controller;

import android.content.Context;

import com.example.forcavendasapp.dao.EnderecoDao;
import com.example.forcavendasapp.dao.EnderecoDao;
import com.example.forcavendasapp.model.Endereco;
import com.example.forcavendasapp.model.Endereco;

import java.util.ArrayList;

public class EnderecoController {
    private Context context;

    public EnderecoController(Context context) {
        this.context = context;
    }

    public long salvarEndereco(int codigo, String logradouro, String numero,
                               String bairro, String cidade, String uf) {
        Endereco endereco = new Endereco(codigo,logradouro, numero, bairro, cidade, uf);
        return EnderecoDao.getInstance(context).insert(endereco);
    }

    public long atualizaAluno(int codigo, String logradouro, String numero,
                              String bairro, String cidade, String uf) {
        Endereco endereco = new Endereco(codigo, logradouro, numero, bairro, cidade, uf);
        return EnderecoDao.getInstance(context).update(endereco);
    }

    public long apagarAluno(Endereco endereco) {
        return EnderecoDao.getInstance(context).update(endereco);
    }

    public ArrayList<Endereco> retornarTodosEnderecos() {
        return EnderecoDao.getInstance(context).getAll();
    }

    public Endereco retornarEndereco(int codigo) {
        return EnderecoDao.getInstance(context).getById(codigo);
    }

    public String validaEndereco(int codigo, String logradouro, String numero,
                                 String bairro, String cidade, String uf) {
        String mensagem = "";

        if(logradouro == null || logradouro.isEmpty()){
            mensagem += "O Logradouro deve ser informado.\n";
        }else if(numero == null || numero.isEmpty()) {
            mensagem += "O Número deve ser informado.\n";
        }else if(bairro == null || bairro.isEmpty()) {
            mensagem += "O Bairro deve ser informado.\n";
        }else if(cidade == null || cidade.isEmpty()) {
            mensagem += "A Cidade deve ser informado.\n";
        }else if(uf == null || uf.isEmpty()) {
            mensagem += "A UF deve ser informado.\n";
        }

        return mensagem;
    }
}
