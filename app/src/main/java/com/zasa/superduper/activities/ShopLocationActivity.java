package com.zasa.superduper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.zasa.superduper.Login.LoginActivity;
import com.zasa.superduper.R;
import com.zasa.superduper.helpers.PreferencesData;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopLocationActivity extends AppCompatActivity {
    LocationListener locationListener;
    private static final int REQUEST_LOCATION_UPDATES = 1;
    LocationManager locationManager;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_location);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(ShopLocationActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ShopLocationActivity.this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION
                    }, REQUEST_LOCATION_UPDATES);
        }

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                final double latitude = location.getLatitude();
                final double longitude = location.getLongitude();


                PreferencesData.saveString(ShopLocationActivity.this, "latitude", String.valueOf(latitude));
                PreferencesData.saveString(ShopLocationActivity.this, "longitude", String.valueOf(longitude));
                ////////// getting full address by lat long start///////////////
                try {
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                   String address = addresses.get(0).getAddressLine(0);

//                    Toast.makeText(CustomerLocationActivity.this, address, Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                ////////// getting full address by lat long end///////////////

//                Toast.makeText(CustomerLocationActivity.this, "Latitude: " + String.valueOf(latitude) + " , Longitude: " + String.valueOf(longitude), Toast.LENGTH_SHORT).show();
            }
        };
        try {
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}