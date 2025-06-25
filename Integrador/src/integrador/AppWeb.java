package integrador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppWeb implements ObserverMuestra{
	
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
	
	public void registrarOrganizacion(Organizacion o) {
		this.organizaciones.add(o);
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	public void addUsuario(Usuario u) {
		this.usuarios.add(u);
	}
	
	public void addMuestra(Muestra m) {
		this.muestrasRecibidas.add(m);
	}
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
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
	public Set<Muestra> getMuestrasRecibidas() {
		return muestrasRecibidas;
	}
	public BuscadorDeMuestra getFiltroDeMuestras() {
		return filtroDeMuestras;
	}
	
	public void setFiltroDeMuestras(BuscadorDeMuestra filtroDeMuestras) {
		this.filtroDeMuestras = filtroDeMuestras;
	}
	
	public List<Muestra> filtrarMuestrasSegunCriterio(ArrayList<Muestra> muestrasAFiltrar, CriterioDeBusqueda c){
		getFiltroDeMuestras().setCriterioDeBusqueda(c);
		getFiltroDeMuestras().setMuestrasAFiltrar(muestrasAFiltrar);
		return getFiltroDeMuestras().filtrar();
	}
	
	public void addZonaDeCobertura(ZonaDeCobertura z) {
		//cuando la app registra una nueva zona de cobertura se encarga de ver cuales al solapan
		//agregando a estas zonas a z como zonaSolapada y visceversa.
		Set<ZonaDeCobertura> zonasQueLaSolapan = getZonasDeCobertura().stream()
				.filter(zona -> zona.comparteUbicacionCon(z)).collect(Collectors.toSet());
		zonasQueLaSolapan.forEach(zona -> zona.addZonaSolapada(z));
		z.setZonasSolapadas(zonasQueLaSolapan);
		zonasDeCobertura.add(z);
	}
	

	
	public Set<ZonaDeCobertura> getZonasDeCoberturaDeUbicacion(Set<ZonaDeCobertura> zonas, Ubicacion u) {
		return zonas.stream().filter(z -> z.perteneceUbicacion(u)).collect(Collectors.toSet());
	}
	
	/*public void recibirMuestra(Muestra m) {
		addMuestra(m);
		Set<ZonaDeCobertura> zonasDeMuestra = getZonasDeCoberturaDeUbicacion(getZonasDeCobertura(), m.getUbicacion());
		zonasDeMuestra.stream().forEach(z -> z.addMuestraEnZona(m));
		for(ZonaDeCobertura z : zonasDeMuestra) {
			avisarAOrganizacionesInteresadasPorNuevaMuestra(z, m);
		}
		
		
	}*/
	
	public void registrarMuestra(Muestra m){

			Opinion opinionInicial = new Opinion(m.getResultadoActual(), m);

			if (m.getIdentificacion().getEstadoUsuario().esExperto()) {
					m.getEstadoMuestra().recibirOpinionUsuarioExperto(opinionInicial);;
			} else {
				m.getEstadoMuestra().recibirOpinionUsuarioBasico(opinionInicial);
			}

			m.getIdentificacion().addMuestraReportada(m);
			m.getIdentificacion().addOpinion(opinionInicial);
			
			addMuestra(m);
			
			Set<ZonaDeCobertura> zonasDeMuestra = getZonasDeCoberturaDeUbicacion(getZonasDeCobertura(), m.getUbicacion());
			zonasDeMuestra.stream().forEach(z -> z.addMuestraEnZona(m));
			for(ZonaDeCobertura z : zonasDeMuestra) {
				avisarAOrganizacionesInteresadasPorNuevaMuestra(z, m);
			}
			
			m.getIdentificacion().getEstadoUsuario().verificarCambioDeEstado(m.getIdentificacion());
	}
	
	private void avisarAOrganizacionesInteresadasPorNuevaMuestra(ZonaDeCobertura z, Muestra m) {
		for (Organizacion o : getOrganizaciones()) {
			if (o.estaInteresadaEnZona(z)) {
				o.useFENuevaMuestra(z, m);
			}
		}
	}
	
	@Override
	public void nuevaMuestraVerificada(Muestra m) {
		Set<ZonaDeCobertura> zonasDeMuestra = getZonasDeCoberturaDeUbicacion(getZonasDeCobertura(), m.getUbicacion());
		zonasDeMuestra.stream().forEach(z -> z.addMuestraEnZona(m));
		for(ZonaDeCobertura z : zonasDeMuestra) {
			avisarAOrganizacionesInteresadasPorMuestraVerificada(z, m);
		}
		
	}
	private void avisarAOrganizacionesInteresadasPorMuestraVerificada(ZonaDeCobertura z, Muestra m) {
		for (Organizacion o : getOrganizaciones()) {
			if (o.estaInteresadaEnZona(z)) {
				o.useFEMuestraVerificada(z, m);
			}
		}
	}



	
}
