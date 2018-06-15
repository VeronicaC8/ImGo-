package com.example.veronica.imgo;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class FavoritosFragment extends ListFragment {

    public FavoritosFragment() {
        // Required empty public constructor
    }

    ControlBD helper;

    List sitios;
    ListView listViewFav;
    ArrayList<String> Lista;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_favoritos,container,false);
        //ListView lista= (ListView) v.findViewById(R.id.listViewFav);
       // helper  =new ControlBD(getActivity());

        //sitios =new ArrayList<>();
       // sitios=obtenerSitio();
       // ArrayAdapter<String> adaptador = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,sitios);
      // listViewFav.setAdapter(adaptador);
        return inflater.inflate(R.layout.fragment_favoritos, null);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        helper  =new ControlBD(getActivity());

        sitios =new ArrayList<>();
        sitios=obtenerSitio();

       // ArrayAdapter<Sitio>adapter=new ItemFavAdapter();

        // Establecemos el Adapter a la Lista del Fragment
    // setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, sitios));
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, sitios));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);

        // Mostramos un mensaje con el elemento pulsado
        Toast.makeText(getActivity(), "Ha pulsado " + sitios.get(position),
                Toast.LENGTH_SHORT).show();
    }

   /* public class ItemFavAdapter extends ArrayAdapter<Sitio> {

        public ItemFavAdapter(){
            super(getActivity(),R.layout.item_fav,sitios);
        }

        public View getView(int posicion,View view, ViewGroup parent){

            View itemview=view;
            if(itemview==null)
                itemview=getLayoutInflater().inflate(R.layout.item_fav,parent,false);
            //LayoutInflater inflater=getActivity().getLayoutInflater();
           // View rowView=inflater.inflate(R.layout.item_fav,null,true);

            Sitio CurrentSitio= (Sitio) sitios.get(posicion);

            ImageView imageView = (ImageView) itemview.findViewById(R.id.imageFav);
            imageView.setImageResource((int) CurrentSitio.getPrecioMax());
            TextView txtTitle = (TextView) itemview.findViewById(R.id.sitio_text);
            txtTitle.setText(CurrentSitio.getNombreSitio());
            TextView txtDesc = (TextView) itemview.findViewById(R.id.text_desc);
            txtDesc.setText(CurrentSitio.getDescripcion());


            return itemview;
        }



    }*/
    private List obtenerSitio() {

        String tabla = "categoria";
        helper.abrir();
        Categoria sitio = null;//CAMBIAR A LA ENTIDAD CORRESPONDIENTE

            Lista = new ArrayList<>();

            Cursor cursor = helper.db.rawQuery("SELECT * FROM " +tabla, null);

            while (cursor.moveToNext()) {
                sitio = new Categoria();//CAMBIAR A LA ENTIDAD CORRESPONDIENTE
               sitio.setIdCategoria(cursor.getInt(0));
               sitio.setNombreCategoria(cursor.getString(1));
                Lista.add(cursor.getString(1)+"-"+cursor.getString(1));

            }

        return (Lista);
    }


}
