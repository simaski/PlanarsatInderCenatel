package com.cenatel.desarrollo.planarsatinderbd;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

//

@SuppressLint("NewApi")
public class SQLite {

    private SQLiteHelper sqliteHelper;
    private SQLiteDatabase db;

    /** Constructor de clase */
    public SQLite(Context context)
    {
        sqliteHelper = new SQLiteHelper( context );
    }

    /** Abre conexion a base de datos */
    public void abrir(){
        Log.i("SQLite", "Se abre conexion a la base de datos " + sqliteHelper.getDatabaseName());
        db = sqliteHelper.getWritableDatabase();
    }

    /** Cierra conexion a la base de datos */
    public void cerrar()
    {
        Log.i("SQLite", "Se cierra conexion a la base de datos " + sqliteHelper.getDatabaseName());
        sqliteHelper.close();
    }

    /**
     * Metodo para agregar un nuevo registro
     * @param String nombre Nombre completo
     * @param String fecha fecha de nacimiento de la forma 12/05/1900
     * @param String pais
     * @param String sexo
     * @param String ingles si habla ingles
     * @return BOOLEAN TRUE si tuvo exito FALSE caso contrario
     * */
    public boolean addRegistro( String funcionario_nombre,String fecha_captura,String tipo_obra_captacion,String punto_origen,String punto_destino,String latitud_pan,String longitud_pan,
                                String latitud_det,String longitud_det,String tipo_pavimento,String observacion,String problemas)
    {
        if( funcionario_nombre.length()> 0 )
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(sqliteHelper.FuncionarioNombre,funcionario_nombre);
            contentValues.put(sqliteHelper.FechaCaptura,fecha_captura);
            contentValues.put(sqliteHelper.TipoObraCaptacion,tipo_obra_captacion);
            contentValues.put(sqliteHelper.PuntoOrigen,punto_origen);
            contentValues.put(sqliteHelper.PuntoDestino,punto_destino);
            contentValues.put(sqliteHelper.Latitudpan,latitud_pan);
            contentValues.put(sqliteHelper.Longitudpan,longitud_pan);
            contentValues.put(sqliteHelper.Latituddet,latitud_det);
            contentValues.put(sqliteHelper.Longituddet,longitud_det);
            contentValues.put(sqliteHelper.TipoPavimento,tipo_pavimento);
            contentValues.put(sqliteHelper.Problemas,problemas);
            contentValues.put(sqliteHelper.Observacion,observacion);
            Log.i("SQLite", "Nuevo registro ");
            return ( db.insert( sqliteHelper.N_TABLA , null, contentValues ) != -1 )?true:false;
        }
        else
            return false;
    }


    /**
     * Metodo que retorna el ID del ultimo universitario registrado
     * @return integer ID -1 si no existen registros
     * */
    public int getUltimoID()
    {
        int id = -1;
        //query(String table,
        //String[] columns,
        //String selection, String[] selectionArgs, String groupBy, String having,
        //String orderBy, String limit)
        Cursor cursor = db.query( sqliteHelper.N_TABLA ,
                new String[]{ sqliteHelper.ID_FILA },
                null, null, null,null,
                sqliteHelper.ID_FILA + " DESC ", "1");
        if( cursor.moveToFirst() )
        {
            do
            {
                id = cursor.getInt(0);
            } while ( cursor.moveToNext() );
        }
        return id;
    }

    /**
     * @param INT ID del registro a eliminar
     * @return BOOLEAN
     * */
	/*public boolean borrar_registro( int id )
	{
		//table , whereClause, whereArgs
		return  (db.delete( sqliteHelper.N_TABLA , sqliteHelper.ID_FILA + " = " + id ,  null) > 0) ? true:false;

	}
	/**
	 * Obtiene todos los registros de la unica tabla de la base de datos
	 * @return Cursor
	 * */
    public Cursor getRegistros()
    {
        return db.query( sqliteHelper.N_TABLA ,
                new String[]{
                        sqliteHelper.ID_FILA ,
                        sqliteHelper.FuncionarioNombre,
                        sqliteHelper.FechaCaptura,
                        sqliteHelper.TipoObraCaptacion,
                        sqliteHelper.PuntoOrigen,
                        sqliteHelper.PuntoDestino,
                        sqliteHelper.Latitudpan,
                        sqliteHelper.Longitudpan,
                        sqliteHelper.Latituddet,
                        sqliteHelper.Longituddet,
                        sqliteHelper.TipoPavimento,
                        sqliteHelper.Problemas,
                        sqliteHelper.Observacion

                },
                null, null, null, null, null);
    }


    /**
     * Obtiene un registro
     * */
    public Cursor getRegistro( int id )
    {
        return db.query( sqliteHelper.N_TABLA ,
                new String[]{
                        sqliteHelper.ID_FILA ,
                        sqliteHelper.FuncionarioNombre,
                        sqliteHelper.FechaCaptura,
                        sqliteHelper.TipoObraCaptacion,
                        sqliteHelper.PuntoOrigen,
                        sqliteHelper.PuntoDestino,
                        sqliteHelper.Latitudpan,
                        sqliteHelper.Longitudpan,
                        sqliteHelper.Latituddet,
                        sqliteHelper.Longituddet,
                        sqliteHelper.TipoPavimento,
                        sqliteHelper.Problemas,
                        sqliteHelper.Observacion

                },
                sqliteHelper.ID_FILA + " = " + id ,
                null, null, null, null);
    }

    /**
     * Dado un Cursor con los registros de la base de datos, da formato y retorna resultado
     * @return ArrayList<String>
     * */
    public ArrayList<String> getFormatListUniv( Cursor cursor )
    {
        ArrayList<String> listData = new ArrayList<String>();
        String item = "";
        if( cursor.moveToFirst() )
        {
            do
            {
                item += "ID: [" + cursor.getInt(0) + "]\r\n";
                item += "Nombre Funcionario: " + cursor.getString(1) + "\r\n";
                item += "Fecha de Captura: " + cursor.getString(2) + "\r\n";
                item += "Tipo Obra de Captacion: " + cursor.getString(3) + "\r\n";
                item += "Punto de Origen: " + cursor.getString(4) + "\r\n";
                item += "Punto de Destino: " + cursor.getString(5) + "\r\n";
                item += "Latitud Foto Panoramica: " + cursor.getString(11) + "\r\n";
                item += "Longitud Foto Panoramica: " + cursor.getString(12) + "\r\n";
                item += "Latitud Foto Detalle: " + cursor.getString(13) + "\r\n";
                item += "Longitud Foto Detalle: " + cursor.getString(14) + "\r\n";
                item += "Tipo de Pavimento: " + cursor.getString(15) + "\r\n";
                item += "Problemas Encontrados: " + cursor.getString(16) + "\r\n";
                item += "Observaciones: " + cursor.getString(17) + "";
                listData.add( item );
                item="";

            } while ( cursor.moveToNext() );
        }
        return listData;
    }

    public ArrayList<String> getFormatListaPrimera( Cursor cursor )
    {
        ArrayList<String> listData2 = new ArrayList<String>();
        String item = "";
        if( cursor.moveToFirst() )
        {
            do
            {
                item += "ID: " + cursor.getInt(0) + "\r\n";
                item += "Nombre Funcionario: " + cursor.getString(1) + "\r\n";
                item += "Fecha de Captura: " + cursor.getString(2) + "\r\n";
                listData2.add( item );
                item="";

            } while ( cursor.moveToNext() );
        }
        return listData2;
    }

}