package com.example.veronica.imgo;

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
    }   /*
    ControlBD helper;
    private View vista;
    List sitios;
    ArrayList<String> Lista;
    ArrayList<Sitio> listaSitio;
    RecyclerView recyclerSitio;

    Context context;
    ListView listaSitioPrecio;
    ArrayList<Sitio> Lista1;
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
        obtenerSitio();

        AdaptadorList adapter=new AdaptadorList(listaSitio);
        recyclerSitio.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View view){

                                       }
                                   }
        );
        // ArrayAdapter<String> adaptador = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,sitios);
        // listViewFav.setAdapter(adaptador);
        return view;
    }

    private List obtenerSitio(String precioDeseado) {

        String tabla = "sitio";
        helper.abrir();
        Sitio sitio = null;//CAMBIAR A LA ENTIDAD CORRESPONDIENTE

       //------ //Lista = new ArrayList<>();

     //   Cursor cursor = helper.db.rawQuery("SELECT * FROM " +tabla, null);

        while (cursor.moveToNext()) {
            sitio = new Sitio();//CAMBIAR A LA ENTIDAD CORRESPONDIENTE
            sitio.setNombreSitio(cursor.getString(0));
            sitio.setDescripcion(cursor.getString(1));

            listaSitio.add(sitio);
        }


        final String[]camposSitio=new String[]{"idSitio","idCategoria","descripcion","nombreSitio","precioMax","precioMin"};
        ArrayList<Sitio> listaSitioPrecio= new ArrayList<>();
        List listaSitioPre= new ArrayList();
        String[] precioDe={precioDeseado,precioDeseado};   //precioMax","precioMin
        helper.abrir();
        Cursor cursor=helper.db.query("sitio",camposSitio,"precioMin < ? AND precioMax > ? ",precioDe,null,null,null);
        //    Cursor cursor=db.query("sitio",camposSitio,"idSitio WHERE precioMin < ? AND precioMax > ? ",precioDe,null,null,null);
        while (cursor.moveToNext()){
            Sitio sitio=new Sitio();
            //   sitio.setIdSitio(cursor.getInt(0));
            //   sitio.setIdUsuario(cursor.getInt(1));
            //   sitio.setDescripcion(cursor.getString(2));
            sitio.setNombreSitio(cursor.getString(3));
            //   sitio.setPrecioMax(cursor.getFloat(4));
            //   sitio.setPrecioMin(cursor.getFloat(5));
            listaSitioPrecio.add(sitio);
        }

        return (listaSitio);
    }





*/








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




}
