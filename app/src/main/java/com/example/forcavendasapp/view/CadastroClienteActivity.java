package com.example.forcavendasapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Endereco;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText edCodigo;
    private EditText edNome;
    private EditText edCpf;
    private EditText edDataNascimento;
    private EditText edCodEndereco;
    private EditText edLogradouro;
    private EditText edNumero;
    private EditText edBairro;
    private EditText edCidade;
    private EditText edUf;
    private Button btSalvarCliente;
    private ClienteController clienteController;

    private EnderecoController enderecoController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        setTitle("Cadastrar Cliente");

        edNome = findViewById(R.id.edNome);
        edCpf = findViewById(R.id.edCpf);
        edDataNascimento = findViewById(R.id.edDataNascimento);
        edCodEndereco = findViewById(R.id.edCodEndereco);
        edLogradouro = findViewById(R.id.edLogradouro);
        edNumero = findViewById(R.id.edNumero);
        edBairro = findViewById(R.id.edBairro);
        edCidade = findViewById(R.id.edCidade);
        edUf = findViewById(R.id.edUf);

        btSalvarCliente = findViewById(R.id.btSalvarCliente);

        edCodEndereco.setOnFocusChangeListener((view, focoNoCampo) -> {
            String codEndereco = String.valueOf(edCodEndereco.getText());
            if (!focoNoCampo) {
                preencheEndereco(Integer.parseInt(codEndereco));
            }
        });

        btSalvarCliente.setOnClickListener(view -> salvarCliente());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnSalvar) {
            salvarCliente();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarCliente() {
        String validacao = clienteController.validaCliente(
                edNome.getText().toString(),
                edCpf.getText().toString(),
                edDataNascimento.getText().toString()
        );

        if (!validacao.equals("")) {
            if (validacao.contains("Nome")) {
                edNome.setError(validacao);
            }
            if (validacao.contains("Cpf")) {
                edCpf.setError(validacao);
            }
            if (validacao.contains("Data")) {
                edDataNascimento.setError(validacao);
            }
        } else {
            clienteController = new ClienteController(this);
            String codEndereco = String.valueOf(edCodEndereco.getText());
            String logradouro = String.valueOf(edLogradouro.getText());
            String numero = String.valueOf(edNumero.getText());
            String bairro = String.valueOf(edBairro.getText());
            String cidade = String.valueOf(edCidade.getText());
            String uf = String.valueOf(edUf.getText());

            Endereco endereco = new Endereco(Integer.parseInt(codEndereco), logradouro, numero,
                    bairro, cidade, uf);
            if (clienteController.salvarCliente(
                    edNome.getText().toString(),
                    edCpf.getText().toString(),
                    edDataNascimento.getText().toString(),
                    endereco) > 0) {
                Toast.makeText(this,
                        "Cliente cadastrado com sucesso!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,
                        "Erro ao cadastrar Aluno, verifique LOG.",
                        Toast.LENGTH_LONG).show();
            }
        }
        voltarTelaListagem();
    }

    private void voltarTelaListagem() {
        Intent intent = new Intent(CadastroClienteActivity.this, ListaClienteActivity.class);
        startActivity(intent);
    }

    private void preencheEndereco(int codigo) {
        enderecoController = new EnderecoController(this);
        Endereco endereco = enderecoController.retornarEndereco(codigo);
        edLogradouro.setText(endereco.getLogradouro());
        edNumero.setText(endereco.getNumero());
        edBairro.setText(endereco.getBairro());
        edCidade.setText(endereco.getCidade());
        edUf.setText(endereco.getUf());


    }
}