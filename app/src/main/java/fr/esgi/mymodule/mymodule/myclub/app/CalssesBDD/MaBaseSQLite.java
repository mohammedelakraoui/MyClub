package fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by melakraoui on 07/08/2014.
 */
public class MaBaseSQLite extends SQLiteOpenHelper {



    private static final  String adherents="adherents";
    private static final  String activites="activites";
    private static final  String entrainemets="entrainements";
    private static final  String salles="salles";
    private static final  String MAPS="maps";

    private static final String CREATE_Adherents = "create table adherents(id INTEGER PRIMARY KEY   AUTOINCREMENT, nom TEXT,prenom TEXT,sexe TEXT,poid INTEGER, age INTEGER,phone TEXT,discipline TEXT,pic TEXT);";
    private static final String CREATE_Activites = "create table activites(id INTEGER PRIMARY KEY   AUTOINCREMENT,intitule TEXT,date_demarrage DATE,date_fin DATE,type_activite TEXT,commentaire TEXT);";
    private static final String CREATE_Entrainements = "create table entrainements(id INTEGER PRIMARY KEY   AUTOINCREMENT, nom_seance_entrainement TEXT,date_entrainement date,heur_entrainement TEXT,nombre_places INTEGER,commentaire TEXT);";
    private static final String CREATE_Salles = "create table salles(id INTEGER PRIMARY KEY   AUTOINCREMENT, nom_salle TEXT,capacite INTEGER,nom_coach TEXT,type_activite TEXT);";
    private static final String CREATE_Maps = "create table maps(id INTEGER PRIMARY KEY   AUTOINCREMENT, nom_club TEXT,adresse TEXT,longtitude DOUBLE,laltitude DOUBLE);";


    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        sqLiteDatabase.execSQL(CREATE_Entrainements);
        sqLiteDatabase.execSQL(CREATE_Adherents);
        sqLiteDatabase.execSQL(CREATE_Salles);
        sqLiteDatabase.execSQL(CREATE_Maps);
        sqLiteDatabase.execSQL(CREATE_Activites);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

         //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + entrainemets + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + adherents + ";");
        sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS " + salles + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ MAPS+";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + activites + ";");

        onCreate(sqLiteDatabase);
    }
}
