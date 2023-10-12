package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.forcavendasapp.R;

public class MainActivity extends AppCompatActivity {

    private Button btCadastrarCliente;
    private Button btCadastrarEndereco;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        btCadastrarCliente.setOnClickListener(view -> abrirActivityCliente());

        btCadastrarEndereco = findViewById(R.id.btCadastrarEndereco);
        btCadastrarEndereco.setOnClickListener(view -> abrirActivityEndereco());

    }

    public void abrirActivityCliente(){
        Intent intent = new Intent(MainActivity.this,
                ListaClienteActivity.class);

        startActivity(intent);
    }

    public void abrirActivityEndereco(){
        Intent intent = new Intent(MainActivity.this,
                ListaEnderecoActivity.class);

        startActivity(intent);
    }

}