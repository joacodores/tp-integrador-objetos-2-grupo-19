package integrador;

import java.time.LocalDate;
import java.util.ArrayList;

import integrador.Muestra.DescripcionOpinion;

public class Muestra { 

	// Variables
	private Usuario identificacion;
	private DescripcionOpinion especie;		//cambia segun la opinion
	private Foto foto; //consultar
	private LocalDate fechaDeEnvio;
	private ArrayList<Opinion> opinionesUsuarios = new ArrayList<Opinion>();
	private Ubicacion ubicacion;
	private EstadoMuestra estadoMuestra;
	
	// Constructor
	public Muestra (Ubicacion ubicacion, DescripcionOpinion especie, Usuario user, Foto foto) {
		this.identificacion = user;
		this.ubicacion = ubicacion;
		this.especie = especie;
		this.foto = foto;
		this.fechaDeEnvio = LocalDate.now();	
		this.estadoMuestra = new MuestraAbierta();
	}
	
	// Metodos
	public ArrayList<Opinion> getOpiniones(){
		return this.opinionesUsuarios;
	}
	
	public void addOpinion(Opinion opinion) {
		this.opinionesUsuarios.add(opinion);
	}
	
	public DescripcionOpinion getResultadoActual() {	
		return this.especie;
	}

	public Usuario getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Usuario identificacion) {
		this.identificacion = identificacion;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public EstadoMuestra getEstadoMuestra() {
		return estadoMuestra;
	}

	public void setEstadoMuestra(EstadoMuestra estadoMuestra) {
		this.estadoMuestra = estadoMuestra;
	}

	public void setEspecie(DescripcionOpinion especie) {
		this.especie = especie;
	}

	public LocalDate getFechaDeEnvio() {
		return fechaDeEnvio;
	}
	
	public void verificarMuestra() {
		setEstadoMuestra(new MuestraVerificada());
		ArrayList<ZonaDeCobertura> zonasDeCoberturaDeMuestra = getUbicacion().getZonasDeCobertura();
		zonasDeCoberturaDeMuestra.stream().forEach(zona -> zona.avisarAOrganizacionesPorVerificacion(this));
		//observer, revisar estructura
	}

	
	

	
}
