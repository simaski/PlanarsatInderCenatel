package com.cenatel.desarrollo.planarsatinder;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
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

/**
 * Created by San Casimiro on 26/08/2015.
 */
public class Vialidad extends Fragment implements LocationListener {

    //****************Spinner**************************//
    public Spinner spi_tipopavimento;

    //****************EditText**************************//
    public EditText et_tipoObraCaptacion;

    //****************TextView**************************//
    public TextView tvLatitud;
    public TextView tvLongitud;
    public TextView tvPrecision;
    public TextView latp1;
    public TextView latp2;
    public TextView lonp1;
    public TextView lonp2;

    //****************Button**************************//
    public Button btCapturar;

    //****************ImageView**************************//
    public ImageView imv_captacion;
    public ImageView imv_conduccion;
    public ImageView imv_distribucion;
    public ImageView imv_areaderiego;

    //****************String**************************//
    public String st_et_inspectorR;
    public String st_et_fechaCapturaR;
    public String st_spi_tipoObraCaptacionR;
    public String st_spi_tipopavimentoR;
    public String latglobal;
    public String longlobal;

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
        View v = inflater.inflate(R.layout.fragment_vialidad, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Vialidad");
        ((MainActivity) getActivity()).setVariable(1);
        in_acum = 1;

        st_et_inspectorR = getArguments().getString("Key");
        st_et_fechaCapturaR = getArguments().getString("Key2");
        //st_spi_tipoObraCaptacionR = getArguments().getString("Key3");

        /*et_tipoObraCaptacion = (EditText) v.findViewById(R.id.et_tipoObracaptacion);
        et_tipoObraCaptacion.setText(st_spi_tipoObraCaptacionR);*/

        tvLatitud = (TextView) v.findViewById(R.id.latitudres);
        tvLongitud = (TextView) v.findViewById(R.id.longitudres);
        tvPrecision = (TextView) v.findViewById(R.id.precisonres);

        latp1 = (TextView) v.findViewById(R.id.lat_p1);
        latp2 = (TextView) v.findViewById(R.id.lat_p2);
        lonp1 = (TextView) v.findViewById(R.id.lon_p1);
        lonp2 = (TextView) v.findViewById(R.id.lon_p2);

        btCapturar = (Button) v.findViewById(R.id.bt_capturar);
        btCapturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvLatitud.getText().toString().equals("No disponible")) {
                    Toast.makeText(getActivity(), "No ha posicionado aun. Por favor espere!", Toast.LENGTH_SHORT).show();
                } else if(in_acum == 1){
                    latglobal = tvLatitud.getText().toString();
                    longlobal = tvLongitud.getText().toString();
                    latp1.setText(latglobal);
                    lonp1.setText(longlobal);
                    in_acum = 2;
                    //DialogoPersonalizado();
                }else if (in_acum == 2){
                    latglobal = tvLatitud.getText().toString();
                    longlobal = tvLongitud.getText().toString();
                    latp2.setText(String.valueOf(latglobal));
                    lonp2.setText(String.valueOf(longlobal));
                    //DialogoPersonalizado();
                    btCapturar.setVisibility(View.GONE);
                }
            }
        });

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

    }

}
