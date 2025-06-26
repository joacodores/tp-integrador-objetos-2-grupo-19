package integrador.zonaDeCobertura;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import integrador.muestra.Muestra;
import integrador.ubicacion.Ubicacion;

public class ZonaDeCobertura {
	
	// Variables
	private double radioEnKm;
	private String nombre;
	private Ubicacion epicentro;
	private Set<ZonaDeCobertura> zonasSolapadas;
	private ArrayList<Muestra> muestrasEnZona; 
	private ArrayList<IObserverZona> observers;
	
	// Constructor

	public ZonaDeCobertura(double radioEnKm, String nombre, Ubicacion epicentro) {
		super();
		this.radioEnKm = radioEnKm;
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.zonasSolapadas = new HashSet<ZonaDeCobertura>();
		this.muestrasEnZona = new ArrayList<Muestra>();
		this.observers = new ArrayList<IObserverZona>(); //organizaciones interesadas
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<IObserverZona> getObservers() {
		return observers;
	}
	public void addObserver(IObserverZona o) {
		observers.add(o);
	}
	public void setObservers(ArrayList<IObserverZona> observers) {
		this.observers = observers;
	}

	public Set<ZonaDeCobertura> getZonasSolapadas() {
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


	public void notificarNuevaMuestra(Muestra m) {
		getObservers().stream().forEach(o -> o.nuevaMuestraRegistrada(this, m));
	}
	public void notificarMuestraVerificada(Muestra m) {
		getObservers().stream().forEach(o -> o.nuevaVerificacionMuestra(this, m));
	}
	
	public List<Muestra> getMuestrasVerificadasEnZona(){
		List<Muestra> muestras = getMuestrasEnZona().stream().filter(m -> m.estaVerificada()).collect(Collectors.toList());
		return muestras; 
	}



}
