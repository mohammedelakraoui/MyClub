package fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Entrainement;

/**
 * Created by melakraoui on 25/08/2014.
 */
public class EntrainementBDD {


    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "myclub.db";
    private static final String TABLE_Entrainement= "entrainements";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_Nom_Seance_Entrainement = "nom_seance_entrainement";
    private static final int NUM_COL_Nom_Seance_Entrainement = 1;
    private static final String COL_Date_Entrainement= "date_entrainement";
    private static final int NUM_COL_Date_Entrainement = 2;
    private static final String COL_Heur_Entrainement = "heur_entrainement";
    private static final int NUM_COL_Heur_Entrainement = 3;
    private static final String COL_Nombre_Places = "nombre_places";
    private static final int NUM_COL_Nombre_Places = 4;
    private static final String COL_Commentaire= "commentaire";
    private static final int NUM_COL_Commentaire = 5;


    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public EntrainementBDD(Context context){
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

    public long insertEntrainement(Entrainement entrainement){

        ContentValues values = new ContentValues();
        values.put(COL_Nom_Seance_Entrainement, entrainement.getNom_seance_entrainement());
        values.put(COL_Date_Entrainement, entrainement.getDate_entrainement());
        values.put(COL_Heur_Entrainement, entrainement.getHeur_entrainement());
        values.put(COL_Nombre_Places, entrainement.getNombre_places_entrainement());
        values.put(COL_Commentaire, entrainement.getCommentaire());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_Entrainement, null, values);
    }

    public int updateEntrainement(int id, Entrainement entrainement){

        ContentValues values = new ContentValues();
        values.put(COL_Nom_Seance_Entrainement, entrainement.getNom_seance_entrainement());
        values.put(COL_Date_Entrainement, entrainement.getDate_entrainement());
        values.put(COL_Heur_Entrainement, entrainement.getHeur_entrainement());
        values.put(COL_Nombre_Places, entrainement.getNombre_places_entrainement());
        values.put(COL_Commentaire, entrainement.getCommentaire());

        return bdd.update(TABLE_Entrainement, values, COL_ID + " = " +id, null);
    }

    public int removeEntrainementWithID(int id){
        //Suppression d'un Adherent de la BDD grâce à l'ID

        return bdd.delete(TABLE_Entrainement ,COL_ID + " = " +id, null);
    }

    public ArrayList<Entrainement> getAllEntrainement()
    {
        Cursor c = bdd.query(TABLE_Entrainement, new String[] {COL_ID, COL_Nom_Seance_Entrainement, COL_Date_Entrainement,COL_Heur_Entrainement,COL_Nombre_Places,COL_Commentaire},null, null, null, null, null);
        return cursorToAllEntrainement(c);

    }

    public Entrainement getEntrainementWithNom(String nom){
        //Récupère dans un Cursor les valeurs correspondant à un Adherent contenu dans la BDD (ici on sélectionne le Adherent grâce à son nom)
        Cursor c = bdd.query(TABLE_Entrainement, new String[] {COL_ID, COL_Nom_Seance_Entrainement, COL_Date_Entrainement,COL_Heur_Entrainement,COL_Nombre_Places,COL_Commentaire}, COL_Nom_Seance_Entrainement + " LIKE \"" + nom +"\"", null, null, null, null);
        return cursorToEntrainement(c);
    }

    public Entrainement getAdherentWithId(String id){
        //Récupère dans un Cursor les valeurs correspondant à un Adherent contenu dans la BDD (ici on sélectionne le Adherent grâce à son titre)
        Cursor c = bdd.query(TABLE_Entrainement, new String[] {COL_ID, COL_Nom_Seance_Entrainement, COL_Date_Entrainement,COL_Heur_Entrainement,COL_Nombre_Places,COL_Commentaire}, COL_ID + " LIKE \"" + id +"\"", null, null, null, null);
        return cursorToEntrainement(c);
    }

    //Cette méthode permet de convertir un cursor en un Adherent
    private Entrainement cursorToEntrainement(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();

        Entrainement entrainement = new Entrainement();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        entrainement.setId(c.getInt(NUM_COL_ID));
        entrainement.setNom_seance_entrainement(c.getString(NUM_COL_Nom_Seance_Entrainement));
        entrainement.setDate_entrainement(c.getString(NUM_COL_Date_Entrainement));
        entrainement.setHeur_entrainement(c.getString(NUM_COL_Heur_Entrainement));
        entrainement.setNombre_places_entrainement(c.getInt(NUM_COL_Nombre_Places));
        entrainement.setCommentaire(c.getString(NUM_COL_Commentaire));

        //On ferme le cursor
        c.close();

        //On retourne le Adherent
        return entrainement;
    }


    // get la liste des
    private ArrayList<Entrainement> cursorToAllEntrainement(Cursor c){

        ArrayList<Entrainement> listEntrainement=new ArrayList<Entrainement>();
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        if (c.moveToFirst()) {
            do {
                //On créé un Adherent
                Entrainement entrainement = new Entrainement();
                //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
                entrainement.setId(c.getInt(NUM_COL_ID));
                entrainement.setNom_seance_entrainement(c.getString(NUM_COL_Nom_Seance_Entrainement));
                entrainement.setDate_entrainement(c.getString(NUM_COL_Date_Entrainement));
                entrainement.setHeur_entrainement(c.getString(NUM_COL_Heur_Entrainement));
                entrainement.setNombre_places_entrainement(c.getInt(NUM_COL_Nombre_Places));
                entrainement.setCommentaire(c.getString(NUM_COL_Commentaire));

                listEntrainement.add(entrainement);
            } while (c.moveToNext());
        }
        if (c != null && !c.isClosed()) {
            //On ferme le cursor
            c.close();
        }

        //On retourne le Adherent
        return listEntrainement;
    }

}
