package com.example.veronica.imgo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    int idUsuarioPrueba=1;
    ControlBD BDhelper;

    //Declaracion de botones
    private ActionBar toolbar;
   FavoritosFragment listaSitiosF;
  // DetalleSitioFragment detalleSitio;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(idUsuarioPrueba==0){
            setContentView(R.layout.activity_main);
        }else {
            setContentView(R.layout.activity_administrador);
        }

        toolbar=getSupportActionBar();

        BottomNavigationView navigationView=(BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("CATEGORIA");
        loadFragment(new CategoriaFragment());


        //Cargar Base de datos
        BDhelper=new ControlBD(this);
        BDhelper.abrir();
        String tost=BDhelper.llenarBD();
        BDhelper.cerrar();
        Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_categoria:
                    toolbar.setTitle("CATEGORIA");
                    fragment = new CategoriaFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_favorito:
                    toolbar.setTitle("FAVORITOS");
                    listaSitiosF=new FavoritosFragment();
                    loadFragment(listaSitiosF);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,listaSitiosF).commit();
                    return true;

                case R.id.navigation_precio:
                    toolbar.setTitle("PRECIO");
                    fragment = new PrecioFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_ubicacion:
                    toolbar.setTitle("UBICACION");
                    fragment = new UbicacionFragment();
                    loadFragment(fragment);
                    return  true;
                case R.id.navigation_admin:
                    toolbar.setTitle("ADMINISTRAR");
                    fragment = new AdministrarFragment();
                    loadFragment(fragment);
                    return  true;
            }
            return false;
        }
    };


    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container , fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }



}
