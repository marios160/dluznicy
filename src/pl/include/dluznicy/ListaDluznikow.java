
package pl.include.dluznicy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import pl.include.MainActivity;
import pl.include.Osoba;
import pl.include.R;
import pl.include.R.id;
import pl.include.R.layout;

public class ListaDluznikow extends Activity {

	private ListView list;
	private DluznicyAdapter adapter;
	int kto;
	
	public void dodajDluznika(View view) {
		Intent intent = new Intent(this, DodajDluznika.class);
		startActivity(intent);
	}

	public void edytujDluznika(View view, int id) {
		

		Intent intent = new Intent(this, ListaDlugow.class);
		intent.putExtra("profil", id);
		startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_dluznikow);
		this.kto = pl.include.MainActivity.kto;
		list = (ListView) findViewById(R.id.listaDluznikow);
		
		switch(this.kto){
		case 1:
			adapter = new DluznicyAdapter(this, R.layout.lista_dluznikow, MainActivity.glowna.getDluznicy());
			break;
		case 2:
			adapter = new DluznicyAdapter(this, R.layout.lista_dluznikow, MainActivity.glowna.getWierzyciele());
			break;
		default:
			break;
		
		}
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	edytujDluznika(view, position);
                
            }
        });

	}
	
	@Override
	protected void onStart() {
		super.onStart();
		double suma = 0;
		switch(this.kto){
		case 1:
			for (Osoba i : MainActivity.glowna.getDluznicy()) {
				suma += i.getSaldo();
			}
			break;
		case 2:
			for (Osoba i : MainActivity.glowna.getWierzyciele()) {
				suma += i.getSaldo();
			}
			break;
		default:
			break;
		
		}
		
		TextView saldo = (TextView) findViewById(R.id.edytujDlugDataPozyczki);
		saldo.setText(Double.toString(suma));
	};
	
	@Override
	protected void onRestart() {		
		super.onRestart();
		
		list = (ListView) findViewById(R.id.listaDluznikow);

		switch(this.kto){
		case 1:
			adapter = new DluznicyAdapter(this, R.layout.lista_dluznikow, MainActivity.glowna.getDluznicy());
			break;
		case 2:
			adapter = new DluznicyAdapter(this, R.layout.lista_dluznikow, MainActivity.glowna.getWierzyciele());
			break;
		default:
			break;
		
		}
		
		list.setAdapter(adapter);
	};

	
}
