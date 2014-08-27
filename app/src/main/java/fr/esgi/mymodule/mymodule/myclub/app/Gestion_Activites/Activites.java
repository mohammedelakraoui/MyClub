package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Activites;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.Afficher;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.AjouterAdherent;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.ModifierAdherent;
import fr.esgi.mymodule.mymodule.myclub.app.R;


public class Activites extends ActivityGroup {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activites);

        TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup(this.getLocalActivityManager());

        setTabHost(tabHost);

    }
    private void setTabHost(TabHost tabHost)
    {

        TabHost.TabSpec spec1=tabHost.newTabSpec("Ajouter");
        spec1.setIndicator("",getResources().getDrawable(R.drawable.add));
        spec1.setContent(new Intent(this, AjouterActivite.class));


        TabHost.TabSpec spec4=tabHost.newTabSpec("Afficher");
        spec4.setIndicator("",getResources().getDrawable(R.drawable.search));
        spec4.setContent(new Intent(this, AfficherActivites.class));

        tabHost.addTab(spec1);

        tabHost.addTab(spec4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activites, menu);
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
