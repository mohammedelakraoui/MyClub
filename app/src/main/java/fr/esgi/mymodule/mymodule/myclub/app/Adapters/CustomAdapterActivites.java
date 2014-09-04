package fr.esgi.mymodule.mymodule.myclub.app.Adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.esgi.mymodule.mymodule.myclub.app.CalssesBDD.ActiviteBDD;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Activite;
import fr.esgi.mymodule.mymodule.myclub.app.Classes.Adherent;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.ManagerError;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.MessageBox;
import fr.esgi.mymodule.mymodule.myclub.app.Manager.PicturesManager;
import fr.esgi.mymodule.mymodule.myclub.app.R;


/**
 * Created by melakraoui on 27/08/2014.
 */
public class CustomAdapterActivites extends BaseAdapter {


    private final ArrayList<Activite> listItem;
    private final Context context;
    private int tag;

    public CustomAdapterActivites(Context context, ArrayList<Activite> listItem,int tag_news ) {

        this.context = context;
        this.listItem = listItem;
        this.tag=tag_news;

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

            convertView = inflater.inflate(R.layout.list_item_afficher_activites, parent, false);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);



        }



        holder = (ViewHolder) convertView.getTag();

        if(tag==0)
        {
            holder.modify_event.setVisibility(View.INVISIBLE);
        }
        else
        {
            holder.modify_event.setVisibility(View.VISIBLE);
        }



        final ViewHolder my_holder=holder;

        final Activite activite = listItem.get(position);


        holder.intitule.setText(activite.getIntitule_activite().toString()+"");
        holder.date_debut.setText(activite.getDate_demarrage().toString()+"");
        holder.date_fin.setText(activite.getDate_fin()+"");
        holder.type.setText(activite.getType_activite()+ "");
        holder.commentaire.setText(activite.getCommentaires().toString()+"");
        final EditText[] editTexts={holder.intitule,holder.type,holder.commentaire};
        final EditText[] editTexts1={holder.date_debut,holder.date_fin};

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
                if(ManagerError.check(editTexts, context) && ManagerError.matchesText(editTexts1, context)) {
                    disabledKeyBoard(my_holder);
                    my_holder.accept_event.setVisibility(View.INVISIBLE);
                    my_holder.cancel_event.setVisibility(View.INVISIBLE);
                    setEditableOption(my_holder, false);
                    ActiviteBDD activiteBDD = new ActiviteBDD(context);
                    activite.setIntitule_activite(my_holder.intitule.getText().toString());
                    activite.setDate_demarrage(my_holder.date_debut.getText().toString());
                    activite.setDate_fin(my_holder.date_fin.getText().toString());
                    activite.setType_activite(my_holder.type.getText().toString());
                    activite.setCommentaires(my_holder.commentaire.getText().toString());
                    activiteBDD.open();
                    activiteBDD.updateActivite(activite.getId(), activite);
                    activiteBDD.close();
                    Toast.makeText(context, "la modification est bien faite", Toast.LENGTH_LONG).show();
                }
                else
                {
                    MessageBox.Show(context, "Error", "Verifiez les champs SVP !");
                }


            }
        });


        my_holder.cancel_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_holder.accept_event.setVisibility(View.INVISIBLE);
                my_holder.cancel_event.setVisibility(View.INVISIBLE);
                disabledKeyBoard(my_holder);
                restValueOnClickCancel(my_holder,activite);
            }
        });


        return convertView;
    }


    private void restValueOnClickCancel(ViewHolder holder,Activite activite)
    {
        holder.intitule.setText(activite.getIntitule_activite().toString()+"");
        holder.date_debut.setText(activite.getDate_demarrage().toString());
        holder.date_fin.setText(activite.getDate_fin()+"");
        holder.type.setText(activite.getType_activite()+ "");
        holder.commentaire.setText(activite.getCommentaires().toString()+"");

    }

    private void enabledKeyBoard()
    {
        InputMethodManager imm = (InputMethodManager)context.getSystemService( Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }

    private void disabledKeyBoard(ViewHolder holder)
    {

        InputMethodManager imm = (InputMethodManager)context.getSystemService( Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(holder.intitule.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.date_debut.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.date_fin.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.type.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(holder.commentaire.getWindowToken(), 0);


    }

    private void setEditableOption(ViewHolder holder,boolean switch_val)
    {



        holder.intitule.setFocusableInTouchMode(switch_val);
        holder.date_fin.setFocusableInTouchMode(switch_val);
        holder.date_debut.setFocusableInTouchMode(switch_val);
        holder.commentaire.setFocusableInTouchMode(switch_val);
        holder.type.setFocusableInTouchMode(switch_val);

    }


    private static  class ViewHolder {


        private final TableRow myRow;
        private final Button modify_event;
        private final Button accept_event;
        private final Button cancel_event;
        private final EditText intitule;
        private final EditText date_debut;
        private final EditText date_fin;
        private final EditText type;
        private final EditText commentaire;

        public ViewHolder(View view) {

            myRow = (TableRow) view.findViewById(R.id.row_event);
            accept_event = (Button) view.findViewById(R.id.accept_event);
            cancel_event = (Button) view.findViewById(R.id.cancel_event);
            modify_event = (Button) view.findViewById(R.id.modify_event);
            intitule = (EditText) view.findViewById(R.id.intitule_activite_me);
            date_debut = (EditText) view.findViewById(R.id.date_debut_me);
            date_fin = (EditText) view.findViewById(R.id.date_fin_me);
            type = (EditText) view.findViewById(R.id.type_me);
            commentaire = (EditText) view.findViewById(R.id.commentaire_me);


        }

    }
}
