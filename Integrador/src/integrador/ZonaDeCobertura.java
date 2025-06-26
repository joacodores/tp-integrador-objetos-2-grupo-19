package integrador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import integrador.muestra.Muestra;

public class ZonaDeCobertura {
	
	// Variables
	private double radioEnKm;
	private String nombre;
	private Ubicacion epicentro;
	private Set<ZonaDeCobertura> zonasSolapadas;
	private ArrayList<Muestra> muestrasEnZona; 
	
	// Constructor
	public ZonaDeCobertura(double radioEnKm, String nombre, Ubicacion epicentro) {
		super();
		this.radioEnKm = radioEnKm;
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.zonasSolapadas = new HashSet<ZonaDeCobertura>();
		this.muestrasEnZona = new ArrayList<Muestra>();
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Set<ZonaDeCobertura> getZonasSolapadass() { //este no
		return zonasSolapadas;
	}
	
	public void setZonasSolapadas(Set<ZonaDeCobertura> zonasSolapadas) {
		this.zonasSolapadas = zonasSolapadas;
	}
	
	public void addZonaSolapada(ZonaDeCobertura z) {
		this.zonasSolapadas.add(z) ;
	}
	
	public ArrayList<Muestra> getMuestrasEnZona() {
		return muestrasEnZona;
	}
	
	public void setMuestrasEnZona(ArrayList<Muestra> muestrasEnZona) {
		this.muestrasEnZona = muestrasEnZona;
	}
	
	public void addMuestraEnZona(Muestra m) {
		this.muestrasEnZona.add(m) ;
	}

	public double getRadioEnKm() {
		return radioEnKm;
	}
	
	public Ubicacion getEpicentro() {
		return epicentro;
	}
	
	public boolean perteneceUbicacion(Ubicacion u) {
		return u.distanciaHastaEnKm(this.getEpicentro()) <= this.getRadioEnKm();
	}

	public boolean contieneMuestra(Muestra muestra) {
		return muestra.getUbicacion().distanciaHastaEnKm(this.epicentro) <= this.radioEnKm;
	}

	public boolean comparteUbicacionCon(ZonaDeCobertura z) { 
		// para ver si se solapa con otra zona, la llama la app cuando se registra en sistema
		double distanciaEntreCentros = this.epicentro.distanciaHastaEnKm(z.getEpicentro());
	    return distanciaEntreCentros <= (this.radioEnKm + z.getRadioEnKm());
	}
	



}
