package com.myapplicationdev.android.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;
    LatLng poi_Central, poi_East, poi_North, poi_SG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                UiSettings ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);
                ui.setMyLocationButtonEnabled(true);

                poi_East = new LatLng(1.352730, 103.944739);
                Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                poi_Central = new LatLng(1.300908, 103.839842);
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                poi_North = new LatLng(1.44224, 103.785733);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.btn_star_big_on)));

                poi_SG = new LatLng(1.3521, 103.8189);
                Marker sg = map.addMarker(new
                        MarkerOptions()
                        .position(poi_SG));
                CameraUpdate location = CameraUpdateFactory.newLatLngZoom(poi_SG, 0);
                map.animateCamera(location);
            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    CameraUpdate location = CameraUpdateFactory.newLatLngZoom(poi_North, 15);
                    map.animateCamera(location);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    CameraUpdate location = CameraUpdateFactory.newLatLngZoom(poi_Central, 15);
                    map.animateCamera(location);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    CameraUpdate location = CameraUpdateFactory.newLatLngZoom(poi_East, 15);
                    map.animateCamera(location);
                }
            }
        });
    }
}