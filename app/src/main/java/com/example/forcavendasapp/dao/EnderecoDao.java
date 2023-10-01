package com.example.forcavendasapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.forcavendasapp.helper.SQLiteDataHelper;
import com.example.forcavendasapp.model.Endereco;

import java.util.ArrayList;

public class EnderecoDao implements GenericDao<Endereco>{
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase bd;

    //Nome das Colunas na Tabela;
    private String[]colunas = {"CODIGO", "LOGRADOURO", "NUMERO", "BAIRRO", "CIDADE", "UF"};

    //Nome da Tabela;
    private String tableName = "ENDERECO";

    private Context context;

    private static EnderecoDao instancia;

    /**
     * Método que CRIA a instância uma única vez
     * Toda vez que for necessário utilizar essa classe
     * Irá retornar sempre a mesma instância*/

    public static EnderecoDao getInstance(Context context){
        if (instancia == null)
            return instancia = new EnderecoDao(context);
        else
            return instancia;

    }

    private EnderecoDao(Context context){
        this.context = context;
        //Carregando Base de Dados
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        // Atribuindo a BD a variável e dando permissão para escrever nas tabelas
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Endereco obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("LOGRADOURO", obj.getLogradouro());
            valores.put("NUMERO", obj.getNumero());
            valores.put("BAIRRO", obj.getBairro());
            valores.put("CIDADE", obj.getCidade());
            valores.put("UF", obj.getUf());

            //Método para inserir na tabela(nome da tabela, coluna especifica que queira inserir, dados
            //Retorna a linha que foi inserida na tabela
            return bd.insert(tableName, null, valores);
        }catch (SQLException e){
            Log.e("insert", e.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Endereco obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());

            //Identificar para o Where
            String[]identificador = {String.valueOf(obj.getCodigo())};

            //Método para atualizar
            //<nome da tabela>, <valores> , <clausula where>, <valor do where>
            return bd.update(tableName, valores, "CODIGO = ?", identificador);

        }catch (SQLException e){
            Log.e("ERROR", "EnderecoDao.inser(): " + e.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(Endereco obj) {
        try {
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);
        }catch (SQLException ex){
            Log.e("ERRO", "EnderecoDao.delete(): " + ex.getMessage());
        }
        return -1 ;
    }

    @Override
    public ArrayList<Endereco> getAll() {
        ArrayList<Endereco> lista = new ArrayList<>();

        try {
            Cursor cursor = bd.query(tableName, colunas, null,
                    null,null,null, "CODIGO asc");

            if (cursor.moveToFirst()){
                do{
                    Endereco endereco = new Endereco();
                    endereco.setCodigo(cursor.getInt(0));
                    endereco.setLogradouro(cursor.getString(1));
                    endereco.setNumero(cursor.getString(2));
                    endereco.setBairro(cursor.getString(3));
                    endereco.setCidade(cursor.getString(4));
                    endereco.setUf(cursor.getString(5));

                    lista.add(endereco);
                }while (cursor.moveToNext());
            }
        }catch (SQLException ex) {
            Log.e("ERRO", "EnderecoDao.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Endereco getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas, "CODIGO = ?",
                    identificador, null, null, null);
            if (cursor.moveToFirst()){
                Endereco endereco = new Endereco();
                endereco.setCodigo(cursor.getInt(0));
                endereco.setLogradouro(cursor.getString(1));
                endereco.setNumero(cursor.getString(2));
                endereco.setBairro(cursor.getString(3));
                endereco.setCidade(cursor.getString(4));
                endereco.setUf(cursor.getString(4));

                return endereco;
            }
        }catch (SQLException ex) {
            Log.e("ERRO", "AlunoDao.getId(): " + ex.getMessage());
        }
        return null;
    }
}
