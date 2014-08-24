package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents;

import android.app.ActionBar;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import fr.esgi.mymodule.mymodule.myclub.app.R;

public class Adherents extends ActivityGroup {
    private   Intent intent;
   private  TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adherents);


        tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup(this.getLocalActivityManager());
        this.setTabHost(tabHost);



    }


    private void setTabHost(TabHost tabHost)
    {
        tabHost.clearAllTabs();
        TabHost.TabSpec spec1=tabHost.newTabSpec("Ajouter");
        spec1.setIndicator("",getResources().getDrawable(R.drawable.add));
        spec1.setContent(new Intent(this,AjouterAdherent.class));

        // intent = new Intent().setClass(this, test.class);
        TabHost.TabSpec spec2=tabHost.newTabSpec("Modifier");
        spec2.setIndicator("",getResources().getDrawable(R.drawable.modifier));
        spec2.setContent(new Intent(this,ModifierAdherent.class));

      /*  TabHost.TabSpec spec3=tabHost.newTabSpec("Supprimer");
        spec3.setIndicator("",getResources().getDrawable(R.drawable.delete));
        spec3.setContent(R.id.Supprimer);*/

        TabHost.TabSpec spec4=tabHost.newTabSpec("Afficher");
        spec4.setIndicator("",getResources().getDrawable(R.drawable.search));
        spec4.setContent(new Intent(this,Afficher.class));

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        //    tabHost.addTab(spec3);
        tabHost.addTab(spec4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.adherents, menu);
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
