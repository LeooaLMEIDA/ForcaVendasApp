package com.example.forcavendasapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.forcavendasapp.R;
import com.example.forcavendasapp.model.PedidoVenda;
import java.util.ArrayList;

public class PedidoListAdapter extends RecyclerView.Adapter<PedidoListAdapter.ViewHolder>{
    private ArrayList<PedidoVenda> listaPedidos;
    private Context context;

    public PedidoListAdapter(ArrayList<PedidoVenda> listaPedidos, Context context) {
        this.listaPedidos = listaPedidos;
        this.context = context;
    }

    @NonNull
    @Override
    public PedidoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listPedidoVenda = layoutInflater.inflate(R.layout.item_list_pedido_venda,parent,false);
        return new PedidoListAdapter.ViewHolder(listPedidoVenda);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoListAdapter.ViewHolder holder, int position) {
        holder.tvCodigo.setText(String.valueOf(listaPedidos.get(position).getCodigo()));
        holder.tvCodCliente.setText(listaPedidos.get(position).getCodCliente());
    }

    @Override
    public int getItemCount() {
        return this.listaPedidos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvCodigo;
        public TextView tvCodCliente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);
            this.tvCodCliente = itemView.findViewById(R.id.tvCodCliente);

        }
    }
}
