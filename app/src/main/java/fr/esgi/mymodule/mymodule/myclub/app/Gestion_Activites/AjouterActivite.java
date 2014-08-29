package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Activites;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.ActiviteBDD;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.EntrainementBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Activite;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Entrainement;
import fr.esgi.mymodule.mymodule.myclub.app.R;

public class AjouterActivite extends ActionBarActivity {


    EditText intitule_activite;
    EditText date_demarrage;
    EditText date_fin;
    EditText type_activite;
    EditText commentaires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_activite);

         intitule_activite=(EditText) findViewById(R.id.intitule_activite);
         date_demarrage=(EditText) findViewById(R.id.date_debut);
         date_fin=(EditText) findViewById(R.id.date_fin);
         type_activite=(EditText) findViewById(R.id.type_d_activite);
         commentaires=(EditText) findViewById(R.id.commentaire_activite);
    }


    public void AjouterActivite(View v)
    {

        ActiviteBDD activiteBDD = new ActiviteBDD(this);


        Activite activite=new Activite(intitule_activite.getText().toString(),date_demarrage.getText().toString(),date_fin.getText().toString(),type_activite.getText().toString(),commentaires.getText().toString());

        activiteBDD.open();

        activiteBDD.insertActivite(activite);


       Activite    activite1 = activiteBDD.getActiviteWithNom(activite.getIntitule_activite());

        if(activite1 != null){
            //On affiche les infos du livre dans un Toast
            Toast.makeText(this, "L'ajout à été effectué correctement", Toast.LENGTH_LONG).show();
            clean();

        }else
        {
            Toast.makeText(this,"Error", Toast.LENGTH_LONG).show();

        }

        activiteBDD.close();


    }

    private void clean()
    {
        intitule_activite.setText("");
        date_demarrage.setText("");
        date_fin.setText("");
        type_activite.setText("");
        commentaires.setText("");
    }


    public void aff(View v)
    {
        ActiviteBDD activiteBDD=new ActiviteBDD(AjouterActivite.this);
        activiteBDD.open();
        Toast.makeText(this,activiteBDD.getAllActivite().get(0).toString(), Toast.LENGTH_LONG).show();
   activiteBDD.close();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ajouter_activite, menu);
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
