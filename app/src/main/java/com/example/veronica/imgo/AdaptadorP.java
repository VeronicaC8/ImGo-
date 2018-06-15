package com.example.veronica.imgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorP extends BaseAdapter{
    Context context;
    List<Sitio>ListaObjetos;
    public AdaptadorP(Context context,List<Sitio> listaObjetos){
        this.context=context;
        ListaObjetos=listaObjetos;
    }

    @Override
    public int getCount() {
        return ListaObjetos.size();
    }

    @Override
    public Object getItem(int position) {
        return ListaObjetos.get(position).getIdSitio();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vistai=convertView;
        LayoutInflater inflater =LayoutInflater.from(context);
        vistai=inflater.inflate(R.layout.list_item_precio,null);

        ImageView imagen=(ImageView) vistai.findViewById(R.id.imageView);
        TextView titulo=(TextView) vistai.findViewById(R.id.editNombreSitio);

        titulo.setText(ListaObjetos.get(position).getNombreSitio());
       // imagen.setImageResource(ListaObjetos.get(position).getImagen());
        return null;
    }
}
