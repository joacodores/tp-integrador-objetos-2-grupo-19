package integrador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Muestra { 

	// Variables
	private Usuario identificacion;
	private DescripcionOpinion especie;		//cambia segun la opinion
	private String foto;
	private LocalDate fechaDeEnvio;
	private ArrayList<Opinion> opinionesUsuarios;
	private Ubicacion ubicacion;
	private EstadoMuestra estadoMuestra;
	private ObserverMuestra observerMuestra;
	

	public Muestra (Ubicacion ubicacion, DescripcionOpinion especie, Usuario user, String foto, ObserverMuestra observerMuestra) {
		this.identificacion = user;
		this.ubicacion = ubicacion;
		this.especie = especie;
		this.foto = foto;
		this.fechaDeEnvio = LocalDate.now();	
		this.estadoMuestra = new MuestraAbierta();
		this.opinionesUsuarios = new ArrayList<Opinion>();
		this.observerMuestra = observerMuestra;
	}
	
	
	public ArrayList<Opinion> getOpiniones(){
		return this.opinionesUsuarios;
	}
	
	public String getFoto() {
		return this.foto;
	}
	
	public void addOpinion(Opinion opinion) {
		this.opinionesUsuarios.add(opinion);
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


	public LocalDate getFechaDeEnvio() {
		return fechaDeEnvio;
	}
	
	public void setEspecie(DescripcionOpinion especie) {
		this.especie = especie;
	}
	
	public DescripcionOpinion getEspecie() { //revisar
		//implementar en estadomuestra, solo devuelve cuando esta verificada(¿)
	}
	public ArrayList<Opinion> getOpinionesUsuarios() {
		return opinionesUsuarios;
	}


	public void setOpinionesUsuarios(ArrayList<Opinion> opinionesUsuarios) {
		this.opinionesUsuarios = opinionesUsuarios;
	}


	public ObserverMuestra getObserverMuestra() {
		return observerMuestra;
	}


	public void setObserverMuestra(ObserverMuestra observerMuestra) {
		this.observerMuestra = observerMuestra;
	}


	public void setFechaDeEnvio(LocalDate fechaDeEnvio) {
		this.fechaDeEnvio = fechaDeEnvio;
	}
	
	public DescripcionOpinion getResultadoActual() {	
		return getEstadoMuestra().getResultadoActual(this);
	}
	
	public Map<DescripcionOpinion, Long> getOpinionesYVotos(){
		return 
			(getOpiniones().stream() // Map de opiniones con cantidad de votos
					.collect(Collectors.groupingBy(
							opinion -> opinion.getDescripcionOpinion(),
							Collectors.counting())));
	}
	
	public DescripcionOpinion getOpinionMasVotada() {
		
		Map<DescripcionOpinion, Long>  conteoOpiniones = getOpinionesYVotos();
		DescripcionOpinion opinionMasVotada = null;
		long max = 0;
		for(Map.Entry<DescripcionOpinion, Long> entry : conteoOpiniones.entrySet()) {
			if(entry.getValue() > max) { //recorro el Map evaluando el valor(cantidad de votos)
				max = entry.getValue();
				opinionMasVotada = entry.getKey();
			}
		}
		return opinionMasVotada;			
	}
	
	public boolean hayEmpate() {
		Map<DescripcionOpinion, Long>  conteoOpiniones = getOpinionesYVotos();
		long max = 0;		
		for (Long votosDeOpinion : conteoOpiniones.values()) {
			if(votosDeOpinion > max) max = votosDeOpinion;
		}//max ahora almacena la cantidad de votos maxima
		
		int cantidadMax = 0; // cuenta cuantas opiniones tienen **max** votos, si es mayor a 1, hay empate
		for (Long votosDeOpinion : conteoOpiniones.values()) {
			if (votosDeOpinion == max) cantidadMax++;
		}
		return cantidadMax > 1;
	}
	
	public void recibirOpinionUsuarioBasico(Opinion o) throws Exception {
		getEstadoMuestra().recibirOpinionUsuarioBasico(o);
	}
	
	public void recibirOpinionUsuarioExperto(Opinion o) throws Exception {
		getEstadoMuestra().recibirOpinionUsuarioExperto(o);
	}
	
	public void borrarOpiniones() { // se llama cuando cambia a estado experto, para borrar las opiniones de los u.basicos
		this.opinionesUsuarios.removeAll(opinionesUsuarios);
	}
	
	public void verificarMuestra() {
		setEstadoMuestra(new MuestraVerificada());
		getObserverMuestra().muestraVerficada(this);;
	}

}
