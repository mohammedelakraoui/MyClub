package fr.esgi.mymodule.mymodule.myclub.app.Maps;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.MapsBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Maps;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Salle;
import fr.esgi.mymodule.mymodule.myclub.app.R;

public class MapsActivity extends FragmentActivity implements LocationListener, LocationSource{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LocationSource.OnLocationChangedListener mListener;
    private LocationManager locationManager;
    private final String longtitude="longtitude";
    private  final  String latitude="latitude";
    private  Maps maps;
    private MapsBDD mapsBDD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        mapsBDD=new MapsBDD(getBaseContext());

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


    public  void infoLocation()
    {


        AlertDialog.Builder alert = new AlertDialog.Builder(MapsActivity.this);

        alert.setTitle("Ajouter un club?");
        alert.setMessage("Adresse du nouveau club?");

// Set an EditText view to get user input

        final EditText input = new EditText(MapsActivity.this);

        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String addresse_new_club="";

                addresse_new_club= input.getText().toString();
                // Do something with value!
                HashMap<String,Double> coordonnes=getLatitudeLongtitude(addresse_new_club);
                if(coordonnes.size()>0)
                {
                    MapsBDD mapsBDD=new MapsBDD(getBaseContext());
                    Maps maps=new Maps();
                    mapsBDD.open();
                    maps.setNom_Club("Forest Hill");
                    maps.setAdresse(addresse_new_club);
                    maps.setLongtitude(coordonnes.get(longtitude));
                    maps.setLatitude(coordonnes.get(latitude));
                    mapsBDD.insertCoordonnees(maps);
                    Maps maps1 = mapsBDD.getMapsWithAdresse(maps.getAdresse());

                    if(maps1 != null)
                    {
                        //On affiche les infos du livre dans un Toast
                        Toast.makeText(getBaseContext(), "L'ajout à été effectué correctement", Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"Error", Toast.LENGTH_LONG).show();

                    }



                    mapsBDD.close();
                }


            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();



    }


    private HashMap<String,Double> getLatitudeLongtitude(String addresse_club)
    {

        HashMap<String,Double> coordonnes=new HashMap<String, Double>() ;
        Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocationName(addresse_club, 5);
            if (addresses.size() > 0) {

                Double lat = (double) (addresses.get(0).getLatitude());
                Double lon = (double) (addresses.get(0).getLongitude());

                coordonnes.put(longtitude,lat);
                coordonnes.put(latitude,lon);

                final LatLng user = new LatLng(lat, lon);

                Marker hamburg = mMap.addMarker(new MarkerOptions()
                        .position(user)
                        .title(addresse_club)
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.club_icon)));
                // Move the camera instantly to hamburg with a zoom of 15.
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 15));

                // Zoom in, animating the camera.
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      return coordonnes;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.maps, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ajouterCoordonnes:

              infoLocation();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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

        mapsBDD.open();
        ArrayList<Maps> mapsList=new ArrayList<Maps>();

        mapsList=mapsBDD.getAllcoordonnees();
        for(Maps map:mapsList)
        {

            final LatLng pos = new LatLng(map.getLongtitude(), map.getLatitude());
            Marker hamburg = mMap.addMarker(new MarkerOptions()
                    .position(pos)
                    .title(map.getNom_Club() + " : \n " + map.getAdresse()));
                //    .icon(BitmapDescriptorFactory
                     //       .fromResource(R.drawable.club_icon)));
            // Move the camera instantly to hamburg with a zoom of 15.
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));

            // Zoom in, animating the camera.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);


            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
            {

                @Override
                public boolean onMarkerClick(Marker arg0) {
                //    if(arg0.getTitle().equals("MyHome")) // if marker source is clicked
                        Toast.makeText(MapsActivity.this, "Event pour la gestion de la Maps (modifier+ supprimer)"+arg0.getTitle(),Toast.LENGTH_LONG).show();// display toast
                    return true;
                }

            });


            //   mMap.addMarker(new MarkerOptions().position(new LatLng(map.getLongtitude(),map.getLatitude())).title(map.getNom_Club()+" : \n "+map.getAdresse()));
        }


        mapsBDD.close();


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
