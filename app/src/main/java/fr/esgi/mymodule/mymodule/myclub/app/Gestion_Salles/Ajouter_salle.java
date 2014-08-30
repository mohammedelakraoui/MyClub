package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Salles;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.SalleBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Salle;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.ManagerError;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.MessageBox;
import fr.esgi.mymodule.mymodule.myclub.app.R;

public class Ajouter_salle extends ActionBarActivity {

      EditText nom_salle;
      EditText capacite;
      EditText  nom_coach;
      Spinner liste;
EditText[] editTexts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_salle);

        liste=(Spinner) findViewById(R.id.spinnerliste);
        nom_salle =(EditText) findViewById(R.id.NomSalle);
        capacite=(EditText) findViewById(R.id.capacite);
        nom_coach=(EditText) findViewById(R.id.nomresponsable);
        ArrayAdapter<CharSequence> adp3=ArrayAdapter.createFromResource(this,R.array.desciplines, android.R.layout.select_dialog_item);
        adp3.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        liste.setAdapter(adp3);
        EditText[] editTexts1={nom_salle,capacite,nom_coach};
        editTexts=editTexts1;


    }

public void  AjouterSalle(View v)
{
    if(ManagerError.check(editTexts,Ajouter_salle.this))
    {
    SalleBDD salleBDD = new SalleBDD(this);
    Salle salle=new Salle(nom_salle.getText().toString(),Integer.parseInt(capacite.getText().toString()),nom_coach.getText().toString(),liste.getSelectedItem().toString());
    salleBDD.open();
    salleBDD.insertSalle(salle);
    Salle sallefromDB = salleBDD.getSalleWithNom(salle.getNom_salle());

    if(sallefromDB != null)
    {
        //On affiche les infos du livre dans un Toast
        Toast.makeText(this, "L'ajout à été effectué correctement", Toast.LENGTH_LONG).show();

    }
    else
    {
        Toast.makeText(this,"Error", Toast.LENGTH_LONG).show();

    }

}
    else
    {
        MessageBox.Show(Ajouter_salle.this, "Error", "Verifiez-vous les zones de saisi");
    }

}

    public void annuller(View v)
    {
        nom_salle.setText("");
        capacite.setText("");
        nom_coach.setText("");
        liste.setSelection(0);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ajouter_salle, menu);
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
