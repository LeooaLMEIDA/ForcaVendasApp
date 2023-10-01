package com.example.forcavendasapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Endereco;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText edCodigo;
    private EditText edNome;
    private EditText edCpf;
    private EditText edDataNascimento;
    private EditText edCodEndereco;
    private Button btSalvarCliente;
    private ClienteController clienteController;

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
        btSalvarCliente = findViewById(R.id.btSalvarCliente);

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

        if(!validacao.equals("")){
            if (validacao.contains("Nome")){
                edNome.setError(validacao);
            }
            if (validacao.contains("Cpf")){
                edCpf.setError(validacao);
            }
            if (validacao.contains("Data")) {
                edDataNascimento.setError(validacao);
            }
        }else{
            Endereco endereco = new Endereco("Rua Atilio de Bona", "234",
                    "Santa Clara 3","Toledo","PR");
            if(clienteController.salvarCliente(
                    edNome.getText().toString(),
                    edCpf.getText().toString(),
                    edDataNascimento.getText().toString(),
                    endereco
            ) > 0) {
                Toast.makeText(this,
                        "Cliente cadastrado com sucesso!",
                        Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,
                        "Erro ao cadastrar Aluno, verifique LOG.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}