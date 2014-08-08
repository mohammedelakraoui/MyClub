package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents;



import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapter;
import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapterAdherents;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.R;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.*;
public class Afficher extends ActionBarActivity {

    private ListView maListViewPerso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_adhrent);
        maListViewPerso = (ListView) findViewById(R.id.listview_afficher_adherents);
        AdherentBDD adb=new AdherentBDD(this);
        adb.open();
        ArrayList<Adherent> list=adb.getAllAdherent();
        adb.close();
        Adherent r=list.get(0);
       // Log.v("Nom", r.getNom());
        CustomAdapterAdherents adapter = new CustomAdapterAdherents(this,list);

        maListViewPerso.setAdapter(adapter);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
