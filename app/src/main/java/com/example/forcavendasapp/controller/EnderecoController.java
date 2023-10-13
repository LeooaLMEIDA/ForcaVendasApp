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

    public String validaEndereco(String codigo, String logradouro, String numero,
                                 String bairro, String cidade, String uf) {
        StringBuilder mensagem = new StringBuilder();

        if (isEmpty(codigo)) {
            mensagem.append("O Código deve ser informado.\n");
        }else {
            int codigoEndereco = Integer.parseInt(codigo);
            Endereco enderecoExistente = EnderecoDao.getInstance(context).getById(codigoEndereco);
            if (enderecoExistente != null) {
                mensagem.append("Já existe um endereço com o Código informado.\n");
            }
        }
        if (isEmpty(logradouro)) {
            mensagem.append("O Logradouro deve ser informado.\n");
        }
        if (isEmpty(numero)) {
            mensagem.append("O Número deve ser informado.\n");
        }
        if (isEmpty(bairro)) {
            mensagem.append("O Bairro deve ser informado.\n");
        }
        if (isEmpty(cidade)) {
            mensagem.append("A Cidade deve ser informada.\n");
        }
        if (isEmpty(uf)) {
            mensagem.append("A UF deve ser informada.\n");
        }

        return mensagem.toString();
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
