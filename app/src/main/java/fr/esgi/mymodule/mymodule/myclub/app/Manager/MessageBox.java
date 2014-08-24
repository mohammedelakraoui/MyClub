package fr.esgi.mymodule.mymodule.myclub.app.Manager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by server-pc on 24/08/2014.
 */
public class   MessageBox {

    public static void Show(Context c,String Title,String Message)
    {
        // Remove Item
        AlertDialog.Builder adb = new AlertDialog.Builder(c);
        adb.setTitle(Title);
        adb.setMessage(Message);

        adb.setNegativeButton("Cancel", null);
        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // *** Here is where I am experiencing issueos ***

            }
        });
        adb.show();

    }

}
