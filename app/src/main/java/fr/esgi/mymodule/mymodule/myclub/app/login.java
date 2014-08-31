package fr.esgi.mymodule.mymodule.myclub.app;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.Manager.MessageBox;


public class login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




    }

    public void connect(View v)
    {

        String result = null;
        InputStream is = null;


        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("username","esgi@esgi.fr"));
        nameValuePairs.add(new BasicNameValuePair("password","esgi"));
        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://192.168.40.1:800/select.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            MessageBox.Show(getBaseContext(),"URL",httppost.getURI().toString());
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            Log.e("log_tag", "connection success ");

        }
        catch(Exception e)
        {
            Log.e("log_tag", "Error in http connection "+e.toString());
            Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

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

            result=sb.toString();
        }
        catch(Exception e)
        {
            Log.e("log_tag", "Error converting result "+e.toString());

            Toast.makeText(getApplicationContext(), " Input reading fail", Toast.LENGTH_SHORT).show();

        }

        //parse json data
        try{


            JSONObject object = new JSONObject(result);
            String ch=object.getString("re");
            if(ch.equals("success"))
            {

                JSONObject no = object.getJSONObject("0");

                //long q=object.getLong("f1");
                String w= no.getString("f2");
                long e=no.getLong("f3");


                String myString = NumberFormat.getInstance().format(e);



            }


            else
            {

                Toast.makeText(getApplicationContext(), "Record is not available.. Enter valid number", Toast.LENGTH_SHORT).show();

            }


        }
        catch(JSONException e)
        {
            Log.e("log_tag", "Error parsing data "+e.toString());
            Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
