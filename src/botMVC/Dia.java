package botMVC;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class Dia {

	private String fs, titulo, data;
	private SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
	Locale lugar = new Locale("pt", "BR");
	GregorianCalendar cal = new GregorianCalendar(); 

	public Dia(String data, String fs, String titulo) {
		this.data = data;
		this.fs = fs;
		this.titulo = titulo;
	}

	public Dia() {
		new Dia(formatar.format(cal.getTime()), "N/A", "N/A");
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return this.data;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setFS(String fs) {
		this.fs = fs;
	}

	public String getFS() {
		return this.fs;
	}




}
