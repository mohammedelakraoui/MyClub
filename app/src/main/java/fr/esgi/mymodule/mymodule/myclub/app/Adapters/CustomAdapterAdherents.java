package fr.esgi.mymodule.mymodule.myclub.app.Adapters;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.ActiviteBDD;
import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.AdherentBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Activite;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.PicturesManager;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.R;

/**
 * Created by melakraoui on 08/08/2014.
 */
public class CustomAdapterAdherents extends BaseAdapter{

    private final ArrayList<Adherent> listItem;
    private final Context context;

    public CustomAdapterAdherents(Context context, ArrayList<Adherent> listItem ) {

        this.context = context;
        this.listItem = listItem;

    }
    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_item_afficher_adherent, parent, false);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);



        }



        holder = (ViewHolder) convertView.getTag();
        final ViewHolder my_holder=holder;

        final Adherent adherent = listItem.get(position);
           Bitmap pic= BitmapFactory.decodeResource(convertView.getResources(),R.drawable.myclub);

           Drawable dr=new BitmapDrawable(convertView.getResources(),PicturesManager.getRoundedCornerImage(pic));
            holder.image.setBackground(dr);
            holder.Nom.setText(adherent.getNom().toString());
            holder.Prenom.setText(adherent.getPrenom().toString());
            holder.Age.setText(adherent.getAge()+" ans");
            holder.Poid.setText(adherent.getPoid()+ " KG");
            holder.Sexe.setText(adherent.getSexe().toString());
            holder.phone.setText(adherent.getPhone().toString());
           holder.Discipline.setText(adherent.getDiscipline().toString());


        my_holder.modify_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                my_holder.accept_event.setVisibility(View.VISIBLE);
                my_holder.cancel_event.setVisibility(View.VISIBLE);
                my_holder.myRow.setBackgroundColor(context.getResources().getColor(R.color.modify));
                setEditableOption(my_holder,true);


            }
        });


        my_holder.accept_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disabledKeyBoard(my_holder);
                my_holder.accept_event.setVisibility(View.INVISIBLE);
                my_holder.cancel_event.setVisibility(View.INVISIBLE);
                setEditableOption(my_holder,false);
                AdherentBDD adherentBDD =new AdherentBDD(context);
                adherent.setNom(my_holder.Nom.getText().toString());
                adherent.setPrenom(my_holder.Prenom.getText().toString());
                adherent.setAge(Integer.parseInt(my_holder.Age.getText().toString()));
                adherent.setPoid(Integer.parseInt(my_holder.Poid.getText().toString()));
                adherent.setSexe(my_holder.Sexe.getText().toString());
                adherent.setPhone(my_holder.phone.getText().toString());
                adherentBDD.open();
                adherentBDD.updateAdherent(adherent.getId(), adherent);
                adherentBDD.close();
                Toast.makeText(context, "la modification est bien faite", Toast.LENGTH_LONG).show();

            }
        });


        my_holder.cancel_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_holder.accept_event.setVisibility(View.INVISIBLE);
                my_holder.cancel_event.setVisibility(View.INVISIBLE);
                disabledKeyBoard(my_holder);
                restValueOnClickCancel(my_holder,adherent);
            }
        });



        return convertView;
    }

    private void restValueOnClickCancel(ViewHolder holder,Adherent adherent)
    {

        holder.Nom.setText(adherent.getNom().toString());
        holder.Prenom.setText(adherent.getPrenom().toString());
        holder.Poid.setText(adherent.getPoid()+"");
        holder.Age.setText(adherent.getAge()+"");
        holder.Sexe.setText(adherent.getSexe().toString());
        holder.phone.setText(adherent.getPhone().toString());
        holder.Discipline.setText(adherent.getDiscipline().toString());

    }


    private void disabledKeyBoard(ViewHolder holder)
    {

        InputMethodManager imm = (InputMethodManager)context.getSystemService( Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(holder.Nom.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.Prenom.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.Poid.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.Age.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.Sexe.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.phone.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.Discipline.getWindowToken(), 0);


    }
    private void setEditableOption(ViewHolder holder,boolean switch_val)
    {



        holder.Nom.setFocusableInTouchMode(switch_val);
        holder.Prenom.setFocusableInTouchMode(switch_val);
        holder.Poid.setFocusableInTouchMode(switch_val);
        holder.Age.setFocusableInTouchMode(switch_val);
        holder.Sexe.setFocusableInTouchMode(switch_val);
        holder.phone.setFocusableInTouchMode(switch_val);
        holder.Discipline.setFocusableInTouchMode(switch_val);

    }




    private static  class ViewHolder {

        private final TableRow myRow;
        private final Button modify_event;
        private final Button accept_event;
        private final Button cancel_event;

        private final ImageView image;
        private final EditText Nom;
        private final EditText Prenom;
        private final EditText Poid;
        private final EditText Age;
        private final EditText Sexe;
        private final EditText phone;
        private final EditText Discipline;

        public ViewHolder(View view) {
            myRow = (TableRow) view.findViewById(R.id.row_event);
            accept_event = (Button) view.findViewById(R.id.accept_event);
            cancel_event = (Button) view.findViewById(R.id.cancel_event);
            modify_event = (Button) view.findViewById(R.id.modify_event);

            image = (ImageView) view.findViewById(R.id.Image_me);
            Nom = (EditText) view.findViewById(R.id.Name_me);
            Prenom = (EditText) view.findViewById(R.id.Prenom_me);
            Poid = (EditText) view.findViewById(R.id.Poid_me);
            Age = (EditText) view.findViewById(R.id.Age_me);
            Sexe = (EditText) view.findViewById(R.id.Sexe_me);
            phone=(EditText) view.findViewById(R.id.Phone_me);
            Discipline = (EditText) view.findViewById(R.id.Discipline_me);

        }
    }
}
