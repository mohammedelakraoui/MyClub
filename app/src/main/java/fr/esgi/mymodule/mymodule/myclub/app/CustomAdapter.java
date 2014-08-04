package fr.esgi.mymodule.mymodule.myclub.app;

/**
 * Created by server-pc on 03/08/2014.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

    int groupid;
    String[] titles;
    Context context;

    public CustomAdapter(Context context, int vg, int id, String[] titles){
        super(context,vg, id, titles);
        this.context=context;
        groupid=vg;
        this.titles=titles;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(groupid, parent, false);
        TextView textTitle= (TextView) itemView.findViewById(R.id.title);
        textTitle.setText(titles[position]);
        ImageView ico=(ImageView)itemView.findViewById(R.id.icon);
        ico.setImageResource(getImage(position));
        return itemView;
    }

    public Integer getImage(int pos){
        Integer[] imageIds=new Integer[5];
        imageIds[0]=R.drawable.users6;
        imageIds[1]=R.drawable.viral;
        imageIds[2]=R.drawable.entrainement;
        imageIds[3]=R.drawable.activite;
        imageIds[4]=R.drawable.placeholder8;
        return(imageIds[pos]);

    }

}
