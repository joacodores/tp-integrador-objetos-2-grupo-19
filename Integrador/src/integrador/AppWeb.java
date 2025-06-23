package integrador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppWeb {
	
	private Set<Muestra> muestrasRecibidas;
	private Set<ZonaDeCobertura> zonasDeCobertura;
	private BuscadorDeMuestra filtroDeMuestras;
	private ArrayList<Organizacion> organizaciones;
	private Set<Usuario> usuarios; // capaz no va
	
	
	public AppWeb(Set<Muestra> muestrasRecibidas, Set<ZonaDeCobertura> zonasDeCobertura,
			BuscadorDeMuestra filtroDeMuestras, Set<Usuario> usuarios, 
			ArrayList<Organizacion> organizaciones) {
		this.muestrasRecibidas = muestrasRecibidas;
		this.zonasDeCobertura = zonasDeCobertura;
		this.filtroDeMuestras = filtroDeMuestras;
		this.organizaciones = organizaciones;
		this.usuarios = usuarios;
		
	}

	public ArrayList<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(ArrayList<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Set<Muestra> getMuestrasRecibidas() {
		return muestrasRecibidas;
	}
	
	public void setMuestrasRecibidas(Set<Muestra> muestrasRecibidas) {
		this.muestrasRecibidas = muestrasRecibidas;
	}
	
	public Set<ZonaDeCobertura> getZonasDeCobertura() {
		return zonasDeCobertura;
	}
	
	public void setZonasDeCobertura(Set<ZonaDeCobertura> zonasDeCobertura) {
		this.zonasDeCobertura = zonasDeCobertura;
	}
	
	public BuscadorDeMuestra getFiltroDeMuestras() {
		return filtroDeMuestras;
	}
	
	public void setFiltroDeMuestras(BuscadorDeMuestra filtroDeMuestras) {
		this.filtroDeMuestras = filtroDeMuestras;
	}
	
	public void addUsuario(Usuario u) {
		this.usuarios.add(u);
	}
	
	public void addMuestra(Muestra m) {
		this.muestrasRecibidas.add(m);
	}
	
	public Set<ZonaDeCobertura> getZonasDeCoberturaDeUbicacion(Set<ZonaDeCobertura> zonas, Ubicacion u) {
		return zonas.stream().filter(z -> z.perteneceUbicacion(u)).collect(Collectors.toSet());
	}
	
	public void recibirMuestra(Muestra m) {
		addMuestra(m);
		Set<ZonaDeCobertura> zonasDeMuestra = getZonasDeCoberturaDeUbicacion(getZonasDeCobertura(), m.getUbicacion());
		zonasDeMuestra.stream().forEach(z -> z.addMuestraEnZona(m));
		for(ZonaDeCobertura z : zonasDeMuestra) {
			avisarAOrganizacionesInteresadasPorNuevaMuestra(z, m);
		}
	}
	
	public void avisarAOrganizacionesInteresadasPorNuevaMuestra(ZonaDeCobertura z, Muestra m) {
		for (Organizacion o : getOrganizaciones()) {
			if (o.estaInteresadaEnZona(z)) {
				o.useFENuevaMuestra(z, m);
			}
		}
	}
	
	public void nuevaMuestraVerificada(Muestra m) {
		Set<ZonaDeCobertura> zonasDeMuestra = getZonasDeCoberturaDeUbicacion(getZonasDeCobertura(), m.getUbicacion());
		zonasDeMuestra.stream().forEach(z -> z.addMuestraEnZona(m));
		for(ZonaDeCobertura z : zonasDeMuestra) {
			avisarAOrganizacionesInteresadasPorMuestraVerificada(z, m);
		}
		
	}

	public void avisarAOrganizacionesInteresadasPorMuestraVerificada(ZonaDeCobertura z, Muestra m) {
		for (Organizacion o : getOrganizaciones()) {
			if (o.estaInteresadaEnZona(z)) {
				o.useFEMuestraVerificada(z, m);
			}
		}
		
	}
	//no la use pero la dejo por si la necesitamos luego
	/*public List<Organizacion> getOrganizacionesInteresadasEnZonas(Set<ZonaDeCobertura> zonas){
		List<Organizacion> organizacionesInteresadas = new ArrayList<Organizacion>();
		for(ZonaDeCobertura z : zonas) {
			organizacionesInteresadas.addAll(getOrganizaciones().stream().filter(o -> o.estaInteresadaEnZona(z)).collect(Collectors.toList()));
		}
		return organizacionesInteresadas;
	}*/
	





	
}
