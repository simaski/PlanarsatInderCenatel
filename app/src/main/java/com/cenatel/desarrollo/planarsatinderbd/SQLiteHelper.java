package com.cenatel.desarrollo.planarsatinderbd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    //****************CAMPOS COMUNES BD*******************************************************//
    public static final String N_BD = "PlanarsatInder";
    public static final int VERSION_BD = 1;
    public static final String ID_FILA = "id";
    public static final String FuncionarioNombre = "funcionario_nombre";
    public static final String FechaCaptura = "fecha_captura";
    public static final String NombreSistemaRiego = "nombre_sistema_riego";
    public static final String TipoObraCaptacion = "tipo_obra_captacion";
    public static final String NombreFotoCaptacion = "nombre_foto_captacion";
    public static final String TipoObraConduccion = "tipo_obra_conduccion";
    public static final String CapacidadObraConduccion = "capacidad_obra_conduccion";
    public static final String NombreFotoConduccion = "nombre_foto_conduccion";
    public static final String TipoObraDistribucion = "tipo_obra_distribucion";
    public static final String CapacidadObraDistribucion = "capacidad_obra_distribucion";
    public static final String NombreFotoDistribucion = "nombre_foto_distribucion";
    public static final String SuperficieAreaRiego = "superficie_area_riego";
    public static final String CultivosAreaRiego = "cultivos_area_riego";
    public static final String MetodosRiego = "metodos_riego";
    public static final String AreaRegable = "area_regable";
    public static final String AreaBajoRiego = "area_bajo_riego";
    public static final String AreaRegada = "area_regada";
    public static final String NombreFotoAreaRiego = "nombre_foto_area_riego";
    public static final String Problemas = "problemas";
    public static final String Observacion = "observacion";
    public static final String Longitud = "longitud";
    public static final String Latitud = "latitud";
    //****************FIN CAMPOS COMUNES BD*******************************************************//

    //**********BASE DE DATOS VIALIDAD**************************************//
    public static final String N_TABLA = "Tabla_planarsat_vialidad";
    public static final String PuntoOrigen = "punto_origen";
    public static final String PuntoDestino = "punto_destino";
    public static final String Latitudpan = "latitud_pan";
    public static final String Longitudpan = "longitud_pan";
    public static final String Latituddet = "latitud_det";
    public static final String Longituddet = "longitud_det";
    public static final String TipoPavimento = "tipo_pavimento";
    public static final String NombrePanoramica = "nombre_panoramica";
    public static final String NombreDetalle = "nombre_detalle";
    //**********FIN BASE DE DATOS VIALIDAD**************************************//

    //**********BASE DE DATOS EMBALSE**************************************//
    public static final String N_TABLA2 = "Tabla_planarsat_embalse";
    public static final String TipoDiqueEmbalse = "tipo_dique_embalse";
    public static final String LongitudDiqueEmbalse = "longitud_dique_embalse";
    public static final String AlturaDiqueEmbalse = "altura_dique_embalse";
    public static final String EspejoAguaEmbalse = "espejo_agua_embalse";
    public static final String TipoTomaEmbalse = "tipo_toma_embalse";
    public static final String CapacidadTomaEmbalse = "capacidad_toma_embalse";
    public static final String TipoAliviaderoEmbalse = "tipo_aliviadero_embalse";
    public static final String CapacidadAliviaderoEmbalse = "capacidad_aliviadero_embalse";
    //**********FIN BASE DE DATOS EMBALSE**************************************//

    //**********BASE DE DATOS DERIVACION**************************************//
    public static final String N_TABLA3 = "Tabla_planarsat_derivacion";
    public static final String TipoDerivacion = "tipo_derivacion";
    public static final String CapacidadDerivacion = "capacidad_derivacion";
    public static final String CapacidadDesarenador = "capacidad_desarenador";
    //**********FIN BASE DE DATOS EMBALSE**************************************//

    //**********BASE DE DATOS DERIVACION**************************************//
    public static final String N_TABLA4 = "Tabla_planarsat_laguna";
    public static final String CapacidadLaguna = "capacidad_laguna";
    public static final String EspejoAguaLaguna = "espejo_agua_laguna";
    public static final String TipoLaguna = "tipo_laguna";
    public static final String AlturaDiqueLaguna = "altura_dique_laguna";
    public static final String LongitudDiqueLaguna = "Longitud_dique_laguna";
    //**********FIN BASE DE DATOS EMBALSE**************************************//



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
            Observacion + " TEXT NOT NULL, " +
            NombrePanoramica + " TEXT NOT NULL, " +
            NombreDetalle + " TEXT NOT NULL" + " )";

    private final String sql1 = "CREATE TABLE " + N_TABLA2 +
            "(" +
            ID_FILA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FuncionarioNombre + " TEXT NOT NULL, " +
            FechaCaptura + " TEXT NOT NULL, " +
            NombreSistemaRiego + " TEXT NOT NULL, " +
            TipoObraCaptacion + " TEXT NOT NULL, " +
            NombreFotoCaptacion + " TEXT NOT NULL, " +
            TipoObraConduccion + " TEXT NOT NULL, " +
            CapacidadObraConduccion + " TEXT NOT NULL, " +
            NombreFotoConduccion + " TEXT NOT NULL, " +
            TipoObraDistribucion + " TEXT NOT NULL, " +
            CapacidadObraDistribucion + " TEXT NOT NULL, " +
            NombreFotoDistribucion + " TEXT NOT NULL, " +
            TipoDiqueEmbalse + " TEXT NOT NULL, " +
            LongitudDiqueEmbalse + " TEXT NOT NULL, " +
            AlturaDiqueEmbalse + " TEXT NOT NULL, " +
            EspejoAguaEmbalse + " TEXT NOT NULL, " +
            TipoTomaEmbalse + " TEXT NOT NULL, " +
            CapacidadTomaEmbalse + " TEXT NOT NULL, " +
            TipoAliviaderoEmbalse + " TEXT NOT NULL, " +
            CapacidadAliviaderoEmbalse + " TEXT NOT NULL, " +
            SuperficieAreaRiego + " TEXT NOT NULL, " +
            CultivosAreaRiego + " TEXT NOT NULL, " +
            MetodosRiego + " TEXT NOT NULL, " +
            AreaRegable + " TEXT NOT NULL, " +
            AreaBajoRiego + " TEXT NOT NULL, " +
            AreaRegada + " TEXT NOT NULL, " +
            NombreFotoAreaRiego + " TEXT NOT NULL, " +
            Problemas + " TEXT NOT NULL, " +
            Observacion + " TEXT NOT NULL, " +
            Longitud + " TEXT NOT NULL, " +
            Latitud + " TEXT NOT NULL" + " )";

    private final String sql2 = "CREATE TABLE " + N_TABLA3 +
            "(" +
            ID_FILA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FuncionarioNombre + " TEXT NOT NULL, " +
            FechaCaptura + " TEXT NOT NULL, " +
            NombreSistemaRiego + " TEXT NOT NULL, " +
            TipoObraCaptacion + " TEXT NOT NULL, " +
            NombreFotoCaptacion + " TEXT NOT NULL, " +
            TipoObraConduccion + " TEXT NOT NULL, " +
            CapacidadObraConduccion + " TEXT NOT NULL, " +
            NombreFotoConduccion + " TEXT NOT NULL, " +
            TipoObraDistribucion + " TEXT NOT NULL, " +
            CapacidadObraDistribucion + " TEXT NOT NULL, " +
            NombreFotoDistribucion + " TEXT NOT NULL, " +
            TipoDerivacion + " TEXT NOT NULL, " +
            CapacidadDerivacion + " TEXT NOT NULL, " +
            CapacidadDesarenador + " TEXT NOT NULL, " +
            SuperficieAreaRiego + " TEXT NOT NULL, " +
            CultivosAreaRiego + " TEXT NOT NULL, " +
            MetodosRiego + " TEXT NOT NULL, " +
            AreaRegable + " TEXT NOT NULL, " +
            AreaBajoRiego + " TEXT NOT NULL, " +
            AreaRegada + " TEXT NOT NULL, " +
            NombreFotoAreaRiego + " TEXT NOT NULL, " +
            Problemas + " TEXT NOT NULL, " +
            Observacion + " TEXT NOT NULL, " +
            Longitud + " TEXT NOT NULL, " +
            Latitud + " TEXT NOT NULL" + " )";

    private final String sql3 = "CREATE TABLE " + N_TABLA4 +
            "(" +
            ID_FILA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FuncionarioNombre + " TEXT NOT NULL, " +
            FechaCaptura + " TEXT NOT NULL, " +
            NombreSistemaRiego + " TEXT NOT NULL, " +
            TipoObraCaptacion + " TEXT NOT NULL, " +
            NombreFotoCaptacion + " TEXT NOT NULL, " +
            TipoObraConduccion + " TEXT NOT NULL, " +
            CapacidadObraConduccion + " TEXT NOT NULL, " +
            NombreFotoConduccion + " TEXT NOT NULL, " +
            TipoObraDistribucion + " TEXT NOT NULL, " +
            CapacidadObraDistribucion + " TEXT NOT NULL, " +
            NombreFotoDistribucion + " TEXT NOT NULL, " +
            CapacidadLaguna + " TEXT NOT NULL, " +
            EspejoAguaLaguna + " TEXT NOT NULL, " +
            TipoLaguna + " TEXT NOT NULL, " +
            AlturaDiqueLaguna + " TEXT NOT NULL, " +
            LongitudDiqueLaguna + " TEXT NOT NULL, " +
            SuperficieAreaRiego + " TEXT NOT NULL, " +
            CultivosAreaRiego + " TEXT NOT NULL, " +
            MetodosRiego + " TEXT NOT NULL, " +
            AreaRegable + " TEXT NOT NULL, " +
            AreaBajoRiego + " TEXT NOT NULL, " +
            AreaRegada + " TEXT NOT NULL, " +
            NombreFotoAreaRiego + " TEXT NOT NULL, " +
            Problemas + " TEXT NOT NULL, " +
            Observacion + " TEXT NOT NULL, " +
            Longitud + " TEXT NOT NULL, " +
            Latitud + " TEXT NOT NULL" + " )";



    /**
     * Constructor de clase
     * */
    public SQLiteHelper(Context context) {
        super( context, N_BD, null, VERSION_BD );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( sql );
        db.execSQL( sql1 );
        db.execSQL( sql2 );
        db.execSQL( sql3 );

    }

    @Override
    public void onUpgrade( SQLiteDatabase db,  int oldVersion, int newVersion ) {
        if ( newVersion > oldVersion )
        {
            //elimina tabla
            db.execSQL( "DROP TABLE IF EXISTS " + N_TABLA );
            db.execSQL( "DROP TABLE IF EXISTS " + N_TABLA2 );
            db.execSQL( "DROP TABLE IF EXISTS " + N_TABLA3 );
            db.execSQL( "DROP TABLE IF EXISTS " + N_TABLA4 );
            //y luego creamos la nueva tabla
            db.execSQL( sql );
            db.execSQL( sql1 );
            db.execSQL( sql2 );
            db.execSQL( sql3 );
        }
    }

}