package com.example.veronica.imgo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar on 10/06/2018.
 */

public class ControlBD {

    private static final String[]camposRol=new String[]{"idRol","nombreRol"};
    private static final String[]camposUsuario=new String[]{"idUsuario","idRol","userName","password"};
    private static final String[]camposCategoria=new String[]{"idCategoria","nombreCategoria"};
    private static final String[]camposUbicacion=new String[]{"idUbicacion","idSitio","direccion","coordenadaX","coordenadaY"};
    private static final String[]camposSitio=new String[]{"idSitio","idCategoria","descripcion","nombreSitio","precioMax","precioMin"};
    private static final String[]camposPuntuacion=new String[]{"idPuntuacion","descripcionPuntuacion"};
    private static final String[]camposPuntuacionSitio=new String[]{"idPuntuacionSitio","idPuntuacion","idSitio","resena"};


    private final Context context;
    public DatabaseHelper DBHelper;
    public SQLiteDatabase db;

    private static final String DROP_TABLE1 = "DROP TABLE IF EXISTS rol; ";
    private static final String DROP_TABLE2 = "DROP TABLE IF EXISTS usuario; ";
    private static final String DROP_TABLE3 = "DROP TABLE IF EXISTS categoria; ";
    private static final String DROP_TABLE4 = "DROP TABLE IF EXISTS ubicacion; ";
    private static final String DROP_TABLE5 = "DROP TABLE IF EXISTS sitio; ";
    private static final String DROP_TABLE6 = "DROP TABLE IF EXISTS puntuacion; ";
    private static final String DROP_TABLE7 = "DROP TABLE IF EXISTS puntuacionSitio; ";

    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS="imgo.s3db";
        private static final int VERSION = 1;

        DatabaseHelper(Context context){
            super(context,BASE_DATOS,null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL("CREATE TABLE rol(idRol INTEGER NOT NULL PRIMARY KEY, nombreRol VARCHAR(20) NOT NULL); ");
                db.execSQL("CREATE TABLE usuario(idUsuario INTEGER NOT NULL PRIMARY KEY, idRol INTEGER NOT NULL, userName VARCHAR(20) NOT NULL, password VARCHAR(8) NOT NULL); ");
                db.execSQL("CREATE TABLE categoria(idCategoria INTEGER NOT NULL PRIMARY KEY, nombreCategoria VARCHAR(50) NOT NULL); ");
                db.execSQL("CREATE TABLE ubicacion(idUbicacion INTEGER NOT NULL PRIMARY KEY, idSitio INTEGER NOT NULL, direccion VARCHAR(50) NOT NULL, coordenadaX FLOAT NOT NULL,coordenadaY FLOAT NOT NULL); ");
                db.execSQL("CREATE TABLE sitio(idSitio INTEGER NOT NULL PRIMARY KEY, idCategoria INTEGER NOT NULL, descripcion VARCHAR(100) NOT NULL, nombreSitio VARCHAR(20) NOT NULL, precioMax FLOAT NOT NULL, precioMin FLOAT NOT NULL); ");
                db.execSQL("CREATE TABLE puntuacion(idPuntuacion INTEGER NOT NULL PRIMARY KEY, descripcionPuntuacion VARCHAR(10) NOT NULL); ");
                db.execSQL("CREATE TABLE puntuacionSitio(idPuntuacionSitio INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, idPuntuacion INTEGER NOT NULL, idSitio INTEGER NOT NULL, resena VARCHAR(50) NOT NULL); ");
                //db.execSQL("INSERT INTO sitio(idSitio, idCategoria, descripcion, nombreSitio, precioMax, precioMin) values(01,1,'Pizzeria de especialidad, ingredientes seleccionados, telefono a domicilio 2275-5555','Pizza Hut',15,4);");

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            try{
                db.execSQL(DROP_TABLE1);
                db.execSQL(DROP_TABLE2);
                db.execSQL(DROP_TABLE3);
                db.execSQL(DROP_TABLE4);
                db.execSQL(DROP_TABLE5);
                db.execSQL(DROP_TABLE6);
                db.execSQL(DROP_TABLE7);
            }catch (Exception e){
            }

        }
    }

    public void abrir() throws SQLException{
        db=DBHelper.getWritableDatabase();
        return;
    }
    public void cerrar(){
        DBHelper.close();
    }


    public String insertar(Rol rol){
        String regInsertados="Registro Insertado No= ";
        long contador=0;
        ContentValues ro = new ContentValues();
        ro.put("idRol", rol.getIdRol());
        ro.put("nombreRol", rol.getNombreRol());
        contador=db.insert("rol", null, ro);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error, verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertar(Categoria categoria){
        String regInsertados="Registro Insertado No = ";
        long contador=0;
        ContentValues cate = new ContentValues();
        cate.put("idCategoria", categoria.getIdCategoria());
        cate.put("nombreCategoria",categoria.getNombreCategoria());
        contador=db.insert("categoria", null, cate);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error, verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertar(Puntuacion puntuacion){
        String regInsertados="Registro Insertado No = ";
        long contador=0;
        ContentValues puntu = new ContentValues();
        puntu.put("idPuntuacion", puntuacion.getIdPuntuacion());
        puntu.put("descripcionPuntuacion",puntuacion.getDescripcionPuntuacion());
        contador=db.insert("puntuacion", null, puntu);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error, verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }


    public ArrayList<Sitio> getSitioPrecio(String precioDeseado){
        ArrayList<Sitio> listaSitioPrecio= new ArrayList<>();
        List listaSitioPre= new ArrayList();
        String[] precioDe={precioDeseado,precioDeseado};   //precioMax","precioMin
        abrir();
        Cursor cursor=db.query("sitio",camposSitio,"precioMin < ? AND precioMax > ? ",precioDe,null,null,null);
        //    Cursor cursor=db.query("sitio",camposSitio,"idSitio WHERE precioMin < ? AND precioMax > ? ",precioDe,null,null,null);
        while (cursor.moveToNext()){
            Sitio sitio=new Sitio();
            //   sitio.setIdSitio(cursor.getInt(0));
            //   sitio.setIdUsuario(cursor.getInt(1));
            //   sitio.setDescripcion(cursor.getString(2));
            sitio.setNombreSitio(cursor.getString(3));
            //   sitio.setPrecioMax(cursor.getFloat(4));
            //   sitio.setPrecioMin(cursor.getFloat(5));
            listaSitioPrecio.add(sitio);
        }
        cerrar();
        return listaSitioPrecio;
    }

    public Sitio consultarSitioPrecio(Integer idSitio){
        String[] id= {String.valueOf(idSitio)};
        Sitio sitio=null;
        abrir();
        Cursor cursor=db.query("sitio",camposSitio,"idSitio = ? ",id,null,null,null);

        if (cursor.moveToFirst()){
            sitio=new Sitio();
            sitio.setIdSitio(cursor.getInt(0));
            sitio.setIdCategoria(cursor.getInt(1));
            sitio.setDescripcion(cursor.getString(2));
            sitio.setNombreSitio(cursor.getString(3));
            sitio.setPrecioMax(cursor.getFloat(4));
            sitio.setPrecioMin(cursor.getFloat(5));
        }
        cerrar();
        return sitio;
    }

    public String insertar(Sitio sitio){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues sit = new ContentValues();
        sit.put("idSitio", sitio.getIdSitio());
        sit.put("idCategoria", sitio.getIdCategoria());
        sit.put("descripcion", sitio.getDescripcion());
        sit.put("nombreSitio", sitio.getNombreSitio());
        sit.put("precioMax", sitio.getPrecioMax());
        sit.put("precioMin", sitio.getPrecioMin());

        contador=db.insert("sitio", null, sit);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }



    public String llenarBD(){

        final  Integer[] VRidRol={0,1};
        final  String[] VRnombreRol={"admin","usuario"};

        final  Integer[] VCidCategoria={1,2,3,4,5};
        final  String[] VCnombreCategoria={"Restaurante","Cafe","Parque","Bar","Cines"};

        final  Integer[] VPidPuntuacion={1,2,3,4,5};
        final  String[] VPdescripcionPuntuacion={"Malo","Regular","Bueno","Muy bueno","Excelente"};

        abrir();
        db.execSQL("DELETE FROM rol;");
        db.execSQL("DELETE FROM categoria;");
        db.execSQL("DELETE FROM puntuacion;");

        Rol rol=new Rol();
        for(int i=0;i<2;i++){
            rol.setIdRol(VRidRol[i]);
            rol.setNombreRol(VRnombreRol[i]);
            insertar(rol);
        }

        Categoria categoria=new Categoria();
        for(int i=0;i<5;i++){
            categoria.setIdCategoria(VCidCategoria[i]);
            categoria.setNombreCategoria(VCnombreCategoria[i]);
            insertar(categoria);
        }

        Puntuacion puntuacion=new Puntuacion();
        for(int i=0;i<5;i++){
            puntuacion.setIdPuntuacion(VPidPuntuacion[i]);
            puntuacion.setDescripcionPuntuacion(VPdescripcionPuntuacion[i]);
            insertar(puntuacion);
        }

        cerrar();
        return "Guardado correctamente";
    }

}
