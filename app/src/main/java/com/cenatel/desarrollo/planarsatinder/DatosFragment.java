package com.cenatel.desarrollo.planarsatinder;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by San Casimiro on 29/07/2015.
 */
public class DatosFragment extends Fragment implements LocationListener {

    public Spinner spi_Estados;
    public Spinner spi_Municipios;
    public Spinner spi_Parroquias;
    public Spinner spi_Sector;
    public Spinner spi_TipoObra;

    public String spi_EstadosR;
    public String spi_MunicipiosR;
    public String spi_ParroquiasR;
    public String spi_SectorR;
    public String spi_TipoObraR;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_datos, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Planarsat Inder");
        spi_Estados = (Spinner) v.findViewById(R.id.spi_estado);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.arr_estados, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_Estados.setAdapter(adapter1);
        spi_Estados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spi_EstadosR = spi_Estados.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spi_Municipios = (Spinner) v.findViewById(R.id.spi_municipio);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.arr_estados, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_Municipios.setAdapter(adapter2);
        spi_Municipios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spi_MunicipiosR = spi_Municipios.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spi_Parroquias = (Spinner) v.findViewById(R.id.spi_parroquia);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.arr_estados, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_Parroquias.setAdapter(adapter3);
        spi_Parroquias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spi_ParroquiasR = spi_Parroquias.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spi_Sector = (Spinner) v.findViewById(R.id.spi_sector);
        ArrayAdapter adapter4 = ArrayAdapter.createFromResource(getActivity(), R.array.arr_estados, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_Sector.setAdapter(adapter4);
        spi_Sector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spi_SectorR = spi_Sector.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spi_TipoObra = (Spinner) v.findViewById(R.id.spi_tipoObra);
        ArrayAdapter adapter5 = ArrayAdapter.createFromResource(getActivity(), R.array.array_tipoObra, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_TipoObra.setAdapter(adapter5);
        spi_TipoObra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spi_TipoObraR = spi_TipoObra.getSelectedItem().toString();
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
