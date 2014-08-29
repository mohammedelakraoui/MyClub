package fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Map;

import fr.esgi.mymodule.mymodule.myclub.app.Classes.Maps;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Salle;

/**
 * Created by melakraoui on 22/08/2014.
 */
public class MapsBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "myclub.db";
    private static final String TABLE_Maps = "maps";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_Nom_Club = "nom_club";
    private static final int NUM_COL_Nom_club = 1;
    private static final String COL_Adresse= "adresse";
    private static final int NUM_COL_Adresse= 2;
    private static final String COL_Longtitude = "longtitude";
    private static final int NUM_COL_Longtitude = 3;
    private static final String COL_Laltitude = "laltitude";
    private static final int NUM_COL_Laltitude= 4;


    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public MapsBDD(Context context){
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

    public Maps getMapsWithAdresse(String adresse){
        //Récupère dans un Cursor les valeurs correspondant à un Adherent contenu dans la BDD (ici on sélectionne le Adherent grâce à son nom)
        Cursor c = bdd.query(TABLE_Maps, new String[] {COL_ID, COL_Nom_Club, COL_Adresse,COL_Longtitude,COL_Laltitude}, COL_Adresse + " LIKE \"" + adresse +"\"", null, null, null, null);
        return cursorToMaps(c);
    }

    //Cette méthode permet de convertir un cursor en un Adherent
    private Maps cursorToMaps(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();

        Maps maps = new Maps();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        maps.setNom_Club(c.getString(NUM_COL_ID));
        maps.setAdresse(c.getString(NUM_COL_Adresse));
        maps.setLongtitude(c.getDouble(NUM_COL_Longtitude));
        maps.setLatitude(c.getDouble(NUM_COL_Laltitude));

        return maps;
    }

    public long insertCoordonnees(Maps maps){

        ContentValues values = new ContentValues();
        values.put(COL_Nom_Club, maps.getNom_Club());
        values.put(COL_Adresse,maps.getAdresse());
        values.put(COL_Longtitude, maps.getLongtitude());
        values.put(COL_Laltitude, maps.getLatitude());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_Maps, null, values);
    }

    public int updateCoordonnees(int id, Maps maps ){

        ContentValues values = new ContentValues();
        values.put(COL_Nom_Club, maps.getNom_Club());
        values.put(COL_Adresse,maps.getAdresse());
        values.put(COL_Longtitude, maps.getLongtitude());
        values.put(COL_Laltitude, maps.getLatitude());


        return bdd.update(TABLE_Maps, values, COL_ID + " = " +id, null);
    }

    public int removeCoordonneesWithID(int id){
        //Suppression d'un objet de la BDD grâce à l'ID

        return bdd.delete(TABLE_Maps,COL_ID + " = " +id, null);
    }

    public ArrayList<Maps> getAllcoordonnees()
    {
        Cursor c = bdd.query(TABLE_Maps, new String[] {COL_ID, COL_Nom_Club, COL_Adresse,COL_Longtitude,COL_Laltitude},null, null, null, null, null);
        return cursorToAllCoordonnes(c);

    }

    private ArrayList<Maps> cursorToAllCoordonnes(Cursor c){

        ArrayList<Maps> listCoordonnes=new ArrayList<Maps>();
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        if (c.moveToFirst()) {
            do {
                //On créé un Adherent
                Maps maps = new Maps();
                //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
                maps.setId(c.getInt(NUM_COL_ID));
                maps.setNom_Club(c.getString(NUM_COL_Nom_club));
                maps.setAdresse(c.getString(NUM_COL_Adresse));
                maps.setLongtitude(c.getDouble(NUM_COL_Longtitude));
                maps.setLatitude(c.getDouble(NUM_COL_Laltitude));

                listCoordonnes.add(maps);
            } while (c.moveToNext());
        }
        if (c != null && !c.isClosed()) {
            //On ferme le cursor
            c.close();
        }

        //On retourne le Adherent
        return listCoordonnes;
    }

}
