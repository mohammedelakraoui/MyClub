package fr.esgi.mymodule.mymodule.myclub.app.Adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.activity_afficher_adhrent, parent, false);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);



        }



        holder = (ViewHolder) convertView.getTag();
        Adherent adherent = listItem.get(position);
Log.v("Nom", adherent.getNom());
           // holder.image.setBackground(R.drawable.myclub);
           /* holder.Nom.setText(adherent.getNom());
            holder.Prenom.setText(adherent.getPrenom());
            holder.Age.setText(adherent.getAge());
            holder.Poid.setText(adherent.getPoid());
            holder.Sexe.setText(adherent.getSexe());
            holder.Discipline.setText(adherent.getDiscipline());*/

         //   Drawable btmpDrawable=new BitmapDrawable(convertView.getResources(), R.drawable.myclub);
          //  holder.newspic.setBackground(btmpDrawable);
          /*  if(post.getStatutCurrentUser()==3)
            {
                holder.plusun.setVisibility(View.VISIBLE);
            }
            if( post.getPlusun().toLowerCase().trim().equals("true"))
            {
                holder.plusun.setChecked(true);
            }*/




        return convertView;
    }

    private static  class ViewHolder {

        private final ImageView image;
        private final TextView Nom;
        private final TextView Prenom;
        private final TextView Poid;
        private final TextView Age;
        private final TextView Sexe;
        private final TextView Discipline;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image);
            Nom = (TextView) view.findViewById(R.id.Nom);
            Prenom = (TextView) view.findViewById(R.id.prenom);
            Poid = (TextView) view.findViewById(R.id.poid);
            Age = (TextView) view.findViewById(R.id.age);
            Sexe = (TextView) view.findViewById(R.id.sexe);
            Discipline = (TextView) view.findViewById(R.id.discipline);

        }
    }
}
