package integrador.muestra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import integrador.app.IObserverMuestra;
import integrador.app.ObserverMuestra;
import integrador.opinion.DescripcionOpinion;
import integrador.opinion.Opinion;
import integrador.ubicacion.Ubicacion;
import integrador.usuario.Usuario;
import integrador.zonaDeCobertura.ZonaDeCobertura;

public class Muestra { 

	// Variables
	private Usuario identificacion;
	private String foto;
	private LocalDate fechaDeEnvio;
	private ArrayList<Opinion> opinionesUsuarios;
	private Ubicacion ubicacion;
	private EstadoMuestra estadoMuestra;
	private List<IObserverMuestra> observers;


	public Muestra (Ubicacion ubicacion, DescripcionOpinion especie, Usuario user, String foto) {
		this.identificacion = user;
		this.ubicacion = ubicacion;
		this.foto = foto;
		this.fechaDeEnvio = LocalDate.now();	
		this.estadoMuestra = new MuestraAbierta();
		this.opinionesUsuarios = new ArrayList<Opinion>();
		this.observers = new ArrayList<IObserverMuestra>(); 
		//El Observer por ahora es uno solo con las zonasDeCobertura interesadas en notificar muestra, 
		//pero por patron de diseño dejamos la lista de observers para que ene l futuro se puedan agregar mas
		
		// Cuando se inicializa la muestra, la especie indicada funje como primera opinion
		// Importa que tipo de usuario crea la muestra
		Opinion o = new Opinion(especie, this); 
		if (this.identificacion.getEstadoUsuario().esExperto()) {
			getEstadoMuestra().recibirOpinionUsuarioExperto(o);;
		} else {
			getEstadoMuestra().recibirOpinionUsuarioBasico(o);
		}
		
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
	
	public LocalDate getFechaUltimaVotacion() {
		return getOpinionesUsuarios().stream()
				 .map(o -> o.getFechaOpinion())				 
			     .max(LocalDate::compareTo)
			     .orElse(null);
	}
	public ArrayList<Opinion> getOpinionesUsuarios() {
		return opinionesUsuarios;
	}


	public void setOpinionesUsuarios(ArrayList<Opinion> opinionesUsuarios) {
		this.opinionesUsuarios = opinionesUsuarios;
	}

	public void agregarObservador(IObserverMuestra observer) {
        if (!observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    public void removerObservador(IObserverMuestra observer) {
        this.observers.remove(observer);
    }

    public void setFechaDeEnvio(LocalDate fechaDeEnvio) {
		this.fechaDeEnvio = fechaDeEnvio;
	}
	
	public DescripcionOpinion getResultadoActual() {	//depende si el resultado esta definido o no
		return getEstadoMuestra().getResultadoActual(this);
	}
	
	public Map<DescripcionOpinion, Long> getOpinionesYVotos(){
		return 
			(getOpiniones().stream() // Map de opiniones con cantidad de votos
					.collect(Collectors.groupingBy(
							opinion -> opinion.getDescripcionOpinion(),
							Collectors.counting())));
	}
	
	public DescripcionOpinion getOpinionMasVotada() { //resultado actual
		
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
	
	public void recibirOpinionUsuarioBasico(Opinion o) {
		getEstadoMuestra().recibirOpinionUsuarioBasico(o);
	}
	
	public void recibirOpinionUsuarioExperto(Opinion o) { 
		getEstadoMuestra().recibirOpinionUsuarioExperto(o);
	}
	
	public void borrarOpiniones() { // se llama cuando cambia a estado soloExpertos, para borrar las opiniones de los u.basicos
		this.opinionesUsuarios.removeAll(opinionesUsuarios);
	}
	
	public void seVerificaLaMuestra() {
		setEstadoMuestra(new MuestraVerificada());
		notificarVerificacionMuestra();
	}
	
	public boolean estaVerificada() {
        return (this.getEstadoMuestra() instanceof MuestraVerificada);
    }


	public void notificarNuevaMuestra() {
		getObservers().stream().forEach(o -> o.nuevaMuestraRegistrada(this));
		
	}
	public void notificarVerificacionMuestra() {
		getObservers().stream().forEach(o -> o.nuevaMuestraVerificada(this));
		
	}

	public List<IObserverMuestra> getObservers() {
		return observers;
	}

	public void setObservers(List<IObserverMuestra> observers) {
		this.observers = observers;
	}
	
	public void addObserver(ObserverMuestra observerMuestra) {
		this.observers.add(observerMuestra);
		
	}

}
