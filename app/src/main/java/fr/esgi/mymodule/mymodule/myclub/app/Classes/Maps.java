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



    private int id;

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




@Override
    public String toString() {
        return "Maps{ID="+id+",Nom Club="+Nom_Club+" " +
                "adresse='" + Adresse + '\'' +
                ", Latitude=" + Latitude +
                ", Longtitude=" + Longtitude +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
