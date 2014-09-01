package fr.esgi.mymodule.mymodule.myclub.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.login.LoginException;

import fr.esgi.mymodule.mymodule.myclub.app.Manager.JSONParser;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.ManagerNetWork;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.MessageBox;


/*

site : myclub.olympe.in




 */



public class login extends ActionBarActivity {

    private JSONArray response;
    private EditText username;
    private EditText password;
    private TextView error;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        error=(TextView) findViewById(R.id.error);
        btn=(Button) findViewById(R.id.btn);


    }

    public void connect(View v)
    {
        btn.setEnabled(false);
        Toast.makeText(getApplicationContext(),"Connexion en cours...Atthendez SVP",Toast.LENGTH_LONG).show();
        error.setText("");


        String username_=username.getText().toString();
        String password_=password.getText().toString();
        if(username_.isEmpty() || password_.isEmpty())
        {
            error.setText("(*) champs username et password sont obligatoires ");
            btn.setEnabled(true);
            return;
        }


        String Url = "http://myclub.olympe.in/select.php?username="+username_+"&password="+password_;

        if(!isValidUrl(Url))
        {
            error.setText("(*) Error Username/password!");
            //Toast.makeText(this,"Error Please Check your Username/Password",Toast.LENGTH_LONG).show();
            this.username.setTextColor(Color.parseColor("#F01414"));
            this.password.setTextColor(Color.parseColor("#F01414"));
            this.username.setFocusable(true);
            this.username.requestFocus();
            btn.setEnabled(true);
            return;
        }


        if(ManagerNetWork.isNetworkAvailable(this))
        {
            try
            {
                response = new JSONParser(Url).execute().get();

               // MessageBox.Show(login.this,"response",response.toString());
                JSONObject rep=response.getJSONObject(0);

             if (rep.getString("re").contains("success")) {

                    btn.setEnabled(true);

                    Intent intent = new Intent(this, MainActivity.class);

                    startActivity(intent);
                    finish();


                }
               else {
                    error.setText("(*) Error Username/password!");
                    //Toast.makeText(this,"Error Please Check your Username/Password",Toast.LENGTH_LONG).show();
                    this.username.setTextColor(Color.parseColor("#F01414"));
                    this.password.setTextColor(Color.parseColor("#F01414"));
                    this.username.setFocusable(true);
                    this.username.requestFocus();
                     btn.setEnabled(true);
                }
            }
            catch (Exception ex)
            {
                btn.setEnabled(true);
            }


        }
        else
        {
            ManagerNetWork.alertNetwork(login.this);
            btn.setEnabled(true);
        }
        btn.setEnabled(true);


    }
    private boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url);
        if(m.matches())
            return true;
        else
            return false;
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
