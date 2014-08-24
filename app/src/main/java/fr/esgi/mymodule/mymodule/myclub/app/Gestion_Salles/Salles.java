package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Salles;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.Afficher;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.AjouterAdherent;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.ModifierAdherent;
import fr.esgi.mymodule.mymodule.myclub.app.R;

import static android.widget.Toast.makeText;

import android.widget.Toast;
public class Salles extends ActivityGroup {

    final static String TAB_AJOUTER ="Ajouter";
    final static String TAB_MODIFIER="Modifier";
    final static String TAB_AFFICHER="Afficher";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salles);


        final TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup(this.getLocalActivityManager());
        this.setTabHost(tabHost);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                //Ajouter
                if(TAB_AJOUTER.equals(s)) {
                   // onCreate(savedInstanceState);
                    Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
                }
                //Modifier
                if(TAB_MODIFIER.equals(s)) {
                    Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
                }
                //Afficher
                if(TAB_AFFICHER.equals(s)) {
                   // onCreate(savedInstanceState);
                    Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
                 // tabHost.refreshDrawableState();
                   tabHost.invalidate();
                }
            }
        });



    }



    private void setTabHost(TabHost tabHost)
    {

        TabHost.TabSpec spec1=tabHost.newTabSpec("Ajouter");
        spec1.setIndicator("",getResources().getDrawable(R.drawable.add));
        spec1.setContent(new Intent(this,Ajouter_salle.class));

        // intent = new Intent().setClass(this, test.class);
        TabHost.TabSpec spec2=tabHost.newTabSpec("Modifier");
        spec2.setIndicator("",getResources().getDrawable(R.drawable.modifier));
        spec2.setContent(new Intent(this,Modifier_salle.class));

      /*  TabHost.TabSpec spec3=tabHost.newTabSpec("Supprimer");
        spec3.setIndicator("",getResources().getDrawable(R.drawable.delete));
        spec3.setContent(R.id.Supprimer);*/

        TabHost.TabSpec spec4=tabHost.newTabSpec("Afficher");
        spec4.setIndicator("",getResources().getDrawable(R.drawable.search));
        spec4.setContent(new Intent(this,Afficher_salle.class));

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        //    tabHost.addTab(spec3);
        tabHost.addTab(spec4);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.salles, menu);
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
