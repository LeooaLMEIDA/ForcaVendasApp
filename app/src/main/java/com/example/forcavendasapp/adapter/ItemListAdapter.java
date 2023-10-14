package com.example.forcavendasapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.forcavendasapp.R;
import com.example.forcavendasapp.model.Item;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder>{

    private ArrayList<Item> listaItens;
    private Context context;

    public ItemListAdapter(ArrayList<Item> listaItens, Context context) {
        this.listaItens = listaItens;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_list_item,parent,false);
        return new ItemListAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.ViewHolder holder, int position) {
        holder.tvCodigo.setText(String.valueOf(listaItens.get(position).getCodigo()));
        holder.tvDescricao.setText(listaItens.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return this.listaItens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvCodigo;
        public TextView tvDescricao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);
            this.tvDescricao = itemView.findViewById(R.id.tvDescricao);

        }
    }

}
