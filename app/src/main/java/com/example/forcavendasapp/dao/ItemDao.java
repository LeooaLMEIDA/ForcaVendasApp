package com.example.forcavendasapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.forcavendasapp.model.Item;

import java.util.ArrayList;

public class ItemDao implements GenericDao<Item>{
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase bd;

    //Nome das Colunas na Tabela;
    private String[]colunas = {"CODIGO", "DESCRICAO", "VLRUNIT", "UNMEDIDA"};

    //Nome da Tabela;
    private String tableName = "ITEM";

    private Context context;

    private static ItemDao instancia;

    /**
     * Método que CRIA a instância uma única vez
     * Toda vez que for necessário utilizar essa classe
     * Irá retornar sempre a mesma instância*/

    public static ItemDao getInstance(Context context){
        if (instancia == null)
            return instancia = new ItemDao(context);
        else
            return instancia;

    }

    private ItemDao(Context context){
        this.context = context;
        //Carregando Base de Dados
        openHelper = new com.example.bancodadosexemplo.helper.SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        // Atribuindo a BD a variável e dando permissão para escrever nas tabelas
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Item obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("DESCRICAO", obj.getDescricao());
            valores.put("VLUNIT", obj.getVlUnit());
            valores.put("UNMEDIDA", obj.getUnMedida());

            //Método para inserir na tabela(nome da tabela, coluna especifica que queira inserir, dados
            //Retorna a linha que foi inserida na tabela
            return bd.insert(tableName, null, valores);
        }catch (SQLException e){
            Log.e("insert", e.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Item obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());

            //Identificar para o Where
            String[]identificador = {String.valueOf(obj.getCodigo())};

            //Método para atualizar
            //<nome da tabela>, <valores> , <clausula where>, <valor do where>
            return bd.update(tableName, valores, "CODIGO = ?", identificador);

        }catch (SQLException e){
            Log.e("ERROR", "ItemDao.inser(): " + e.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(Item obj) {
        try {
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);
        }catch (SQLException ex){
            Log.e("ERRO", "ItemDao.delete(): " + ex.getMessage());
        }
        return -1 ;
    }

    @Override
    public ArrayList<Item> getAll() {
        ArrayList<Item> lista = new ArrayList<>();

        try {
            Cursor cursor = bd.query(tableName, colunas, null,
                    null,null,null, "CODIGO asc");

            if (cursor.moveToFirst()){
                do{
                    Item item = new Item();
                    item.setCodigo(cursor.getInt(0));
                    item.setDescricao(cursor.getString(1));
                    item.setVlUnit(cursor.getDouble(2));
                    item.setUnMedida(cursor.getString(3));

                    lista.add(item);
                }while (cursor.moveToNext());
            }
        }catch (SQLException ex) {
            Log.e("ERRO", "ItemDao.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Item getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas, "CODIGO = ?",
                    identificador, null, null, null);
            if (cursor.moveToFirst()){
                Item item = new Item();
                item.setCodigo(cursor.getInt(0));
                item.setDescricao(cursor.getString(1));
                item.setVlUnit(cursor.getDouble(2));
                item.setUnMedida(cursor.getString(3));
                return item;
            }
        }catch (SQLException ex) {
            Log.e("ERRO", "ItemDao.getId(): " + ex.getMessage());
        }
        return null;
    }
}
