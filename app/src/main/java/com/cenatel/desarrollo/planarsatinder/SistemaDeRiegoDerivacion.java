package com.cenatel.desarrollo.planarsatinder;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by simaski on 02/08/2015.
 */
public class SistemaDeRiegoDerivacion extends Fragment implements LocationListener {

    public Spinner spi_tipoObraCaptacion;

    public Spinner spi_tipoObraDistribucion;

    public String spi_tipoObraDistribucionR;

    public Spinner spi_tipoObraConduccion;

    public String spi_tipoObraConduccionR;

    public Spinner spi_metodosriego;

    public String spi_metodosriegoR;

    public String et_inspectorR;
    public String et_fechaCapturaR;
    public String spi_tipoObraCaptacionR;

    public EditText et_tipoObraCaptacion;


    private LocationManager locationManager;

    public TextView lblLatitud;
    public TextView lblLongitud;
    public TextView lblPrecision;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sistemariegoderivacion, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Sistema de Riego: Derivacion");
        ((MainActivity) getActivity()).setVariable(1);

        et_inspectorR = getArguments().getString("Key");
        et_fechaCapturaR = getArguments().getString("Key2");
        spi_tipoObraCaptacionR = getArguments().getString("Key3");

        et_tipoObraCaptacion = (EditText) v.findViewById(R.id.et_tipoObracaptacion);
        et_tipoObraCaptacion.setText(spi_tipoObraCaptacionR);

        spi_tipoObraConduccion = (Spinner) v.findViewById(R.id.spi_tipoObraconduccion);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.array_tipoObraConduccion, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_tipoObraConduccion.setAdapter(adapter3);
        spi_tipoObraConduccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spi_tipoObraConduccionR = spi_tipoObraConduccion.getSelectedItem().toString();
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
                spi_tipoObraDistribucionR = spi_tipoObraDistribucion.getSelectedItem().toString();
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
                spi_metodosriegoR = spi_metodosriego.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        lblLatitud = (TextView) v.findViewById(R.id.latitudres);
        lblLongitud = (TextView) v.findViewById(R.id.longitudres);
        lblPrecision = (TextView) v.findViewById(R.id.precisonres);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 400, 1, this);


        if (location != null) {
            onLocationChanged(location);
        } else {
            lblLatitud.setText("No disponible");
            lblLongitud.setText("No disponible");
            lblPrecision.setText("No disponible");
        }

        return v;
    }

    @Override
    public void onLocationChanged(Location location) {

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
