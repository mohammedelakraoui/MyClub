package fr.esgi.mymodule.mymodule.myclub.app.Adapters;
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
        Adherent adherent = listItem.get(position);
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

          //  Drawable btmpDrawable=new BitmapDrawable(convertView.getResources(), R.drawable.myclub);
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
        private final TextView phone;
        private final TextView Discipline;

        public ViewHolder(View view) {
           image = (ImageView) view.findViewById(R.id.Image_me);
            Nom = (TextView) view.findViewById(R.id.Name_me);
            Prenom = (TextView) view.findViewById(R.id.Prenom_me);
            Poid = (TextView) view.findViewById(R.id.Poid_me);
            Age = (TextView) view.findViewById(R.id.Age_me);
            Sexe = (TextView) view.findViewById(R.id.Sexe_me);
            phone=(TextView) view.findViewById(R.id.Phone_me);
           Discipline = (TextView) view.findViewById(R.id.Discipline_me);

        }
    }
}
