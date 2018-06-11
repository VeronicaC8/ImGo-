package com.example.veronica.imgo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class FavoritosFragment extends Fragment {

    public FavoritosFragment() {
        // Required empty public constructor
    }

    ListView listViewFav;
    ArrayList<String> Lista;
    ArrayAdapter adaptador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_favoritos,container,false);
        listViewFav= (ListView) v.findViewById(R.id.listViewFav);

        /*Hacer coneccion a la BD*/

        return inflater.inflate(R.layout.fragment_favoritos, null);
    }


}
