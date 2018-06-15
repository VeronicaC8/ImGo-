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
public class AdministrarFragment extends Fragment {

    public AdministrarFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_administrar, container, false);
        Button insert_button = (Button) view.findViewById(R.id.insert_button);
        Button edit_button = (Button) view.findViewById(R.id.edit_button);
        Button delete_button = (Button) view.findViewById(R.id.delete_button);
        Button list_button = (Button) view.findViewById(R.id.list_button);
        //insertar
        insert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new  Intent(getActivity(), sitioInsertActivity.class);
                in.putExtra("some", "some date");
                startActivity(in);
            }
        });
        //editar

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new  Intent(getActivity(), sitioEditActivity.class);
                in.putExtra("some", "some date");
                startActivity(in);
            }
        });
        //Peliminar
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new  Intent(getActivity(), sitioDeleteActivity.class);
                in.putExtra("some", "some date");
                startActivity(in);
            }
        });

        //lista
        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new  Intent(getActivity(), sitioListActivity.class);
                in.putExtra("some", "some date");
                startActivity(in);
            }
        });


        return view;

    }

}

