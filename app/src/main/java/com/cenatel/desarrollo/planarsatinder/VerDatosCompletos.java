package com.cenatel.desarrollo.planarsatinder;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cenatel.desarrollo.planarsatinderbd.SQLite;

import java.util.ArrayList;

/**
 * Created by sancasimiro on 21/10/15.
 */
public class VerDatosCompletos extends Fragment {
    private TextView textView;

    //****************Sqlite**************************//
    private SQLite sqlite;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_verdatoscompletos,container,false);


        textView = (TextView) v.findViewById(R.id.txtResultado);
        textView.setText( ""  );
        int j = getArguments().getInt("Key");
        sqlite = new SQLite(getActivity());
        sqlite.abrir();
        Cursor cursor = sqlite.getRegistro(j);
        ArrayList<String> reg = sqlite.getFormatListUniv(cursor);
        textView.setText( reg.get(0)  );
        return v;
    }

}