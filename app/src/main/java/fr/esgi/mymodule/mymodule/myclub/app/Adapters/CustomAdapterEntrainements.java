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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.EntrainementBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Entrainement;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.PicturesManager;
import fr.esgi.mymodule.mymodule.myclub.app.R;

/**
 * Created by melakraoui on 26/08/2014.
 */
public class CustomAdapterEntrainements extends BaseAdapter{
    private final ArrayList<Entrainement> listItem;
    private final Context context;

    public CustomAdapterEntrainements(Context context, ArrayList<Entrainement> listItem ) {

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

            convertView = inflater.inflate(R.layout.list_item_afficher_entrainements, parent, false);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);



        }



        holder = (ViewHolder) convertView.getTag();
        final ViewHolder my_holder=holder;

        final Entrainement entrainement = listItem.get(position);


        holder.Nom_seance_entrainement.setText(entrainement.getNom_seance_entrainement().toString()+"");
        holder.Date_seance_entrainement.setText(entrainement.getDate_entrainement().toString()+"");
        holder.Heur_seance_entrainement.setText(entrainement.getHeur_entrainement()+"");
        holder.Nombre_places_seance_entrainement.setText(entrainement.getNombre_places_entrainement()+ "");
        holder.Commentaire_seance_entrainement.setText(entrainement.getCommentaire().toString()+"");

       my_holder.modify_event.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {



               my_holder.myRow.setBackgroundColor(context.getResources().getColor(R.color.modify));
               setEditableOption(my_holder,true);


           }
       });


        my_holder.accept_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEditableOption(my_holder,false);
                EntrainementBDD entrainementBDD =new EntrainementBDD(context);
                entrainement.setNom_seance_entrainement(my_holder.Nom_seance_entrainement.getText().toString());
                entrainement.setDate_entrainement(my_holder.Date_seance_entrainement.getText().toString());
                entrainement.setHeur_entrainement(my_holder.Heur_seance_entrainement.getText().toString());
                entrainement.setNombre_places_entrainement(Integer.parseInt(my_holder.Nombre_places_seance_entrainement.getText().toString()));
                entrainement.setCommentaire(my_holder.Commentaire_seance_entrainement.getText().toString());
                entrainementBDD.open();
                entrainementBDD.updateEntrainement(entrainement.getId(),entrainement);
                entrainementBDD.close();
                Toast.makeText(context, "la modification est bien faite", Toast.LENGTH_LONG).show();

            }
        });


        my_holder.cancel_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            disabledKeyBoard(my_holder);
            restValueOnClickCancel(my_holder,entrainement);
            }
        });


        return convertView;
    }

    private void restValueOnClickCancel(ViewHolder holder,Entrainement entrainement)
    {
        holder.Nom_seance_entrainement.setText(entrainement.getNom_seance_entrainement().toString()+"");
        holder.Date_seance_entrainement.setText(entrainement.getDate_entrainement().toString()+"");
        holder.Heur_seance_entrainement.setText(entrainement.getHeur_entrainement()+"");
        holder.Nombre_places_seance_entrainement.setText(entrainement.getNombre_places_entrainement()+ "");
        holder.Commentaire_seance_entrainement.setText(entrainement.getCommentaire().toString()+"");

    }

    private void enabledKeyBoard()
    {
        InputMethodManager imm = (InputMethodManager)context.getSystemService( Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }

    private void disabledKeyBoard(ViewHolder holder)
    {

        InputMethodManager imm = (InputMethodManager)context.getSystemService( Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(holder.Nom_seance_entrainement.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.Date_seance_entrainement.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.Heur_seance_entrainement.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.Nombre_places_seance_entrainement.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.Commentaire_seance_entrainement.getWindowToken(), 0);


    }

    private void setEditableOption(ViewHolder holder,boolean switch_val)
    {



        holder.Nom_seance_entrainement.setFocusableInTouchMode(switch_val);
        holder.Date_seance_entrainement.setFocusableInTouchMode(switch_val);
        holder.Heur_seance_entrainement.setFocusableInTouchMode(switch_val);
        holder.Nombre_places_seance_entrainement.setFocusableInTouchMode(switch_val);
        holder.Commentaire_seance_entrainement.setFocusableInTouchMode(switch_val);

    }


    private static  class ViewHolder {


        private final TableRow myRow;
        private  final Button modify_event;
        private  final Button accept_event;
        private  final Button cancel_event;
        private final EditText Nom_seance_entrainement;
        private final EditText Date_seance_entrainement;
        private final EditText Heur_seance_entrainement;
        private final EditText Nombre_places_seance_entrainement;
        private final EditText Commentaire_seance_entrainement;

        public ViewHolder(View view) {

            myRow=(TableRow) view.findViewById(R.id.row_event);
            accept_event=(Button) view.findViewById(R.id.accept_event);
            cancel_event=(Button) view.findViewById(R.id.cancel_event);
            modify_event=(Button) view.findViewById(R.id.modify_event);
            Nom_seance_entrainement = (EditText) view.findViewById(R.id.nom_seance_entrainement_me);
            Date_seance_entrainement = (EditText) view.findViewById(R.id.date_seance_entrainement);
            Heur_seance_entrainement = (EditText) view.findViewById(R.id.heur_seance_entrainement);
            Nombre_places_seance_entrainement = (EditText) view.findViewById(R.id.nombre_places_seance_entrainement);
            Commentaire_seance_entrainement = (EditText) view.findViewById(R.id.commentaire_seance_entrainement);


        }
    }
}
