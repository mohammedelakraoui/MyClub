package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import android.support.v7.internal.widget.AdapterViewICS;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.R;

public class ModifierAdherent extends ActionBarActivity {

    Button annuller;
    ArrayAdapter<CharSequence> adp3;
    private Spinner spinnerId;
    private ArrayList<Adherent> listId;
    Adherent adherent;
    Adherent adtoUpdate=new Adherent();
    AdherentBDD adherentBDD=new AdherentBDD(getBaseContext());

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
        setContentView(R.layout.activity_modifier__adherent);
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        spinnerId=(Spinner) findViewById(R.id.spinnerID);
        annuller=(Button) findViewById(R.id.button2);
        nom=(EditText)findViewById(R.id.Nom);
        prenom=(EditText)findViewById(R.id.prenom);
        sexe =(RadioGroup) findViewById(R.id.radiosexe);
        poid=(EditText)findViewById(R.id.poid);
        age=(EditText)findViewById(R.id.age);
        phone =(EditText) findViewById(R.id.phone);
        listedesciplines=(Spinner) findViewById(R.id.spinner);

        adherent=new Adherent();
        adherentBDD=new AdherentBDD(getBaseContext());
        listId=new ArrayList<Adherent>();
        adherentBDD.open();
        listId=adherentBDD.getAllAdherent();
        if(listId==null)
        {

            Toast.makeText(getBaseContext(),"La BD est vide" , Toast.LENGTH_SHORT).show();
            return;

        }
        ArrayList<String> spinnerArray=new ArrayList<String>();
        spinnerArray.add("Choisissez un ID adtherent!!");
        for (int i = 0; i < listId.size(); i++) {

         spinnerArray.add(listId.get(i).getId() + "");
      //  Toast.makeText(getBaseContext(),listId.size()+"" , Toast.LENGTH_SHORT).show();
        }

        adp3=ArrayAdapter.createFromResource(this,R.array.desciplines, android.R.layout.simple_list_item_1);
        adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listedesciplines.setAdapter(adp3);

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,spinnerArray);
        spinnerId.setAdapter(spinnerArrayAdapter);


  spinnerId.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
         adherentBDD.open();
       //   Toast.makeText(getBaseContext(),i + "", Toast.LENGTH_SHORT).show();

          if(i!=0) {
           //  Toast.makeText(getBaseContext(), adherentBDD.getAdherentWithId(spinnerId.getSelectedItem().toString().trim()).toString() + "", Toast.LENGTH_SHORT).show();

              adtoUpdate=adherentBDD.getAdherentWithId(spinnerId.getSelectedItem().toString().trim());
              setText(adtoUpdate);

          }
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
  });


        annuller.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
            }
        });
        adherentBDD.close();
    }




    public void update(View v)
    {
       adherentBDD.open();

        adtoUpdate.setNom(this.nom.getText().toString());
        adtoUpdate.setPrenom(this.prenom.getText().toString());
        adtoUpdate.setAge(Integer.parseInt(this.age.getText().toString()));
        adtoUpdate.setPoid(Integer.parseInt(poid.getText().toString()));
        adtoUpdate.setDiscipline(listedesciplines.getSelectedItem().toString());

          if(adherentBDD.updateAdherent(adtoUpdate.getId(), adtoUpdate)==1) {
              Toast.makeText(this, "La modification à été bien effectuée", Toast.LENGTH_LONG).show();
              clean();

          }else
          {
              Toast.makeText(this, "Error:modification!!", Toast.LENGTH_LONG).show();
          }
        adherentBDD.close();


    }
    private void clean()
    {

        spinnerId.setSelection(0);
        this.nom.setText("");
        this.prenom.setText("");
        this.age.setText("");
        this.poid.setText("");
        this.phone.setText("");
    }

    private void setText(Adherent adherent)
    {
        if(adherent!=null) {
            this.nom.setText(adherent.getNom());
            this.prenom.setText(adherent.getPrenom());
            this.age.setText(adherent.getAge()+"");
            this.poid.setText(adherent.getPoid()+"");
            this.phone.setText(adherent.getPhone());
            //    adp3.add(adherent.getDiscipline());
            //    adp3.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modifier__adherent, menu);
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
