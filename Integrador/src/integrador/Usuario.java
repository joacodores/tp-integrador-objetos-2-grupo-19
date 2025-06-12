package integrador;

import java.util.ArrayList;

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
		return estadoUsuario;
	}

	public void setEstadoUsuario(NivelConocimiento estadoUsuario) {
		this.estadoUsuario = new UsuarioBasico();
	}

	public Boolean getEsEspecialista() {
		return esEspecialista;
	}
	
	public void enviarMuestra(Muestra m) {
		
	}
	
	
	
	
	
	
}
