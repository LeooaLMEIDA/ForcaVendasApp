package com.example.forcavendasapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.model.Endereco;

import java.util.ArrayList;

public class EnderecoListAdapter extends RecyclerView.Adapter<EnderecoListAdapter.ViewHolder> {

    private ArrayList<Endereco> listaEnderecos;
    private Context context;

    public EnderecoListAdapter(ArrayList<Endereco> listaEnderecos, Context context) {
        this.listaEnderecos = listaEnderecos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_list_endereco,parent,false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCodigo.setText(String.valueOf(listaEnderecos.get(position).getCodigo()));
        holder.tvLogradouro.setText(listaEnderecos.get(position).getLogradouro());
        holder.tvNumero.setText(String.valueOf(listaEnderecos.get(position).getNumero()));
//        holder.tvBairro.setText(String.valueOf(listaEnderecos.get(position).getBairro()));
//        holder.tvCidade.setText(String.valueOf(listaEnderecos.get(position).getCidade()));
//        holder.tvUf.setText(String.valueOf(listaEnderecos.get(position).getUf()));
    }

    @Override
    public int getItemCount() {
        return this.listaEnderecos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvCodigo;
        public TextView tvLogradouro;
        public TextView tvNumero;
//        public TextView tvBairro;
//        public TextView tvCidade;
//        public TextView tvUf;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);
            this.tvLogradouro = itemView.findViewById(R.id.tvLogradouro);
            this.tvNumero = itemView.findViewById(R.id.tvNumero);

        }
    }

}
