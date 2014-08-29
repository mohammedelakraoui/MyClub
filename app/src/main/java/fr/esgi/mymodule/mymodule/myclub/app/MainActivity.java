package fr.esgi.mymodule.mymodule.myclub.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapter;
import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapterActivites;
import fr.esgi.mymodule.mymodule.myclub.app.Adapters.CustomAdapterAdherents;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.ActiviteBDD;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Activite;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Activites.Activites;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.Adherents;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.Afficher;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Entrainements.Entrainements;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Salles.Salles;
import fr.esgi.mymodule.mymodule.myclub.app.Maps.MapsActivity;

public class MainActivity extends ActionBarActivity {

    private String[] SlideListViewItems;
    private ListView drawerListView;
    DrawerLayout drawer;
    Activity context;
    private WebView webview;
    private ImageView home;



    private ListView maListViewPerso;
    ArrayList<Activite> list;
    CustomAdapterActivites adapter;
    ActiviteBDD activiteBDD;

    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        // Activite d'aujourd'hui


        maListViewPerso = (ListView) findViewById(R.id.listView_news);
        //  tab=(TabHost) findViewById(R.id.t)
        activiteBDD=new ActiviteBDD(this);
        activiteBDD.open();

        list = activiteBDD.getAllActivite();

        activiteBDD.close();

        if(list!=null)
        {


            adapter = new CustomAdapterActivites(this, list,0);

            maListViewPerso.setAdapter(adapter);

            registerForContextMenu(maListViewPerso);
        }



       // setContentView(R.layout.activity_main);

        // get the UI webview
        webview=(WebView) findViewById(R.id.webView);
        // get slide list items from strings.xml
        SlideListViewItems = getResources().getStringArray(R.array.titles);
        //get ListView defined in activity_main.xml
        drawerListView = (ListView) findViewById(R.id.left_slide);
        //Set item click listener to slide list
        drawerListView.setOnItemClickListener(new SlideItemAction());
        //Set the adapter for the list view
        drawerListView.setAdapter(new CustomAdapter(this, R.layout.list_item, R.id.title, SlideListViewItems));


        // html presentation

        String summary = "<html><body><marquee> <h1> MyClub  :Liste des activit&egrave;s d'aujourd'hui </h1> </marquee>" +
                "</body></html>";
        webview.loadData(summary, "text/html", null);

    }


    @Override
    protected void onResume() {
        super.onResume();

        refresh();

    }

private void refresh()
{

    activiteBDD.open();

    list = activiteBDD.getAllActiviteBetweenTowDate();

    activiteBDD.close();

    if(list!=null)
    {


        adapter = new CustomAdapterActivites(this, list,0);

        maListViewPerso.setAdapter(adapter);

        registerForContextMenu(maListViewPerso);
    }

}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class SlideItemAction implements OnItemClickListener{

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            ViewGroup vg=(ViewGroup)view;
            TextView txt=(TextView)vg.findViewById(R.id.title);
            redirect(position);

            //show selected item
            //Toast.makeText(context, position+"", Toast.LENGTH_LONG).show();
           // Toast.makeText(context, txt.getText().toString(), Toast.LENGTH_LONG).show();

        }
    }

    private  void redirect(int pos)
    {

        switch (pos)
        {
            case 0:  // gestion des adherents

                Intent intAdh = new Intent(this,Adherents.class);
                startActivity(intAdh);
                break;
            case 1:  // gestion des salles
                Intent intSalles = new Intent(this,Salles.class);
                startActivity(intSalles);
                break;
            case 2:  // gestion des evenements
                Intent intEnvent = new Intent(this,Entrainements.class);
                startActivity(intEnvent);
                break;
            case 3:  // gestion des gestion des activités
                Intent intactivite = new Intent(this,Activites.class);
                startActivity(intactivite);
                break;
            case 4:  // Maps
                Intent intMaps = new Intent(this,MapsActivity.class);
                startActivity(intMaps);
            //    Intent intMaps = new Intent(this,Maps.class);
              //  startActivity(intMaps);
                break;
        }

    }

}
