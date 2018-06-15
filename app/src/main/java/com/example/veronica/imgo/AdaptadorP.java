package com.example.veronica.imgo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorP extends RecyclerView.Adapter<AdaptadorList.PersonajesViewHolder> implements View.OnClickListener {

    List<Sitio> listaSitio;
    private View.OnClickListener listener;

    public AdaptadorP(List<Sitio> listaSitio) {
        this.listaSitio = listaSitio;
    }

    @Override
    public AdaptadorList.PersonajesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_precio, null, false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layParams);

        view.setOnClickListener(this);

        return new AdaptadorList.PersonajesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorList.PersonajesViewHolder holder, int position) {
        holder.txtNombre.setText(listaSitio.get(position).getNombreSitio());
        // holder.foto.setImageResource(listaSitio.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return listaSitio.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public static class PersonajesViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtInformacion;
        ImageView foto;

        public PersonajesViewHolder(View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.editNombreSitio);
            /*if (Utilidades.PORTRAIT==true){
                txtInformacion= (TextView) itemView.findViewById(R.id.idInfo);
            }
            */

            foto = (ImageView) itemView.findViewById(R.id.imageView);
        }

    }
}
