package com.example.veronica.imgo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class sitioInsertActivity extends AppCompatActivity {

    ControlBD helper;

    EditText editIdSitio;
    EditText editDescripcion;
    EditText editIdCategoria;
    EditText editNombreSitio;
    EditText editPrecioMin;
    EditText editPrecioMax;
    EditText editDireccion;
    EditText editLatitud;
    EditText editLongitud;

    Button btnFoto;
    ImageView imgFoto;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitio_insert);
        helper = new ControlBD(this);
        editIdSitio = (EditText) findViewById(R.id.editIdSitio);
        editIdCategoria = (EditText) findViewById(R.id.editIdCategoria);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);
        editNombreSitio = (EditText) findViewById(R.id.editNombreSitio);
        editPrecioMin = (EditText) findViewById(R.id.editPrecioMin);
        editPrecioMax = (EditText) findViewById(R.id.editPrecioMax);
        editDireccion = (EditText) findViewById(R.id.editDireccion);
        editLatitud =(EditText) findViewById(R.id.editLatitud);
        editLongitud = (EditText) findViewById(R.id.editLongitud);

        imgFoto=(ImageView) findViewById(R.id.imgFoto);
        btnFoto=(Button)findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoOpciones();
            }
        });
    }


    public void insertarSitio(View v) {

            Integer idSitio = Integer.valueOf(editIdSitio.getText().toString());
            Integer idCategoria = Integer.valueOf(editIdCategoria.getText().toString());

            String descripcion = editDescripcion.getText().toString();
            String nombreSitio = editNombreSitio.getText().toString();
            Integer precioMin = Integer.valueOf(editPrecioMin.getText().toString());
            Integer precioMax = Integer.valueOf(editPrecioMax.getText().toString());
            String direccion = editDireccion.getText().toString();
            Float latitud = Float.valueOf(editLatitud.getText().toString());
            Float longitud = Float.valueOf(editLongitud.getText().toString());
            String regInsertados;
            String regInsertadoUbicacion;


            Sitio sitio = new Sitio();
            sitio.setIdSitio(idSitio);
            sitio.setIdCategoria(idCategoria);
            sitio.setDescripcion(descripcion);
            sitio.setNombreSitio(nombreSitio);
            sitio.setPrecioMax(precioMax);
            sitio.setPrecioMin(precioMin);

            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setIdUbicacion(idSitio);
            ubicacion.setIdSitio(idSitio);
            ubicacion.setDireccion(direccion);
            ubicacion.setCoordenadaX(longitud);
            ubicacion.setCoordenadaY(latitud);

            helper.abrir();
            regInsertados = helper.insertar(sitio);
            regInsertadoUbicacion = helper.insertar(ubicacion);

            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, regInsertadoUbicacion, Toast.LENGTH_SHORT).show();

     //       editIdSitio.setText("");
     //       editIdCategoria.setText("");
     //       editDescripcion.setText("");
     //       editNombreSitio.setText("");
     //       editPrecioMax.setText("");
     //       editPrecioMin.setText("");
     //       editDireccion.setText("");
     //       editLatitud.setText("");
     //       editLongitud.setText("");

    }

    private void cargarImagen(){
        Intent intent =new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione una aplicacion"),10);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK){
            Uri path=data.getData();
            imgFoto.setImageURI(path);
        }
    }

    private void mostrarDialogoOpciones(){
        final CharSequence[] opciones={"Tomar Foto", "Elegir de Galeria","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Elegir una Opcion: ");

        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    //   abrirCamara();
                    Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                }else {
                    if (opciones[i].equals("Elegir de Galeria")){
                        cargarImagen();
                    }else {
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

  //  file.mkdirs();


}
