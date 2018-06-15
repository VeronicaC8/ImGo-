package com.example.veronica.imgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class categoriaEntretenimientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_entretenimiento);
        Bundle bundle=  getIntent().getExtras();
        if (bundle!=null){
            if(bundle.getString("some") !=null){
                Toast.makeText(getApplicationContext(), "data:"+ bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }

        }
    }
}

