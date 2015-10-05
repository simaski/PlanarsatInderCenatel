package com.cenatel.desarrollo.planarsatinder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cenatel.desarrollo.planarsatinderbd.SQLite;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by simaski on 02/08/2015.
 */
public class SistemaDeRiegoDerivacion extends Fragment implements LocationListener {

    //****************Spinner**************************//
    public Spinner spi_tipoObraConduccion;
    public Spinner spi_tipoObraDistribucion;
    public Spinner spi_tipoDerivacion;
    public Spinner spi_metodosriego;

    //****************EditText**************************//
    public EditText et_tipoObraCaptacion;
    public EditText et_nombreSistemaRiego;
    public EditText et_capacidadObraConduccion;
    public EditText et_capacidadObraDistribucion;
    public EditText et_capacidadDerivacion;
    public EditText et_capacidadDesarenador;
    public EditText et_superficieAreaRiegoDerivacion;
    public EditText et_cultivosAreaRiegoDerivacion;
    public EditText et_areaRegableDerivacion;
    public EditText et_areaBajoRiegoDerivacion;
    public EditText et_areaRegadaDerivacion;
    public EditText et_problemas;
    public EditText et_observaciones;

    //****************TextView**************************//
    public TextView tv_Latitud;
    public TextView tv_Longitud;
    public TextView tv_Precision;

    //****************Button**************************//
    public Button btCapturarCaptacion;
    public Button btCapturarConduccion;
    public Button btCapturarDistribucion;
    public Button btCapturarAreaRiego;
    public Button bt_Enviar;

    //****************ImageView**************************//
    public ImageView imv_captacion;
    public ImageView imv_conduccion;
    public ImageView imv_distribucion;
    public ImageView imv_areaderiego;

    //****************String**************************//
    public String st_et_inspectorR;
    public String st_et_fechaCapturaR;
    public String st_et_nombreSistemaRiegoR;
    public String st_et_tipoObraCaptacionR;
    public String st_tv_tipoObraCaptacionR;
    public String st_spi_tipoObraConduccionR;
    public String st_et_capacidadObraConduccionR;
    public String st_tv_tipoObraConduccionR;
    public String st_spi_tipoObraDistribucionR;
    public String st_et_capacidadObraDistribucionR;
    public String st_tv_tipoObraDistribucionR;
    public String st_spi_tipoDerivacionR;
    public String st_et_capacidadDerivacionR;
    public String st_et_capacidadDesarenadorR;
    public String st_et_superficieAreaRiegoDerivacionR;
    public String st_et_cultivosAreaRiegoDerivacionR;
    public String st_spi_metodosriegoR;
    public String st_et_areaRegableDerivacionR;
    public String st_et_areaBajoRiegoDerivacionR;
    public String st_et_areaRegadaDerivacionR;
    public String st_tv_areaRiegoDerivacionR;
    public String st_et_problemasR;
    public String st_et_observacionesR;
    public String st_tv_longitudR;
    public String st_tv_latitudR;
    private String st_mCurrentPhotoPath; // String para guardar el camino hacia la foto
    private String st_imageFileName;
    private String st_timeStamp;
    private static final String st_JPEG_FILE_PREFIX = "IMG_"; // prefijo imagenes
    private static final String st_JPEG_FILE_SUFFIX = ".jpg"; // sufijo para jpeg

    //****************Integer**************************//
    public static int TAKE_PICTURE = 1;//no lleva in_ por ser una variable usada a la hora de la captura de la imagen
    int in_dw; // ancho pantalla
    int in_dh; // alto pantalla
    int in_acum = 0;

    //****************LocationManager**************************//
    private LocationManager locationManager;

    //****************Uri**************************//
    private Uri imageFileUri; // Ver proveedores de contenidos

    //****************Sqlite**************************//
    private SQLite sqlite;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sistemariegoderivacion, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Sistema de Riego: Derivacion");
        ((MainActivity) getActivity()).setVariable(1);

        st_et_inspectorR = getArguments().getString("Key");
        st_et_fechaCapturaR = getArguments().getString("Key2");
        st_et_tipoObraCaptacionR = getArguments().getString("Key3");

        et_tipoObraCaptacion = (EditText) v.findViewById(R.id.et_tipoObracaptacion);
        et_tipoObraCaptacion.setText(st_et_tipoObraCaptacionR);

        et_nombreSistemaRiego = (EditText) v.findViewById(R.id.et_nombreSistema);
        et_capacidadObraConduccion = (EditText) v.findViewById(R.id.et_capacidadObraConduccion);
        et_capacidadObraDistribucion = (EditText) v.findViewById(R.id.et_capacidadObraDistribucion);
        et_capacidadDerivacion = (EditText) v.findViewById(R.id.et_capacidadderivacion);
        et_capacidadDesarenador = (EditText) v.findViewById(R.id.et_capacidaddesarenador);
        et_superficieAreaRiegoDerivacion = (EditText) v.findViewById(R.id.et_superficieriego);
        et_cultivosAreaRiegoDerivacion = (EditText) v.findViewById(R.id.et_cultivos);
        et_areaRegableDerivacion = (EditText) v.findViewById(R.id.et_arearegable);
        et_areaBajoRiegoDerivacion = (EditText) v.findViewById(R.id.et_areabajoriego);
        et_areaRegadaDerivacion = (EditText) v.findViewById(R.id.et_arearegada);
        et_problemas = (EditText) v.findViewById(R.id.et_problemas);
        et_observaciones = (EditText) v.findViewById(R.id.et_observaciones);


        spi_tipoObraConduccion = (Spinner) v.findViewById(R.id.spi_tipoObraconduccion);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.array_tipoObraConduccion, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_tipoObraConduccion.setAdapter(adapter3);
        spi_tipoObraConduccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st_spi_tipoObraConduccionR = spi_tipoObraConduccion.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spi_tipoObraDistribucion = (Spinner) v.findViewById(R.id.spi_tipoObradistribucion);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.array_tipoObraConduccion, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_tipoObraDistribucion.setAdapter(adapter2);
        spi_tipoObraDistribucion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st_spi_tipoObraDistribucionR = spi_tipoObraDistribucion.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spi_tipoDerivacion = (Spinner) v.findViewById(R.id.spi_tipoderivacion);
        ArrayAdapter adapter4 = ArrayAdapter.createFromResource(getActivity(), R.array.array_tipoDereivacion, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_tipoDerivacion.setAdapter(adapter4);
        spi_tipoDerivacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st_spi_tipoDerivacionR = spi_tipoDerivacion.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spi_metodosriego = (Spinner) v.findViewById(R.id.spi_metodosriego);
        ArrayAdapter adapter5 = ArrayAdapter.createFromResource(getActivity(), R.array.array_metodosriego, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_metodosriego.setAdapter(adapter5);
        spi_metodosriego.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st_spi_metodosriegoR = spi_metodosriego.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        tv_Latitud = (TextView) v.findViewById(R.id.latitudres);
        tv_Longitud = (TextView) v.findViewById(R.id.longitudres);
        tv_Precision = (TextView) v.findViewById(R.id.precisonres);

        imv_captacion = (ImageView) v.findViewById(R.id.imv_captacion);
        imv_conduccion = (ImageView) v.findViewById(R.id.imv_conduccion);
        imv_distribucion = (ImageView) v.findViewById(R.id.imv_distribucion);
        imv_areaderiego = (ImageView) v.findViewById(R.id.imv_areariego);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 400, 1, this);


        if (location != null) {
            onLocationChanged(location);
        } else {
            tv_Latitud.setText("No disponible");
            tv_Longitud.setText("No disponible");
            tv_Precision.setText("No disponible");
        }

        btCapturarCaptacion = (Button) v.findViewById(R.id.bt_fotoCaptacion);
        btCapturarConduccion = (Button) v.findViewById(R.id.bt_fotoConduccion);
        btCapturarDistribucion = (Button) v.findViewById(R.id.bt_fotoDistribucion);
        btCapturarAreaRiego = (Button) v.findViewById(R.id.bt_fotoAreaRiego);

        //--------------------CARPETA IMAGENES--------------------------------------------------------------
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Planarsat/Derivacion/");
        if (!f.exists()) {
            f.mkdir();
        }
        //--------------------------------------------------------------------------------------------------

        in_dw = 200;
        in_dh = 200;
        st_timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        st_imageFileName = st_JPEG_FILE_PREFIX + st_timeStamp;
        st_mCurrentPhotoPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Planarsat/Derivacion/" + st_imageFileName;
        imageFileUri = Uri.fromFile(new File(st_mCurrentPhotoPath));

        btCapturarCaptacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_Latitud.getText().toString().equals("No disponible")) {
                    Toast.makeText(getActivity(), "No ha posicionado aun. Por favor espere!", Toast.LENGTH_SHORT).show();
                } else {
                    in_acum = 1;
                    Intent ivd = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    ivd.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
                    startActivityForResult(ivd, TAKE_PICTURE);
                }

            }
        });

        btCapturarConduccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_Latitud.getText().toString().equals("No disponible")) {
                    Toast.makeText(getActivity(), "No ha posicionado aun. Por favor espere!", Toast.LENGTH_SHORT).show();
                } else {
                    in_acum = 2;
                    Intent ivd = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    ivd.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
                    startActivityForResult(ivd, TAKE_PICTURE);
                }

            }
        });

        btCapturarDistribucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_Latitud.getText().toString().equals("No disponible")) {
                    Toast.makeText(getActivity(), "No ha posicionado aun. Por favor espere!", Toast.LENGTH_SHORT).show();
                } else {
                    in_acum = 3;
                    Intent ivd = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    ivd.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
                    startActivityForResult(ivd, TAKE_PICTURE);
                }

            }
        });

        btCapturarAreaRiego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_Latitud.getText().toString().equals("No disponible")) {
                    Toast.makeText(getActivity(), "No ha posicionado aun. Por favor espere!", Toast.LENGTH_SHORT).show();
                } else {
                    in_acum = 4;
                    Intent ivd = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    ivd.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
                    startActivityForResult(ivd, TAKE_PICTURE);
                }

            }
        });

        bt_Enviar = (Button) v.findViewById(R.id.bt_enviar);
        bt_Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (et_nombreSistemaRiego.getText().toString().equals("")) {
                    CamposVacios();
                } else if (et_capacidadObraDistribucion.getText().toString().equals("")) {
                    CamposVacios();
                } else if (imv_captacion.getDrawable() == null) {
                    CamposVacios();
                } else if (imv_detalle.getDrawable() == null) {
                    CamposVacios();
                } else {*/
                Toast.makeText(getActivity(), "No ha posicionado aun. Por favor espere!", Toast.LENGTH_SHORT).show();

                st_et_nombreSistemaRiegoR = et_nombreSistemaRiego.getText().toString();
                st_et_capacidadObraConduccionR = et_capacidadObraConduccion.getText().toString();
                st_et_capacidadObraDistribucionR = et_capacidadObraDistribucion.getText().toString();
                st_et_capacidadDerivacionR = et_capacidadDerivacion.getText().toString();
                st_et_capacidadDesarenadorR = et_capacidadDesarenador.getText().toString();
                st_et_superficieAreaRiegoDerivacionR = et_superficieAreaRiegoDerivacion.getText().toString();
                st_et_cultivosAreaRiegoDerivacionR = et_cultivosAreaRiegoDerivacion.getText().toString();
                st_et_areaRegableDerivacionR = et_areaRegableDerivacion.getText().toString();
                st_et_areaBajoRiegoDerivacionR = et_areaBajoRiegoDerivacion.getText().toString();
                st_et_areaRegadaDerivacionR = et_areaRegadaDerivacion.getText().toString();
                st_et_problemasR = et_problemas.getText().toString();
                st_et_observacionesR = et_observaciones.getText().toString();
                st_tv_longitudR = tv_Longitud.getText().toString();
                st_tv_latitudR = tv_Latitud.getText().toString();

                Log.i("Aqui", "DDDDDDDDD " + st_et_inspectorR);

                sqlite = new SQLite(getActivity());
                sqlite.abrir();
                sqlite.addRegistroDerivacion(st_et_inspectorR, st_et_fechaCapturaR, st_et_nombreSistemaRiegoR, st_et_tipoObraCaptacionR, st_tv_tipoObraCaptacionR, st_spi_tipoObraConduccionR,
                        st_et_capacidadObraConduccionR, st_tv_tipoObraConduccionR, st_spi_tipoObraDistribucionR, st_et_capacidadObraDistribucionR, st_tv_tipoObraDistribucionR, st_spi_tipoDerivacionR,
                        st_et_capacidadDerivacionR, st_et_capacidadDesarenadorR, st_et_superficieAreaRiegoDerivacionR, st_et_cultivosAreaRiegoDerivacionR, st_spi_metodosriegoR, st_et_areaRegableDerivacionR,
                        st_et_areaBajoRiegoDerivacionR, st_et_areaRegadaDerivacionR, st_tv_areaRiegoDerivacionR, st_et_problemasR, st_et_observacionesR, st_tv_longitudR, st_tv_latitudR);
                sqlite.cerrar();


                    /*et_puntoOrigen.setText("");
                    et_puntoDestino.setText("");
                    et_problemas.setText("");
                    et_observaciones.setText("");
                    imv_panoramica.setImageDrawable(null);
                    imv_detalle.setImageDrawable(null);

                }*/
            }
        });



        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
       /* Se revisa si la imagen viene de la camara (TAKE_PICTURE) o de la galeria (SELECT_PICTURE)*/
        try {
            if (requestCode == TAKE_PICTURE) {

                BitmapFactory.Options bmOptions1 = new BitmapFactory.Options();
                bmOptions1.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(st_mCurrentPhotoPath, bmOptions1);
                int photoW = bmOptions1.outWidth;
                int photoH = bmOptions1.outHeight;
                // Determinar cuanto escalamos la imagen
                int scaleFactor1 = Math.min(photoW / in_dw, photoH / in_dh);
                // Decodificar la imagen en un Bitmap escalado a View
                bmOptions1.inJustDecodeBounds = false;
                bmOptions1.inSampleSize = scaleFactor1;
                bmOptions1.inPurgeable = true;
                Bitmap bitmap1 = BitmapFactory.decodeFile(st_mCurrentPhotoPath, bmOptions1);
                if(in_acum == 1) {
                    imv_captacion.setImageBitmap(bitmap1);
                    st_imageFileName = "Planarsat";
                    File file = new File(st_mCurrentPhotoPath + st_imageFileName + st_JPEG_FILE_SUFFIX);
                    try {
                        file.createNewFile();
                        FileOutputStream out = new FileOutputStream(file);
                        // bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, out);//Convertimos la imagen a JPEG
                        bitmap1.compress(Bitmap.CompressFormat.PNG, 50, out);//Convertimos la imagen a JPEG
                        out.flush();
                        out.close();
                        Toast.makeText(getActivity(), "Hecho 1!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    st_tv_tipoObraCaptacionR = st_mCurrentPhotoPath + st_imageFileName + st_JPEG_FILE_SUFFIX;
                    //latitudpan = tv_Latitud.getText().toString();
                    //longitudpan = tv_Longitud.getText().toString();
                }
                if(in_acum == 2) {
                    imv_conduccion.setImageBitmap(bitmap1);
                    st_imageFileName = "Planarsat";
                    File file = new File(st_mCurrentPhotoPath + st_imageFileName + 1 + st_JPEG_FILE_SUFFIX);
                    try {
                        file.createNewFile();
                        FileOutputStream out = new FileOutputStream(file);
                        // bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, out);//Convertimos la imagen a JPEG
                        bitmap1.compress(Bitmap.CompressFormat.PNG, 50, out);//Convertimos la imagen a JPEG
                        out.flush();
                        out.close();
                        Toast.makeText(getActivity(), "Hecho 2!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    st_tv_tipoObraConduccionR = st_mCurrentPhotoPath + st_imageFileName + 1 + st_JPEG_FILE_SUFFIX;
                    //latitudpan = tv_Latitud.getText().toString();
                    //longitudpan = tv_Longitud.getText().toString();
                }
                if(in_acum == 3) {
                    imv_distribucion.setImageBitmap(bitmap1);
                    st_imageFileName = "Planarsat";
                    File file = new File(st_mCurrentPhotoPath + st_imageFileName + 2 + st_JPEG_FILE_SUFFIX);
                    try {
                        file.createNewFile();
                        FileOutputStream out = new FileOutputStream(file);
                        // bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, out);//Convertimos la imagen a JPEG
                        bitmap1.compress(Bitmap.CompressFormat.PNG, 50, out);//Convertimos la imagen a JPEG
                        out.flush();
                        out.close();
                        Toast.makeText(getActivity(), "Hecho 3!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    st_tv_tipoObraDistribucionR = st_mCurrentPhotoPath + st_imageFileName + 2 + st_JPEG_FILE_SUFFIX;
                    //latitudpan = tv_Latitud.getText().toString();
                    //longitudpan = tv_Longitud.getText().toString();
                }
                if(in_acum == 4) {
                    imv_areaderiego.setImageBitmap(bitmap1);
                    st_imageFileName = "Planarsat";
                    File file = new File(st_mCurrentPhotoPath + st_imageFileName + 3 + st_JPEG_FILE_SUFFIX);
                    try {
                        file.createNewFile();
                        FileOutputStream out = new FileOutputStream(file);
                        // bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, out);//Convertimos la imagen a JPEG
                        bitmap1.compress(Bitmap.CompressFormat.PNG, 50, out);//Convertimos la imagen a JPEG
                        out.flush();
                        out.close();
                        Toast.makeText(getActivity(), "Hecho 4!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    st_tv_areaRiegoDerivacionR = st_mCurrentPhotoPath + st_imageFileName + 3 + st_JPEG_FILE_SUFFIX;
                    //latitudpan = tv_Latitud.getText().toString();
                    //longitudpan = tv_Longitud.getText().toString();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//---------------------------FIN Funciones IMAGEN-------------------------------------------------------------------

    @Override
    public void onLocationChanged(Location location) {
        tv_Latitud.setText(String.valueOf(location.getLatitude()));
        tv_Longitud.setText(String.valueOf(location.getLongitude()));
        tv_Precision.setText(String.valueOf(location.getAccuracy()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        locationManager.removeUpdates(this);
    }
}
