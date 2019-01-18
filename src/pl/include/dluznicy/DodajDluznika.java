package pl.include.dluznicy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import pl.include.MainActivity;
import pl.include.Osoba;
import pl.include.R;
import pl.include.R.id;
import pl.include.R.layout;

public class DodajDluznika extends Activity {
	
	String ksywka;
	String imie;
	String telefon;
	String adres;
	String email;
	Bundle savedInstanceState;
	int kto;

	public void dodajDluznika(View view){
		EditText ET = (EditText)findViewById(R.id.edytujDlugKwotaEdit);
		ksywka = ET.getText().toString();
		ET = (EditText)findViewById(R.id.edytujDlugDataPozyczkiEdit);
		imie = ET.getText().toString();
		ET = (EditText)findViewById(R.id.edytujDlugDataZwrotuEdit);
		telefon = ET.getText().toString();
		ET = (EditText)findViewById(R.id.edytujDlugNotkaEdit);
		adres = ET.getText().toString();
		ET = (EditText)findViewById(R.id.editText6);
		email = ET.getText().toString();
		Osoba osoba = new Osoba(imie,ksywka,telefon,adres,email);
		switch (this.kto) {
		case 1:
			MainActivity.glowna.dodajDluznika(osoba);
			break;
		case 2:
			MainActivity.glowna.dodajWierzyciela(osoba);
			break;
		default:
			break;

		}
		onBackPressed();

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.savedInstanceState = savedInstanceState;
		setContentView(R.layout.activity_dodaj_dluznika);
		this.kto = pl.include.MainActivity.kto;
	}

	
}
