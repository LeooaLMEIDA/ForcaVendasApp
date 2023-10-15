package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.ItemController;

public class CadastroItemActivity extends AppCompatActivity {
    private EditText edCodigo;
    private EditText edDescricao;
    private EditText edVlrUnit;
    private EditText edUnMedida;
    private Button btSalvarItem;
    private ItemController itemController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);

        edCodigo = findViewById(R.id.edCodigo);
        edDescricao = findViewById(R.id.edDescricao);
        edVlrUnit = findViewById(R.id.edVlrUnit);
        edUnMedida = findViewById(R.id.edUnMedida);
        btSalvarItem = findViewById(R.id.btSalvarItem);

        itemController = new ItemController(this);

        btSalvarItem.setOnClickListener(view -> salvarItem());
    }

    private void salvarItem() {
        String validacao = itemController.validaItem(
                edCodigo.getText().toString(),
                edDescricao.getText().toString(),
                edVlrUnit.getText().toString(),
                edUnMedida.getText().toString()
        );

        if (!validacao.equals("")) {
            if (validacao.contains("Código")) {
                edCodigo.setError(validacao);
            }
            if (validacao.contains("Descrição")) {
                edCodigo.setError(validacao);
            }
            if (validacao.contains("Valor Unitário")) {
                edCodigo.setError(validacao);
            }
            if (validacao.contains("Unidade de Medida")) {
                edCodigo.setError(validacao);
            }
        } else {
            if (itemController.salvarItem(
                    Integer.parseInt(edCodigo.getText().toString()),
                    edDescricao.getText().toString(),
                    Double.parseDouble(edVlrUnit.getText().toString()),
                    edUnMedida.getText().toString()) > 0) {
                Toast.makeText(this,
                        "Item cadastrado com sucesso!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,
                        "Erro ao cadastrar Item, verifique o LOG.",
                        Toast.LENGTH_LONG).show();
            }
            voltarTelaListagem();
        }

    }
    private void voltarTelaListagem() {
        Intent intent = new Intent(CadastroItemActivity.this, ListaItemActivity.class);
        startActivity(intent);
    }
}