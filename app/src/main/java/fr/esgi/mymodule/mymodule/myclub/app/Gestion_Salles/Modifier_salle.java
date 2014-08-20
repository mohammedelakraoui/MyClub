package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Salles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.SalleBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Salle;
import fr.esgi.mymodule.mymodule.myclub.app.R;

public class Modifier_salle extends ActionBarActivity {
    Salle salle;
    SalleBDD salleBDD;
    ArrayList<Salle> list;
    EditText search;

    EditText nom_salle;
    EditText capacite;
    EditText  nom_coach;
    Spinner liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_salle);
        search=(EditText) findViewById(R.id.search);

        liste=(Spinner) findViewById(R.id.spinnerliste);
        nom_salle =(EditText) findViewById(R.id.NomSalle);
        capacite=(EditText) findViewById(R.id.capacite);
        nom_coach=(EditText) findViewById(R.id.nomresponsable);

        ArrayAdapter<CharSequence> adp3=ArrayAdapter.createFromResource(this,R.array.desciplines, android.R.layout.select_dialog_item);
        adp3.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        liste.setAdapter(adp3);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
               salleBDD=new SalleBDD(getBaseContext());
               salleBDD.open();



               salle=salleBDD.searchSalle(charSequence.toString());
                if(salle!=null) {
                    Toast.makeText(getBaseContext(), salle.toString(), Toast.LENGTH_SHORT).show();

                    // Remove Item
                    AlertDialog.Builder adb = new AlertDialog.Builder(Modifier_salle.this);
                    adb.setTitle("Resultats de la rechrche?");
                    adb.setMessage("[Salle] : "+salle.getNom_salle()+" [Capacitée] : "+salle.getCapacite());

                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Confirmer", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // *** Here is where I am experiencing issueos ***
                     loadAndPutValues(salle);

                        }
                    });
                    adb.show();

                }
                salleBDD.close();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void loadAndPutValues(Salle salle)
    {
        nom_salle.setText(salle.getNom_salle());
        capacite.setText(salle.getCapacite());
        nom_coach.setText(salle.getNom_coach());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modifier_salle, menu);
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
