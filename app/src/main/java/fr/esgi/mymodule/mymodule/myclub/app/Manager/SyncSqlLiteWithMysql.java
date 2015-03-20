package fr.esgi.mymodule.mymodule.myclub.app.Manager;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;

/**
 * Created by server-pc on 02/09/2014.
 */
public class SyncSqlLiteWithMysql {

  static   StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  static   InputStream is;

    public static void syncroAdherent(Context c)
    {
        AdherentBDD adherentBDD=new AdherentBDD(c);
        adherentBDD.open();
        List<Adherent> adherents=adherentBDD.getAllAdherent();
        for (Adherent adherent:adherents)
        {
            StrictMode.setThreadPolicy(policy);

            Toast.makeText(c, "Syncro..."+adherent.getId()+" adherent(s)", Toast.LENGTH_SHORT).show();
            //http post
            try{
                url de votre serveur
                        +"nom="+adherent.getNom()
                        +"&prenom="+adherent.getPrenom()
                        +"&sexe="+adherent.getSexe()
                        +"&poid="+adherent.getPoid()
                        +"&age="+adherent.getAge()
                        +"&phone="+adherent.getPhone()
                        +"&discipline="+adherent.getDiscipline()
                        +"&pic="+adherent.getPic()
                        ;
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(url);

                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();



            }


            catch(Exception e)
            {
                MessageBox.Show(c,"Error","Connexion echouée!");


            }
            //convert response to string
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");

                }
                is.close();

                MessageBox.Show(c,"Reponse",sb.toString());

            }
            catch(Exception e)
            {

            }




        }
      adherentBDD.close();

    }


}
