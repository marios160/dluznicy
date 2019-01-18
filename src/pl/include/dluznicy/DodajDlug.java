package pl.include.dluznicy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import pl.include.Dlug;
import pl.include.MainActivity;
import pl.include.Osoba;
import pl.include.R;
import pl.include.R.id;
import pl.include.R.layout;

public class DodajDlug extends Activity {
	Osoba osoba;
	EditText dataPozyczki;
	EditText dataZwrotu;
	EditText dataEdit;
	int id;
	int kto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle tobolek = getIntent().getExtras();
		id = tobolek.getInt("osobadlug");
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
		setContentView(R.layout.activity_dodaj_dlug);
		TextView ksywa = (TextView) findViewById(R.id.dodajDlugKsywka);
		ksywa.setText(osoba.getKsywka());
		dataPozyczki = (EditText) findViewById(R.id.edytujDlugDataPozyczkiEdit);
		dataPozyczki.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		dataZwrotu = (EditText) findViewById(R.id.edytujDlugDataZwrotuEdit);
		dataZwrotu.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}

	public void zapisz(View view) {
		EditText suma = (EditText) findViewById(R.id.edytujDlugKwotaEdit);
		EditText dataPozyczki = (EditText) findViewById(R.id.edytujDlugDataPozyczkiEdit);
		EditText dataZwrotu = (EditText) findViewById(R.id.edytujDlugDataZwrotuEdit);
		EditText notka = (EditText) findViewById(R.id.edytujDlugNotkaEdit);
		CheckBox przypomnienie = (CheckBox) findViewById(R.id.checkBox1);
		double cos = Double.parseDouble(suma.getText().toString());
		Dlug dlug = new Dlug(cos, dataPozyczki.getText().toString(),
				dataZwrotu.getText().toString(), notka.getText().toString());
		osoba.dodajDlug(dlug);
		if(przypomnienie.isChecked()){
			dodajWydarzenie(dataZwrotu.getText().toString(), suma.getText().toString());
		}
		onBackPressed();
	}
	
	public void dodajWydarzenie(String data, String suma){
		int year = Integer.parseInt(data.substring(0,4));
		int month = Integer.parseInt(data.substring(5,7));
		
		int day = Integer.parseInt(data.substring(8));
		System.out.println(day);
		long calID = 1;
		long startMillis = 0; 
		long endMillis = 0;     
		Calendar beginTime = Calendar.getInstance();
		beginTime.set(year, month-1, day, 15, 0);
		startMillis = beginTime.getTimeInMillis();
		Calendar endTime = Calendar.getInstance();
		endTime.set(year, month-1, day+1, 16, 0);
		endMillis = endTime.getTimeInMillis();
		ContentResolver cr = getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Events.DTSTART, startMillis);
		values.put(Events.DTEND, endMillis);
		values.put(Events.TITLE, suma+" z� - "+osoba.getKsywka());
		values.put(Events.DESCRIPTION, "");
		values.put(Events.CALENDAR_ID, calID);
		values.put(Events.EVENT_TIMEZONE,"Europe/Warsaw");
		values.put(Events.ALL_DAY, true);
		values.put(Events.HAS_ALARM, true);
		Uri uri = cr.insert(Events.CONTENT_URI, values);
		long eventID = Long.parseLong(uri.getLastPathSegment());
		ContentValues reminders = new ContentValues();
        reminders.put(Reminders.EVENT_ID, eventID);
        reminders.put(Reminders.METHOD, Reminders.METHOD_ALERT);
        reminders.put(Reminders.MINUTES, -780);
        
        Uri uri2 = cr.insert(Reminders.CONTENT_URI, reminders);
		
	}
	
	


	public void wybierzDateZwrotu(View view) {
		dataEdit = (EditText) findViewById(R.id.edytujDlugDataZwrotuEdit);
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}

	public void wybierzDatePozyczki(View view) {
		dataEdit = (EditText) findViewById(R.id.edytujDlugDataPozyczkiEdit);
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}

	public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {

			Date data2 = new GregorianCalendar(year, month, day).getTime();
			String data = new SimpleDateFormat("yyyy-MM-dd").format(data2);
			dataEdit.setText(data);
		}
	}

}
