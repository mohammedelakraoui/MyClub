package fr.esgi.mymodule.mymodule.myclub.app.Manager;

import android.content.Context;
import android.widget.EditText;

import fr.esgi.mymodule.mymodule.myclub.app.R;

/**
 * Created by server-pc on 30/08/2014.
 */
public class ManagerError {

    public static boolean check(EditText[] objets,Context c)
    {
        for(EditText t:objets)
        {
            if(t.getText().toString().trim().equals(""))
            {
                t.setText("error");
                t.setTextColor(c.getResources().getColor(R.color.Red));
                return false;
            }

        }
        return true;
    }
}
