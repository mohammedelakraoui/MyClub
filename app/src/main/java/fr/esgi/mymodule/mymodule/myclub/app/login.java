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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import fr.esgi.mymodule.mymodule.myclub.app.Manager.JSONParser;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.ManagerNetWork;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.MessageBox;


/*

site : myclub.olympe.in




 */



public class login extends ActionBarActivity {

    private JSONArray response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




    }

    public void connect(View v)
    {


        String UrlUser = "http://myclub.olympe.in/select.php?username=esgi&password=esgi";







        if(ManagerNetWork.isNetworkAvailable(this))
        {
            try
            {
                response = new JSONParser(UrlUser).execute().get();
                Toast.makeText(getApplicationContext(),"Connecting...Please wait",Toast.LENGTH_LONG).show();
                MessageBox.Show(login.this,"response",response.toString());

             /*   if (ManagerUser.authenticate(username, Password) == true) {

                    Intent intent = new Intent(this, NewsFeed.class);
                    intent.putExtra("currentUser", ManagerUser.getCurrentUser()
                            .toString());
                    startActivity(intent);

                    android.content.SharedPreferences prefs = getSharedPreferences("UserData", 0);
                    android.content.SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("currentuser", ManagerUser.getCurrentUser().toString());
                    editor.commit();*/
               // }
               // else {
                  /*  Toast.makeText(this,"Error Please Check your Username/Password",Toast.LENGTH_LONG).show();
                    this.UserName.setTextColor(Color.parseColor("#F01414"));
                    this.UserName.setTextColor(Color.parseColor("#F01414"));
                    this.UserName.setFocusable(true);
                    this.UserName.requestFocus();
                }*/
            }
            catch (Exception ex)
            {

            }


        }
        else
        {
            ManagerNetWork.alertNetwork(login.this);
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
