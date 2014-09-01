package fr.esgi.mymodule.mymodule.myclub.app.Manager;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;

import java.io.UnsupportedEncodingException;

/**
 * Created by melakraoui on 01/09/2014.
 */
public class ManagerNetWork {

    public static boolean isNetworkAvailable(Context myContext) {
        ConnectivityManager connectivityManager  = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static String decodeString(String myString)
            throws UnsupportedEncodingException {
        String tempString = new String(myString.getBytes("ISO-8859-1"), "UTF-8");
        return Html.fromHtml(tempString).toString();

    }
    public static void alertNetwork(Context c)
    {
        AlertDialog.Builder adb = new AlertDialog.Builder(c);
        adb.setTitle("No NetWork");
        adb.setMessage("Your mobile is not connected,Please cheeck your wifi/cellulaire network.");
        adb.setPositiveButton("Ok", null);
        adb.show();

    }
}
