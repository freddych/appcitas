package com.example.retrofitcrud;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.retrofitcrud.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        // Add a marker in Sydney and move the camera
        LatLng upc = new LatLng(-12.074585171881106, -77.09366598392168);
        LatLng sjl = new LatLng(-12.029282527645472, -77.01018468076107);
        LatLng pl = new LatLng(-12.006321399698155, -77.06075919079632);

        mMap.addMarker(new MarkerOptions().position(upc).title("UPC San Miguel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(upc));

        mMap.addMarker(new MarkerOptions().position(sjl).title("Municipalidad SJL"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sjl));

        mMap.addMarker(new MarkerOptions().position(pl).title("Plaza Norte"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pl));
        mMap.animateCamera(CameraUpdateFactory);
    }
}