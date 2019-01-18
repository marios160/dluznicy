package pl.include.dluznicy;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import pl.include.Dlug;
import pl.include.R;
import pl.include.R.id;

public class DlugiAdapter extends ArrayAdapter<Dlug> {
	
	Context context;
	int textVievResourceId;
	List<Dlug> objects = null;

	public DlugiAdapter(Context context, int textViewResourceId, List<Dlug> objects) {
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
            holder.saldo = (TextView)row.findViewById(R.id.kwotaDlugu);
            holder.data = (TextView)row.findViewById(R.id.dataZwrotu);
 
            row.setTag(holder);
        }
        else
        {
            holder = (RowBeanHolder)row.getTag();
        }
 
        Dlug object = objects.get(position);
        holder.saldo.setText(Double.toString(object.getSuma()));
        holder.data.setText(object.getDataZwrotu().toString());
 
        return row;
    }
 
    static class RowBeanHolder
    {
        TextView saldo;
        TextView data;
    }
}
