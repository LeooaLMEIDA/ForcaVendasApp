package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;

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
    private EditText edVlrUnit;
    private EditText edUnMedida;
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
        edVlrUnit = findViewById(R.id.edVlrUnit);
        edUnMedida = findViewById(R.id.edUnMedida);
        btSalvarPedidoVenda = findViewById(R.id.btSalvarPedidoVenda);

        pedidoController = new PedidoController(this);
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
                    preencheCampos(chaveSelecionada);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }

        edQuantidade.setOnFocusChangeListener((view, b) -> calculaValorTotal());
        edValorFrete.setOnFocusChangeListener((view, b) -> calculaValorTotal());

        rgFormasPagamentos.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rdAvista) {
                condPagto = "À vista";
                qtdParcelas = 0;
                tvQtdParcelas.setVisibility(View.GONE);
                edQuantidadeParcelas.setVisibility(View.GONE);

            } else if (checkedId == R.id.rdParcelado) {
                condPagto = "Parcelado";
                if (!edQuantidadeParcelas.getText().toString().equals("")) {
                    qtdParcelas = Integer.parseInt(edQuantidadeParcelas.getText().toString());
                    if (qtdParcelas < 0) {
                        edQuantidadeParcelas.setError("Quantidade de Parcelas inválida");
                    }
                }
                tvQtdParcelas.setVisibility(View.VISIBLE);
                edQuantidadeParcelas.setVisibility(View.VISIBLE);
            }
        });

        calculaValorTotal();

        btSalvarPedidoVenda.setOnClickListener(v -> salvarPedido());

    }

    private void preencheCampos(int codigo) {
        itemController = new ItemController(this);
        Item item = itemController.retornarItem(codigo);
        if (item != null) {
            Log.d("PREENCHEENDERECO", "Resultado da validação: " + item);
            edVlrUnit.setText(String.valueOf(item.getVlUnit()));
            edUnMedida.setText(item.getUnMedida());
        }
    }

    private void calculaValorTotal() {
        String quantidadeText = edQuantidade.getText().toString();
        String vlrUnitText = edVlrUnit.getText().toString();
        String vlrFreteText = edValorFrete.getText().toString();

        if (!quantidadeText.isEmpty() && !vlrUnitText.isEmpty()) {
            int qtdItens = Integer.parseInt(quantidadeText);
            double vlrUnit = Double.parseDouble(vlrUnitText);
            double vlrFrete = 0;

            if (!vlrFreteText.isEmpty()) {
                vlrFrete = Double.parseDouble(vlrFreteText);
            }

            double vlrTot = qtdItens * vlrUnit;
            vlrTot += vlrFrete;

            edValorTotal.setText(String.valueOf(vlrTot));
        } else {
            edValorTotal.setText("0.0");
        }
    }

    private void salvarPedido() {
        String codEndereco = "";
        double totalItens = 0;
        totalItens = Double.parseDouble(edVlrUnit.getText().toString());
        totalItens = totalItens * Integer.parseInt(edQuantidade.getText().toString());

        Cliente cliente = clienteController.retornarCliente(chaveSelecionada);
        if (cliente != null) {
            codEndereco = cliente.getCodEndereco().toString();
        }

        String validacao = pedidoController.validaPedido(
                edCodigo.getText().toString(),
                String.valueOf(chaveSelecionada),
                condPagto,
                String.valueOf(qtdParcelas),
                edValorFrete.getText().toString(),
                edQuantidade.getText().toString(),
                String.valueOf(totalItens),
                edValorTotal.getText().toString(),
                codEndereco
                );

        if (!validacao.equals("")) {
            if (validacao.contains("Pedido") || validacao.contains("Código") ) {
                edCodigo.setError(validacao);
            }
            if (validacao.contains("Quantidade")) {
                edQuantidade.setError(validacao);
            }
            if (validacao.contains("Frete")) {
                edValorFrete.setError(validacao);
            }
        } else {
            if (pedidoController.salvarPedido(
                    Integer.parseInt(edCodigo.getText().toString()),
                    chaveSelecionada,
                    condPagto,
                    qtdParcelas,
                    Double.parseDouble(edValorFrete.getText().toString()),
                    Integer.parseInt(edQuantidade.getText().toString()),
                    totalItens,
                    Double.parseDouble(edValorTotal.getText().toString()),
                    Integer.parseInt(codEndereco)
                    ) > 0) {
                Toast.makeText(this,
                        "Pedido cadastrado com sucesso!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,
                        "Erro ao Pedido, verifique o LOG.",
                        Toast.LENGTH_LONG).show();
            }
            voltarTelaListagem();
        }

    }

    private void voltarTelaListagem() {
        Intent intent = new Intent(CadastroPedidoVendaActivity.this,
                ListaPedidoActivity.class);
        startActivity(intent);
    }
}