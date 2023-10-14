package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.adapter.ItemListAdapter;
import com.example.forcavendasapp.controller.ItemController;
import com.example.forcavendasapp.controller.PedidoController;
import com.example.forcavendasapp.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaPedidoActivity extends AppCompatActivity {

    private PedidoController itemController;
    private RecyclerView rvItens;
    private FloatingActionButton btNovoItem;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_item);

        itemController = new ItemController(this);
        rvItens = findViewById(R.id.rvItens);
        btNovoItem = findViewById(R.id.btNovoItem);
        btNovoItem.setOnClickListener(view -> abrirCadastroItem());

        atualizarListaItens();

    }

    private void abrirCadastroItem() {
        Intent intent = new Intent(ListaItemActivity.this,
                CadastroItemActivity.class);
        startActivity(intent);
    }

    private void atualizarListaItens() {
        ArrayList<Item> listaItens = itemController.retornarTodosItens();
        ItemListAdapter adapter = new ItemListAdapter(listaItens, this);
        rvItens.setLayoutManager(new LinearLayoutManager(this));
        rvItens.setAdapter(adapter);
    }
}