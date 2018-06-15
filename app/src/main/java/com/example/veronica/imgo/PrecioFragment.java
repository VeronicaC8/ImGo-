package com.example.veronica.imgo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

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

    public PrecioFragment() {
        // Required empty public constructor
    }
    private DetalleSitioFragment.OnFragmentInteractionListener mListener;
    ControlBD helper;

    List sitios;

    ArrayList<String> Lista;
    ArrayList<Sitio> listaSitio;
    RecyclerView recyclerSitio;
    Activity activity;
    IComunicaFragment interfaceComunicaFragments;

    Context context;
    TextView editPrecio, editNombreSitio;
    Button btnPrecio;
    //Bloud

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_precio,container,false);
        // listViewFav= (ListView) v.findViewById(R.id.listViewFav);

        helper  =new ControlBD(getActivity());
        listaSitio=new ArrayList<>();
        recyclerSitio=view.findViewById(R.id.recyclerId);
        recyclerSitio.setLayoutManager(new LinearLayoutManager(getContext()));
        editPrecio=(EditText) view.findViewById(R.id.editPrecio);

       // obtenerSitio(editPrecio.getText().toString());

        btnPrecio= view.findViewById(R.id.botonPrecio);
        btnPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Presionado",Toast.LENGTH_LONG).show();

                AdaptadorList adapter=new AdaptadorList((ArrayList<Sitio>) obtenerSitio(editPrecio.getText().toString()));
                recyclerSitio.setAdapter(adapter);
                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        interfaceComunicaFragments.enviarSitio(listaSitio.get(recyclerSitio.getChildAdapterPosition(view)));
                    }
                });
            }
        });
        return view;
    }

    private List obtenerSitio(String precioDeseado) {
        helper.abrir();

        String[]camposSitio=new String[]{"idSitio","idCategoria","descripcion","nombreSitio","precioMax","precioMin"};
        String[] precioDe={precioDeseado,precioDeseado};
        Sitio sitio=null;
        Cursor cursor=helper.db.query("sitio",camposSitio,"precioMin <= ? AND precioMax >= ? ",precioDe,null,null,null);
        while (cursor.moveToNext()){
                 sitio=new Sitio();
            sitio.setNombreSitio(cursor.getString(3));
            listaSitio.add(sitio);
        }

        return (listaSitio);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Activity){
            this.activity= (Activity) context;
            interfaceComunicaFragments= (IComunicaFragment) this.activity;
        }

        if (context instanceof DetalleSitioFragment.OnFragmentInteractionListener) {
            mListener = (DetalleSitioFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

/*
    ControlBD helper;
    private View vista;
    Context context;
    ListView listaSitioPrecio;
    ArrayList<Sitio> Lista;
    TextView editPrecio, editNombreSitio;
    Button btnPrecio;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        helper = new ControlBD(getActivity());
        vista =inflater.inflate(R.layout.fragment_precio,container,false);

        context=getActivity().getApplication().getApplicationContext();

        editPrecio=(EditText) vista.findViewById(R.id.editPrecio);

        listaSitioPrecio=(ListView) vista.findViewById(R.id.lst_precio);
        //editNombreSitio=(TextView) vista.findViewById(R.id.editNombreSitio);
        Lista=new ArrayList<Sitio>();


        btnPrecio= vista.findViewById(R.id.botonPrecio);
        btnPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Presionado",Toast.LENGTH_LONG).show();
                Lista=helper.getSitioPrecio(editPrecio.getText().toString());

              //  AdaptadorP miAdaptador=new AdaptadorP(getApplicationContext(),Lista);

                ArrayAdapter<Sitio> adapterFinal= new ArrayAdapter<Sitio>(context, android.R.layout.simple_spinner_item, Lista);
                listaSitioPrecio.setAdapter(adapterFinal);
            }
        });

        return vista;
    }
*/

}
