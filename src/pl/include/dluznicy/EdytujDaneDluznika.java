package pl.include.dluznicy;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import pl.include.MainActivity;
import pl.include.Osoba;
import pl.include.R;
import pl.include.R.id;
import pl.include.R.layout;

public class EdytujDaneDluznika extends Activity {

	EditText ksywka;
	EditText nazwa;
	EditText telefon;
	EditText adres;
	EditText email;
	int id;
	int kto;
	Osoba osoba;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle tobolek = getIntent().getExtras();
		id =  tobolek.getInt("profil"); 
		super.onCreate(savedInstanceState);
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
		setContentView(R.layout.activity_edytuj_dane_dluznika);
		ksywka = (EditText) findViewById(R.id.ksywaed);
		ksywka.setText(osoba.getKsywka());
		nazwa = (EditText) findViewById(R.id.nazwiskoed);
		nazwa.setText(osoba.getImie());
		telefon = (EditText) findViewById(R.id.teled);
		telefon.setText(osoba.getTelefon());
		adres = (EditText) findViewById(R.id.adresed);
		adres.setText(osoba.getAdresZamieszkania());
		email = (EditText) findViewById(R.id.emailed);
		email.setText(osoba.getEmail());
		
	}
	
	public void zapisz(View view) {
		osoba.setKsywka(ksywka.getText().toString());
		osoba.setImie(nazwa.getText().toString());
		osoba.setTelefon(telefon.getText().toString());
		osoba.setAdresZamieszkania(adres.getText().toString());
		osoba.setEmail(email.getText().toString());
		MainActivity.glowna.setClassFile(MainActivity.glowna);
		onBackPressed();
		Toast.makeText(this, "Zapisano!", Toast.LENGTH_SHORT).show();
	}

	

	
}
