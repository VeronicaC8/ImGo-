package com.example.veronica.imgo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
        Button btn_bares = (Button) view.findViewById(R.id.btn_bares);
        Button btn_parques = (Button) view.findViewById(R.id.btn_parques);
        Button btn_entretenimiento = (Button) view.findViewById(R.id.btn_entretenimiento);
       //RESTAURANTE
    btn_restaurante.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                  Intent in = new  Intent(getActivity(), categoriaRestaurantesActivity.class);
                  in.putExtra("some", "some date");
                  startActivity(in);
        }
    });
     //BARES

        btn_bares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new  Intent(getActivity(), categoriaBaresActivity.class);
                in.putExtra("some", "some date");
                startActivity(in);
            }
        });
     //PARQUES
        btn_parques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new  Intent(getActivity(), categoriaParquesActivity.class);
                in.putExtra("some", "some date");
                startActivity(in);
            }
        });

        //ENTRETENIMIENTO
        btn_entretenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new  Intent(getActivity(), categoriaEntretenimientoActivity.class);
                in.putExtra("some", "some date");
                startActivity(in);
            }
        });


       return view;

    }

}

