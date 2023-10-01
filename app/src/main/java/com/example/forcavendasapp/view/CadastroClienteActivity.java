package com.example.forcavendasapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.model.Cliente;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText edCodigo;
    private EditText edNome;
    private EditText edCpf;
    private EditText edDataNascimento;
    private EditText edCodEndereco;
    private ClienteController clienteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        setTitle("Cadastrar Cliente");

        edNome = findViewById(R.id.edNome);
        edCpf = findViewById(R.id.edCpf);
        edDataNascimento = findViewById(R.id.edDataNascimento);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnSalvar) {
            salvarAluno();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarAluno() {
        Cliente clienteRetorno = clienteController.retornarCliente(edCpf.toString());
        String validacao = clienteController.validaCliente(clienteRetorno);

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
            if(clienteController.salvarCliente(clienteRetorno) > 0) {
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