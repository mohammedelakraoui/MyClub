package fr.esgi.mymodule.mymodule.myclub.app.Classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by melakraoui on 22/08/2014.
 */
public class Maps {




    private  String Nom_Club;
    private String Adresse;
    private double Latitude;
    private  double Longtitude;

   public Maps(){}

    public Maps( String nom_Club, String adresse,double longtitude, double latitude) {
        Longtitude = longtitude;
        Latitude = latitude;
        Adresse = adresse;
        Nom_Club = nom_Club;
    }


public  void infoLocation(Context c)
{

    final String addresse_club="";

    AlertDialog.Builder alert = new AlertDialog.Builder(c);

    alert.setTitle("Ajouter un club?");
    alert.setMessage("Message");

// Set an EditText view to get user input
    final EditText input = new EditText(c);
    alert.setView(input);

    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {

            // Do something with value!


        }
    });

    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
            // Canceled.
        }
    });

    alert.show();


    Geocoder geoCoder = new Geocoder(c, Locale.getDefault());
    try {
        List<Address> addresses = geoCoder.getFromLocationName(addresse_club, 5);
        if (addresses.size() > 0) {

            Double lat = (double) (addresses.get(0).getLatitude());
            Double lon = (double) (addresses.get(0).getLongitude());




            AlertDialog.Builder adb = new AlertDialog.Builder(c);
            adb.setTitle("Google Map");
            adb.setMessage("lat-long " + lat + "......." + lon);
            adb.setPositiveButton("Close",null);
            adb.show();

            /*    final LatLng user = new LatLng(lat, lon);

                Marker hamburg = map.addMarker(new MarkerOptions()
                        .position(user)
                        .title(adderess)
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.marker)));
                // Move the camera instantly to hamburg with a zoom of 15.
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 15));

                // Zoom in, animating the camera.
                map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);*/
        }
    } catch (IOException e) {
        e.printStackTrace();
    }


}


@Override
    public String toString() {
        return "Maps{"+Nom_Club+" " +
                "adresse='" + Adresse + '\'' +
                ", Latitude=" + Latitude +
                ", Longtitude=" + Longtitude +
                '}';
    }

    public String getNom_Club() {
        return Nom_Club;
    }

    public void setNom_Club(String nom_Club) {
        Nom_Club = nom_Club;
    }
    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(double longtitude) {
        Longtitude = longtitude;
    }
}
