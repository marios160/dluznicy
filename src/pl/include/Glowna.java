package pl.include;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class Glowna implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Osoba> dluznicy;
	private ArrayList<Osoba> wierzyciele;

	public Glowna() {
		File f = Environment.getExternalStorageDirectory();
		File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "Dluznicy");
		if (!folder.exists()) {
			folder.mkdir();
		}
		Glowna glowna = getConfFile();
		if (glowna != null) {

			this.dluznicy = glowna.dluznicy;
			this.wierzyciele = glowna.wierzyciele;

		} else {

			this.dluznicy = new ArrayList<Osoba>();
			this.wierzyciele = new ArrayList<Osoba>();
		}
	}

	public void setClassFile(Glowna c) {
		ObjectOutputStream pl = null;
		try {

			File f = Environment.getExternalStorageDirectory();
			pl = new ObjectOutputStream(new FileOutputStream(f.getAbsolutePath() + "/Dluznicy/config.txt"));

			pl.writeObject(c);
			pl.flush();
			pl.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Glowna getConfFile() {
		File f = Environment.getExternalStorageDirectory();
		File file2 = new File(f.getAbsolutePath() + "/Dluznicy/config.txt");
		System.out.println(f.getAbsolutePath());
		Glowna c = null;

		if (file2.exists()) {
			ObjectInputStream pl2 = null;
			try {
				pl2 = new ObjectInputStream(new FileInputStream(f.getAbsolutePath() + "/Dluznicy/config.txt"));
				c = (Glowna) pl2.readObject();
				pl2.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}

		return c;
	}

	public void dodajDluznika(Osoba osoba) {
		dluznicy.add(osoba);
		setClassFile(this);

	}

	public void usunDluznika(int id) {
		dluznicy.remove(id);
		setClassFile(this);
	}

	public void dodajWierzyciela(Osoba osoba) {
		wierzyciele.add(osoba);
		setClassFile(this);
	}

	public void usunWierzyciela(int id) {
		wierzyciele.remove(id);
		setClassFile(this);
	}

	public ArrayList<Osoba> getDluznicy() {
		return dluznicy;
	}

	public void setDluznicy(ArrayList<Osoba> dluznicy) {
		this.dluznicy = dluznicy;
	}

	public ArrayList<Osoba> getWierzyciele() {
		return wierzyciele;
	}

	public void setWierzyciele(ArrayList<Osoba> wierzyciele) {
		this.wierzyciele = wierzyciele;
	}

	public Osoba getDluznik(int id) {
		return dluznicy.get(id);
	}

	public Osoba getWierzyciel(int id) {
		return wierzyciele.get(id);
	}

}
