package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.example.forcavendasapp.R;
import com.example.forcavendasapp.adapter.ClienteListAdapter;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.model.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaClienteActivity extends AppCompatActivity {

    private ClienteController clienteController;
    private RecyclerView rvClientes;
    private FloatingActionButton btNovoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);

        clienteController = new ClienteController(this);
        rvClientes = findViewById(R.id.rvClientes);
        btNovoCliente = findViewById(R.id.btNovoCliente);
        btNovoCliente.setOnClickListener(view -> abrirCadastroCliente());

        atualizarListaClientes();

    }

    private void abrirCadastroCliente() {
        Intent intent = new Intent(ListaClienteActivity.this, CadastroClienteActivity.class);
        startActivity(intent);
    }

    private void atualizarListaClientes() {
        ArrayList<Cliente> listaClientes = clienteController.retornarTodosClientes();
        ClienteListAdapter adapter = new ClienteListAdapter(listaClientes,this);
        rvClientes.setLayoutManager(new LinearLayoutManager(this));
        rvClientes.setAdapter(adapter);
    }
}