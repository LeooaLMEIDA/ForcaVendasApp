package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.controller.ItemController;
import com.example.forcavendasapp.controller.PedidoController;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CadastroPedidoVendaActivity extends AppCompatActivity {

    private EditText edCodigo;
    private Spinner spClientes;
    private Spinner spItens;
    private RadioGroup rgFormasPagamentos;
    private RadioButton rdAvista;
    private TextView tvQtdParcelas;
    private EditText edQuantidadeParcelas;
    private int qtdParcelas;
    private RadioButton rdParcelado;
    private EditText edValorFrete;
    private EditText edQuantidade;
    private EditText edValorTotal;
    private Button btSalvarPedidoVenda;
    private ListView lvItens;
    private PedidoController pedidoController;
    private ClienteController clienteController;
    private ItemController itemController;
    private int chaveSelecionada;
    private String condPagto;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido_venda);

        edCodigo = findViewById(R.id.edCodigo);
        spClientes = findViewById(R.id.spClientes);
        spItens = findViewById(R.id.spItens);
        rgFormasPagamentos = findViewById(R.id.rgFormasPagamentos);
        rdAvista = findViewById(R.id.rdAvista);
        tvQtdParcelas = findViewById(R.id.tvQtdParcelas);
        edQuantidadeParcelas = findViewById(R.id.edQuantidadeParcelas);
        rdParcelado = findViewById(R.id.rdParcelado);
        edValorFrete = findViewById(R.id.edValorFrete);
        edQuantidade = findViewById(R.id.edQuantidade);
        edValorTotal = findViewById(R.id.edValorTotal);
        btSalvarPedidoVenda = findViewById(R.id.btSalvarPedidoVenda);

        clienteController = new ClienteController(this);
        itemController = new ItemController(this);

        ArrayList<Cliente> listaClientes = clienteController.retornarTodosClientes();
        Map<Integer, String> mapaClientes = new HashMap<>();

        for (Cliente cliente : listaClientes) {
            mapaClientes.put(cliente.getCodigo(), cliente.getNome());
        }

        ArrayList<Item> listaItens = itemController.retornarTodosItens();
        Map<Integer, String> mapaItens = new HashMap<>();

        for (Item item : listaItens) {
            mapaItens.put(item.getCodigo(), item.getDescricao());
        }

        if (!mapaClientes.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, new ArrayList<>(mapaClientes.values()));

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spClientes.setAdapter(adapter);

            spClientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    chaveSelecionada = new ArrayList<>(mapaClientes.keySet()).get(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }

        if (!mapaItens.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, new ArrayList<>(mapaItens.values()));

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spItens.setAdapter(adapter);
            spItens.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    chaveSelecionada = new ArrayList<>(mapaItens.keySet()).get(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }

        rgFormasPagamentos.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rdAvista) {
                condPagto = "À vista";
                qtdParcelas = 0;
                tvQtdParcelas.setVisibility(View.GONE);
                edQuantidadeParcelas.setVisibility(View.GONE);

            } else if (checkedId == R.id.rdParcelado) {
                condPagto = "Parcelado";
                qtdParcelas = Integer.parseInt(edQuantidadeParcelas.getText().toString());
                tvQtdParcelas.setVisibility(View.VISIBLE);
                edQuantidadeParcelas.setVisibility(View.VISIBLE);
            }
        });

        btSalvarPedidoVenda.setOnClickListener(v -> salvarPedido());

    }

    private void salvarPedido() {
//        double vlrTotItens =
//                Integer.parseInt(edQuantidade.getText().toString())
//                *
//                ed;
//        String validacao = pedidoController.validaPedido(
//                edCodigo.getText(),
//                String.valueOf(chaveSelecionada),
//                condPagto,
//                String.valueOf(qtdParcelas),
//                edValorFrete.getText().toString(),
//                edQuantidade.getText().toString(),
//
//                edValorTotal.getText().toString(),
//
//                )
    }

    private void voltarTelaListagem() {
        Intent intent = new Intent(CadastroPedidoVendaActivity.this,
                ListaPedidoActivity.class);
        startActivity(intent);
    }
}