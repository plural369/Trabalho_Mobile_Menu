package com.example.menu;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker markerMap;
    private int qt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //mudar o tipo de mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        //clique rápido no mapa
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Adc marcador
                if (qt == 0) {
                    markerMap = mMap.addMarker(
                            new MarkerOptions()
                                    .position(latLng)
                                    .title("Usuario")
                                    .snippet("Eu cliquei aqui")
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                    );
                    qt++;
                }
                else{
                    markerMap.remove();
                    markerMap = mMap.addMarker(
                            new MarkerOptions()
                                    .position(latLng)
                                    .title("Usuario")
                                    .snippet("Eu cliquei aqui")
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                    );
                }
            }
        });
        //Click longo no mapa
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;


                //Apresentar dados
                Toast.makeText(MapsActivity.this,"Você clicou \n Latitudde " + latitude
                        + "\nLongitude" + longitude,Toast.LENGTH_SHORT).show();

                //Adicionar Marcador
                Marker marker = mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Longo")
                                .snippet("Clique longo")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                );
            }
        });


        // Add a marker in Sydney and move the camera
        LatLng serrana = new LatLng(-21.200798, -47.603667);

        mMap.addMarker(
                new MarkerOptions().
                        position(serrana)
                        .title("Casa")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
        );
        //Aplicar ZOOM variando entre 2.0 a 21.0
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(serrana, 19));

    }
}