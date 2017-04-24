package com.software.nafl.sqlite_test.Modelos;

/**
 * Created by Neury on 3/23/2017.
 */

//Control de movimiento de vehiculo
public class reg2403DBDef {
    //Nombre de la base de datos
    public static final String DATABASE_NAME= "Prueba04";

    //version de la base de datos
    public static final int DATABASE_VERSION = 1;

    //propiedades de la tabla y sus columnas

    public static class REGS2403 {
        //nombre de la tabla
        public static final String TABLE_NAME = "reg2403";
        //columnas
        public static final String ID_COL = "id";
        public static final String COMPANIA_COL = "compania_2403";
        public static final String FICHA_COL = "ficha_2403";
        public static final String FECHA_COL = "fecha_2403";
        public static final String LATITUD_COL = "latitud_2403";
        public static final String LONGITUD_COL = "longitud_2403";
        public static final String CREADOPOR_COL = "creado_por_2403";
    }

    //creamos la tabla
    public static final String REG2403_TABLA_CREATE = "CREATE TABLE " + REGS2403.TABLE_NAME + " (" +
            REGS2403.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            REGS2403.COMPANIA_COL + " TEXT,"
            + REGS2403.FICHA_COL + " TEXT,"
            + REGS2403.FECHA_COL + " INTEGER,"
            + REGS2403.LATITUD_COL + " TEXT,"
            + REGS2403.LONGITUD_COL + " TEXT,"
            + REGS2403.CREADOPOR_COL + " TEXT);";


    public static final String REG2403_TABLE_DROP = "DROP TABLE IF EXISTS" + REGS2403.TABLE_NAME;
}
