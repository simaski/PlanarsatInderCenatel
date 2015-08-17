package com.cenatel.desarrollo.planarsatinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by simaski on 02/08/2015.
 */
public class SistemaDeRiegoPozo extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sistemariegopozo, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Sistema de Riego: Pozo");
        ((MainActivity) getActivity()).setVariable(1);

        return v;
    }

}
