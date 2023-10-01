package com.example.forcavendasapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bancodadosexemplo.helper.SQLiteDataHelper;
import com.example.forcavendasapp.model.Cliente;

import java.util.ArrayList;

public class ClienteDao implements GenericDao<Cliente> {
    //Abrir a Conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase bd;

    //Nome das Colunas na Tabela;
    private String[]colunas = {"CODIGO", "NOME", "CPF", "DTNASC", "CODENDERECO"};

    //Nome da Tabela;
    private String tableName = "CLIENTE";

    private Context context;

    private static ClienteDao instancia;

    /**
     * Método que CRIA a instância uma única vez
     * Toda vez que for necessário utilizar essa classe
     * Irá retornar sempre a mesma instância*/

    public static ClienteDao getInstance(Context context){
        if (instancia == null)
            return instancia = new ClienteDao(context);
        else
            return instancia;

    }

    private ClienteDao(Context context){
        this.context = context;
        //Carregando Base de Dados
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        // Atribuindo a BD a variável e dando permissão para escrever nas tabelas
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Cliente obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("NOME", obj.getNome());
            valores.put("CPF", obj.getCpf());
            valores.put("DTNASC", obj.getDtNasc());
            valores.put("CODENDERECO", obj.getCodEndereco());

            //Método para inserir na tabela(nome da tabela, coluna especifica que queira inserir, dados
            //Retorna a linha que foi inserida na tabela
            return bd.insert(tableName, null, valores);
        }catch (SQLException e){
            Log.e("insert", e.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Cliente obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());

            //Identificar para o Where
            String[]identificador = {String.valueOf(obj.getCodigo())};

            //Método para atualizar
            //<nome da tabela>, <valores> , <clausula where>, <valor do where>
            return bd.update(tableName, valores, "CODIGO = ?", identificador);

        }catch (SQLException e){
            Log.e("ERROR", "ClienteDao.inser(): " + e.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(Cliente obj) {
        try {
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);
        }catch (SQLException ex){
            Log.e("ERRO", "ClienteDao.delete(): " + ex.getMessage());
        }
        return -1 ;
    }

    @Override
    public ArrayList<Cliente> getAll() {
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            Cursor cursor = bd.query(tableName, colunas, null,
                    null,null,null, "CODIGO asc");

            if (cursor.moveToFirst()){
                do{
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(cursor.getInt(0));
                    cliente.setNome(cursor.getString(1));
                    cliente.setCpf(cursor.getString(2));
                    cliente.setDtNasc(cursor.getString(3));
                    cliente.setCodEndereco(cursor.getString(4));

                    lista.add(cliente);
                }while (cursor.moveToNext());
            }
        }catch (SQLException ex) {
            Log.e("ERRO", "ClienteDao.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Cliente getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas, "CODIGO = ?",
                    identificador, null, null, null);
            if (cursor.moveToFirst()){
                Cliente cliente = new Cliente();
                cliente.setCodigo(cursor.getInt(0));
                cliente.setNome(cursor.getString(1));
                cliente.setCpf(cursor.getString(2));
                cliente.setDtNasc(cursor.getString(3));
                cliente.setCodEndereco(cursor.getString(4));

                return cliente;
            }
        }catch (SQLException ex) {
            Log.e("ERRO", "ClienteDao.getId(): " + ex.getMessage());
        }
        return null;
    }
}
