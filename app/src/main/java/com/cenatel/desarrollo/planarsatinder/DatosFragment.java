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

    //****************Spinner**************************//
    public Spinner spi_Estados;
    public Spinner spi_Municipios;
    public Spinner spi_Parroquias;
    public Spinner spi_Sector;
    public Spinner spi_TipoObra;
    public Spinner spi_tipoObraCaptacion;

    //****************EditText**************************//
    public EditText et_fechaCaptura;
    public EditText et_inspector;

    //****************TextView**************************//
    public TextView tv_ObraCaptacion;
    public TextView tv_tipoObraCaptacion;

    //****************String**************************//
    public String st_spi_TipoObraR;
    public String st_et_inspectorR;
    public String st_et_fechaCapturaR;
    public String st_spi_tipoObraCaptacionR;

    //****************Integer**************************//
    private int in_mYear;
    private int in_mMonth;
    private int in_mDay;

    //****************Calendar**************************//
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
        in_mYear = c.get(Calendar.YEAR);
        in_mMonth = c.get(Calendar.MONTH) + 1;
        in_mDay = c.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        et_inspector = (EditText) v.findViewById(R.id.et_inspector);
        et_fechaCaptura = (EditText) v.findViewById(R.id.et_fechaCaptura);
        et_fechaCaptura.setText(sdf.format(c.getTime()));


        spi_TipoObra = (Spinner) v.findViewById(R.id.spi_tipoObra);
        ArrayAdapter adapter5 = ArrayAdapter.createFromResource(getActivity(), R.array.array_tipoObra, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spi_TipoObra.setAdapter(adapter5);
        spi_TipoObra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st_spi_TipoObraR = spi_TipoObra.getSelectedItem().toString();

                //Toast.makeText(getActivity(), "Spinner1: position=" + position + " id=" + id, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 1:
                        ((MainActivity) getActivity()).setActionBarTitle("Sistema de Riego");
                        tv_ObraCaptacion.setVisibility(View.VISIBLE);
                        tv_tipoObraCaptacion.setVisibility(View.VISIBLE);
                        spi_tipoObraCaptacion.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        //Toast.makeText(getActivity(), "Opcion no disponible", Toast.LENGTH_SHORT).show();
                        if (et_inspector.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Nombre de Inspector Vacio", Toast.LENGTH_SHORT).show();
                        } else {
                            Vialidad fragment = new Vialidad();
                            Bundle parametro = new Bundle();
                            parametro.putString("Key", st_et_inspectorR);
                            parametro.putString("Key2", st_et_fechaCapturaR);
                            parametro.putString("Key3", st_spi_tipoObraCaptacionR);
                            fragment.setArguments(parametro);
                            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame, fragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                        break;
                    case 3:
                        Toast.makeText(getActivity(), "Opcion no disponible", Toast.LENGTH_SHORT).show();
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
                        if (et_inspector.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Nombre de Inspector Vacio", Toast.LENGTH_SHORT).show();
                        } else {
                            st_et_inspectorR = et_inspector.getText().toString();
                            st_et_fechaCapturaR = et_fechaCaptura.getText().toString();
                            st_spi_tipoObraCaptacionR = spi_tipoObraCaptacion.getSelectedItem().toString();
                            SistemaDeRiegoEmbalse fragment = new SistemaDeRiegoEmbalse();
                            Bundle parametro = new Bundle();
                            parametro.putString("Key", st_et_inspectorR);
                            parametro.putString("Key2",st_et_fechaCapturaR);
                            parametro.putString("Key3",st_spi_tipoObraCaptacionR);
                            fragment.setArguments(parametro);
                            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame, fragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            position = 0;
                        }
                        break;
                    case 2:
                        if (et_inspector.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Nombre de Inspector Vacio", Toast.LENGTH_SHORT).show();
                        } else {
                            st_et_inspectorR = et_inspector.getText().toString();
                            st_et_fechaCapturaR = et_fechaCaptura.getText().toString();
                            st_spi_tipoObraCaptacionR = spi_tipoObraCaptacion.getSelectedItem().toString();
                        SistemaDeRiegoDerivacion fragment1 = new SistemaDeRiegoDerivacion();
                            Bundle parametro = new Bundle();
                            parametro.putString("Key", st_et_inspectorR);
                            parametro.putString("Key2",st_et_fechaCapturaR);
                            parametro.putString("Key3",st_spi_tipoObraCaptacionR);
                            fragment1.setArguments(parametro);
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.frame, fragment1);
                        fragmentTransaction1.addToBackStack(null);
                        fragmentTransaction1.commit();
                        }
                        break;
                    case 3:
                        if (et_inspector.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Nombre de Inspector Vacio", Toast.LENGTH_SHORT).show();
                        } else {
                            st_et_inspectorR = et_inspector.getText().toString();
                            st_et_fechaCapturaR = et_fechaCaptura.getText().toString();
                            st_spi_tipoObraCaptacionR = spi_tipoObraCaptacion.getSelectedItem().toString();
                            SistemaDeRiegoPozo fragment2 = new SistemaDeRiegoPozo();
                            Bundle parametro = new Bundle();
                            parametro.putString("Key", st_et_inspectorR);
                            parametro.putString("Key2", st_et_fechaCapturaR);
                            parametro.putString("Key3", st_spi_tipoObraCaptacionR);
                            fragment2.setArguments(parametro);
                            android.support.v4.app.FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
                            fragmentTransaction2.replace(R.id.frame, fragment2);
                            fragmentTransaction2.addToBackStack(null);
                            fragmentTransaction2.commit();
                        }
                        break;
                    case 4:
                        if (et_inspector.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Nombre de Inspector Vacio", Toast.LENGTH_SHORT).show();
                        } else {
                            st_et_inspectorR = et_inspector.getText().toString();
                            st_et_fechaCapturaR = et_fechaCaptura.getText().toString();
                            st_spi_tipoObraCaptacionR = spi_tipoObraCaptacion.getSelectedItem().toString();
                            SistemaDeRiegoLaguna fragment3 = new SistemaDeRiegoLaguna();
                            Bundle parametro = new Bundle();
                            parametro.putString("Key", st_et_inspectorR);
                            parametro.putString("Key2", st_et_fechaCapturaR);
                            parametro.putString("Key3", st_spi_tipoObraCaptacionR);
                            fragment3.setArguments(parametro);
                            android.support.v4.app.FragmentTransaction fragmentTransaction3 = getFragmentManager().beginTransaction();
                            fragmentTransaction3.replace(R.id.frame, fragment3);
                            fragmentTransaction3.addToBackStack(null);
                            fragmentTransaction3.commit();
                        }
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
