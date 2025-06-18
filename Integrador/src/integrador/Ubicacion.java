package integrador;

import java.util.ArrayList;
import java.util.List;

public class Ubicacion {
	private double latitud;
	private double longitud;
	
	public Ubicacion(double latitud, double longitud, ArrayList<ZonaDeCobertura> zonaDeCobertura) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
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
	
	public double distanciaHastaEnKm(Ubicacion u) {
		final int R = 6371; // Radio de la Tierra en km
		double lat1 = Math.toRadians(this.latitud);
		double lon1 = Math.toRadians(this.longitud);
		double lat2 = Math.toRadians(u.latitud);
		double lon2 = Math.toRadians(u.longitud);
		
		double dLat = lat2 - lat1;
		double dLon = lon2 - lon1;
		
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + 
				   Math.cos(lat1) * Math.cos(lat2) *
	               Math.sin(dLon/2) * Math.sin(dLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return R * c;
		
	}
	
	public List<Ubicacion> ubicacionesAMenosDe(ArrayList<Ubicacion> ubis, double distanciaEnKm ){
		return ubis.stream()
				.filter(u -> this.distanciaHastaEnKm(u) < distanciaEnKm).toList();
	}
	
	
	
}
