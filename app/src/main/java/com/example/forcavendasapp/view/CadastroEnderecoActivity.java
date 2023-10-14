package com.example.forcavendasapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.model.Endereco;

public class CadastroEnderecoActivity extends AppCompatActivity {

    private EditText edCodigo;
    private EditText edLogradouro;
    private EditText edNumero;
    private EditText edBairro;
    private EditText edCidade;
    private EditText edUf;
    private Button btSalvarEndereco;
    private EnderecoController enderecoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        setTitle("Cadastrar Endereco");

        enderecoController = new EnderecoController(this);

        edCodigo = findViewById(R.id.edCodigo);
        edLogradouro = findViewById(R.id.edLogradouro);
        edNumero = findViewById(R.id.edNumero);
        edBairro = findViewById(R.id.edBairro);
        edCidade = findViewById(R.id.edCidade);
        edUf = findViewById(R.id.edUf);
        btSalvarEndereco = findViewById(R.id.btSalvarEndereco);

        btSalvarEndereco.setOnClickListener(view -> salvarEndereco());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mnInflater = getMenuInflater();
        mnInflater.inflate(R.menu.menu_padrao, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnSalvar) {
            salvarEndereco();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarEndereco() {
        String validacao = enderecoController.validaEndereco(
                edCodigo.getText().toString(),
                edLogradouro.getText().toString(),
                edNumero.getText().toString(),
                edBairro.getText().toString(),
                edCidade.getText().toString(),
                edUf.getText().toString()
        );

        if (!validacao.equals("")) {
            if (validacao.contains("Código")) {
                edCodigo.setError(validacao);
            }
            if (validacao.contains("Logradouro")) {
                edLogradouro.setError(validacao);
            }
            if (validacao.contains("Número")) {
                edNumero.setError(validacao);
            }
            if (validacao.contains("Bairro")) {
                edBairro.setError(validacao);
            }
            if (validacao.contains("Cidade")) {
                edCidade.setError(validacao);
            }
            if (validacao.contains("Uf")) {
                edUf.setError(validacao);
            }
        } else {
            if (enderecoController.salvarEndereco(
                    Integer.parseInt(edCodigo.getText().toString()),
                    edLogradouro.getText().toString(),
                    edNumero.getText().toString(),
                    edBairro.getText().toString(),
                    edCidade.getText().toString(),
                    edUf.getText().toString()) > 0) {
                Toast.makeText(this,
                        "Endereco cadastrado com sucesso!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,
                        "Erro ao cadastrar Endereco, verifique o LOG.",
                        Toast.LENGTH_LONG).show();
            }
            voltarTelaListagem();
        }
    }

    private void voltarTelaListagem() {
        Intent intent = new Intent(CadastroEnderecoActivity.this, ListaEnderecoActivity.class);
        startActivity(intent);
    }

}




