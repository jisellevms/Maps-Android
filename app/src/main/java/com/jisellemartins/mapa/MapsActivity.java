package com.jisellemartins.mapa;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        LatLng vilaDoMar = new LatLng(-3.698459299951344, -38.5753567004942);

        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(vilaDoMar);
        circleOptions.radius(500);
        circleOptions.strokeWidth(0);
        circleOptions.strokeColor(Color.BLACK);
        circleOptions.fillColor(Color.argb(128,255, 204, 255));
        mMap.addCircle(circleOptions);

        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-3.6982218617994707, -38.57568950966656));
        polygonOptions.add(new LatLng(-3.6987800508919375, -38.57543238723994));
        polygonOptions.add(new LatLng(-3.6983794152079548, -38.57493618606577));
        polygonOptions.strokeColor(Color.argb(255,255, 204, 255));
        mMap.addPolygon(polygonOptions);

        mMap.setOnMapClickListener(latLng -> {

            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.add(vilaDoMar);
            polylineOptions.add(latLng);
            polylineOptions.color(Color.BLACK);
            polylineOptions.width(20);

            mMap.addPolyline(polylineOptions);

            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("Local")
                    .snippet("Descrição")
                    //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.cactus)));
        });
        mMap.addMarker(new MarkerOptions()
                .position(vilaDoMar)
                .title("Vila do Mar")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.rose)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vilaDoMar, 18));
    }
}