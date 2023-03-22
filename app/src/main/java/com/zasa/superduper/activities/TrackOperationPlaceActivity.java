package com.zasa.superduper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.zasa.superduper.Fragment.BottomSheetDialog;
//import com.zasa.superduper.MyInfoWindowAdapter;
import com.zasa.superduper.MyInfoWindowAdapter;
import com.zasa.superduper.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TrackOperationPlaceActivity extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment smf;
    String address;
    FusedLocationProviderClient client;
    Button btn_operation_on;
    public static String route;
    public static int routeId;

    TextView txt_routes,txt_addShops;
    private GoogleMap mMap;
    LatLng gourmet_ghalib_market = new LatLng(31.5324454, 74.2987515);
    LatLng gourmet_mini_market = new LatLng(31.5325822, 74.2987514);
    ArrayList<LatLng> arrayList = new ArrayList<>();
    ArrayList<String> title = new ArrayList<String>();
    LocationListener locationListener;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION_UPDATES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_operation_place);

        btn_operation_on = (Button) findViewById(R.id.btn_operationOn);
        txt_addShops = findViewById(R.id.tv_shops);
        txt_addShops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrackOperationPlaceActivity.this,AddShopsActivity.class);
                startActivity(intent);
            }
        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        txt_routes = findViewById(R.id.tv_routes);
        Intent intent = getIntent();
//        route = intent.getExtras().getString("route_name");
//        routeId = intent.getExtras().getString("routeID");
        txt_routes.setText(route);

        btn_operation_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomDrawer();
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient(this);
        smf.getMapAsync(this);
        arrayList.add(gourmet_ghalib_market);
        arrayList.add(gourmet_mini_market);

        title.add("Gourmet Mini Market");
        title.add("Gourmet Ghalib Market");

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                        getmylocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
        openBottomDrawer();
    }

    public void getmylocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                smf.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You are here...!!");

//                        Toast.makeText(TrackOperationPlaceActivity.this, (int) location.getLatitude()+"\n"+location.getLongitude(), Toast.LENGTH_SHORT).show();

                        googleMap.addMarker(markerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                    }
                });
            }
        });
    }

    public void openBottomDrawer() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
        bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
//        LatLng gourmetGhalibMarket = new LatLng(31.5324454,74.2987515);
//        mMap.addMarker(new MarkerOptions().position(gourmetGhalibMarket).title("Gourmet Ghalib Market"));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(31.5325822,74.2987514)).title("Gourmet Mini Market"));
//        mMap.getUiSettings().setMyLocationButtonEnabled(true);
//        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter(TrackOperationPlaceActivity.this));
        for (int i = 0; i < arrayList.size(); i++) {

            for (int j = 0; j < title.size(); j++) {
                mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(j))));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    marker.showInfoWindow();
                    Toast.makeText(TrackOperationPlaceActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
    }
}