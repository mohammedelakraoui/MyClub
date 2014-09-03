package fr.esgi.mymodule.mymodule.myclub.app.Manager;

import android.content.Context;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.esgi.mymodule.mymodule.myclub.app.R;

/**
 * Created by server-pc on 30/08/2014.
 */
public class ManagerError {

    public static boolean matchesText(EditText[] editTexts,Context c)
    {
        if(editTexts!=null)
        {
            for(EditText t:editTexts) {

                if(!t.getText().toString().matches("[a-zA-Z.? ]*")) {

                    t.setBackgroundColor(c.getResources().getColor(R.color.Red));
                    return false;

                }
                }
        }
        return true;
    }
    public static boolean check(EditText[] objets,Context c)
    {
        if(objets!=null) {
            for (EditText t : objets) {
                if (t.getText().toString().trim().equals("")) {

                    t.setBackgroundColor(c.getResources().getColor(R.color.Red));
                    return false;
                }

            }
        }
        return true;
    }

    public static Boolean isDate(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyy", Locale.US);
        sdf.setLenient(false);
        try{
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }


       // if(date.length()<8){ return false;}
        //else {return true;}





    }
}
