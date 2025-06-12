package integrador;

import java.time.LocalDate;
import java.util.ArrayList;

public class Muestra {
	//no esta hecho el estado de la muestra ni sus accessors
	
	// Variables
	private Usuario usuario;
	private DescripcionOpinion descripcion;		//establecida por el usuario que sube la muestra
	private Foto foto;
	private LocalDate fechaDeEnvio;
	private ArrayList<Opinion> opinionesUsuarios = new ArrayList<Opinion>();
	private Ubicacion ubicacion;
	
	// Constructor
	public Muestra (Ubicacion ubicacion, DescripcionOpinion descripcion, Usuario user, Foto foto) {
		this.usuario = user;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.foto = foto;
		this.fechaDeEnvio = LocalDate.now();	//obtiene la fecha actual
	}
	
	// Metodos
	public ArrayList<Opinion> getOpiniones(){
		return this.opinionesUsuarios;
	}
	
	public void addOpinion(Opinion opinion) {
		this.opinionesUsuarios.add(opinion);
	}
	
	public DescripcionOpinion getEspecie() {	//retorna la especie inicial con la que se genero la muestra
		return this.descripcion;
	}

	
}
