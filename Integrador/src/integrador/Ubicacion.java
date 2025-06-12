package integrador;

import java.util.ArrayList;

public class Ubicacion {
	private double latitud;
	private double longitud;
	private ArrayList<ZonaDeCobertura> zonasDeCobertura; // puede tener mas de una
	
	public Ubicacion(double latitud, double longitud, ArrayList<ZonaDeCobertura> zonaDeCobertura) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
		this.zonasDeCobertura = zonaDeCobertura;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public ArrayList<ZonaDeCobertura> getZonasDeCobertura() {
		return zonasDeCobertura;
	}
	public void setZonaDeCobertura(ArrayList<ZonaDeCobertura> zonaDeCobertura) {
		this.zonasDeCobertura = zonaDeCobertura;
	}
	
}
