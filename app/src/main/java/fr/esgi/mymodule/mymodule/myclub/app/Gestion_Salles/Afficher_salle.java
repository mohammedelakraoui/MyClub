package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Salles;

import android.app.ActionBar;
import android.app.ActivityGroup;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapterAdherents;
import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapterSalles;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.SalleBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Salle;

import fr.esgi.mymodule.mymodule.myclub.app.R;

import static android.view.ContextMenu.*;

public class Afficher_salle extends ActionBarActivity {

    private ListView maListViewPerso;
    ArrayList<Salle> list;
    CustomAdapterSalles adapter;
    SalleBDD salleBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_salles);

        maListViewPerso = (ListView) findViewById(R.id.listview_afficher_salles);
        //  tab=(TabHost) findViewById(R.id.t)
        salleBDD = new SalleBDD(this);
        salleBDD.open();
        list = salleBDD.getAllSalles();
        salleBDD.close();

        if (list != null) {
            Salle s = list.get(0);
            // Log.v("Nom", r.getNom());
            adapter = new CustomAdapterSalles(this, list);

            maListViewPerso.setAdapter(adapter);

            registerForContextMenu(maListViewPerso);
        }



    }

    private void refresh()
    {
        salleBDD = new SalleBDD(this);
        salleBDD.open();
        list = salleBDD.getAllSalles();
        salleBDD.close();

        if (list != null) {
            Salle s = list.get(0);
            // Log.v("Nom", r.getNom());
            adapter = new CustomAdapterSalles(this, list);

            maListViewPerso.setAdapter(adapter);

            registerForContextMenu(maListViewPerso);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        refresh();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId()==R.id.listview_afficher_salles) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(list.get(info.position).getNom_salle());
            String[] menuItems = getResources().getStringArray(R.array.menu_gestion);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        //supprimer
        if(menuItemIndex==0)
        {
            // Remove Item
            AlertDialog.Builder adb = new AlertDialog.Builder(Afficher_salle.this);
            adb.setTitle("Suppression?");
            adb.setMessage("Voullez-vous vraiment supprimer ce item?");
            final int positionToRemove = info.position;
            //   final String removeItem = item;

            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Confirmer", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // *** Here is where I am experiencing issueos ***

                    salleBDD.open();
                    if(salleBDD.removeSalleWithID(list.get(positionToRemove).getId())==1)
                    {
                        list.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "la suppression est ok", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Error: suppression", Toast.LENGTH_LONG).show();
                    }
                    salleBDD.close();
                }
            });
            adb.show();
        }
        //modifier
        if(menuItemIndex==1)
        {

            Intent intAdh = new Intent(this,Salles.class);
            intAdh.putExtra("id",list.get(info.position).getId());
            startActivity(intAdh);

        }

        //  String[] menuItems = getResources().getStringArray(R.array.menu_gestion);
        //   String menuItemName = menuItems[menuItemIndex];
        //  String listItemName = list.get(info.position).getNom();
        // text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
        //  Toast.makeText(getBaseContext(), menuItemIndex+":", Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);


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
