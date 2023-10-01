package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.adapter.EnderecoListAdapter;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.model.Endereco;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaEnderecoActivity extends AppCompatActivity {

    private EnderecoController enderecoController;
    private RecyclerView rvEnderecos;
    private FloatingActionButton btNovoEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_endereco);

        enderecoController = new EnderecoController(this);
        rvEnderecos = findViewById(R.id.rvEnderecos);
        btNovoEndereco = findViewById(R.id.btNovoEndereco);
        btNovoEndereco.setOnClickListener(view -> abrirCadastroEndereco());

        atualizarListaEnderecos();

    }

    private void abrirCadastroEndereco() {
        Intent intent = new Intent(ListaEnderecoActivity.this,
                CadastroEnderecoActivity.class);
        startActivity(intent);
    }

    private void atualizarListaEnderecos() {
        ArrayList<Endereco> listaEnderecos = enderecoController.retornarTodosEnderecos();
        EnderecoListAdapter adapter = new EnderecoListAdapter(listaEnderecos, this);
        rvEnderecos.setLayoutManager(new LinearLayoutManager(this));
        rvEnderecos.setAdapter(adapter);
    }
}