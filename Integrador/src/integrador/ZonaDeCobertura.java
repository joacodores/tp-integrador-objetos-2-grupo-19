package integrador;

import java.util.ArrayList;

public class ZonaDeCobertura {
	
	// Variables
	private double radioEnKm;
	private String nombre;
	private Ubicacion epicentro;
	private ArrayList<ZonaDeCobertura> zonasSolapadas;
	private ArrayList<Muestra> muestrasEnZona; // de aca sacamos las ubicaciones de la zona
	private ArrayList<Organizacion> organizacionesInteresadas;
	
	// Constructor
	public ZonaDeCobertura(double radioEnKm, String nombre, Ubicacion epicentro) {
		super();
		this.radioEnKm = radioEnKm;
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.zonasSolapadas = new ArrayList<ZonaDeCobertura>();
		this.muestrasEnZona = new ArrayList<Muestra>();
		this.organizacionesInteresadas = new ArrayList<Organizacion>();
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<ZonaDeCobertura> getZonasSolapadass() { //este no
		return zonasSolapadas;
	}
	
	public void setZonasSolapadas(ArrayList<ZonaDeCobertura> zonasSolapadas) {
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
		avisarAOrganizacionesPorNuevaMuestra(m);
	}
	
	public ArrayList<Organizacion> getOrganizacionesInteresadas() {
		return organizacionesInteresadas;
	}
	
	public void setOrganizacionesInteresadas(ArrayList<Organizacion> organizacionesInteresadas) {
		this.organizacionesInteresadas = organizacionesInteresadas;
	}
	
	public void addOrganizacion(Organizacion o) {
		this.organizacionesInteresadas.add(o) ;
	}
	
	public double getRadioEnKm() {
		return radioEnKm;
	}
	
	public Ubicacion getEpicentro() {
		return epicentro;
	}
	
	public ArrayList<ZonaDeCobertura> getZonasSolapadas(ArrayList<ZonaDeCobertura> zSolapadas){
		//hacer
	}
	
	
	public void avisarAOrganizacionesPorNuevaMuestra(Muestra muestra) {
		organizacionesInteresadas.stream().forEach(o -> o.useFENuevaMuestra( this, muestra));
	}
	
	public void avisarAOrganizacionesPorVerificacion(Muestra muestra) {
		organizacionesInteresadas.stream().forEach(o -> o.useFEMuestraVerificada(this, muestra));
	}
}
