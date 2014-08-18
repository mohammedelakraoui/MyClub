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

    private static final String CREATE_Adherents = "create table adherents(id INTEGER PRIMARY KEY   AUTOINCREMENT, nom TEXT,prenom TEXT,sexe TEXT,poid INTEGER, age INTEGER,phone TEXT,discipline TEXT);";
  //  private static final String CREATE_Activites = "create table activites(id INTEGER PRIMARY KEY   AUTOINCREMENT, nom TEXT,prenom TEXT,sexe TEXT,poid INTEGER, age INTEGER,phone TEXT,discipline TEXT);";
  //  private static final String CREATE_Entrainements = "create table entrainements(id INTEGER PRIMARY KEY   AUTOINCREMENT, nom TEXT,prenom TEXT,sexe TEXT,poid INTEGER, age INTEGER,phone TEXT,discipline TEXT);";
    private static final String CREATE_Salles = "create table salles(id INTEGER PRIMARY KEY   AUTOINCREMENT, nom_salle TEXT,capacite INTEGER,nom_coach TEXT,type_activite TEXT);";

    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        sqLiteDatabase.execSQL(CREATE_Adherents);
        sqLiteDatabase.execSQL(CREATE_Salles);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

         //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0

        sqLiteDatabase.execSQL("DROP TABLE " + adherents + ";");
        sqLiteDatabase.execSQL("DROP TABLE " + salles + ";");
      /*  sqLiteDatabase.execSQL("DROP TABLE " + activites + ";");
        sqLiteDatabase.execSQL("DROP TABLE " + entrainemets + ";");
        sqLiteDatabase.execSQL("DROP TABLE " + salles + ";");*/
        onCreate(sqLiteDatabase);
    }
}
