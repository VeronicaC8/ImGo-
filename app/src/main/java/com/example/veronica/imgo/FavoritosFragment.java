package com.example.veronica.imgo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FavoritosFragment extends Fragment {

    public FavoritosFragment() {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_favoritos,container,false);
        // listViewFav= (ListView) v.findViewById(R.id.listViewFav);

        helper  =new ControlBD(getActivity());
        listaSitio=new ArrayList<>();
        recyclerSitio=view.findViewById(R.id.recyclerId);
        recyclerSitio.setLayoutManager(new LinearLayoutManager(getContext()));
        obtenerSitio();

        AdaptadorList adapter=new AdaptadorList(listaSitio);
        recyclerSitio.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                interfaceComunicaFragments.enviarSitio(listaSitio.get(recyclerSitio.getChildAdapterPosition(view)));
            }
        });
        // ArrayAdapter<String> adaptador = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,sitios);
        // listViewFav.setAdapter(adaptador);
        return view;
    }

    private List obtenerSitio() {

        String tabla = "sitio";
        helper.abrir();
        Sitio sitio = null;//CAMBIAR A LA ENTIDAD CORRESPONDIENTE

        //Lista = new ArrayList<>();

        Cursor cursor = helper.db.rawQuery("SELECT * FROM " +tabla, null);

        while (cursor.moveToNext()) {
            sitio = new Sitio();//CAMBIAR A LA ENTIDAD CORRESPONDIENTE
            sitio.setNombreSitio(cursor.getString(3));
            sitio.setDescripcion(cursor.getString(2));
            sitio.setDato(cursor.getString(7));
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


  /*  public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        helper  =new ControlBD(getActivity());

        sitios =new ArrayList<>();
        sitios=obtenerSitio();
        // Establecemos el Adapter a la Lista del Fragment
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, sitios));
    }
*/

    /*public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);

        // Mostramos un mensaje con el elemento pulsado
        Toast.makeText(getActivity(), "Ha pulsado " + sitios.get(position),
                Toast.LENGTH_SHORT).show();
    }*/

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    


}
