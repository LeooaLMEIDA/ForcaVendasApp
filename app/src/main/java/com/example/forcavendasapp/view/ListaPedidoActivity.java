package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.adapter.ItemListAdapter;
import com.example.forcavendasapp.adapter.PedidoListAdapter;
import com.example.forcavendasapp.controller.ItemController;
import com.example.forcavendasapp.controller.PedidoController;
import com.example.forcavendasapp.model.Item;
import com.example.forcavendasapp.model.PedidoVenda;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaPedidoActivity extends AppCompatActivity {
    private PedidoController pedidoController;
    private RecyclerView rvPedidos;
    private FloatingActionButton btNovoPedido;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedido);

        pedidoController = new PedidoController(this);
        rvPedidos = findViewById(R.id.rvItens);
        btNovoPedido = findViewById(R.id.btNovoItem);
        btNovoPedido.setOnClickListener(view -> abrirCadastroPedidos());

        atualizarListaPedidos();

    }

    private void abrirCadastroPedidos() {
        Intent intent = new Intent(ListaPedidoActivity.this,
                CadastroPedidoVendaActivity.class);
        startActivity(intent);
    }

    private void atualizarListaPedidos() {
        ArrayList<PedidoVenda> listaPedidos = pedidoController.retornarTodosPedidos();
        PedidoListAdapter adapter = new PedidoListAdapter(listaPedidos, this);
        rvPedidos.setLayoutManager(new LinearLayoutManager(this));
        rvPedidos.setAdapter(adapter);
    }
}