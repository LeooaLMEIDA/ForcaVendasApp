//package com.example.forcavendasapp.controller;
//
//import android.content.Context;
//import com.example.forcavendasapp.dao.ItemDao;
//import com.example.forcavendasapp.model.Item;
//
//import java.util.ArrayList;
//
//public class ItemController {
//
//    private Context context;
//
//    public ItemController(Context context) {
//        this.context = context;
//    }
//
//    public long salvarItem(Item item) {
//        return ItemDao.getInstance(context).insert(item);
//    }
//
//    public long atualizaAluno(Item item) {
//        return ItemDao.getInstance(context).update(item);
//    }
//
//    public long apagarAluno(Item item) {
//        return ItemDao.getInstance(context).update(item);
//    }
//
//    public ArrayList<Item> retornarTodosItems() {
//        return ItemDao.getInstance(context).getAll();
//    }
//
//    public Item retornarItem(int codigo) {
//        return ItemDao.getInstance(context).getById(codigo);
//    }
//}
