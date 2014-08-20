package fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Salle;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Salles.Salles;

/**
 * Created by server-pc on 16/08/2014.
 */
public class SalleBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "myclub.db";
    private static final String TABLE_Salle = "salles";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_Nom_salle = "nom_salle";
    private static final int NUM_COL_Nom_salle = 1;
    private static final String COL_capacite= "capacite";
    private static final int NUM_COL_capacite= 2;
    private static final String COL_Nom_coach = "nom_coach";
    private static final int NUM_COL_Nom_coach = 3;
    private static final String COL_type_activite = "type_activite";
    private static final int NUM_COL_type_activite = 4;

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public SalleBDD(Context context){
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

    public long insertSalle(Salle salle){

        ContentValues values = new ContentValues();
        values.put(COL_Nom_salle, salle.getNom_salle());
        values.put(COL_capacite,salle.getCapacite());
        values.put(COL_Nom_coach, salle.getNom_coach());
        values.put(COL_type_activite, salle.getType_activite());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_Salle, null, values);
    }

    public int updateSalle(int id, Salle salle ){
        //La mise à jour d'un Adherent dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel Adherent on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_Nom_salle, salle.getNom_salle());
        values.put(COL_capacite,salle.getCapacite());
        values.put(COL_Nom_coach, salle.getNom_coach());
        values.put(COL_type_activite, salle.getType_activite());

        return bdd.update(TABLE_Salle, values, COL_ID + " = " +id, null);
    }

    public int removeSalleWithID(int id){
        //Suppression d'un Adherent de la BDD grâce à l'ID

        return bdd.delete(TABLE_Salle,COL_ID + " = " +id, null);
    }

    public ArrayList<Salle> getAllSalles()
    {
        Cursor c = bdd.query(TABLE_Salle, new String[] {COL_ID, COL_Nom_salle, COL_capacite,COL_Nom_coach,COL_type_activite},null, null, null, null, null);
        return cursorToAllSalles(c);

    }

    public Salle searchSalle(String value){
        //Récupère dans un Cursor les valeurs correspondant à un Adherent contenu dans la BDD (ici on sélectionne le Adherent grâce à son nom)
        Cursor c = bdd.query(TABLE_Salle, new String[] {COL_ID, COL_Nom_salle, COL_capacite,COL_Nom_coach,COL_type_activite}, COL_Nom_salle + " LIKE \"" + value +"\" or "+ COL_Nom_coach + " LIKE \"" + value +"\" or "+ COL_capacite + " LIKE \"" + value +"\" or "+COL_ID + " LIKE \"" + value +"\" or "+COL_type_activite + " LIKE \"" + value +"\"", null, null, null, null);
        return cursorToSalle(c);
    }

    public Salle getSalleWithNom(String nom){
        //Récupère dans un Cursor les valeurs correspondant à un Adherent contenu dans la BDD (ici on sélectionne le Adherent grâce à son nom)
        Cursor c = bdd.query(TABLE_Salle, new String[] {COL_ID, COL_Nom_salle, COL_capacite,COL_Nom_coach,COL_type_activite}, COL_Nom_salle + " LIKE \"" + nom +"\"", null, null, null, null);
        return cursorToSalle(c);
    }

    public Salle getSalleWithId(String id){
        //Récupère dans un Cursor les valeurs correspondant à un Adherent contenu dans la BDD (ici on sélectionne le Adherent grâce à son titre)
        Cursor c = bdd.query(TABLE_Salle, new String[] {COL_ID, COL_Nom_salle, COL_capacite,COL_Nom_coach,COL_type_activite}, COL_ID+ " LIKE \"" + id +"\"", null, null, null, null);
        return cursorToSalle(c);
    }

    //Cette méthode permet de convertir un cursor en un Adherent
    private Salle cursorToSalle(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();

        Salle salle = new Salle();
        salle.setId(c.getInt(NUM_COL_ID));
        salle.setNom_salle(c.getString(NUM_COL_Nom_salle));
        salle.setCapacite(c.getInt(NUM_COL_capacite));
        salle.setNom_coach(c.getString(NUM_COL_Nom_coach));
        salle.setType_activite(c.getString(NUM_COL_type_activite));
        //On ferme le cursor
        c.close();

        //On retourne le Adherent
        return salle;
    }


    // get la liste des salles
    private ArrayList<Salle> cursorToAllSalles(Cursor c){

        ArrayList<Salle> listsalles=new ArrayList<Salle>();
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        if (c.moveToFirst()) {
            do {
                //On créé un Adherent
                Salle salle = new Salle();
                //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
                salle.setId(c.getInt(NUM_COL_ID));
                salle.setNom_salle(c.getString(NUM_COL_Nom_salle));
                salle.setCapacite(c.getInt(NUM_COL_capacite));
                salle.setNom_coach(c.getString(NUM_COL_Nom_coach));
                salle.setType_activite(c.getString(NUM_COL_type_activite));

                listsalles.add(salle);
            } while (c.moveToNext());
        }
        if (c != null && !c.isClosed()) {
            //On ferme le cursor
            c.close();
        }

        //On retourne le Adherent
        return listsalles;
    }


}
