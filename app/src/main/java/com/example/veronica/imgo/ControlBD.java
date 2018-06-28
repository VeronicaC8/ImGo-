package com.example.veronica.imgo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import com.google.android.gms.actions.ReserveIntents;

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
    private static final String[]camposSitio=new String[]{"idSitio","idCategoria","descripcion","nombreSitio","precioMax","precioMin","imagen"};
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
                db.execSQL("CREATE TABLE sitio(idSitio INTEGER NOT NULL PRIMARY KEY, idCategoria INTEGER NOT NULL, descripcion VARCHAR(100) NOT NULL, nombreSitio VARCHAR(20) NOT NULL, precioMax FLOAT NOT NULL, precioMin FLOAT NOT NULL, imagen VARCHAR(40),imagen2 INTEGER,dato VARCHAR(100)); ");
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


  //  public ArrayList<Sitio>
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



    //Lista de sitios por categoria
    public ArrayList<Sitio> getCategoria(String precioDeseado){
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

    public String insertar(Sitio sitio){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        if(verificarIntegridad(sitio,1)){  //7
            regInsertados="ID Sitio ya existe";
        }else {
            ContentValues sit = new ContentValues();
            sit.put("idSitio", sitio.getIdSitio());
            sit.put("idCategoria", sitio.getIdCategoria());
            sit.put("descripcion", sitio.getDescripcion());
            sit.put("nombreSitio", sitio.getNombreSitio());
            sit.put("precioMax", sitio.getPrecioMax());
            sit.put("precioMin", sitio.getPrecioMin());
            sit.put("imagen2", String.valueOf(sitio.getImagen()));
            sit.put("dato",sitio.getDato());


            contador = db.insert("sitio", null, sit);
            if (contador == -1 || contador == 0) {
                regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            } else {
                regInsertados = regInsertados + contador;
            }
        }
        return regInsertados;
    }

    public String actualizar(Sitio sitio){     //26
        if(verificarIntegridad(sitio,3)){
            String[] id={Integer.toString(sitio.getIdSitio())};
            ContentValues cv = new ContentValues();
            cv.put("idSitio", sitio.getIdSitio());
            cv.put("idCategoria", sitio.getIdCategoria());
            cv.put("descripcion", sitio.getDescripcion());
            cv.put("nombreSitio", sitio.getNombreSitio());
            cv.put("precioMax", sitio.getPrecioMax());
            cv.put("precioMin", sitio.getPrecioMin());
            db.update("sitio", cv, "idSitio=?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con código"+ sitio.getIdSitio()+ "No existe";
        }
    }

    public String eliminar(Sitio sitio){

        String regAfectados="filas afectadas= "; int contador=0;
        // 2 Verificar registro que exista
        if(verificarIntegridad(sitio, 3)) {
            String where="idSitio='"+sitio.getIdSitio()+"'";
            contador+=db.delete("sitio", where, null);
            regAfectados+=contador;
            return regAfectados;
        } else { return "Registro no Existe"; }
    }

    public String insertar(Ubicacion ubica){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        if(verificarIntegridad(ubica, 2)){
            regInsertados=" . ";
        }else {
            ContentValues ubi = new ContentValues();
            ubi.put("idUbicacion", ubica.getIdUbicacion());
            ubi.put("idSitio", ubica.getIdSitio());
            ubi.put("direccion", ubica.getDireccion());
            ubi.put("coordenadaX", ubica.getCoordenadaX());
            ubi.put("coordenadaY", ubica.getCoordenadaY());

            contador = db.insert("ubicacion", null, ubi);
            if (contador == -1 || contador == 0) {
                regInsertados = "Error";
            } else {
                regInsertados = regInsertados + contador;
            }
        }
        return regInsertados;
    }
    public String actualizar(Ubicacion ubicacion){     //26
        if(verificarIntegridad(ubicacion,4)){
            String[] id={Integer.toString(ubicacion.getIdUbicacion())};
            ContentValues cv = new ContentValues();
            cv.put("idUbicacion", ubicacion.getIdUbicacion());
            cv.put("idSitio", ubicacion.getIdSitio());
            cv.put("direccion", ubicacion.getDireccion());
            cv.put("coordenadaX", ubicacion.getCoordenadaX());
            cv.put("coordenadaY", ubicacion.getCoordenadaY());
            db.update("ubicacion", cv, "idUbicacion=?", id);
            return "Correcto";
        }else{
            return "No actualizado"+ ubicacion.getIdSitio();
        }
    }
    public String eliminar(Ubicacion ubicacion){

        String regAfectados="filas afectadas= "; int contador=0;
        // 2 Verificar registro que exista
        if(verificarIntegridad(ubicacion, 4)) {
            String where="idSitio='"+ubicacion.getIdUbicacion()+"'";
            contador+=db.delete("ubicacion", where, null);
            regAfectados="";
            regAfectados+=contador;
            return regAfectados;
        } else { return "Registro no Existe"; }
    }



    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch (relacion){
            case 1: {
                Sitio sit=(Sitio) dato;
                String[] id ={Integer.toString(sit.getIdSitio())};
                abrir();
                Cursor c2 = db.query("sitio", null, "idSitio = ? ", id, null, null, null);
                if(c2.moveToFirst()){
                    return true;
                }
                return false;
            }
            case 2: {
                Ubicacion sit=(Ubicacion) dato;
                String[] id ={Integer.toString(sit.getIdSitio())};
                abrir();
                Cursor c2 = db.query("ubicacion", null, "idSitio = ? ", id, null, null, null);
                if(c2.moveToFirst()){
                    return true;
                }
                return false;
            }
            case 3:{
                Sitio sitio = (Sitio) dato;
                String[] id = {Integer.toString(sitio.getIdSitio())};
                abrir();
                Cursor c2 = db.query("sitio", null, "idSitio = ? ", id, null, null, null);
                if(c2.moveToFirst()){ //Se encontro
                    return true; }
                return false;
            }
            case 4:{
                Ubicacion ubicacion = (Ubicacion) dato;
                String[] id = {Integer.toString(ubicacion.getIdUbicacion())};
                abrir();
                Cursor c2 = db.query("ubicacion", null, "idUbicacion = ? ", id, null, null, null);
                if(c2.moveToFirst()){ //Se encontro
                    return true; }
                return false;
            }


            default:return false;
        }
    }



    //LISTAR LAS CATEGORIAS POR RESTAURANTE
   public List llenar_restaurantes(){

        List lista= new ArrayList<>();
        SQLiteDatabase database=DBHelper.getWritableDatabase();
        String q= "SELECT * from sitio WHERE idCategoria=1";
        Cursor registro= database.rawQuery(q,null);

        while(registro.moveToNext()){
          Sitio  sitio= new Sitio();
          sitio.setNombreSitio(registro.getString(3));
          sitio.setDescripcion(registro.getString(2));
          lista.add(sitio);
        }

        return  lista;
   }

    //LISTAR LAS CATEGORIAS POR BARES
    public List llenar_bares(){

        List lista= new ArrayList<>();
        SQLiteDatabase database=DBHelper.getWritableDatabase();
        String q= "SELECT * from sitio WHERE idCategoria=2";
        Cursor registro= database.rawQuery(q,null);

        while(registro.moveToNext()){
            Sitio  sitio= new Sitio();
            sitio.setNombreSitio(registro.getString(3));
            sitio.setDescripcion(registro.getString(2));
            lista.add(sitio);
        }

        return  lista;
    }

    //LISTAR LAS CATEGORIAS POR PARQUES
    public List llenar_parques(){

        List lista= new ArrayList<>();
        SQLiteDatabase database=DBHelper.getWritableDatabase();
        String q= "SELECT * from sitio WHERE idCategoria=3";
        Cursor registro= database.rawQuery(q,null);

        while(registro.moveToNext()){
            Sitio  sitio= new Sitio();
            sitio.setNombreSitio(registro.getString(3));
            sitio.setDescripcion(registro.getString(2));
            lista.add(sitio);
        }

        return  lista;
    }


    //LISTAR LAS CATEGORIAS POR ENTRETENIMIENTO
    public List llenar_entretenimiento(){

        List lista= new ArrayList<>();
        SQLiteDatabase database=DBHelper.getWritableDatabase();
        String q= "SELECT * from sitio WHERE idCategoria=3";
        Cursor registro= database.rawQuery(q,null);

        while(registro.moveToNext()){
            Sitio  sitio= new Sitio();
            sitio.setNombreSitio(registro.getString(3));
            sitio.setDescripcion(registro.getString(2));
            lista.add(sitio);
        }

        return  lista;
    }


    public String llenarBD(){

        final  Integer[] VRidRol={0,1};
        final  String[] VRnombreRol={"admin","usuario"};

        final  Integer[] VCidCategoria={1,2,3,4};
        final  String[] VCnombreCategoria={"Restaurante","Bares","Parque","Entretenimiento"};


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
        for(int i=0;i<4;i++){
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
