package com.cenatel.desarrollo.planarsatinder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    //****************DrawerLayout**************************//
    DrawerLayout drawerLayout;

    //****************Toolbar**************************//
    Toolbar toolbar;

    //****************Action**************************//
    ActionBar actionBar;

    //****************TextView**************************//
    public TextView tv_Latitud;
    public TextView tv_Longitud;
    public TextView tv_Precision;

    //****************Integer**************************//
    int unicode;

    //****************Handler**************************//
    final android.os.Handler hand = new android.os.Handler();

    //****************LocationManager**************************//
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unicode = 0;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        DatosFragment fragment = new DatosFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

        hand.removeCallbacks(actualizar);
        hand.postDelayed(actualizar,100);

        tv_Latitud = (TextView) findViewById(R.id.latitudres);
        tv_Longitud = (TextView) findViewById(R.id.longitudres);
        tv_Precision = (TextView)   findViewById(R.id.precisonres);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 400, 1, this);


        if (location != null) {
            onLocationChanged(location);
        } else {
            /*tv_Latitud.setText("No disponible");
            tv_Longitud.setText("No disponible");
            tv_Precision.setText("No disponible");*/
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //textView = (TextView) findViewById(R.id.textView);
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_inbox:
                                menuItem.setChecked(true);
                                //textView.setText(menuItem.getTitle());
                                //actionBar.setTitle(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                DatosFragment fragment = new DatosFragment();
                                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.frame, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                                return true;
                            case R.id.item_navigation_drawer_starred:
                                menuItem.setChecked(true);
                                //textView.setText(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                VerDatosListaFragment fragment3 = new VerDatosListaFragment();
                                android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction3.replace(R.id.frame, fragment3);
                                fragmentTransaction3.commit();
                                return true;
                            case R.id.item_navigation_drawer_sent_mail:
                                menuItem.setChecked(true);
                                //textView.setText(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                /*VerRegistrosFragment fragment3 = new VerRegistrosFragment();
                                android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction3.replace(R.id.frame, fragment3);
                                fragmentTransaction3.commit();*/
                                return true;
                            case R.id.item_navigation_drawer_drafts:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_settings:
                                menuItem.setChecked(true);
                                Toast.makeText(MainActivity.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                /*Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivity(intent);*/
                                return true;
                            case R.id.item_navigation_drawer_help_and_feedback:
                                menuItem.setChecked(true);
                                Toast.makeText(MainActivity.this, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }


    public void setActionBarTitle(String title) {
        actionBar.setTitle(title);
    }

    public void setVariable(int var) {
        unicode = var;
    }

    private Runnable actualizar = new Runnable() {
        @Override
        public void run() {
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                showSettingsAlert();
            }
            hand.postDelayed(this,7000);

        }
    };//------------FIN Runnable----------------------------------------------------------------------


    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        // Setting Dialog Title
        alertDialog.setTitle("Configuración GPS");
        // Setting Dialog Message
        alertDialog.setMessage("GPS no está activado. ¿Quieres ir al menú de configuración?");
        // Setting Icon to Dialog
        // alertDialog.setIcon(R.drawable.delete);
        // On pressing Settings button
        alertDialog.setPositiveButton("Configuración",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                        //dialog.cancel();
                    }
                });
        // on pressing cancel button
        alertDialog.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }//------------------------FIN Dialogo GPS-------------------------------------------------------------------------


    @Override
    public void onLocationChanged(Location location) {
        /*tv_Latitud.setText(String.valueOf(location.getLatitude()));
        tv_Longitud.setText(String.valueOf(location.getLongitude()));
        tv_Precision.setText(String.valueOf(location.getAccuracy()));*/
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        locationManager.removeUpdates(this);
        Toast.makeText(this, "Disabled provider " + provider,Toast.LENGTH_SHORT).show();

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            switch (unicode) {
                case 0:
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    // Setting Dialog Title
                    alertDialog.setTitle("Salir de la Aplicaci\u00f3n");
                    // Setting Dialog Message
                    alertDialog.setMessage("\u00bfQuieres salir de la aplicaci\u00f3n?");
                    // Setting Icon to Dialog
                    // alertDialog.setIcon(R.drawable.delete);
                    // On pressing Settings button
                    alertDialog.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    // on pressing cancel button
                    alertDialog.setNegativeButton("Si",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    hand.removeCallbacks(actualizar);
                                    //locationManager.removeUpdates();
                                    onProviderDisabled(LocationManager.GPS_PROVIDER);
                                    MainActivity.this.finish();
                                }
                            });
                    // Showing Alert Message
                    alertDialog.show();
                    break;
                case 1:
                    DatosFragment fragment = new DatosFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
            }

        }
        return true;
    }


}