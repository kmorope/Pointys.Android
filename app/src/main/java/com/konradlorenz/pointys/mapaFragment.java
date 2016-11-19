package com.konradlorenz.pointys;


import android.*;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class mapaFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mapa, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                UiSettings uiSettings = mMap.getUiSettings();
                uiSettings.setZoomControlsEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mMap.getUiSettings().setMapToolbarEnabled(true);

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getActivity().getApplication(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplication(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more detai

                    return;
                }
                LocationManager locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                final Location localizacion = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LatLng PosicionActual = new LatLng(localizacion.getLatitude(), localizacion.getLongitude());
                if (PosicionActual != null) {
                    mMap.addMarker(new MarkerOptions()
                            .position(PosicionActual)
                            .title(setLocation(localizacion))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PosicionActual, 18));
                    // googleMap.setMyLocationEnabled(true);


                    // For dropping a marker at a point on the Map
//                    LatLng sydney = new LatLng(-34, 151);
//                    googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
//
//                    // For zooming automatically to the location of the marker
//                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
//                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                } else {
                    Toast.makeText(getActivity().getApplication(), "Gps desactivado", Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    //saber posicion actual en direccion y calle
    public String setLocation(Location loc) {
        String direccionActual = null;
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(getActivity().getApplication(), Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    direccionActual = DirCalle.getAddressLine(0);

                    return direccionActual;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException nb) {
                return direccionActual;
            }
        }
        return direccionActual;
    }

}
