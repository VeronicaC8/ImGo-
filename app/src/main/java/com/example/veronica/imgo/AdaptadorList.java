package com.example.veronica.imgo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.veronica.imgo.R;
import com.example.veronica.imgo.Sitio;

/**
 * Created by Oscar on 14/06/2018.
 */

public class AdaptadorList extends RecyclerView.Adapter<AdaptadorList.PersonajesViewHolder> implements View.OnClickListener {

    ArrayList<Sitio>listaSitio;
    private View.OnClickListener listener;

    public AdaptadorList(ArrayList<Sitio> listaSitio) {//listaPersonajes
        this.listaSitio = listaSitio;
    }

    @Override
    public PersonajesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layParams);

        view.setOnClickListener(this);

        return new PersonajesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonajesViewHolder holder, int position) {
        holder.txtNombre.setText(listaSitio.get(position).getNombreSitio());
        holder.txtInformacion.setText(listaSitio.get(position).getDescripcion());
       // holder.foto.setImageResource(listaSitio.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return listaSitio.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class PersonajesViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre,txtInformacion;
        ImageView foto;

        public PersonajesViewHolder(View itemView) {
            super(itemView);
            txtNombre= (TextView) itemView.findViewById(R.id.idNombre);
            if (Utilidades.PORTRAIT==true){
                txtInformacion= (TextView) itemView.findViewById(R.id.idInfo);
            }

            foto= (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
