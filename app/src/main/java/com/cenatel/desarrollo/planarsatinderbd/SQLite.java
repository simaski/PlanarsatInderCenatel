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


    public boolean addRegistro( String funcionario_nombre,String fecha_captura,String tipo_obra_captacion,String punto_origen,String punto_destino,String latitud_pan,String longitud_pan,
                                String latitud_det,String longitud_det,String tipo_pavimento,String observacion,String problemas,String nombre_panoramica,String nombre_detalle)
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
            contentValues.put(sqliteHelper.NombrePanoramica,nombre_panoramica);
            contentValues.put(sqliteHelper.NombreDetalle,nombre_detalle);
            Log.i("SQLite", "Nuevo registro ");
            return ( db.insert( sqliteHelper.N_TABLA , null, contentValues ) != -1 )?true:false;
        }
        else
            return false;
    }


    public boolean addRegistroEmbalse( String funcionario_nombre,String fecha_captura,String nombre_sistema_riego, String tipo_obra_captacion,String nombre_foto_captacion,
                                       String tipo_obra_conduccion,String capacidad_obra_conduccion,String nombre_foto_conduccion, String tipo_obra_distribucion,
                                       String capacidad_obra_distribucion, String nombre_foto_distribucion, String tipo_dique_embalse, String longitud_dique_embalse,
                                       String altura_dique_embalse, String tipo_toma_embalse, String espejo_agua_embalse, String capacidad_toma_embalse, String tipo_aliviadero_embalse,
                                       String capacidad_aliviadero_embalse, String superficie_area_riego, String cultivos_area_riego, String metodos_riego, String area_regable,
                                       String area_bajo_riego, String area_regada, String nombre_foto_area_riego, String observacion, String problemas,String longitud,String latitud)
    {
        if( funcionario_nombre.length()> 0 )
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(sqliteHelper.FuncionarioNombre,funcionario_nombre);
            contentValues.put(sqliteHelper.FechaCaptura,fecha_captura);
            contentValues.put(sqliteHelper.NombreSistemaRiego,nombre_sistema_riego);
            contentValues.put(sqliteHelper.TipoObraCaptacion,tipo_obra_captacion);
            contentValues.put(sqliteHelper.NombreFotoCaptacion,nombre_foto_captacion);
            contentValues.put(sqliteHelper.TipoObraConduccion,tipo_obra_conduccion);
            contentValues.put(sqliteHelper.CapacidadObraConduccion,capacidad_obra_conduccion);
            contentValues.put(sqliteHelper.NombreFotoConduccion,nombre_foto_conduccion);
            contentValues.put(sqliteHelper.TipoObraDistribucion,tipo_obra_distribucion);
            contentValues.put(sqliteHelper.CapacidadObraDistribucion,capacidad_obra_distribucion);
            contentValues.put(sqliteHelper.NombreFotoDistribucion,nombre_foto_distribucion);
            contentValues.put(sqliteHelper.TipoDiqueEmbalse,tipo_dique_embalse);
            contentValues.put(sqliteHelper.LongitudDiqueEmbalse,longitud_dique_embalse);
            contentValues.put(sqliteHelper.AlturaDiqueEmbalse,altura_dique_embalse);
            contentValues.put(sqliteHelper.EspejoAguaEmbalse,espejo_agua_embalse);
            contentValues.put(sqliteHelper.TipoTomaEmbalse,tipo_toma_embalse);
            contentValues.put(sqliteHelper.CapacidadTomaEmbalse,capacidad_toma_embalse);
            contentValues.put(sqliteHelper.TipoAliviaderoEmbalse,tipo_aliviadero_embalse);
            contentValues.put(sqliteHelper.CapacidadAliviaderoEmbalse,capacidad_aliviadero_embalse);
            contentValues.put(sqliteHelper.SuperficieAreaRiego,superficie_area_riego);
            contentValues.put(sqliteHelper.CultivosAreaRiego,cultivos_area_riego);
            contentValues.put(sqliteHelper.MetodosRiego,metodos_riego);
            contentValues.put(sqliteHelper.AreaRegable,area_regable);
            contentValues.put(sqliteHelper.AreaBajoRiego,area_bajo_riego);
            contentValues.put(sqliteHelper.AreaRegada,area_regada);
            contentValues.put(sqliteHelper.NombreFotoAreaRiego,nombre_foto_area_riego);
            contentValues.put(sqliteHelper.Problemas,problemas);
            contentValues.put(sqliteHelper.Observacion,observacion);
            contentValues.put(sqliteHelper.Longitud,longitud);
            contentValues.put(sqliteHelper.Latitud,latitud);
            Log.i("SQLite", "Nuevo registro ");
            return ( db.insert( sqliteHelper.N_TABLA2 , null, contentValues ) != -1 )?true:false;
        }
        else
            return false;
    }


    public boolean addRegistroDerivacion( String funcionario_nombre,String fecha_captura,String nombre_sistema_riego, String tipo_obra_captacion,String nombre_foto_captacion,
                                       String tipo_obra_conduccion,String capacidad_obra_conduccion,String nombre_foto_conduccion, String tipo_obra_distribucion,
                                       String capacidad_obra_distribucion, String nombre_foto_distribucion, String tipo_derivacion, String capacidad_derivacion,
                                       String capacidad_desarenador, String superficie_area_riego, String cultivos_area_riego, String metodos_riego, String area_regable,
                                       String area_bajo_riego, String area_regada, String nombre_foto_area_riego, String observacion, String problemas,String longitud,String latitud)
    {
        if( funcionario_nombre.length()> 0 )
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(sqliteHelper.FuncionarioNombre,funcionario_nombre);
            contentValues.put(sqliteHelper.FechaCaptura,fecha_captura);
            contentValues.put(sqliteHelper.NombreSistemaRiego,nombre_sistema_riego);
            contentValues.put(sqliteHelper.TipoObraCaptacion,tipo_obra_captacion);
            contentValues.put(sqliteHelper.NombreFotoCaptacion,nombre_foto_captacion);
            contentValues.put(sqliteHelper.TipoObraConduccion,tipo_obra_conduccion);
            contentValues.put(sqliteHelper.CapacidadObraConduccion,capacidad_obra_conduccion);
            contentValues.put(sqliteHelper.NombreFotoConduccion,nombre_foto_conduccion);
            contentValues.put(sqliteHelper.TipoObraDistribucion,tipo_obra_distribucion);
            contentValues.put(sqliteHelper.CapacidadObraDistribucion,capacidad_obra_distribucion);
            contentValues.put(sqliteHelper.NombreFotoDistribucion,nombre_foto_distribucion);
            contentValues.put(sqliteHelper.TipoDerivacion,tipo_derivacion);
            contentValues.put(sqliteHelper.CapacidadDerivacion,capacidad_derivacion);
            contentValues.put(sqliteHelper.CapacidadDesarenador,capacidad_desarenador);
            contentValues.put(sqliteHelper.SuperficieAreaRiego,superficie_area_riego);
            contentValues.put(sqliteHelper.CultivosAreaRiego,cultivos_area_riego);
            contentValues.put(sqliteHelper.MetodosRiego,metodos_riego);
            contentValues.put(sqliteHelper.AreaRegable,area_regable);
            contentValues.put(sqliteHelper.AreaBajoRiego,area_bajo_riego);
            contentValues.put(sqliteHelper.AreaRegada,area_regada);
            contentValues.put(sqliteHelper.NombreFotoAreaRiego,nombre_foto_area_riego);
            contentValues.put(sqliteHelper.Problemas,problemas);
            contentValues.put(sqliteHelper.Observacion,observacion);
            contentValues.put(sqliteHelper.Longitud,longitud);
            contentValues.put(sqliteHelper.Latitud,latitud);
            Log.i("SQLite", "Nuevo registro ");
            return ( db.insert( sqliteHelper.N_TABLA3 , null, contentValues ) != -1 )?true:false;
        }
        else
            return false;
    }

    public boolean addRegistroLaguna( String funcionario_nombre,String fecha_captura,String nombre_sistema_riego, String tipo_obra_captacion,String nombre_foto_captacion,
                                          String tipo_obra_conduccion,String capacidad_obra_conduccion,String nombre_foto_conduccion, String tipo_obra_distribucion,
                                          String capacidad_obra_distribucion, String nombre_foto_distribucion, String capacidad_laguna, String espejo_agua_laguna,
                                          String tipo_laguna, String altura_dique_laguna, String longitud_dique_laguna, String superficie_area_riego, String cultivos_area_riego, String metodos_riego, String area_regable,
                                          String area_bajo_riego, String area_regada, String nombre_foto_area_riego, String observacion, String problemas,String longitud,String latitud)
    {
        if( funcionario_nombre.length()> 0 )
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(sqliteHelper.FuncionarioNombre,funcionario_nombre);
            contentValues.put(sqliteHelper.FechaCaptura,fecha_captura);
            contentValues.put(sqliteHelper.NombreSistemaRiego,nombre_sistema_riego);
            contentValues.put(sqliteHelper.TipoObraCaptacion,tipo_obra_captacion);
            contentValues.put(sqliteHelper.NombreFotoCaptacion,nombre_foto_captacion);
            contentValues.put(sqliteHelper.TipoObraConduccion,tipo_obra_conduccion);
            contentValues.put(sqliteHelper.CapacidadObraConduccion,capacidad_obra_conduccion);
            contentValues.put(sqliteHelper.NombreFotoConduccion,nombre_foto_conduccion);
            contentValues.put(sqliteHelper.TipoObraDistribucion,tipo_obra_distribucion);
            contentValues.put(sqliteHelper.CapacidadObraDistribucion,capacidad_obra_distribucion);
            contentValues.put(sqliteHelper.NombreFotoDistribucion,nombre_foto_distribucion);
            contentValues.put(sqliteHelper.CapacidadLaguna,capacidad_laguna);
            contentValues.put(sqliteHelper.EspejoAguaLaguna,espejo_agua_laguna);
            contentValues.put(sqliteHelper.TipoLaguna,tipo_laguna);
            contentValues.put(sqliteHelper.AlturaDiqueLaguna,altura_dique_laguna);
            contentValues.put(sqliteHelper.LongitudDiqueLaguna,longitud_dique_laguna);
            contentValues.put(sqliteHelper.SuperficieAreaRiego,superficie_area_riego);
            contentValues.put(sqliteHelper.CultivosAreaRiego,cultivos_area_riego);
            contentValues.put(sqliteHelper.MetodosRiego,metodos_riego);
            contentValues.put(sqliteHelper.AreaRegable,area_regable);
            contentValues.put(sqliteHelper.AreaBajoRiego,area_bajo_riego);
            contentValues.put(sqliteHelper.AreaRegada,area_regada);
            contentValues.put(sqliteHelper.NombreFotoAreaRiego,nombre_foto_area_riego);
            contentValues.put(sqliteHelper.Problemas,problemas);
            contentValues.put(sqliteHelper.Observacion,observacion);
            contentValues.put(sqliteHelper.Longitud,longitud);
            contentValues.put(sqliteHelper.Latitud,latitud);
            Log.i("SQLite", "Nuevo registro ");
            return ( db.insert( sqliteHelper.N_TABLA4 , null, contentValues ) != -1 )?true:false;
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

    public int getUltimoID1()
    {
        int id = -1;
        //query(String table,
        //String[] columns,
        //String selection, String[] selectionArgs, String groupBy, String having,
        //String orderBy, String limit)
        Cursor cursor = db.query( sqliteHelper.N_TABLA2 ,
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

    public int getUltimoID2()
    {
        int id = -1;
        //query(String table,
        //String[] columns,
        //String selection, String[] selectionArgs, String groupBy, String having,
        //String orderBy, String limit)
        Cursor cursor = db.query( sqliteHelper.N_TABLA3 ,
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

    public int getUltimoID3()
    {
        int id = -1;
        //query(String table,
        //String[] columns,
        //String selection, String[] selectionArgs, String groupBy, String having,
        //String orderBy, String limit)
        Cursor cursor = db.query( sqliteHelper.N_TABLA4 ,
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
                        sqliteHelper.Observacion,
                        sqliteHelper.NombrePanoramica,
                        sqliteHelper.NombreDetalle

                },
                null, null, null, null, null);
    }


    public Cursor getRegistros1() {
        return db.query(sqliteHelper.N_TABLA2,
                new String[]{
                        sqliteHelper.ID_FILA,
                        sqliteHelper.FuncionarioNombre,
                        sqliteHelper.FechaCaptura,
                        sqliteHelper.NombreSistemaRiego,
                        sqliteHelper.TipoObraCaptacion,
                        sqliteHelper.NombreFotoCaptacion,
                        sqliteHelper.TipoObraConduccion,
                        sqliteHelper.CapacidadObraConduccion,
                        sqliteHelper.NombreFotoConduccion,
                        sqliteHelper.TipoObraDistribucion,
                        sqliteHelper.CapacidadObraDistribucion,
                        sqliteHelper.NombreFotoDistribucion,
                        sqliteHelper.TipoDiqueEmbalse,
                        sqliteHelper.LongitudDiqueEmbalse,
                        sqliteHelper.AlturaDiqueEmbalse,
                        sqliteHelper.EspejoAguaEmbalse,
                        sqliteHelper.TipoTomaEmbalse,
                        sqliteHelper.CapacidadTomaEmbalse,
                        sqliteHelper.TipoAliviaderoEmbalse,
                        sqliteHelper.CapacidadAliviaderoEmbalse,
                        sqliteHelper.SuperficieAreaRiego,
                        sqliteHelper.CultivosAreaRiego,
                        sqliteHelper.MetodosRiego,
                        sqliteHelper.AreaRegable,
                        sqliteHelper.AreaBajoRiego,
                        sqliteHelper.AreaRegada,
                        sqliteHelper.NombreFotoAreaRiego,
                        sqliteHelper.Problemas,
                        sqliteHelper.Observacion,
                        sqliteHelper.Longitud,
                        sqliteHelper.Latitud

                },
                null, null, null, null, null);
    }


    public Cursor getRegistros2() {
        return db.query(sqliteHelper.N_TABLA3,
                new String[]{
                        sqliteHelper.ID_FILA,
                        sqliteHelper.FuncionarioNombre,
                        sqliteHelper.FechaCaptura,
                        sqliteHelper.NombreSistemaRiego,
                        sqliteHelper.TipoObraCaptacion,
                        sqliteHelper.NombreFotoCaptacion,
                        sqliteHelper.TipoObraConduccion,
                        sqliteHelper.CapacidadObraConduccion,
                        sqliteHelper.NombreFotoConduccion,
                        sqliteHelper.TipoObraDistribucion,
                        sqliteHelper.CapacidadObraDistribucion,
                        sqliteHelper.NombreFotoDistribucion,
                        sqliteHelper.TipoDerivacion,
                        sqliteHelper.CapacidadDerivacion,
                        sqliteHelper.CapacidadDesarenador,
                        sqliteHelper.SuperficieAreaRiego,
                        sqliteHelper.CultivosAreaRiego,
                        sqliteHelper.MetodosRiego,
                        sqliteHelper.AreaRegable,
                        sqliteHelper.AreaBajoRiego,
                        sqliteHelper.AreaRegada,
                        sqliteHelper.NombreFotoAreaRiego,
                        sqliteHelper.Problemas,
                        sqliteHelper.Observacion,
                        sqliteHelper.Longitud,
                        sqliteHelper.Latitud

                },
                null, null, null, null, null);
    }


    public Cursor getRegistros3() {
        return db.query(sqliteHelper.N_TABLA4,
                new String[]{
                        sqliteHelper.ID_FILA,
                        sqliteHelper.FuncionarioNombre,
                        sqliteHelper.FechaCaptura,
                        sqliteHelper.NombreSistemaRiego,
                        sqliteHelper.TipoObraCaptacion,
                        sqliteHelper.NombreFotoCaptacion,
                        sqliteHelper.TipoObraConduccion,
                        sqliteHelper.CapacidadObraConduccion,
                        sqliteHelper.NombreFotoConduccion,
                        sqliteHelper.TipoObraDistribucion,
                        sqliteHelper.CapacidadObraDistribucion,
                        sqliteHelper.NombreFotoDistribucion,
                        sqliteHelper.CapacidadLaguna,
                        sqliteHelper.EspejoAguaLaguna,
                        sqliteHelper.TipoLaguna,
                        sqliteHelper.AlturaDiqueLaguna,
                        sqliteHelper.LongitudDiqueLaguna,
                        sqliteHelper.SuperficieAreaRiego,
                        sqliteHelper.CultivosAreaRiego,
                        sqliteHelper.MetodosRiego,
                        sqliteHelper.AreaRegable,
                        sqliteHelper.AreaBajoRiego,
                        sqliteHelper.AreaRegada,
                        sqliteHelper.NombreFotoAreaRiego,
                        sqliteHelper.Problemas,
                        sqliteHelper.Observacion,
                        sqliteHelper.Longitud,
                        sqliteHelper.Latitud

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
                        sqliteHelper.Observacion,
                        sqliteHelper.NombrePanoramica,
                        sqliteHelper.NombreDetalle

                },
                sqliteHelper.ID_FILA + " = " + id ,
                null, null, null, null);
    }


    public Cursor getRegistro1( int id )
    {
        return db.query( sqliteHelper.N_TABLA2 ,
                new String[]{
                        sqliteHelper.ID_FILA ,
                        sqliteHelper.FuncionarioNombre,
                        sqliteHelper.FechaCaptura,
                        sqliteHelper.NombreSistemaRiego,
                        sqliteHelper.TipoObraCaptacion,
                        sqliteHelper.NombreFotoCaptacion,
                        sqliteHelper.TipoObraConduccion,
                        sqliteHelper.CapacidadObraConduccion,
                        sqliteHelper.NombreFotoConduccion,
                        sqliteHelper.TipoObraDistribucion,
                        sqliteHelper.CapacidadObraDistribucion,
                        sqliteHelper.NombreFotoDistribucion,
                        sqliteHelper.TipoDiqueEmbalse,
                        sqliteHelper.LongitudDiqueEmbalse,
                        sqliteHelper.AlturaDiqueEmbalse,
                        sqliteHelper.EspejoAguaEmbalse,
                        sqliteHelper.TipoTomaEmbalse,
                        sqliteHelper.CapacidadTomaEmbalse,
                        sqliteHelper.TipoAliviaderoEmbalse,
                        sqliteHelper.CapacidadAliviaderoEmbalse,
                        sqliteHelper.SuperficieAreaRiego,
                        sqliteHelper.CultivosAreaRiego,
                        sqliteHelper.MetodosRiego,
                        sqliteHelper.AreaRegable,
                        sqliteHelper.AreaBajoRiego,
                        sqliteHelper.AreaRegada,
                        sqliteHelper.NombreFotoAreaRiego,
                        sqliteHelper.Problemas,
                        sqliteHelper.Observacion,
                        sqliteHelper.Longitud,
                        sqliteHelper.Latitud

                },
                sqliteHelper.ID_FILA + " = " + id ,
                null, null, null, null);
    }

    public Cursor getRegistro2( int id )
    {
        return db.query( sqliteHelper.N_TABLA3 ,
                new String[]{
                        sqliteHelper.ID_FILA ,
                        sqliteHelper.FuncionarioNombre,
                        sqliteHelper.FechaCaptura,
                        sqliteHelper.NombreSistemaRiego,
                        sqliteHelper.TipoObraCaptacion,
                        sqliteHelper.NombreFotoCaptacion,
                        sqliteHelper.TipoObraConduccion,
                        sqliteHelper.CapacidadObraConduccion,
                        sqliteHelper.NombreFotoConduccion,
                        sqliteHelper.TipoObraDistribucion,
                        sqliteHelper.CapacidadObraDistribucion,
                        sqliteHelper.NombreFotoDistribucion,
                        sqliteHelper.TipoDerivacion,
                        sqliteHelper.CapacidadDerivacion,
                        sqliteHelper.CapacidadDesarenador,
                        sqliteHelper.SuperficieAreaRiego,
                        sqliteHelper.CultivosAreaRiego,
                        sqliteHelper.MetodosRiego,
                        sqliteHelper.AreaRegable,
                        sqliteHelper.AreaBajoRiego,
                        sqliteHelper.AreaRegada,
                        sqliteHelper.NombreFotoAreaRiego,
                        sqliteHelper.Problemas,
                        sqliteHelper.Observacion,
                        sqliteHelper.Longitud,
                        sqliteHelper.Latitud

                },
                sqliteHelper.ID_FILA + " = " + id ,
                null, null, null, null);
    }


    public Cursor getRegistro3( int id )
    {
        return db.query( sqliteHelper.N_TABLA3 ,
                new String[]{
                        sqliteHelper.ID_FILA ,
                        sqliteHelper.FuncionarioNombre,
                        sqliteHelper.FechaCaptura,
                        sqliteHelper.NombreSistemaRiego,
                        sqliteHelper.TipoObraCaptacion,
                        sqliteHelper.NombreFotoCaptacion,
                        sqliteHelper.TipoObraConduccion,
                        sqliteHelper.CapacidadObraConduccion,
                        sqliteHelper.NombreFotoConduccion,
                        sqliteHelper.TipoObraDistribucion,
                        sqliteHelper.CapacidadObraDistribucion,
                        sqliteHelper.NombreFotoDistribucion,
                        sqliteHelper.CapacidadLaguna,
                        sqliteHelper.EspejoAguaLaguna,
                        sqliteHelper.TipoLaguna,
                        sqliteHelper.AlturaDiqueLaguna,
                        sqliteHelper.LongitudDiqueLaguna,
                        sqliteHelper.SuperficieAreaRiego,
                        sqliteHelper.CultivosAreaRiego,
                        sqliteHelper.MetodosRiego,
                        sqliteHelper.AreaRegable,
                        sqliteHelper.AreaBajoRiego,
                        sqliteHelper.AreaRegada,
                        sqliteHelper.NombreFotoAreaRiego,
                        sqliteHelper.Problemas,
                        sqliteHelper.Observacion,
                        sqliteHelper.Longitud,
                        sqliteHelper.Latitud

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
                item += "Latitud Foto Panoramica: " + cursor.getString(6) + "\r\n";
                item += "Longitud Foto Panoramica: " + cursor.getString(7) + "\r\n";
                item += "Latitud Foto Detalle: " + cursor.getString(8) + "\r\n";
                item += "Longitud Foto Detalle: " + cursor.getString(9) + "\r\n";
                item += "Tipo de Pavimento: " + cursor.getString(10) + "\r\n";
                item += "Problemas Encontrados: " + cursor.getString(11) + "\r\n";
                item += "Observaciones: " + cursor.getString(12) + "\r\n";
                item += "Nombre Panoramica: " + cursor.getString(13) + "\r\n";
                item += "Nombre Detalle: " + cursor.getString(14) + "";
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