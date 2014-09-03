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
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.AjouterAdherent;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.ManagerError;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.MessageBox;
import fr.esgi.mymodule.mymodule.myclub.app.R;

public class AjouterActivite extends ActionBarActivity {


    EditText intitule_activite;
    EditText date_demarrage;
    EditText date_fin;
    EditText type_activite;
    EditText commentaires;
    EditText[] editTexts;
    EditText[] checkifText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_activite);

         intitule_activite=(EditText) findViewById(R.id.intitule_activite);
         date_demarrage=(EditText) findViewById(R.id.date_debut);
         date_fin=(EditText) findViewById(R.id.date_fin);
         type_activite=(EditText) findViewById(R.id.type_d_activite);
         commentaires=(EditText) findViewById(R.id.commentaire_activite);
         EditText[] editTexts1={intitule_activite,date_demarrage,date_fin,type_activite,commentaires};
         EditText[] checkifText2={intitule_activite,type_activite,commentaires};
         checkifText=checkifText2;
         editTexts=editTexts1;
    }


    public void AjouterActivite(View v) {
        if(!ManagerError.isDate(date_demarrage.getText().toString().trim()) || !ManagerError.isDate(date_fin.getText().toString().trim())) {
            date_demarrage.setBackgroundColor(getResources().getColor(R.color.Red));
            date_fin.setBackgroundColor(getResources().getColor(R.color.Red));
            return;
        }

        if(ManagerError.check(editTexts,AjouterActivite.this)==true  && ManagerError.matchesText(checkifText, AjouterActivite.this)==true)
        {
        ActiviteBDD activiteBDD = new ActiviteBDD(this);


        Activite activite = new Activite(intitule_activite.getText().toString(), date_demarrage.getText().toString(), date_fin.getText().toString(), type_activite.getText().toString(), commentaires.getText().toString());

        activiteBDD.open();

        activiteBDD.insertActivite(activite);


        Activite activite1 = activiteBDD.getActiviteWithNom(activite.getIntitule_activite());

        if (activite1 != null) {
            //On affiche les infos du livre dans un Toast
            Toast.makeText(this, "L'ajout à été effectué correctement", Toast.LENGTH_LONG).show();
            clean(v);

        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();

        }

        activiteBDD.close();
    }
        else
        {
            MessageBox.Show(AjouterActivite.this, "Error", "Verifiez les champs SVP !");
        }



    }

    public void clean(View v)
    {
        intitule_activite.setText("");
        date_demarrage.setText("");
        date_fin.setText("");
        type_activite.setText("");
        commentaires.setText("");
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
