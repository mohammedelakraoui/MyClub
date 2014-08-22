package fr.esgi.mymodule.mymodule.myclub.app.Maps;

import android.app.AlertDialog;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import fr.esgi.mymodule.mymodule.myclub.app.R;

public class MapsActivity extends FragmentActivity implements LocationListener, LocationSource{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LocationSource.OnLocationChangedListener mListener;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager != null) {
            boolean gpsIsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean networkIsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (gpsIsEnabled) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10F, (android.location.LocationListener) this);
            }
            if (networkIsEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000L, 10F, (android.location.LocationListener) this);
            }

        }

        setUpMapIfNeeded();



    }


    @Override
    public void onPause()
    {
        if(locationManager != null)
        {
            locationManager.removeUpdates(this);
        }

        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location)
    {
        if( mListener != null )
        {
            mListener.onLocationChanged( location );

            //Move the camera to the user's location once it's available!
            mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();

        setUpMapIfNeeded();

        if(locationManager != null)
        {
            mMap.setMyLocationEnabled(true);
        }
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {

       // 4-6 Rue Louis Armand Paris ; 48.8331220	2.2775678
        //111 Avenue Victor Hugo Aubervilliers; 48.9070929	2.3762860
        //111 Avenue Victor Hugo 93300 Aubervilliers; 48.9070929	2.3762860
        mMap.setMyLocationEnabled(true);

        mMap.addMarker(new MarkerOptions().position(new LatLng(48.8331220,2.2775678)).title("4-6 Rue Louis Armand Paris"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(48.9070929,2.3762860)).title("111 Avenue Victor Hugo Aubervilliers"));
       // mMap.addMarker(new MarkerOptions().position(new LatLng(48.9070929,	2.3762860)).title("4-6 Rue Louis Armand Paris"));
    }



    @Override
    public void activate(OnLocationChangedListener listener)
    {
        mListener = listener;
    }

    @Override
    public void deactivate()
    {
        mListener = null;
    }


    @Override
    public void onProviderDisabled(String provider)
    {
        // TODO Auto-generated method stub
        Toast.makeText(this, "provider disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider)
    {
        // TODO Auto-generated method stub
        Toast.makeText(this, "provider enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        // TODO Auto-generated method stub
        Toast.makeText(this, "status changed", Toast.LENGTH_SHORT).show();
    }
}
