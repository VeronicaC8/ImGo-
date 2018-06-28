package com.example.veronica.imgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class sitioListActivity extends AppCompatActivity {

    ListView lv;
    List Lista;
    ArrayAdapter adaptador;
    private ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitio_list);
        lv = (ListView) findViewById(R.id.ListSitios);
        lv = (ListView) findViewById(R.id.descripcion);
        ControlBD db = new ControlBD(getApplicationContext());
        Lista = db.llenar_sitios();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Lista);
        lv.setAdapter(adaptador);


    }
    }
