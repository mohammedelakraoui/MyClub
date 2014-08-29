package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Entrainements;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapterAdherents;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.EntrainementBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Entrainement;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.Adherents;
import fr.esgi.mymodule.mymodule.myclub.app.R;
import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapterEntrainements;
public class AfficherEntrainements extends ActionBarActivity {

    private ListView maListViewPerso;
    ArrayList<Entrainement> list;
    CustomAdapterEntrainements adapter;
    EntrainementBDD entrainementBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_entrainements);

        maListViewPerso = (ListView) findViewById(R.id.listview_afficher_entrainement);
        //  tab=(TabHost) findViewById(R.id.t)
        entrainementBDD=new EntrainementBDD(this);
        entrainementBDD.open();
        list=entrainementBDD.getAllEntrainement();
        entrainementBDD.close();

        if(list!=null)
        {


             adapter = new CustomAdapterEntrainements(this, list);

            maListViewPerso.setAdapter(adapter);

            registerForContextMenu(maListViewPerso);
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId()==R.id.listview_afficher_entrainement) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(list.get(info.position).getNom_seance_entrainement()+" Date:"+list.get(info.position).getDate_entrainement()+" Heur : "+list.get(info.position).getHeur_entrainement());
            String[] menuItems = getResources().getStringArray(R.array.menu_list_entrainement);
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
            AlertDialog.Builder adb = new AlertDialog.Builder(AfficherEntrainements.this);
            adb.setTitle("Suppression?");
            adb.setMessage("Voullez-vous vraiment supprimer ce item?");
            final int positionToRemove = info.position;
            //   final String removeItem = item;

            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Confirmer", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // *** Here is where I am experiencing issueos ***

                    entrainementBDD.open();
                    if(entrainementBDD.removeEntrainementWithID(list.get(positionToRemove).getId())==1)
                    {
                        list.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "la suppression est bien faite", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Error: suppression", Toast.LENGTH_LONG).show();
                    }
                    entrainementBDD.close();
                }
            });
            adb.show();
        }
        //refresh
        if(menuItemIndex==1)
        {


        refresh();




        }


        return super.onContextItemSelected(item);


    }


    private void refresh()
    {
        entrainementBDD=new EntrainementBDD(this);
        entrainementBDD.open();
        list=entrainementBDD.getAllEntrainement();
        entrainementBDD.close();

        if(list!=null)
        {


            adapter = new CustomAdapterEntrainements(this, list);

            maListViewPerso.setAdapter(adapter);

            registerForContextMenu(maListViewPerso);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.afficher_entrainements, menu);
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

    @Override
    protected void onResume() {
        super.onResume();

       refresh();

    }
}
