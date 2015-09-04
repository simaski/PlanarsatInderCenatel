package com.cenatel.desarrollo.planarsatinderbd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    //nombre de la base de datos
    //private static final String __DATABASE = "dbUniversidad";
    public static final String N_BD = "PlanarsatInder";
    //versi?n de la base de datos
    //private static final int __VERSION = 3;
    public static final int VERSION_BD = 1;
    //nombre tabla y campos de tabla
    //public final String __tabla__ = "Universitario";
    public static final String N_TABLA = "Tabla_planarsat_vialidad";
    public static final String ID_FILA = "id";
    public static final String FuncionarioNombre = "funcionario_nombre";
    public static final String FechaCaptura = "fecha_captura";
    public static final String TipoObraCaptacion = "tipo_obra_captacion";
    public static final String PuntoOrigen = "punto_origen";
    public static final String PuntoDestino = "punto_destino";
    public static final String Latitudpan = "latitud_pan";
    public static final String Longitudpan = "longitud_pan";
    public static final String Latituddet = "latitud_det";
    public static final String Longituddet = "longitud_det";
    public static final String TipoPavimento = "tipo_pavimento";
    public static final String Problemas = "problemas";
    public static final String Observacion = "observacion";


    private final String sql = "CREATE TABLE " + N_TABLA +
            "(" +
            ID_FILA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FuncionarioNombre + " TEXT NOT NULL, " +
            FechaCaptura + " TEXT NOT NULL, " +
            TipoObraCaptacion + " TEXT NOT NULL, " +
            PuntoOrigen + " TEXT NOT NULL, " +
            PuntoDestino + " TEXT NOT NULL, " +
            Latitudpan + " TEXT NOT NULL, " +
            Longitudpan + " TEXT NOT NULL, " +
            Latituddet + " TEXT NOT NULL, " +
            Longituddet + " TEXT NOT NULL, " +
            TipoPavimento + " TEXT NOT NULL, " +
            Problemas + " TEXT NOT NULL, " +
            Observacion + " TEXT NOT NULL" + " )";

    /**
     * Constructor de clase
     * */
    public SQLiteHelper(Context context) {
        super( context, N_BD, null, VERSION_BD );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( sql );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db,  int oldVersion, int newVersion ) {
        if ( newVersion > oldVersion )
        {
            //elimina tabla
            db.execSQL( "DROP TABLE IF EXISTS " + N_TABLA );
            //y luego creamos la nueva tabla
            db.execSQL( sql );
        }
    }

}