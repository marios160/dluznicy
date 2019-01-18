package pl.include.dluznicy;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import pl.include.Osoba;
import pl.include.R;
import pl.include.R.id;

public class DluznicyAdapter extends ArrayAdapter<Osoba> {
	
	Context context;
	int textVievResourceId;
	List<Osoba> objects = null;

	public DluznicyAdapter(Context context, int textViewResourceId, List<Osoba> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.textVievResourceId = textViewResourceId;
		this.objects = objects;
	}


	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RowBeanHolder holder = null;
 
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(textVievResourceId, parent, false);
 
            holder = new RowBeanHolder();
            holder.saldo = (TextView)row.findViewById(R.id.HajsMusiSieZgadzac);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
 
            row.setTag(holder);
        }
        else
        {
            holder = (RowBeanHolder)row.getTag();
        }
 
        Osoba object = objects.get(position);
        holder.txtTitle.setText(object.getKsywka());
        holder.saldo.setText(Double.toString(object.getSaldo()));
 
        return row;
    }
 
    static class RowBeanHolder
    {
        TextView txtTitle;
        TextView saldo;
    }
}
