package com.example.veronica.imgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import android.widget.Toast;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class categoriaRestaurantesActivity extends AppCompatActivity {


     ListView lv;
     List Lista;
     ArrayAdapter adaptador;
    private ControlBD helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_restaurantes);
        lv=(ListView)findViewById(R.id.ListRestaurantes);
        lv=(ListView)findViewById(R.id.idInfo);
        ControlBD db= new ControlBD(getApplicationContext());
        Lista=db.llenar_restaurantes();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Lista);
        lv.setAdapter(adaptador);




    }
}
