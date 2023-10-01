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

    public long salvarEndereco(Endereco endereco) {
        return EnderecoDao.getInstance(context).insert(endereco);
    }

    public long atualizaAluno(Endereco endereco) {
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


}
