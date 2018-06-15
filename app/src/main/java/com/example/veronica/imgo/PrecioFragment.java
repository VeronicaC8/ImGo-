package com.example.veronica.imgo;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PrecioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrecioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
                            //implements View.OnClickListener
public class PrecioFragment extends Fragment {

    ControlBD helper;
    private View vista;
    Context context;
    ListView listaSitioPrecio;
    ArrayList<Sitio> Lista;
    TextView editPrecio, editNombreSitio;
    Button btnPrecio;
    Sitio sitioSelected;
    //Bloud

    public PrecioFragment() {
        // Required empty public constructor
    }

 //   @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        helper = new ControlBD(getActivity());
        vista =inflater.inflate(R.layout.fragment_precio,container,false);

        context=getActivity().getApplication().getApplicationContext();
        listaSitioPrecio=(ListView) vista.findViewById(R.id.lst_precio);
        //editNombreSitio=(TextView) vista.findViewById(R.id.editNombreSitio);
        Lista=new ArrayList<Sitio>();

        Lista=helper.getSitioPrecio(Float.valueOf("5"));




        ArrayAdapter<Sitio> adapterFinal= new ArrayAdapter<Sitio>(context, android.R.layout.simple_spinner_item, Lista);
        listaSitioPrecio.setAdapter(adapterFinal);








        return vista;
    }



}
