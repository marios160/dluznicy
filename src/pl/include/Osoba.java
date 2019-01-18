package pl.include;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.entity.SerializableEntity;
import org.apache.http.impl.io.SocketOutputBuffer;

import android.widget.Toast;

public class Osoba implements Serializable{
	
	private int id;
	private int idDlug;
	private String imie;
	private String ksywka;
	private String telefon;
	private String adresZamieszkania;
	private String email;
	private List<Dlug> dlugi;
	private double saldo;
	
	
	
	
	public Osoba(String imie, String ksywka, String telefon, String adresZamieszkania, String email) {
		this.idDlug = 0;
		this.imie = imie;
		this.ksywka = ksywka;
		this.telefon = telefon;
		this.adresZamieszkania = adresZamieszkania;
		this.email = email;
		this.dlugi = new ArrayList<Dlug>();
		this.saldo = 0;
		
	}

	public void dodajDlug(Dlug dlug){
		saldo += dlug.getSuma();
		dlugi.add(dlug);
		MainActivity.glowna.setClassFile(MainActivity.glowna);
	}
	
	public void usunDlug(int id){
		saldo -= dlugi.get(id).getSuma();
		dlugi.remove(id);
		MainActivity.glowna.setClassFile(MainActivity.glowna);
	}

	
	
	public int getId() {
		return idDlug;
	}

	public void setId(int id) {
		this.idDlug = id;
	}

	public List<Dlug> getDlugi() {
		return dlugi;
	}

	public void setDlugi(List<Dlug> dlugi) {
		this.dlugi = dlugi;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getKsywka() {
		return ksywka;
	}
	public void setKsywka(String ksywka) {
		this.ksywka = ksywka;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getAdresZamieszkania() {
		return adresZamieszkania;
	}
	public void setAdresZamieszkania(String adresZamieszkania) {
		this.adresZamieszkania = adresZamieszkania;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
