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
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by simaski on 02/08/2015.
 */
public class SistemaDeRiegoPozo extends Fragment implements LocationListener {

    public Spinner spi_tipoObraCaptacion;

    public String spi_tipoObraCaptacionR;

    public Spinner spi_tipoObraDistribucion;

    public String spi_tipoObraDistribucionR;

    public Spinner spi_tipoObraConduccion;

    public String spi_tipoObraConduccionR;

    public Spinner spi_metodosriego;

    public String spi_metodosriegoR;

    public Spinner spi_calidadagua;

    public String spi_calidadaguaR;

    public Spinner spi_abatimiento;

    public String spi_abatimientoR;

    public Spinner spi_tipobomba;

    public String spi_tipobombaR;


    private LocationManager locationManager;

    public TextView lblLatitud;
    public TextView lblLongitud;
    public TextView lblPrecision;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sistemariegopozo, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Sistema de Riego: Pozo");
        ((MainActivity) getActivity()).setVariable(1);

        spi_tipoObraCaptacion = (Spinner) v.findViewById(R.id.spi_tipoObracaptacion);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.array_tipoObraCaptacion, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_tipoObraCaptacion.setAdapter(adapter1);
        spi_tipoObraCaptacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spi_tipoObraCaptacionR = spi_tipoObraCaptacion.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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

        spi_calidadagua = (Spinner) v.findViewById(R.id.spi_calidadaguapozo);
        ArrayAdapter adapter4 = ArrayAdapter.createFromResource(getActivity(), R.array.array_calidadagua, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_calidadagua.setAdapter(adapter4);
        spi_calidadagua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spi_calidadaguaR = spi_calidadagua.getSelectedItem().toString();
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

        spi_abatimiento = (Spinner) v.findViewById(R.id.spi_abatimientopozo);
        ArrayAdapter adapter6 = ArrayAdapter.createFromResource(getActivity(), R.array.array_abatimiento, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_abatimiento.setAdapter(adapter6);
        spi_abatimiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spi_abatimientoR = spi_abatimiento.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spi_tipobomba = (Spinner) v.findViewById(R.id.spi_tipobombapozo);
        ArrayAdapter adapter7 = ArrayAdapter.createFromResource(getActivity(), R.array.array_tipobomba, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_tipobomba.setAdapter(adapter7);
        spi_tipobomba.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spi_tipobombaR = spi_tipobomba.getSelectedItem().toString();
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
        lblLatitud.setText(String.valueOf(location.getLatitude()));
        lblLongitud.setText(String.valueOf(location.getLongitude()));
        lblPrecision.setText(String.valueOf(location.getAccuracy()));
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
