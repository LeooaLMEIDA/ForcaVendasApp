package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.forcavendasapp.R;

public class CadastroEnderecoActivity extends AppCompatActivity {

    private EditText edLogradouro;
    private EditText edNumero;
    private EditText edBairro;
    private EditText edCidade;
    private EditText edUf;
    private Button btSalvarEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        setTitle("Cadastrar Endereco");

        edLogradouro = findViewById(R.id.edLogradouro);
        edNumero = findViewById(R.id.edNumero);
        edBairro = findViewById(R.id.edBairro);
        edCidade = findViewById(R.id.edCidade);
        edUf = findViewById(R.id.edUf);
        btSalvarEndereco = findViewById(R.id.btSalvarEndereco);
        
        btSalvarEndereco.setOnClickListener(view -> salvarEndereco());

    }

    private void salvarEndereco() {
    }
}