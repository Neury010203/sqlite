package com.software.nafl.sqlite_test.Modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neury on 3/24/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, reg2403DBDef.DATABASE_NAME, null, reg2403DBDef.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(reg2403DBDef.REG2403_TABLA_CREATE);
    }


    //ejecutamos al compilar la app
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(reg2403DBDef.REG2403_TABLE_DROP);

        this.onCreate(db);
    }

    public void insertReg2403(Vehiculo vehiculo) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        ContentValues values01 = new ContentValues();

       /* values.put(reg2403DBDef.REGS2403.CREADOPOR_COL, vehiculo.getCreado_por_2403());
        values.put(reg2403DBDef.REGS2403.LONGITUD_COL, vehiculo.getLongitud_2403());
        values.put(reg2403DBDef.REGS2403.LATITUD_COL, vehiculo.getLatitud_2403());
        values.put(reg2403DBDef.REGS2403.FECHA_COL, vehiculo.getFecha_2403());
        values.put(reg2403DBDef.REGS2403.FICHA_COL, vehiculo.getFicha_2403());
        values.put(reg2403DBDef.REGS2403.COMPANIA_COL, vehiculo.getCompania_2403());*/


     /*   values01.put(reg2403DBDef.REGS2403.CREADOPOR_COL, "me");
        values01.put(reg2403DBDef.REGS2403.LONGITUD_COL, "23");
        values01.put(reg2403DBDef.REGS2403.LATITUD_COL, "34");
        values01.put(reg2403DBDef.REGS2403.FECHA_COL, "20");
        values01.put(reg2403DBDef.REGS2403.FICHA_COL, "akah");
        values01.put(reg2403DBDef.REGS2403.COMPANIA_COL, "qjm");
        Log.i("sql", String.valueOf(values01));*/

        //db.insert(reg2403DBDef.REGS2403.TABLE_NAME, null, values);
        //db.insert("reg2403",null,values01);

        String Insert = "INSERT INTO "+ reg2403DBDef.REGS2403.TABLE_NAME +"("
                + reg2403DBDef.REGS2403.COMPANIA_COL + ","
                + reg2403DBDef.REGS2403.FICHA_COL + ","
                + reg2403DBDef.REGS2403.FECHA_COL + ","
                + reg2403DBDef.REGS2403.LATITUD_COL + ","
                + reg2403DBDef.REGS2403.LONGITUD_COL + ","
                + reg2403DBDef.REGS2403.CREADOPOR_COL + ")" + " VALUES ("
                +"'"+ vehiculo.getCompania_2403() +"'" + ","
                +"'"+ vehiculo.getFicha_2403() +"'" + ","
                + vehiculo.getFecha_2403() + ","
                + "'"+ vehiculo.getLatitud_2403()+"'" + ","
                + "'"+ vehiculo.getLongitud_2403()+"'" + ","
                + "'"+ vehiculo.getCreado_por_2403()+"'" + ")";

        db.execSQL(Insert);

        db.close();

    }

    public Vehiculo getVehiculoById(String ficha) {

        Vehiculo aVehiculo = null;

        SQLiteDatabase db = this.getReadableDatabase(); //referencia a la base de datos

        String[] COLUMNS = {    //definimos array con los nombre de las columnas
                reg2403DBDef.REGS2403.ID_COL,
                reg2403DBDef.REGS2403.FICHA_COL,
                reg2403DBDef.REGS2403.COMPANIA_COL,
                reg2403DBDef.REGS2403.CREADOPOR_COL,
                reg2403DBDef.REGS2403.LONGITUD_COL,
                reg2403DBDef.REGS2403.LATITUD_COL,
                reg2403DBDef.REGS2403.FECHA_COL};


        Cursor cursor = db.query(
                reg2403DBDef.REGS2403.TABLE_NAME,
                COLUMNS, //columnas de la tabla
                "id=?", //where
                new String[]{"id"}, //argunmento del where ? ficha
                null,//clausula  Group by
                null,//clausula having
                null,//clausula order by
                null);//limite de registro


        if (cursor!= null){

            cursor.moveToFirst();
            aVehiculo = new Vehiculo();
            aVehiculo.setId(Integer.parseInt(cursor.getString(0)));
            aVehiculo.setCompania_2403(cursor.getString(1));
            aVehiculo.setFicha_2403(cursor.getString(2));
            aVehiculo.setFecha_2403(Integer.parseInt(cursor.getString(3)));
            aVehiculo.setLatitud_2403(cursor.getString(4));
            aVehiculo.setLongitud_2403(cursor.getString(5));
            aVehiculo.setCreado_por_2403(cursor.getString(6));

        }


        return aVehiculo;
    }


    // Obtener todas las notas
    public List getAllVehiculos() {
        //Instanciamos un Array para llenarlo con todos los objetos Notes que saquemos de la BD
        List vehiculos = new ArrayList();

        // 1. Aramos un String con el query a ejecutar
        String query = "SELECT  * FROM " + reg2403DBDef.REGS2403.TABLE_NAME;

        // 2. Obtenemos una reference de la BD con permisos de escritura y ejecutamos el query
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. Iteramos entre cada uno de olos registros y agregarlos al array de Notas
        Vehiculo aVehiculo = null;
        if (cursor.moveToFirst()) {
            do {
                aVehiculo = new Vehiculo();
                aVehiculo.setId(Integer.parseInt(cursor.getString(0)));
                aVehiculo.setCompania_2403(cursor.getString(1));
                aVehiculo.setFicha_2403(cursor.getString(2));
                aVehiculo.setFecha_2403(Integer.parseInt(cursor.getString(3)));
                aVehiculo.setLatitud_2403(cursor.getString(4));
                aVehiculo.setLongitud_2403(cursor.getString(5));
                aVehiculo.setCreado_por_2403(cursor.getString(6));

                // Add book to books
                vehiculos.add(aVehiculo);
            } while (cursor.moveToNext());
        }

        //Cerramos el cursor
        cursor.close();

        // Devolvemos las notas encontradas o un array vacio en caso de que no se encuentre nada
        return vehiculos;
    }

    //Actualizar los datos en una nota
    public int updateNote(Vehiculo vehiculo) {

        // 1. Obtenemos una reference de la BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Creamos el objeto ContentValues con las claves "columna"/valor
        // que se desean actualizar
        ContentValues values = new ContentValues();
        values.put(reg2403DBDef.REGS2403.COMPANIA_COL, vehiculo.getCompania_2403());
        values.put(reg2403DBDef.REGS2403.FICHA_COL, vehiculo.getFicha_2403());
        values.put(reg2403DBDef.REGS2403.FECHA_COL, vehiculo.getFecha_2403());
        values.put(reg2403DBDef.REGS2403.LATITUD_COL,vehiculo.getLatitud_2403());
        values.put(reg2403DBDef.REGS2403.LONGITUD_COL,vehiculo.getLongitud_2403());
        values.put(reg2403DBDef.REGS2403.CREADOPOR_COL,vehiculo.getCreado_por_2403());

        // 3. Actualizamos el registro con el método update el cual devuelve la cantidad
        // de registros actualizados
        int i = db.update(reg2403DBDef.REGS2403.TABLE_NAME, //table
                values, // column/value
                reg2403DBDef.REGS2403.ID_COL+" = ?", // selections
                new String[] { String.valueOf(vehiculo.getId()) }); //selection args

        // 4. Cerramos la conexión a la BD
        db.close();

        // Devolvemos la cantidad de registros actualizados
        return i;
    }

    /*
    // Borrar una Nota
    public void deleteNote(Note note) {

        // 1. Obtenemos una reference de la BD con permisos de escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Borramos el registro
        db.delete(NotesDBDef.NOTES.TABLE_NAME,
                NotesDBDef.NOTES.ID_COL+" = ?",
                new String[] { String.valueOf(note.getId()) });

        // 3. Cerramos la conexión a la Bd
        db.close();
    }*/

}
