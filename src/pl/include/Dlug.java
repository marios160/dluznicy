package pl.include;

import java.io.Serializable;
import java.util.Date;

public class Dlug implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double suma;
	private String dataPozyczki;
	private String dataZwrotu;
	private String notka;
	
	public Dlug( double suma, String dataPozyczki, String dataZwrotu, String notka) {
		this.suma = suma;
		this.dataPozyczki = dataPozyczki;
		this.dataZwrotu = dataZwrotu;
		this.notka = notka;
	}
	

	public double getSuma() {
		return suma;
	}
	public void setSuma(double suma) {
		this.suma = suma;
	}
	public String getDataPozyczki() {
		return dataPozyczki;
	}
	public void setDataPozyczki(String dataPozyczki) {
		this.dataPozyczki = dataPozyczki;
	}
	public String getDataZwrotu() {
		return dataZwrotu;
	}
	public void setDataZwrotu(String dataZwrotu) {
		this.dataZwrotu = dataZwrotu;
	}
	public String getNotka() {
		return notka;
	}
	public void setNotka(String notka) {
		this.notka = notka;
	}

	
	
}
