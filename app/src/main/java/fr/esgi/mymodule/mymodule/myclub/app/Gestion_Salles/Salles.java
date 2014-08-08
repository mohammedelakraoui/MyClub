package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Salles;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import fr.esgi.mymodule.mymodule.myclub.app.R;


public class Salles extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salles);

        TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec1=tabHost.newTabSpec("Ajouter");
        spec1.setContent(R.id.Ajouter);
        spec1.setIndicator("",getResources().getDrawable(R.drawable.add));

        TabHost.TabSpec spec2=tabHost.newTabSpec("Modifier");
        spec2.setIndicator("",getResources().getDrawable(R.drawable.modifier));
        spec2.setContent(R.id.Modifier);

        TabHost.TabSpec spec3=tabHost.newTabSpec("Supprimer");
        spec3.setIndicator("",getResources().getDrawable(R.drawable.delete));
        spec3.setContent(R.id.Supprimer);

        TabHost.TabSpec spec4=tabHost.newTabSpec("Rechercher");
        spec4.setIndicator("",getResources().getDrawable(R.drawable.search));
        spec4.setContent(R.id.Rechercher);

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
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
