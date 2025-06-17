package integrador;

import java.time.LocalDate;	//(yyyy,mm,dd)  | "yyyy-mm-dd"
import java.time.Period;	//para marcar el periodo entre fecha A y B
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Usuario {
	private String nombreUsuario;
	private ArrayList<Muestra> muestrasReportadas;
	private ArrayList<Opinion> opinionesEnviadas;
	private NivelConocimiento estadoUsuario;
	private Boolean esEspecialista;
	
	public Usuario(String nombreUsuario,  Boolean esEspecialista) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.muestrasReportadas = new ArrayList<Muestra>();
		this.opinionesEnviadas = new ArrayList<Opinion>();
		this.esEspecialista = esEspecialista;
		if(esEspecialista) {
			this.estadoUsuario = new UsuarioEspecialista();
		}else {
			this.estadoUsuario = new UsuarioBasico();
		}
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public ArrayList<Muestra> getMuestrasReportadas() {
		return muestrasReportadas;
	}

	public void setMuestrasReportadas(ArrayList<Muestra> muestrasReportadas) {
		this.muestrasReportadas = muestrasReportadas;
	}

	public ArrayList<Opinion> getOpinionesEnviadas() {
		return opinionesEnviadas;
	}

	public void setOpinionesEnviadas(ArrayList<Opinion> opinionesEnviadas) {
		this.opinionesEnviadas = opinionesEnviadas;
	}

	public NivelConocimiento getEstadoUsuario() {
		this.verificarCambioDeEstado();
		return estadoUsuario;
	}
	
	public void verificarCambioDeEstado() {
		this.estadoUsuario.verificarCambioDeEstado(this);
	}
	
	public void setEstadoUsuario(NivelConocimiento estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public Boolean getEsEspecialista() {
		return esEspecialista;
	}
	
	public void addOpinion(Opinion o) {
		this.opinionesEnviadas.add(o);
	}
	
	public void darOpinion(Opinion o) throws Exception{
		this.estadoUsuario.darOpinion(this, o);
		addOpinion(o); //invierto porque talvez el estado de la muestra no le permite hacer la opinion al user
	}
	
	public void enviarMuestra(Muestra m) {
		this.getEstadoUsuario().enviarMuestra(this, m);
		this.muestrasReportadas.add(m);
	}
	
	public ArrayList<Opinion> opinionesDeLosUltimos30Dias(){
		LocalDate fechaActual = LocalDate.now();
		
		ArrayList<Opinion> opiniones30Dias = opinionesEnviadas.stream()
				.filter(o -> Period.between(fechaActual, o.getFechaOpinion()).getDays() <= 30)
				.collect(Collectors.toCollection(ArrayList::new));
		
		return opiniones30Dias;
	}
	
	public ArrayList<Muestra> muestrasDeLosUltimos30Dias(){
		LocalDate fechaActual = LocalDate.now();
		
		ArrayList<Muestra> muestras30Dias = muestrasReportadas.stream()
				.filter(m -> Period.between(fechaActual, m.getFechaDeEnvio()).getDays() <= 30)
				.collect(Collectors.toCollection(ArrayList::new));
		
		return muestras30Dias;
	}
	
}
