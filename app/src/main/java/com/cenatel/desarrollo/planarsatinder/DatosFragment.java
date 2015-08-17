package com.cenatel.desarrollo.planarsatinder;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by San Casimiro on 29/07/2015.
 */
public class DatosFragment extends Fragment implements LocationListener {

    public Spinner spi_Estados;
    public Spinner spi_Municipios;
    public Spinner spi_Parroquias;
    public Spinner spi_Sector;
    public Spinner spi_TipoObra;
    public Spinner spi_tipoObraCaptacion;

    public TextView tv_ObraCaptacion;
    public TextView tv_tipoObraCaptacion;

    public EditText et_fechaCaptura;
    public EditText et_inspector;

    public String spi_EstadosR;
    public String spi_MunicipiosR;
    public String spi_ParroquiasR;
    public String spi_SectorR;
    public String spi_TipoObraR;

    private int mYear;
    private int mMonth;
    private int mDay;

    private Calendar c;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_datos, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Planarsat Inder");
        ((MainActivity) getActivity()).setVariable(0);

        tv_ObraCaptacion = (TextView) v.findViewById(R.id.tv_ocaptacion);
        tv_tipoObraCaptacion = (TextView) v.findViewById(R.id.tv_tipoObraCaptacion);
        spi_tipoObraCaptacion = (Spinner) v.findViewById(R.id.spi_tipoObracaptacion);

       //**************Ocultar el teclado por defecto hasta el touch en el edittext***********************//
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //***********************************************************************************************//

        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        et_inspector = (EditText) v.findViewById(R.id.et_inspector);
        et_fechaCaptura = (EditText) v.findViewById(R.id.et_fechaCaptura);
        et_fechaCaptura.setText(sdf.format(c.getTime()));

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

                //Toast.makeText(getActivity(), "Spinner1: position=" + position + " id=" + id, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 1:
                        ((MainActivity) getActivity()).setActionBarTitle("Sistema de Riego");
                        tv_ObraCaptacion.setVisibility(View.VISIBLE);
                        tv_tipoObraCaptacion.setVisibility(View.VISIBLE);
                        spi_tipoObraCaptacion.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "Opcion no disponible", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                                /*VerRegistrosFragment fragment3 = new VerRegistrosFragment();
                                android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction3.replace(R.id.frame, fragment3);
                                fragmentTransaction3.commit();*/
                        break;
                    }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter adapter6 = ArrayAdapter.createFromResource(getActivity(), R.array.array_tipoObraCaptacion, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_tipoObraCaptacion.setAdapter(adapter6);
        spi_tipoObraCaptacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        SistemaDeRiegoEmbalse fragment = new SistemaDeRiegoEmbalse();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        position = 0;
                        break;
                    case 2:
                        SistemaDeRiegoDerivacion fragment1 = new SistemaDeRiegoDerivacion();
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.frame, fragment1);
                        fragmentTransaction1.addToBackStack(null);
                        fragmentTransaction1.commit();
                        break;
                    case 3:
                        SistemaDeRiegoPozo fragment2 = new SistemaDeRiegoPozo();
                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.frame, fragment2);
                        fragmentTransaction2.addToBackStack(null);
                        fragmentTransaction2.commit();
                        break;
                    case 4:
                        SistemaDeRiegoLaguna fragment3 = new SistemaDeRiegoLaguna();
                        android.support.v4.app.FragmentTransaction fragmentTransaction3 = getFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.frame, fragment3);
                        fragmentTransaction3.addToBackStack(null);
                        fragmentTransaction3.commit();
                        break;
                }
                spi_tipoObraCaptacion.setSelection(0);
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
