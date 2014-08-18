package fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;

/**
 * Created by melakraoui on 07/08/2014.
 */
public class AdherentBDD {


    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "myclub.db";
    private static final String TABLE_Adherent= "adherents";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_Nom = "NOM";
    private static final int NUM_COL_Nom = 1;
    private static final String COL_Prenom= "prenom";
    private static final int NUM_COL_Prenom = 2;
    private static final String COL_Sexe = "sexe";
    private static final int NUM_COL_SEXE = 3;
    private static final String COL_Poid = "poid";
    private static final int NUM_COL_Poid = 4;
    private static final String COL_Age= "age";
    private static final int NUM_COL_AGE = 5;
    private static final String COL_Phone = "phone";
    private static final int NUM_COL_PHONE = 6;
    private static final String COL_DISCIPLINE= "discipline";
    private static final int NUM_COL_DISCIPLINE = 7;

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public AdherentBDD(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertAdherent(Adherent adherent){

        ContentValues values = new ContentValues();
        values.put(COL_Nom, adherent.getNom());
        values.put(COL_Prenom, adherent.getPrenom());
        values.put(COL_Sexe, adherent.getSexe());
        values.put(COL_Poid, adherent.getPoid());
        values.put(COL_Age, adherent.getAge());
        values.put(COL_Phone, adherent.getPhone());
        values.put(COL_DISCIPLINE, adherent.getDiscipline());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_Adherent, null, values);
    }

    public int updateAdherent(int id, Adherent adherent){
        //La mise à jour d'un Adherent dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel Adherent on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_Nom, adherent.getNom());
        values.put(COL_Prenom, adherent.getPrenom());
        values.put(COL_Sexe, adherent.getSexe());
        values.put(COL_Poid, adherent.getPoid());
        values.put(COL_Age, adherent.getAge());
        values.put(COL_Phone, adherent.getPhone());
        values.put(COL_DISCIPLINE, adherent.getDiscipline());

        return bdd.update(TABLE_Adherent, values, COL_ID + " = " +id, null);
    }

    public int removeAdherentWithID(int id){
        //Suppression d'un Adherent de la BDD grâce à l'ID

        return bdd.delete(TABLE_Adherent ,COL_ID + " = " +id, null);
    }

    public ArrayList<Adherent> getAllAdherent()
    {
        Cursor c = bdd.query(TABLE_Adherent, new String[] {COL_ID, COL_Nom, COL_Prenom,COL_Sexe,COL_Poid,COL_Age,COL_Phone,COL_DISCIPLINE},null, null, null, null, null);
        return cursorToAllAdherent(c);

    }

    public Adherent getAdherentWithNom(String nom){
        //Récupère dans un Cursor les valeurs correspondant à un Adherent contenu dans la BDD (ici on sélectionne le Adherent grâce à son nom)
        Cursor c = bdd.query(TABLE_Adherent, new String[] {COL_ID, COL_Nom, COL_Prenom,COL_Sexe,COL_Poid,COL_Age,COL_Phone,COL_DISCIPLINE}, COL_Nom + " LIKE \"" + nom +"\"", null, null, null, null);
        return cursorToAdherent(c);
    }

    public Adherent getAdherentWithId(String id){
        //Récupère dans un Cursor les valeurs correspondant à un Adherent contenu dans la BDD (ici on sélectionne le Adherent grâce à son titre)
        Cursor c = bdd.query(TABLE_Adherent, new String[] {COL_ID, COL_Nom, COL_Prenom,COL_Sexe,COL_Poid,COL_Age,COL_Phone,COL_DISCIPLINE}, COL_ID + " LIKE \"" + id +"\"", null, null, null, null);
        return cursorToAdherent(c);
    }

    //Cette méthode permet de convertir un cursor en un Adherent
    private Adherent cursorToAdherent(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un Adherent
        Adherent adherent = new Adherent();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        adherent.setId(c.getInt(NUM_COL_ID));
        adherent.setNom(c.getString(NUM_COL_Nom));
        adherent.setPrenom(c.getString(NUM_COL_Prenom));
        adherent.setSexe(c.getString(NUM_COL_SEXE));
        adherent.setPoid(c.getInt(NUM_COL_Poid));
        adherent.setAge(c.getInt(NUM_COL_AGE));
        adherent.setPhone(c.getString(NUM_COL_PHONE));
        adherent.setDiscipline(c.getString(NUM_COL_DISCIPLINE));
        //On ferme le cursor
        c.close();

        //On retourne le Adherent
    return adherent;
    }


    // get la liste des adherents
    private ArrayList<Adherent> cursorToAllAdherent(Cursor c){

        ArrayList<Adherent> listAdherent=new ArrayList<Adherent>();
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        if (c.moveToFirst()) {
            do {
                //On créé un Adherent
                Adherent adherent = new Adherent();
                //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
                adherent.setId(c.getInt(NUM_COL_ID));
                adherent.setNom(c.getString(NUM_COL_Nom));
                adherent.setPrenom(c.getString(NUM_COL_Prenom));
                adherent.setSexe(c.getString(NUM_COL_SEXE));
                adherent.setPoid(c.getInt(NUM_COL_Poid));
                adherent.setAge(c.getInt(NUM_COL_AGE));
                adherent.setPhone(c.getString(NUM_COL_PHONE));
                adherent.setDiscipline(c.getString(NUM_COL_DISCIPLINE));
                listAdherent.add(adherent);
            } while (c.moveToNext());
        }
        if (c != null && !c.isClosed()) {
            //On ferme le cursor
            c.close();
        }

        //On retourne le Adherent
        return listAdherent;
    }


}
