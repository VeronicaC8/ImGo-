package com.example.veronica.imgo;

import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class sitioDeleteActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdSitio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitio_delete);
        helper=new ControlBD(this);
        editIdSitio=(EditText) findViewById(R.id.editIdSitio);
    }

    public void eliminarSitio(View v){
        String regEliminar;
        String regEliminarU;
        Sitio sitio=new Sitio();
        Ubicacion ubicacion=new Ubicacion();
        sitio.setIdSitio(Integer.valueOf(editIdSitio.getText().toString()));
        ubicacion.setIdUbicacion(Integer.valueOf(editIdSitio.getText().toString()));
        helper.abrir();
        regEliminar=helper.eliminar(sitio);
        regEliminarU=helper.eliminar(ubicacion);
        helper.cerrar();
        Toast.makeText(this, regEliminar, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, regEliminarU, Toast.LENGTH_SHORT).show();
    }

    public void bsc(View v){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
}