package com.example.forcavendasapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.forcavendasapp.helper.SQLiteDataHelper;
import com.example.forcavendasapp.model.Item;

import java.util.ArrayList;

public class ItemDao implements GenericDao<Item>{
    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    private String[]colunas = {"CODIGO", "DESCRICAO", "VL_UNIT", "UN_MEDIDA"};

    private String tableName = "ITEM";

    private Context context;

    private static ItemDao instancia;

    public static ItemDao getInstance(Context context){
        if (instancia == null)
            return instancia = new ItemDao(context);
        else
            return instancia;

    }

    private ItemDao(Context context){
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Item obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("DESCRICAO", obj.getDescricao());
            valores.put("VL_UNIT", obj.getVlUnit());
            valores.put("UN_MEDIDA", obj.getUnMedida());

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

            String[]identificador = {String.valueOf(obj.getCodigo())};

            return bd.update(tableName, valores, "CODIGO = ?", identificador);

        }catch (SQLException e){
            Log.e("ERROR", "ItemDao.insert(): " + e.getMessage());
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
