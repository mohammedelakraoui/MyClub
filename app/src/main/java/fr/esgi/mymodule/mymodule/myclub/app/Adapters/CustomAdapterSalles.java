package fr.esgi.mymodule.mymodule.myclub.app.Adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.Classes.Salle;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.PicturesManager;
import fr.esgi.mymodule.mymodule.myclub.app.R;

/**
 * Created by melakraoui on 20/08/2014.
 */
public class CustomAdapterSalles extends BaseAdapter {

    private final ArrayList<Salle> listItem;
    private final Context context;

    public CustomAdapterSalles(Context context, ArrayList<Salle> listItem ) {

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

            convertView = inflater.inflate(R.layout.list_item_afficher_salle, parent, false);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);



        }



        holder = (ViewHolder) convertView.getTag();
        Salle salle = listItem.get(position);

        holder.nom_salle.setText(salle.getNom_salle().toString());
        holder.capacite_salle.setText(salle.getCapacite()+"");
        holder.activite_salle.setText(salle.getType_activite().toString());
        holder.nom_coach_salle.setText(salle.getNom_coach());


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


        private final   TextView nom_salle;
        private final   TextView capacite_salle;
        private final   TextView  nom_coach_salle;
        private final   TextView activite_salle;


        public ViewHolder(View view) {

            nom_salle = (TextView) view.findViewById(R.id.nom_salle_me);
            capacite_salle = (TextView) view.findViewById(R.id.capacite_salle_me);
            nom_coach_salle = (TextView) view.findViewById(R.id.coach_responsable_me);
            activite_salle = (TextView) view.findViewById(R.id.type_activite_me);

        }
    }
}
