package com.example.veronica.imgo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;


/*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoriaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoriaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriaFragment extends Fragment {

    public CategoriaFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


      View view =inflater.inflate(R.layout.fragment_categoria, container, false);
        Button btn_restaurante = (Button) view.findViewById(R.id.btn_restaurantes);
        Button btn_bar = (Button) view.findViewById(R.id.btn_bar);
        Button btn_parques = (Button) view.findViewById(R.id.btn_parques);
        Button btn_entretenimiento = (Button) view.findViewById(R.id.btn_entretenimiento);
        FloatingActionButton fabBoton1=(FloatingActionButton) view.findViewById(R.id.fabBoton1);
       //RESTAURANTE
    btn_restaurante.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                  Intent in = new  Intent(getActivity(), categoriaRestaurantesActivity.class);
                  in.putExtra("Categoria", "Restaurantes");
                  startActivity(in);
        }
    });
     //BAR

        btn_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new  Intent(getActivity(), categoriaBaresActivity.class);
                in.putExtra("Categoria", "Bares");
                startActivity(in);
            }
        });
     //PARQUES
        btn_parques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new  Intent(getActivity(), categoriaParquesActivity.class);
                in.putExtra("Categoria", "Parques");
                startActivity(in);
            }
        });

        //ENTRETENIMIENTO
        btn_entretenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new  Intent(getActivity(), categoriaEntretenimientoActivity.class);
                in.putExtra("Categoria", "Entretenimiento");
                startActivity(in);
            }
        });
        fabBoton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),Main2Activity.class);
                startActivity(i);
            }
        });


       return view;

    }

}

