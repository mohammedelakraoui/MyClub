package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Entrainements;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapterAdherents;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.EntrainementBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.Afficher;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.AjouterAdherent;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.ModifierAdherent;
import fr.esgi.mymodule.mymodule.myclub.app.R;


public class Entrainements extends ActivityGroup {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrainements);

        TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup(this.getLocalActivityManager());

       this.setTabHost(tabHost);

    }
    private void setTabHost(TabHost tabHost)
    {
        tabHost.clearAllTabs();
        TabHost.TabSpec spec1=tabHost.newTabSpec("Ajouter");
        spec1.setIndicator("",getResources().getDrawable(R.drawable.add));
        spec1.setContent(new Intent(this, AjouterEntrainement.class));

        // intent = new Intent().setClass(this, test.class);
        TabHost.TabSpec spec2=tabHost.newTabSpec("Modifier");
        spec2.setIndicator("",getResources().getDrawable(R.drawable.modifier));
        spec2.setContent(new Intent(this,ModifierAdherent.class));

      /*  TabHost.TabSpec spec3=tabHost.newTabSpec("Supprimer");
        spec3.setIndicator("",getResources().getDrawable(R.drawable.delete));
        spec3.setContent(R.id.Supprimer);*/

        TabHost.TabSpec spec4=tabHost.newTabSpec("Afficher");
        spec4.setIndicator("",getResources().getDrawable(R.drawable.search));
        spec4.setContent(new Intent(this, AfficherEntrainements.class));

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        //    tabHost.addTab(spec3);
        tabHost.addTab(spec4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.entrainements, menu);
        return true;
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
