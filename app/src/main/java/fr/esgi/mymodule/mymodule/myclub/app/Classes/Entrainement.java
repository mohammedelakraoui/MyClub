package fr.esgi.mymodule.mymodule.myclub.app.Classes;

import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Entrainements.AfficherEntrainements;

/**
 * Created by melakraoui on 07/08/2014.
 */
public class Entrainement {
    private int id;
    private String nom_seance_entrainement;
    private String date_entrainement;
    private String heur_entrainement;
    private int nombre_places_entrainement;
    private String commentaire;

    public Entrainement()
    {}

    public Entrainement(String nom_seance_entrainement, String date_entrainement, String heur_entrainement, int nombre_places_entrainement, String commentaire) {
        this.nom_seance_entrainement = nom_seance_entrainement;
        this.date_entrainement = date_entrainement;
        this.heur_entrainement = heur_entrainement;
        this.nombre_places_entrainement = nombre_places_entrainement;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_seance_entrainement() {
        return nom_seance_entrainement;
    }

    public void setNom_seance_entrainement(String nom_seance_entrainement) {
        this.nom_seance_entrainement = nom_seance_entrainement;
    }

    public String getDate_entrainement() {


        return date_entrainement;
    }

    public void setDate_entrainement(String date_entrainement) {
        this.date_entrainement = date_entrainement;
    }

    public String getHeur_entrainement() {
        return heur_entrainement;
    }

    public void setHeur_entrainement(String heur_entrainement) {
        this.heur_entrainement = heur_entrainement;
    }

    public int getNombre_places_entrainement() {
        return nombre_places_entrainement;
    }

    public void setNombre_places_entrainement(int nombre_places_entrainement) {
        this.nombre_places_entrainement = nombre_places_entrainement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

}
