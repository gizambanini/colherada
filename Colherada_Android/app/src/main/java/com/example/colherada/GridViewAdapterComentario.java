package com.example.colherada;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class GridViewAdapterComentario extends BaseAdapter {

    private List<Avaliacao> listaAvaliacao;
    private Context context;

    public GridViewAdapterComentario(Context context, List<Avaliacao> recebeParametroLista){
        this.listaAvaliacao = recebeParametroLista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaAvaliacao.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAvaliacao.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_gridview, parent,false);
        }

        TextView tvUserComentario = view.findViewById(R.id.tvUserComentario);
        TextView tvComentario = view.findViewById(R.id.tvComentario);
        ImageView imgComentario = view.findViewById(R.id.imgComentario);

        Avaliacao avaliacao = listaAvaliacao.get(position);

        tvUserComentario.setText(avaliacao.getNomeUser());
        tvComentario.setText(avaliacao.getComentario());

        if((avaliacao.getImagemUser() != null) && (avaliacao.getImagemUser().length()>0)){
            Picasso.get().load(avaliacao.getImagemUser()).into(imgComentario);
        }else{
            Toast.makeText(context, "Não carregou a imagem", Toast.LENGTH_LONG).show();
        }

        return view;
    }
}