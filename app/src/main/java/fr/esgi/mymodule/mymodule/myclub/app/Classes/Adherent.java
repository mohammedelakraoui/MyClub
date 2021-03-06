package fr.esgi.mymodule.mymodule.myclub.app.Classes;

import android.graphics.Bitmap;

/**
 * Created by melakraoui on 07/08/2014.
 */
public class Adherent {


    private  int id;
    private String nom;
    private  String prenom;
    private  String sexe;
    private int poid;
    private int age;
    private String phone;
    private  String discipline;
    private String pic;


public Adherent(){};

    public Adherent(String nom, String prenom, String sexe, int poid, int age, String phone, String discipline,String pic) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.poid = poid;
        this.age = age;
        this.phone = phone;
        this.discipline = discipline;
        this.pic=pic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Adherent{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", poid=" + poid +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", discipline='" + discipline + '\'' +
                '}';
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }




}
