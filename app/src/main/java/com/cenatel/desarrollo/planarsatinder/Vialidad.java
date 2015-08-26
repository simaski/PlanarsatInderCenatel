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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by San Casimiro on 26/08/2015.
 */
public class Vialidad extends Fragment implements LocationListener {

    public TextView latp1;
    public TextView latp2;
    public TextView lonp1;
    public TextView lonp2;

    public String et_inspectorR;
    public String et_fechaCapturaR;
    public String spi_tipoObraCaptacionR;
    public String spi_tipopavimentoR;

    public Spinner spi_tipopavimento;

    public EditText et_tipoObraCaptacion;

    private LocationManager locationManager;

    public Button btCapturar;

    public String latglobal;
    public String longlobal;

    public TextView lblLatitud;
    public TextView lblLongitud;
    public TextView lblPrecision;

    public int acum;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vialidad, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Vialidad");
        ((MainActivity) getActivity()).setVariable(1);
        acum = 1;

        et_inspectorR = getArguments().getString("Key");
        et_fechaCapturaR = getArguments().getString("Key2");
        //spi_tipoObraCaptacionR = getArguments().getString("Key3");

        /*et_tipoObraCaptacion = (EditText) v.findViewById(R.id.et_tipoObracaptacion);
        et_tipoObraCaptacion.setText(spi_tipoObraCaptacionR);*/

        lblLatitud = (TextView) v.findViewById(R.id.latitudres);
        lblLongitud = (TextView) v.findViewById(R.id.longitudres);
        lblPrecision = (TextView) v.findViewById(R.id.precisonres);

        latp1 = (TextView) v.findViewById(R.id.lat_p1);
        latp2 = (TextView) v.findViewById(R.id.lat_p2);
        lonp1 = (TextView) v.findViewById(R.id.lon_p1);
        lonp2 = (TextView) v.findViewById(R.id.lon_p2);

        btCapturar = (Button) v.findViewById(R.id.bt_capturar);
        btCapturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lblLatitud.getText().toString().equals("No disponible")) {
                    Toast.makeText(getActivity(), "No ha posicionado aun. Por favor espere!", Toast.LENGTH_SHORT).show();
                } else if(acum == 1){
                    latglobal = lblLatitud.getText().toString();
                    longlobal = lblLongitud.getText().toString();
                    latp1.setText(latglobal);
                    lonp1.setText(longlobal);
                    acum = 2;
                    //DialogoPersonalizado();
                }else if (acum == 2){
                    latglobal = lblLatitud.getText().toString();
                    longlobal = lblLongitud.getText().toString();
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
                spi_tipopavimentoR = spi_tipopavimento.getSelectedItem().toString();
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
