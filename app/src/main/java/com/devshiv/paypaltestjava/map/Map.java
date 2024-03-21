package com.devshiv.paypaltestjava.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.devshiv.paypaltestjava.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener {

    private static final PatternItem DOT = new Dot();
    private FusedLocationProviderClient fusedLocationProviderClient;

    private double lastestLatitude;
    private double lastestLongtitude;



    private static final int FINE_PERMISSION_CODE = 1;

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    lastestLatitude = location.getLatitude();
                    lastestLongtitude = location.getLongitude();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // VD1: Add a marker in Nha Van Hoa Sinh Vien and move the camera
//        // Add a marker in Nha Van Hoa Sinh Vien and move the camera
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(nvhsv).zoom(18f).build();
//
//        googleMap.addMarker(new MarkerOptions().position(nvhsv).title("Nha Van Hoa Sinh Vien"));
//        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //------------------------------------------------------------------------------------------
        // VD2: Draw a line between Nha Van Hoa Sinh Vien and my house. And add click event for the line


        // Nha Van Hoa Sinh Vien position
//        LatLng nvhsv = new LatLng(lastestLatitude, lastestLongtitude);
        LatLng nvhsv = new LatLng(10.875334512286456, 106.80071266724018);
        // My house position
        LatLng myHouse = new LatLng(10.718460164145629, 106.6283236249088);

        // Add markers for Nha Van Hoa Sinh Vien and my house. Then move the camera to the position
        // where we can see both markers
        googleMap.addMarker(new MarkerOptions().position(nvhsv).title("Nha Van Hoa Sinh Vien"));
        googleMap.addMarker(new MarkerOptions().position(myHouse).title("My house"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nvhsv, 25f));

        // Draw a line between Nha Van Hoa Sinh Vien and my house. Show the distance between them
        // and estimate the time to go from Nha Van Hoa Sinh Vien to my house and vice versa, by
        // walking, driving, bicycling, and transit
        Polyline polyline = googleMap.addPolyline(new PolylineOptions()
                .add(nvhsv, myHouse)
                .clickable(true)
                .width(10)
                .color(Color.RED));
        // polyline.setWidth(10);
        // polyline.setColor(Color.RED);

        // Set listeners for click events
        googleMap.setOnPolylineClickListener(this);

        //------------------------------------------------------------------------------------------
        // VD3: Calculate the distance and estimate time to go from Nha Van Hoa Sinh Vien to my house
        // by walking, driving, bicycling, and transit
    }

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {
        // Flip from solid stroke to dotted stroke pattern.
        if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {
            List<PatternItem> pattern = new ArrayList<>();
            pattern.add(DOT);
            polyline.setPattern(pattern);
        } else {
            // The default pattern is a solid stroke.
            polyline.setPattern(null);
        }

        // Calculate the distance between Nha Van Hoa Sinh Vien and my house
        BigDecimal distance = haversineDistance(polyline.getPoints().get(0), polyline.getPoints().get(1));
        Toast.makeText(this, "Distance: " + distance + " km", Toast.LENGTH_SHORT).show();
    }

    public static BigDecimal haversineDistance(LatLng mk1, LatLng mk2) {
        double R = 6371.0; // Radius of the Earth in kilometers
        double rlat1 = Math.toRadians(mk1.latitude); // Convert degrees to radians
        double rlat2 = Math.toRadians(mk2.latitude); // Convert degrees to radians
        double difflat = rlat2 - rlat1; // Radian difference (latitudes)
        double difflon = Math.toRadians(mk2.longitude - mk1.longitude); // Radian difference (longitudes)

        double a = Math.sin(difflat / 2) * Math.sin(difflat / 2) +
                Math.cos(rlat1) * Math.cos(rlat2) *
                        Math.sin(difflon / 2) * Math.sin(difflon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double d = R * c; // Distance in kilometers
        return new BigDecimal(d).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == FINE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else{
                Toast.makeText(this, "Location permission is denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}