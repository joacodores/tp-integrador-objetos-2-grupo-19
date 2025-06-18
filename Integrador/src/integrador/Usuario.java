package integrador;

import java.time.LocalDate;	//(yyyy,mm,dd)  | "yyyy-mm-dd"
import java.time.temporal.ChronoUnit; //para marcar el periodo entre fecha A y B
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Usuario {
	private String nombreUsuario;
	private ArrayList<Muestra> muestrasReportadas;
	private ArrayList<Opinion> opinionesEnviadas;
	private NivelConocimiento estadoUsuario;
	
	public Usuario(String nombreUsuario,  Boolean esEspecialista) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.muestrasReportadas = new ArrayList<Muestra>();
		this.opinionesEnviadas = new ArrayList<Opinion>();
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
	public void addMuestraReportada(Muestra m) {
		this.muestrasReportadas.add(m);
	}

	public ArrayList<Opinion> getOpinionesEnviadas() {
		return opinionesEnviadas;
	}

	public void setOpinionesEnviadas(ArrayList<Opinion> opinionesEnviadas) {
		this.opinionesEnviadas = opinionesEnviadas;
	}

	public NivelConocimiento getEstadoUsuario() {
		verificarCambioDeEstado();
		return estadoUsuario;
	}
	
	public void verificarCambioDeEstado() {
		this.estadoUsuario.verificarCambioDeEstado(this);
	}
	
	public void setEstadoUsuario(NivelConocimiento estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	
	public void addOpinion(Opinion o) {
		this.opinionesEnviadas.add(o);
	}
	
	public void darOpinion(Opinion o) throws Exception{
		exceptionOpinarSobreMuestraPropia(o.getMuestraEvaluada());
		getEstadoUsuario().darOpinion(this, o);
		addOpinion(o); //tal vez el estado de la muestra no le permite hacer la opinion al user
	}
	
	public void enviarMuestra(AppWeb app, Usuario u, Ubicacion ubi, DescripcionOpinion especie, String foto) {
		getEstadoUsuario().enviarMuestra(app, u,  ubi,  especie,  foto);
		
	}
	
	public ArrayList<Opinion> opinionesDeLosUltimos30Dias(){
		LocalDate fechaActual = LocalDate.now();
		
		ArrayList<Opinion> opiniones30Dias = opinionesEnviadas.stream()
				.filter(o -> ChronoUnit.DAYS.between(o.getFechaOpinion(), fechaActual) <= 30)
				.collect(Collectors.toCollection(ArrayList::new));
		
		return opiniones30Dias;
	}
	
	public ArrayList<Muestra> muestrasDeLosUltimos30Dias(){
		LocalDate fechaActual = LocalDate.now();
		
		ArrayList<Muestra> muestras30Dias = muestrasReportadas.stream()
				.filter(m -> ChronoUnit.DAYS.between(m.getFechaDeEnvio(), fechaActual) <= 30)
				.collect(Collectors.toCollection(ArrayList::new));
		
		return muestras30Dias;
	}
	 private void exceptionOpinarSobreMuestraPropia(Muestra m) {
		  try {
			  if (m.getIdentificacion().equals(this)) {
		            throw new Exception("No podés opinar sobre tu propia muestra");
		        }
		    } catch (Exception e) {
		        System.out.println("No podés opinar sobre tu propia muestra");
		    }
	 }
}
