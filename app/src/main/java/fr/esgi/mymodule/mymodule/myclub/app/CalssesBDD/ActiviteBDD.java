package fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import fr.esgi.mymodule.mymodule.myclub.app.Classes.Activite;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;

/**
 * Created by melakraoui on 27/08/2014.
 */
public class ActiviteBDD {



    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "myclub.db";
    private static final String TABLE_Activite= "activites";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_Intitule = "intitule";
    private static final int NUM_COL_Intitule = 1;
    private static final String COL_Date_demarrage= "date_demarrage";
    private static final int NUM_COL_Date_demarrage = 2;
    private static final String COL_Date_fin = "date_fin";
    private static final int NUM_COL_Date_fin = 3;
    private static final String COL_Type_activite= "type_activite";
    private static final int NUM_COL_Type_activite = 4;
    private static final String COL_Commentaire= "commentaire";
    private static final int NUM_COL_Commentaire= 5;



    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public ActiviteBDD(Context context){
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

    public long insertActivite(Activite activite){

        ContentValues values = new ContentValues();

        values.put(COL_Intitule, activite.getIntitule_activite());
        values.put(COL_Date_demarrage, activite.getDate_demarrage());
        values.put(COL_Date_fin, activite.getDate_fin());
        values.put(COL_Type_activite, activite.getType_activite());
        values.put(COL_Commentaire, activite.getCommentaires());

        return bdd.insert(TABLE_Activite, null, values);
    }

    public int updateActivite(int id, Activite activite){

        ContentValues values = new ContentValues();
        values.put(COL_Intitule, activite.getIntitule_activite());
        values.put(COL_Date_demarrage, activite.getDate_demarrage());
        values.put(COL_Date_fin, activite.getDate_fin());
        values.put(COL_Type_activite, activite.getType_activite());
        values.put(COL_Commentaire, activite.getCommentaires());

        return bdd.update(TABLE_Activite, values, COL_ID + " = " +id, null);
    }

    public int removeActiviteWithID(int id){
        //Suppression d'un Adherent de la BDD grâce à l'ID

        return bdd.delete(TABLE_Activite ,COL_ID + " = " +id, null);
    }


    public ArrayList<Activite> getAllActiviteBetweenTowDate()

    {
        Cursor c = bdd.query(TABLE_Activite, new String[] {COL_ID, COL_Intitule, COL_Date_demarrage,COL_Date_fin,COL_Type_activite,COL_Commentaire},COL_Date_demarrage+" >=date('now') and  "+COL_Date_fin +"<=date('now')", null, null, null, null);
        return cursorToAllActivites(c);

    }


    public ArrayList<Activite> getAllActivite()
    {
        Cursor c = bdd.query(TABLE_Activite, new String[] {COL_ID, COL_Intitule, COL_Date_demarrage,COL_Date_fin,COL_Type_activite,COL_Commentaire},null, null, null, null, null);
        return cursorToAllActivites(c);

    }

    public Activite getActiviteWithNom(String nom){
        //Récupère dans un Cursor les valeurs correspondant à un Adherent contenu dans la BDD (ici on sélectionne le Adherent grâce à son nom)
        Cursor c = bdd.query(TABLE_Activite, new String[] {COL_ID, COL_Intitule, COL_Date_demarrage,COL_Date_fin,COL_Type_activite,COL_Commentaire}, COL_Intitule + " LIKE \"" + nom +"\"", null, null, null, null);
        return cursorToActivite(c);
    }

    public Activite getActiviteWithId(String id){
        //Récupère dans un Cursor les valeurs correspondant à un Adherent contenu dans la BDD (ici on sélectionne le Adherent grâce à son titre)
        Cursor c = bdd.query(TABLE_Activite, new String[] {COL_ID, COL_Intitule, COL_Date_demarrage,COL_Date_fin,COL_Type_activite,COL_Commentaire}, COL_ID + " LIKE \"" + id +"\"", null, null, null, null);
        return cursorToActivite(c);
    }


    private Activite cursorToActivite(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        Activite activite=new Activite();
        activite.setId(c.getInt(NUM_COL_ID));
        activite.setIntitule_activite(c.getString(NUM_COL_Intitule));
        activite.setDate_demarrage(c.getString(NUM_COL_Date_demarrage));
        activite.setDate_fin(c.getString(NUM_COL_Date_fin));
        activite.setType_activite(c.getString(NUM_COL_Type_activite));
        activite.setCommentaires(c.getString(NUM_COL_Commentaire));
        //On ferme le cursor
        c.close();

        //On retourne le Adherent
        return activite;
    }


    // get la liste des adherents
    private ArrayList<Activite> cursorToAllActivites(Cursor c){

        ArrayList<Activite> list=new ArrayList<Activite>();
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        if (c.moveToFirst()) {
            do {
                //On créé un Adherent
                Activite activite = new Activite();
                //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
                activite.setId(c.getInt(NUM_COL_ID));
                activite.setIntitule_activite(c.getString(NUM_COL_Intitule));
                activite.setDate_demarrage(c.getString(NUM_COL_Date_demarrage));
                activite.setDate_fin(c.getString(NUM_COL_Date_fin));
                activite.setType_activite(c.getString(NUM_COL_Type_activite));
                activite.setCommentaires(c.getString(NUM_COL_Commentaire));
                list.add(activite);
            } while (c.moveToNext());
        }
        if (c != null && !c.isClosed()) {
            //On ferme le cursor
            c.close();
        }

        //On retourne le Adherent
        return list;
    }




}
