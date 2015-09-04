package com.cenatel.desarrollo.planarsatinder;

import android.content.Context;
import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
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
 * Created by San Casimiro on 26/08/2015.
 */
public class Vialidad extends Fragment implements LocationListener {

    //****************Spinner**************************//
    public Spinner spi_tipopavimento;

    //****************EditText**************************//
    public EditText et_puntoOrigen;
    public EditText et_puntoDestino;
    public EditText et_observaciones;
    public EditText et_problemas;

    //****************TextView**************************//
    public TextView tv_Latitud;
    public TextView tv_Longitud;
    public TextView tv_Precision;

    //****************Button**************************//
    public Button bt_CapturarPanoramica;
    public Button bt_CapturarDetalle;
    public Button bt_Enviar;

    //****************ImageView**************************//
    public ImageView imv_panoramica;
    public ImageView imv_detalle;

    //****************String**************************//
    public String st_spi_tipopavimentoR;
    private String st_mCurrentPhotoPath; // String para guardar el camino hacia la foto
    private String st_imageFileName;
    private String st_timeStamp;
    private String st_et_puntoOrigen;
    private String st_et_puntoDestino;
    private String st_et_problemas;
    private String st_et_observaciones;
    private static final String st_JPEG_FILE_PREFIX = "IMG_"; // prefijo imagenes
    private static final String st_JPEG_FILE_SUFFIX = ".jpg"; // sufijo para jpeg
    public String st_et_inspectorR;
    public String st_et_fechaCapturaR;
    public String st_et_tipoObraCaptacionR;
    public String st_longitudpanR;
    public String st_latitudpanR;
    public String st_latituddetR;
    public String st_longituddetR;

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
        View v = inflater.inflate(R.layout.fragment_vialidad, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Vialidad");
        ((MainActivity) getActivity()).setVariable(1);

        st_et_inspectorR = getArguments().getString("Key");
        st_et_fechaCapturaR = getArguments().getString("Key2");
        st_et_tipoObraCaptacionR = "Vialidad";

        in_acum = 1;

        et_puntoOrigen = (EditText) v.findViewById(R.id.et_puntoOrigen);
        et_puntoDestino = (EditText) v.findViewById(R.id.et_puntoDestino);
        et_observaciones = (EditText) v.findViewById(R.id.et_observaciones);
        et_problemas = (EditText) v.findViewById(R.id.et_problemas);

        spi_tipopavimento = (Spinner) v.findViewById(R.id.spi_tipopavimento);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.array_tipopavimento, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_tipopavimento.setAdapter(adapter1);
        spi_tipopavimento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st_spi_tipopavimentoR = spi_tipopavimento.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        tv_Latitud = (TextView) v.findViewById(R.id.latitudres);
        tv_Longitud = (TextView) v.findViewById(R.id.longitudres);
        tv_Precision = (TextView) v.findViewById(R.id.precisonres);

        imv_panoramica = (ImageView) v.findViewById(R.id.imv_panoramica);
        imv_detalle = (ImageView) v.findViewById(R.id.imv_detalle);

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

        bt_CapturarPanoramica = (Button) v.findViewById(R.id.bt_panoramica);
        bt_CapturarDetalle = (Button) v.findViewById(R.id.bt_detalle);
        bt_Enviar = (Button) v.findViewById(R.id.bt_enviar);

        //--------------------CARPETA IMAGENES--------------------------------------------------------------
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Planarsat/Vialidad/");
        if (!f.exists()) {
            f.mkdir();
        }
        //--------------------------------------------------------------------------------------------------

        in_dw = 200;
        in_dh = 200;
        st_timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        st_imageFileName = st_JPEG_FILE_PREFIX + st_timeStamp;
        st_mCurrentPhotoPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Planarsat/Vialidad/" + st_imageFileName;
        imageFileUri = Uri.fromFile(new File(st_mCurrentPhotoPath));

        bt_CapturarPanoramica.setOnClickListener(new View.OnClickListener() {
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

        bt_CapturarDetalle.setOnClickListener(new View.OnClickListener() {
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

        bt_Enviar = (Button) v.findViewById(R.id.bt_enviar);
        bt_Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_puntoOrigen.getText().toString().equals("")) {
                    CamposVacios();
                } else if (et_puntoDestino.getText().toString().equals("")) {
                    CamposVacios();
                } else if (imv_panoramica.getDrawable() == null) {
                    CamposVacios();
                } else if (imv_detalle.getDrawable() == null) {
                    CamposVacios();
                } else {
                    st_et_puntoOrigen = et_puntoOrigen.getText().toString();
                    st_et_puntoDestino = et_puntoDestino.getText().toString();
                    st_et_observaciones = et_observaciones.getText().toString();
                    st_et_problemas = et_problemas.getText().toString();

                    Log.i("Aqui", "DDDDDDDDD "+st_et_inspectorR);

                    sqlite = new SQLite(getActivity());
                    sqlite.abrir();
                    sqlite.addRegistro(st_et_inspectorR, st_et_fechaCapturaR, st_et_tipoObraCaptacionR, st_et_puntoOrigen, st_et_puntoDestino, st_longitudpanR, st_latitudpanR, st_longituddetR, st_latituddetR,
                            st_spi_tipopavimentoR, st_et_problemas, st_et_observaciones);
                    sqlite.cerrar();



                    et_puntoOrigen.setText("");
                    et_puntoDestino.setText("");
                    et_problemas.setText("");
                    et_observaciones.setText("");
                    imv_panoramica.setImageDrawable(null);
                    imv_detalle.setImageDrawable(null);

                }
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
                if (in_acum == 1) {
                    imv_panoramica.setImageBitmap(bitmap1);
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
                    st_latitudpanR= tv_Latitud.getText().toString();
                    st_longitudpanR = tv_Longitud.getText().toString();
                }
                if (in_acum == 2) {
                    imv_detalle.setImageBitmap(bitmap1);
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
                    st_latituddetR = tv_Latitud.getText().toString();
                    st_longituddetR = tv_Longitud.getText().toString();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//---------------------------FIN Funciones IMAGEN-------------------------------------------------------------------

    public void CamposVacios() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        // Setting Dialog Title
        alertDialog.setTitle("Alerta!!!");
        // Setting Dialog Message
        alertDialog.setMessage("Uno o varios campos obligatorios no han sido llenados. O no ha capturado las Fotografias");
        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);
        // On pressing Settings button
        alertDialog.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }


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

    }

}
