package fr.esgi.mymodule.mymodule.myclub.app.Classes;

/**
 * Created by melakraoui on 07/08/2014.
 */
public class Activite {

    private  int id;
    private String intitule_activite;
    private String date_demarrage;
    private String date_fin;
    private String type_activite;

    private String commentaires;

    public Activite(){};
    public Activite(String intitule_activite, String date_demarrage, String date_fin, String type_activite,String commentaire) {
        this.intitule_activite = intitule_activite;
        this.date_demarrage = date_demarrage;
        this.date_fin = date_fin;
        this.type_activite = type_activite;
        this.commentaires=commentaire;
    }

    @Override
    public String toString() {
        return "Activite{" +
                "id=" + id +
                ", intitule_activite='" + intitule_activite + '\'' +
                ", date_demarrage='" + date_demarrage + '\'' +
                ", date_fin='" + date_fin + '\'' +
                ", type_activite='" + type_activite + '\'' +
                ", commentaires='" + commentaires + '\'' +
                '}';
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule_activite() {
        return intitule_activite;
    }

    public void setIntitule_activite(String intitule_activite) {
        this.intitule_activite = intitule_activite;
    }

    public String getDate_demarrage() {
        return date_demarrage;
    }

    public void setDate_demarrage(String date_demarrage) {
        this.date_demarrage = date_demarrage;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getType_activite() {
        return type_activite;
    }

    public void setType_activite(String type_activite) {
        this.type_activite = type_activite;
    }
}
