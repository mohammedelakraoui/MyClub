package fr.esgi.mymodule.mymodule.myclub.app.Gestion_Adherents;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Gestion_Salles.Ajouter_salle;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.CamTestActivity;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.ManagerError;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.MessageBox;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.PicturesManager;
import fr.esgi.mymodule.mymodule.myclub.app.R;

public class AjouterAdherent extends Activity {


    EditText nom;
    EditText prenom;
    RadioGroup sexe;
    EditText poid;
    EditText age;
    EditText phone;
    Spinner listedesciplines;
    ImageView pic;

    private String path="";

    private EditText[] editforcheck;
    private EditText[] checkIfText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_adherent);

        android.content.SharedPreferences prefs = getSharedPreferences("path_pic", 0);
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.putString("path","");
        editor.commit();


        pic=(ImageView) findViewById(R.id.pic);
        nom=(EditText)findViewById(R.id.Nom);
        prenom=(EditText)findViewById(R.id.prenom);
        sexe =(RadioGroup) findViewById(R.id.radiosexe);
        poid=(EditText)findViewById(R.id.poid);
        age=(EditText)findViewById(R.id.age);
        phone =(EditText) findViewById(R.id.phone);
        listedesciplines=(Spinner) findViewById(R.id.spinner);
        EditText[] editTexts={nom,prenom,poid,age,phone};
        EditText[] editTexts1={nom,prenom};
        checkIfText=editTexts1;
        editforcheck=editTexts;

        pic.setBackgroundResource(R.drawable.user);
        ArrayAdapter<CharSequence> adp3=ArrayAdapter.createFromResource(this,R.array.desciplines, android.R.layout.simple_list_item_1);

        adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listedesciplines.setAdapter(adp3);
        listedesciplines.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {


            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                String ss=listedesciplines.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {



            }

        });



    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onPostResume() {
        super.onPostResume();

        android.content.SharedPreferences prefs = getSharedPreferences("path_pic", 0);
        String my_path = prefs.getString("path", "");


        if (!my_path.isEmpty())
        {

            this.path=my_path;

            Drawable dr=new BitmapDrawable(this.getResources(), PicturesManager.getPicFromPath(my_path,AjouterAdherent.this));
            pic.setBackground(dr);
        }
        else
        {
            pic.setBackgroundResource(R.drawable.user);
        }
    }




    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onRestart() {
        super.onRestart();


        android.content.SharedPreferences prefs = getSharedPreferences("path_pic", 0);
        String my_path = prefs.getString("path","");

        if (!my_path.isEmpty())
        {

            this.path=my_path;
            Drawable dr=new BitmapDrawable(this.getResources(), PicturesManager.getPicFromPath(my_path,AjouterAdherent.this));
            pic.setBackground(dr);
        }


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onResume() {
        super.onResume();

        android.content.SharedPreferences prefs = getSharedPreferences("path_pic", 0);
        String my_path = prefs.getString("path","");

        if (!my_path.isEmpty())
        {

            this.path=my_path;
            Drawable dr=new BitmapDrawable(this.getResources(), PicturesManager.getPicFromPath(my_path,AjouterAdherent.this));
            pic.setBackground(dr);
        }else
        {
            pic.setBackgroundResource(R.drawable.user);

        }

    }



    public void ajouterAdherent(View v)
    {


        if(!path.isEmpty())
        {
            //Image Assigned



      if(ManagerError.check(editforcheck,AjouterAdherent.this)==true && ManagerError.matchesText(checkIfText,AjouterAdherent.this)==true)
      {
          AdherentBDD adherentBDD = new AdherentBDD(this);

          int selectedId = sexe.getCheckedRadioButtonId();

          RadioButton sexe_ = (RadioButton) findViewById(selectedId);



          Adherent adherent=new Adherent(nom.getText().toString(),prenom.getText().toString(),sexe_.getText().toString(),Integer.parseInt(poid.getText().toString()),Integer.parseInt(age.getText().toString()),phone.getText().toString(),listedesciplines.getSelectedItem().toString(),path);

          adherentBDD.open();

          adherentBDD.insertAdherent(adherent);


          Adherent AdherentFromBdd = adherentBDD.getAdherentWithNom(adherent.getNom());

          if(AdherentFromBdd != null){
              //On affiche les infos du livre dans un Toast

              Toast.makeText(this,"L'ajout à été effectué correctement", Toast.LENGTH_LONG).show();

              pic.setBackgroundResource(R.drawable.user);

              android.content.SharedPreferences prefs = getSharedPreferences("path_pic", 0);
              android.content.SharedPreferences.Editor editor = prefs.edit();
              editor.putString("path","" );
              editor.commit();

              this.path="";
              clean(v);



          }else
          {
              Toast.makeText(this,"Error", Toast.LENGTH_LONG).show();

          }

          adherentBDD.close();

      }
        else
      {
          MessageBox.Show(AjouterAdherent.this,"Error","Verifiez les champs SVP !");
      }
        }
        else
        {
            //nothing assigned
            MessageBox.Show(AjouterAdherent.this,"Avertissement","La photo est obligatoire pour la fabrication des badges...!");
            return;
        }

    }





    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void cam(View v)
    {
        pic.setBackground(null);
        Intent intAdh = new Intent(this,CamTestActivity.class);
        startActivity(intAdh);

    }
    public void annuller(View v)
    {

        clean(v);
        nom.setText("");
        prenom.setText("");

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

   public  void clean(View v)
    {

        nom.setText("");
        prenom.setText("");
        age.setText("");
        poid.setText("");
        phone.setText("");
        listedesciplines.setSelection(0);
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
