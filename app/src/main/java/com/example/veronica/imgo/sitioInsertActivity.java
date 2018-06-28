package com.example.veronica.imgo;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
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

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.CAMERA;

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

    Button botonCargar;
    ImageView imagen;

    private final String CARPETA_RAIZ="misImagenesPrueba/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misFotos";
    String path;
    final int COD_SELECCIONA=10;
    final int COD_FOTO=20;

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

        imagen=(ImageView) findViewById(R.id.imagenId);
        botonCargar= (Button) findViewById(R.id.btnCargarImg);

        if(validaPermisos()){
            botonCargar.setEnabled(true);
        }else{
            botonCargar.setEnabled(false);
        }


    }


    public void insertarSitio(View v) {

            Integer idSitio = Integer.valueOf(editIdSitio.getText().toString());
            Integer idCategoria = Integer.valueOf(editIdCategoria.getText().toString());

            String descripcion = editDescripcion.getText().toString();
            String nombreSitio = editNombreSitio.getText().toString();
            Float precioMin = Float.valueOf(editPrecioMin.getText().toString());
            Float precioMax = Float.valueOf(editPrecioMax.getText().toString());
            String direccion = editDireccion.getText().toString();
            Float latitud = Float.valueOf(editLatitud.getText().toString());
            Float longitud = Float.valueOf(editLongitud.getText().toString());
            String regInsertados;
            String regInsertadoUbicacion;

            if(idCategoria==1||idCategoria==2||idCategoria==3||idCategoria==4||idCategoria==5){

                if(precioMin<=precioMax){
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


                }else {
                    Toast.makeText(this, "Precio Maximo debe ser mayor", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(this, "ID Categoria no valido", Toast.LENGTH_SHORT).show();
            }

    }


   /* private void cargarImagen(){
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
    }*/

  //   AGREGADOS OSCAR ACA COMIENZA

    private boolean validaPermisos() {

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if((shouldShowRequestPermissionRationale(CAMERA)) ||
                (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
        }

        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                botonCargar.setEnabled(true);
            }else{
                solicitarPermisosManual();
            }
        }

    }
    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si","no"};
        final android.support.v7.app.AlertDialog.Builder alertOpciones=new android.support.v7.app.AlertDialog.Builder(this);
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("si")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Los permisos no fueron aceptados",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }

    private void cargarDialogoRecomendacion() {
        android.support.v7.app.AlertDialog.Builder dialogo=new android.support.v7.app.AlertDialog.Builder(sitioInsertActivity.this);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
            }
        });
        dialogo.show();
    }
    public void onclick(View view) {
        cargarImagen();
    }
    private void cargarImagen() {

        final CharSequence[] opciones={"Tomar Foto","Cargar Imagen","Cancelar"};
        final android.support.v7.app.AlertDialog.Builder alertOpciones=new android.support.v7.app.AlertDialog.Builder(sitioInsertActivity.this);
        alertOpciones.setTitle("Seleccione una Opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    tomarFotografia();
                }else{
                    if (opciones[i].equals("Cargar Imagen")){
                        Intent intent =new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione una aplicacion"),10);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        alertOpciones.show();

    }

    private void tomarFotografia() {

        File fileImagen=new File(Environment.getExternalStorageDirectory(),RUTA_IMAGEN);
        boolean isCreada=fileImagen.exists();
        String nombreImagen="";
        if(isCreada==false){
            isCreada=fileImagen.mkdirs();
        }

        if(isCreada==true){
            nombreImagen=(System.currentTimeMillis()/1000)+".jpg";
        }


        path=Environment.getExternalStorageDirectory()+
                File.separator+RUTA_IMAGEN+File.separator+nombreImagen;

        File imagen=new File(path);

        Intent intent=null;
        intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ////
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
        {
            String authorities=getApplicationContext().getPackageName()+".provider";
            Uri imageUri= FileProvider.getUriForFile(this,authorities,imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }else
        {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        }
        startActivityForResult(intent,COD_FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

            switch (requestCode){
                case COD_SELECCIONA:
                    Uri mipath=data.getData();
                    imagen.setImageURI(mipath);
                    break;

                case COD_FOTO:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("Ruta de almacenamiento","Path: "+path);
                                }
                            });

                    Bitmap bitmap= BitmapFactory.decodeFile(path);
                    imagen.setImageBitmap(bitmap);

                    break;
            }

        }
    }
}
