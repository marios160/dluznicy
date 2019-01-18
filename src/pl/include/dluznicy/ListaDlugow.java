package pl.include.dluznicy;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import pl.include.Dlug;
import pl.include.MainActivity;
import pl.include.Osoba;
import pl.include.R;
import pl.include.R.id;
import pl.include.R.layout;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListaDlugow extends Activity {

	private ListView list;
	private DlugiAdapter adapter;
	
	Dlug dlug;
	Osoba osoba;
	int id;
	int idDlug;
	int kto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle tobolek = getIntent().getExtras();
		id = tobolek.getInt("profil");
		this.kto = pl.include.MainActivity.kto;
		switch(this.kto){
		case 1:
			this.osoba = MainActivity.glowna.getDluznicy().get(id);
			break;
		case 2:
			this.osoba = MainActivity.glowna.getWierzyciele().get(id);
			break;
		default:
			break;
			
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_dlugow);
		
		
	

	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		list = (ListView) findViewById(R.id.listaDlugow);
		adapter = new DlugiAdapter(this, R.layout.lista_dlugow, osoba.getDlugi());
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				pokazDlug(position);

			}

		});
	}

	public void usun() {
		osoba.getDlugi().remove(dlug);
	}

	public void edytuj() {
		Intent intent = new Intent(this, EdytujDlug.class);
		intent.putExtra("profil", id);
		intent.putExtra("dlug", idDlug);
		startActivity(intent);
	}

	private void pokazDlug(int id) {
		this.idDlug = id;
		this.dlug = this.osoba.getDlugi().get(id);
		edytuj();

	}

	

	public void edytujDane(View view) {

		Intent intent = new Intent(this, EdytujDaneDluznika.class);
		intent.putExtra("profil", id);
		startActivity(intent);
	}

	public void usun(View view) {
		switch(this.kto){
		case 1:
			MainActivity.glowna.usunDluznika(this.id);
			break;
		case 2:
			MainActivity.glowna.usunWierzyciela(this.id);
			break;
		default:
			break;
			
		}
		Toast.makeText(this, "Usunięto!", Toast.LENGTH_SHORT).show();
		onBackPressed();
	}

	public void dodajDlug(View view) {
		Intent intent = new Intent(this, DodajDlug.class);
		intent.putExtra("osobadlug", id);
		startActivity(intent);
	}

	
}
