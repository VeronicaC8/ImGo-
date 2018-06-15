package com.example.veronica.imgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class sitioInsertActivity extends AppCompatActivity {

    ControlBD helper;

    EditText editIdSitio;
    EditText editDescripcion;
    EditText editIdUsuario;
    EditText editNombreSitio;
    EditText editPrecioMin;
    EditText editPrecioMax;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitio_insert);
        helper = new ControlBD(this);
        editIdSitio = (EditText) findViewById(R.id.editIdSitio);
        editIdUsuario = (EditText) findViewById(R.id.editIdUsuario);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);
        editNombreSitio = (EditText) findViewById(R.id.editNombreSitio);
        editPrecioMin = (EditText) findViewById(R.id.editPrecioMin);
        editPrecioMax = (EditText) findViewById(R.id.editPrecioMin);
    }
    public void insertarSitio(View v) {
        Integer idSitio =Integer.valueOf(editIdSitio.getText().toString());
        Integer idUsuario =Integer.valueOf(editIdUsuario.getText().toString());
        String descripcion =editDescripcion.getText().toString();
        String nombreSitio =editNombreSitio.getText().toString();
        Integer precioMin =Integer.valueOf(editPrecioMin.getText().toString());
        Integer precioMax =Integer.valueOf(editPrecioMax.getText().toString());
        String regInsertados;
                Sitio sitio=new Sitio();
                sitio.setIdSitio(idSitio);
                sitio.setIdUsuario(idUsuario);
                sitio.setDescripcion(descripcion);
                sitio.setNombreSitio(nombreSitio);
                sitio.setPrecioMax(precioMax);
                sitio.setPrecioMin(precioMin);
                helper.abrir();
                regInsertados=helper.insertar(sitio);
                helper.cerrar();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdSitio.setText("");
        editDescripcion.setText("");
        editNombreSitio.setText("");
        editPrecioMax.setText("");
        editPrecioMin.setText("");
    } }
