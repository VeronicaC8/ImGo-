package com.example.veronica.imgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class categoriaBaresActivity extends AppCompatActivity {

    ListView lv;
    List Lista;
    ArrayAdapter adaptador;
    private ControlBD helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_bares);
        lv=(ListView)findViewById(R.id.ListRestaurantes);
        lv=(ListView)findViewById(R.id.idInfo);
        ControlBD db= new ControlBD(getApplicationContext());
        Lista=db.llenar_bares();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Lista);
        lv.setAdapter(adaptador);




    }
}
