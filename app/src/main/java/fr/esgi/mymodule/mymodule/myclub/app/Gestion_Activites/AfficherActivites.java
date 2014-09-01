package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapterActivites;
import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapterEntrainements;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.ActiviteBDD;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.EntrainementBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Activite;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Entrainement;
import fr.esgi.mymodule.mymodule.myclub.app.R;

public class AfficherActivites extends ActionBarActivity {

    private ListView maListViewPerso;
    ArrayList<Activite> list;
    CustomAdapterActivites adapter;
    ActiviteBDD activiteBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_activites);

        maListViewPerso = (ListView) findViewById(R.id.listview_afficher_activites);
        //  tab=(TabHost) findViewById(R.id.t)
        activiteBDD=new ActiviteBDD(this);
        activiteBDD.open();

            list = activiteBDD.getAllActivite();

        activiteBDD.close();

        if(list!=null)
        {


            adapter = new CustomAdapterActivites(this, list,1);

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

        if (v.getId()==R.id.listview_afficher_activites) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(list.get(info.position).getIntitule_activite()+" Date:"+list.get(info.position).getDate_demarrage()+" ;Date fin : "+list.get(info.position).getDate_fin());
            String[] menuItems = getResources().getStringArray(R.array.menu_list);
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
            AlertDialog.Builder adb = new AlertDialog.Builder(AfficherActivites.this);
            adb.setTitle("Suppression?");
            adb.setMessage("Voullez-vous vraiment supprimer ce item?");
            final int positionToRemove = info.position;
            //   final String removeItem = item;

            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Confirmer", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // *** Here is where I am experiencing issueos ***

                    activiteBDD.open();
                    if(activiteBDD.removeActiviteWithID(list.get(positionToRemove).getId())==1)
                    {
                        list.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "la suppression est bien faite", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Error: suppression", Toast.LENGTH_LONG).show();
                    }
                    activiteBDD.close();
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
        activiteBDD=new ActiviteBDD(this);
        activiteBDD.open();
        list=activiteBDD.getAllActivite();
        activiteBDD.close();

        if(list!=null)
        {


            adapter = new CustomAdapterActivites(this, list,1);

            maListViewPerso.setAdapter(adapter);

            registerForContextMenu(maListViewPerso);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getMenuInflater().inflate(R.menu.afficher_activites, menu);

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
