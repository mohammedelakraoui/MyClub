package fr.esgi.mymodule.mymodule.myclub.app.Classes;

/**
 * Created by server-pc on 16/08/2014.
 */
public class Salle {
    private  int id;
    private String nom_salle;
    private  int capacite;
    private String type_activite ;
    private String  nom_coach;
    public Salle(){}
    public Salle(String nom_salle,int capacite,String nom_coach, String type_activite) {

        this.nom_coach = nom_coach;
        this.type_activite = type_activite;
        this.capacite = capacite;
        this.nom_salle = nom_salle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getNom_salle() {
        return nom_salle;
    }

    public void setNom_salle(String nom_salle) {
        this.nom_salle = nom_salle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_activite() {
        return type_activite;
    }

    public void setType_activite(String type_activite) {
        this.type_activite = type_activite;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    @Override
    public String toString() {
        return "salle{" +
                "id=" + id +
                ", nom_salle='" + nom_salle + '\'' +
                ", capacite=" + capacite +
                ", type_activite='" + type_activite + '\'' +
                ", nom_coach='" + nom_coach + '\'' +
                '}';
    }
}