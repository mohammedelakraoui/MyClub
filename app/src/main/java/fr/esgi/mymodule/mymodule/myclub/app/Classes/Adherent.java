package fr.esgi.mymodule.mymodule.myclub.app.Classes;

/**
 * Created by melakraoui on 07/08/2014.
 */
public class Adherent {
    private String nom;
    private  String prenom;
    private  String sexe;
    private int poid;
    private int age;
    private String phone;
    private  String discipline;

    public Adherent(){};

    public Adherent(String nom, String prenom, String sexe, int poid, int age, String phone, String discipline) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.poid = poid;
        this.age = age;
        this.phone = phone;
        this.discipline = discipline;
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
