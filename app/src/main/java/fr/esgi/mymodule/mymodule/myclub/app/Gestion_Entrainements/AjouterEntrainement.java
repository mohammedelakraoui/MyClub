package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Entrainements;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.EntrainementBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Entrainement;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents.AjouterAdherent;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.ManagerError;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.MessageBox;
import fr.esgi.mymodule.mymodule.myclub.app.R;

public class AjouterEntrainement extends Activity {


    private EditText nom_seance_entrainement;
    private EditText date_entrainement;
    private TimePicker heur_entrainement;
    private EditText nombre_places_entrainement;
    private EditText commentaire;
private  EditText[] editTextForCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_entrainement);

          nom_seance_entrainement=(EditText)findViewById(R.id.nom_sceance_entraintement);
          date_entrainement=(EditText)findViewById(R.id.date_entrainement);
          heur_entrainement=(TimePicker)findViewById(R.id.heur_entrainement);
          nombre_places_entrainement=(EditText)findViewById(R.id.nombre_place_entrainement);
          commentaire=(EditText)findViewById(R.id.commentaire_entrainement);
          EditText[] editTexts={nom_seance_entrainement,date_entrainement,nombre_places_entrainement,commentaire};
          editTextForCheck=editTexts;

    }

    public void AjouterEntrainement(View v) {

        if(!ManagerError.isDate(date_entrainement.getText().toString().trim()) ) {
            date_entrainement.setBackgroundColor(getResources().getColor(R.color.Red));

            return;
        }

        if(ManagerError.check(editTextForCheck, AjouterEntrainement.this))
        {
        EntrainementBDD entrainementBDD = new EntrainementBDD(this);

        String heur_ = heur_entrainement.getCurrentHour() + ":" + heur_entrainement.getCurrentMinute() + " " + (heur_entrainement.is24HourView() ? " PM" : " AM");


        Entrainement entrainement = new Entrainement(nom_seance_entrainement.getText().toString(), date_entrainement.getText().toString(), heur_, Integer.parseInt(nombre_places_entrainement.getText().toString()), this.commentaire.getText().toString());

        entrainementBDD.open();

        entrainementBDD.insertEntrainement(entrainement);


        Entrainement entrainement1 = entrainementBDD.getEntrainementWithNom(entrainement.getNom_seance_entrainement());

        if (entrainement1 != null) {
            //On affiche les infos du livre dans un Toast
            Toast.makeText(this, "L'ajout à été effectué correctement", Toast.LENGTH_LONG).show();
            clean(v);

        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();

        }

        entrainementBDD.close();
    }
else
        {
            MessageBox.Show(AjouterEntrainement.this,"Error","Verifiez-vous les zones de saisi");
        }
    }

    public void clean(View v)
    {
        nom_seance_entrainement.setText("");
        date_entrainement.setText("");
        nombre_places_entrainement.setText("");
        commentaire.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ajouter_entrainement, menu);
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
