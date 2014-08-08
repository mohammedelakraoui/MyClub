package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.R;

public class AjouterAdherent extends ActionBarActivity {


    EditText nom;
    EditText prenom;
    RadioGroup sexe;
    EditText poid;
    EditText age;
    EditText phone;
    Spinner listedesciplines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_adherent);

        nom=(EditText)findViewById(R.id.Nom);
        prenom=(EditText)findViewById(R.id.prenom);
        sexe =(RadioGroup) findViewById(R.id.radiosexe);
        poid=(EditText)findViewById(R.id.poid);
        age=(EditText)findViewById(R.id.age);
        phone =(EditText) findViewById(R.id.phone);
        listedesciplines=(Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adp3=ArrayAdapter.createFromResource(this,R.array.desciplines, android.R.layout.simple_list_item_1);

        adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listedesciplines.setAdapter(adp3);
        listedesciplines.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                String ss=listedesciplines.getSelectedItem().toString();
                Toast.makeText(getBaseContext(), ss, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {


            }

        });

    }


    public void AjouterAdherent(View v)
    {

        //Création d'une instance de ma classe AdherentBDD
        AdherentBDD adherentBDD = new AdherentBDD(this);

        // get selected radio button from radioGroup
        int selectedId = sexe.getCheckedRadioButtonId();

        // find the radiobutton by returned id
       RadioButton sexe_ = (RadioButton) findViewById(selectedId);

        //Création d'un Adherent
        Adherent adherent=new Adherent(nom.getText().toString(),prenom.getText().toString(),sexe_.getText().toString(),Integer.parseInt(poid.getText().toString()),Integer.parseInt(age.getText().toString()),phone.getText().toString(),listedesciplines.getSelectedItem().toString());

        //On ouvre la base de données pour écrire dedans
        adherentBDD.open();
        //On insère l'adherent que l'on vient de créer
        adherentBDD.insertAdherent(adherent);

        //Pour vérifier que l'on a bien créé notre adherent dans la BDD
        //on extrait le adherent de la BDD grâce au Nom du adherent que l'on a créé précédemment
       Adherent AdherentFromBdd = adherentBDD.getAdherentWithNom(adherent.getNom());
        //Si un adherent est retourné (donc si l'adherent à bien été ajouté à la BDD)
        if(AdherentFromBdd != null){
            //On affiche les infos du livre dans un Toast
            Toast.makeText(this,"L'ajout à été effectué correctement", Toast.LENGTH_LONG).show();
            //On modifie le titre du livre
      //      AdherentFromBdd.setNom("J'ai modifié le Nom d Adherent");
            //Puis on met à jour la BDD
     //       adherentBDD.updateAdherent(AdherentFromBdd.getId(), AdherentFromBdd);
        }else
        {
            Toast.makeText(this,"Error", Toast.LENGTH_LONG).show();

        }
/*
        //On extrait le livre de la BDD grâce au nouveau titre
        livreFromBdd = livreBdd.getLivreWithTitre("J'ai modifié le titre du livre");
        //S'il existe un livre possédant ce titre dans la BDD
        if(livreFromBdd != null){
            //On affiche les nouvelles informations du livre pour vérifier que le titre du livre a bien été mis à jour
            Toast.makeText(this, livreFromBdd.toString(), Toast.LENGTH_LONG).show();
            //on supprime le livre de la BDD grâce à son ID
            livreBdd.removeLivreWithID(livreFromBdd.getId());
        }

        //On essaye d'extraire de nouveau le livre de la BDD toujours grâce à son nouveau titre
        livreFromBdd = livreBdd.getLivreWithTitre("J'ai modifié le titre du livre");
        //Si aucun livre n'est retourné
        if(livreFromBdd == null){
            //On affiche un message indiquant que le livre n'existe pas dans la BDD
            Toast.makeText(this, "Ce livre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }
        //Si le livre existe (mais normalement il ne devrait pas)
        else{
            //on affiche un message indiquant que le livre existe dans la BDD
            Toast.makeText(this, "Ce livre existe dans la BDD", Toast.LENGTH_LONG).show();
        }
*/
        adherentBDD.close();


    }


    public void annuller(View v)
    {


        nom.setText("Nom");
        prenom.setText("Prenom");

    }
    public  void cleanNom(View v)
    {
        nom.setText("");
    }
    public  void cleanPrenom(View v)
    {
        prenom.setText("");
    }
    public  void cleanPoid(View v)
    {
        poid.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ajouter_adherent, menu);
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
