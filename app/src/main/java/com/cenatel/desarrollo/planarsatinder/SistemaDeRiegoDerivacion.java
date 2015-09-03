package com.cenatel.desarrollo.planarsatinder;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by simaski on 02/08/2015.
 */
public class SistemaDeRiegoDerivacion extends Fragment implements LocationListener {

    //****************Spinner**************************//
    public Spinner spi_tipoObraDistribucion;
    public Spinner spi_tipoObraConduccion;
    public Spinner spi_metodosriego;

    //****************EditText**************************//
    public EditText et_tipoObraCaptacion;

    //****************TextView**************************//
    public TextView tvLatitud;
    public TextView tvLongitud;
    public TextView tvPrecision;

    //****************Button**************************//
    public Button btCapturarCaptacion;
    public Button btCapturarConduccion;
    public Button btCapturarDistribucion;
    public Button btCapturarAreaRiego;

    //****************ImageView**************************//
    public ImageView imv_captacion;
    public ImageView imv_conduccion;
    public ImageView imv_distribucion;
    public ImageView imv_areaderiego;

    //****************String**************************//
    public String st_spi_tipoObraDistribucionR;
    public String st_spi_tipoObraConduccionR;
    public String st_spi_metodosriegoR;
    public String st_et_inspectorR;
    public String st_et_fechaCapturaR;
    public String st_spi_tipoObraCaptacionR;
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



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sistemariegoderivacion, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Sistema de Riego: Derivacion");
        ((MainActivity) getActivity()).setVariable(1);

        st_et_inspectorR = getArguments().getString("Key");
        st_et_fechaCapturaR = getArguments().getString("Key2");
        st_spi_tipoObraCaptacionR = getArguments().getString("Key3");

        et_tipoObraCaptacion = (EditText) v.findViewById(R.id.et_tipoObracaptacion);
        et_tipoObraCaptacion.setText(st_spi_tipoObraCaptacionR);

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

        tvLatitud = (TextView) v.findViewById(R.id.latitudres);
        tvLongitud = (TextView) v.findViewById(R.id.longitudres);
        tvPrecision = (TextView) v.findViewById(R.id.precisonres);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 400, 1, this);


        if (location != null) {
            onLocationChanged(location);
        } else {
            tvLatitud.setText("No disponible");
            tvLongitud.setText("No disponible");
            tvPrecision.setText("No disponible");
        }

        btCapturarCaptacion = (Button) v.findViewById(R.id.bt_fotoCaptacion);

        //--------------------CARPETA IMAGENES--------------------------------------------------------------
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Planarsat/");
        if (!f.exists()) {
            f.mkdir();
        }
        //--------------------------------------------------------------------------------------------------

        in_dw = 200;
        in_dh = 200;
        st_timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        st_imageFileName = st_JPEG_FILE_PREFIX + st_timeStamp;
        st_mCurrentPhotoPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Planarsat/" + st_imageFileName;
        imageFileUri = Uri.fromFile(new File(st_mCurrentPhotoPath));

        btCapturarCaptacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvLatitud.getText().toString().equals("No disponible")) {
                    Toast.makeText(getActivity(), "No ha posicionado aun. Por favor espere!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent ivd = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    ivd.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
                    startActivityForResult(ivd, TAKE_PICTURE);
                }

            }
        });

        return v;
    }

    @Override
    public void onLocationChanged(Location location) {
        tvLatitud.setText(String.valueOf(location.getLatitude()));
        tvLongitud.setText(String.valueOf(location.getLongitude()));
        tvPrecision.setText(String.valueOf(location.getAccuracy()));
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
