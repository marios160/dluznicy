package pl.include;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

	static public Glowna glowna;
	static public int kto = 0;
	
	public void lista(View view) {
		kto = 1;
		Intent intent = new Intent(this, pl.include.dluznicy.ListaDluznikow.class);
		startActivity(intent);
	}
	
	public void wierzyciele(View view) {
		kto = 2;
		Intent intent = new Intent(this, pl.include.dluznicy.ListaDluznikow.class);
		startActivity(intent);
	}
	
	public void info(View view) {
		Intent intent = new Intent(this, Info.class);
		startActivity(intent);
	}
	
	
	
	@Override
	protected void onRestart() {
		super.onRestart();
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		glowna = new Glowna();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		double suma = 0;
		for (Osoba i : glowna.getDluznicy()) {
			suma += i.getSaldo();
		}
		
		for (Osoba i : glowna.getWierzyciele()) {
			suma -= i.getSaldo();
		}
		TextView saldo = (TextView) findViewById(R.id.edytujDlugDataZwrotu);
		saldo.setText(Double.toString(suma));
	};

	
}

