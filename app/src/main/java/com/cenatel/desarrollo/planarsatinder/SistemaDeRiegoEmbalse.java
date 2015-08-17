package com.cenatel.desarrollo.planarsatinder;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by San Casimiro on 29/07/2015.
 */
public class SistemaDeRiegoEmbalse extends Fragment implements LocationListener {

    public Spinner spi_tipoObraCaptacion;

    public String spi_tipoObraCaptacionR;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sistemariegoembalse, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Sistema de Riego: Embalse");
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
