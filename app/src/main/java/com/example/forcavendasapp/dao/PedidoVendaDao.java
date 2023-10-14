package com.example.forcavendasapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.forcavendasapp.helper.SQLiteDataHelper;
import com.example.forcavendasapp.model.PedidoVenda;
import com.example.forcavendasapp.model.PedidoVenda;

import java.util.ArrayList;

public class PedidoVendaDao implements GenericDao<PedidoVenda> {

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    private String[]colunas = {"CODIGO", "COD_CLIENTE", "COND_PAGTO", "QTD_PARCELA, VL_FRETE," +
            "QTD_ITENS, VL_TOT_ITENS, VL_TOT_PEDIDO, COD_ENDERECO"};

    private String tableName = "PEDIDOVENDA";

    private Context context;

    private static PedidoVendaDao instancia;

    public static PedidoVendaDao getInstance(Context context){
        if (instancia == null)
            return instancia = new PedidoVendaDao(context);
        else
            return instancia;

    }

    private PedidoVendaDao(Context context){
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);
        bd = openHelper.getWritableDatabase();
    }
    
    @Override
    public long insert(PedidoVenda obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());
            valores.put("COD_CLIENTE", obj.getCodCliente());
            valores.put("COND_PAGTO", obj.getCondPagto());
            valores.put("QTD_PARCELA", obj.getQtdParcela());
            valores.put("VL_FRETE", obj.getVlFrete());
            valores.put("QTD_ITENS", obj.getQtdItens());
            valores.put("VL_TOT_ITENS", obj.getVlTotItens());
            valores.put("VL_TOT_PEDIDO", obj.getVlTotPedido());
            valores.put("COD_ENDERECO", obj.getCodEndereco());

            return bd.insert(tableName, null, valores);
        }catch (SQLException e){
            Log.e("insert", e.getMessage());
        }
        return -1;
    }

    @Override
    public long update(PedidoVenda obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("CODIGO", obj.getCodigo());

            String[]identificador = {String.valueOf(obj.getCodigo())};

            return bd.update(tableName, valores, "CODIGO = ?", identificador);

        }catch (SQLException e){
            Log.e("ERROR", "PedidoVendaDao.insert(): " + e.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(PedidoVenda obj) {
        try {
            String[]identificador = {String.valueOf(obj.getCodigo())};
            return bd.delete(tableName, "CODIGO = ?", identificador);
        }catch (SQLException ex){
            Log.e("ERRO", "PedidoVendaDao.delete(): " + ex.getMessage());
        }
        return -1 ;
    }

    @Override
    public ArrayList<PedidoVenda> getAll() {
        ArrayList<PedidoVenda> lista = new ArrayList<>();

        try {
            Cursor cursor = bd.query(tableName, colunas, null,
                    null,null,null, "CODIGO asc");

            if (cursor.moveToFirst()){
                do{
                    PedidoVenda PedidoVenda = new PedidoVenda();
                    PedidoVenda.setCodigo(cursor.getInt(0));
                    PedidoVenda.setCodCliente(cursor.getInt(1));
                    PedidoVenda.setCondPagto(cursor.getString(2));
                    PedidoVenda.setQtdParcela(cursor.getInt(3));
                    PedidoVenda.setVlFrete(cursor.getDouble(4));
                    PedidoVenda.setQtdItens(cursor.getInt(5));
                    PedidoVenda.setVlTotItens(cursor.getDouble(6));
                    PedidoVenda.setVlTotPedido(cursor.getDouble(7));
                    PedidoVenda.setCodEndereco(cursor.getInt(8));

                    lista.add(PedidoVenda);
                }while (cursor.moveToNext());
            }
        }catch (SQLException ex) {
            Log.e("ERRO", "PedidoVendaDao.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public PedidoVenda getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};

            Cursor cursor = bd.query(tableName, colunas, "CODIGO = ?",
                    identificador, null, null, null);
            if (cursor.moveToFirst()){
                PedidoVenda PedidoVenda = new PedidoVenda();
                PedidoVenda.setCodigo(cursor.getInt(0));
                PedidoVenda.setCodCliente(cursor.getInt(1));
                PedidoVenda.setCondPagto(cursor.getString(2));
                PedidoVenda.setQtdParcela(cursor.getInt(3));
                PedidoVenda.setVlFrete(cursor.getDouble(4));
                PedidoVenda.setQtdItens(cursor.getInt(5));
                PedidoVenda.setVlTotItens(cursor.getDouble(6));
                PedidoVenda.setVlTotPedido(cursor.getDouble(7));
                PedidoVenda.setCodEndereco(cursor.getInt(8));
                return PedidoVenda;
            }
        }catch (SQLException ex) {
            Log.e("ERRO", "PedidoVendaDao.getId(): " + ex.getMessage());
        }
        return null;
    }
}
