package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.forcavendasapp.R;

public class CadastroCliente extends AppCompatActivity {

    private EditText edCodigo;
    private EditText edNome;
    private EditText edCpf;
    private EditText edDataNascimento;
    private EditText edCodEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        edCodigo.findViewById(R.id.edCodigo);
        edNome.findViewById(R.id.edNome);
        edCpf.findViewById(R.id.edCpf);
        edDataNascimento.findViewById(R.id.edDataNascimento);
        edCodEndereco.findViewById(R.id.edCodEndereco);

    }
}