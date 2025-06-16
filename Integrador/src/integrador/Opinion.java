package integrador;

import java.time.LocalDate;

public class Opinion {
	//no esta hecho el estado de la muestra ni sus accessors
	
	private LocalDate fechaOpinion;
	private DescripcionOpinion descripcionOpinion;
	private Muestra muestraEvaluada;
	
	public Opinion(LocalDate fechaOpinion, DescripcionOpinion descripcionOpinion, Muestra muestra) {
		super();
		this.fechaOpinion = fechaOpinion;
		this.descripcionOpinion = descripcionOpinion;
		this.muestraEvaluada = muestra;
	}
	
	public Muestra getMuestraEvaluada() {
		return muestraEvaluada;
	}

	public void setMuestraEvaluada(Muestra muestraEvaluada) {
		this.muestraEvaluada = muestraEvaluada;
	}

	public LocalDate getFechaOpinion() {
		return fechaOpinion;
	}
	public void setFechaOpinion(LocalDate fechaOpinion) {
		this.fechaOpinion = fechaOpinion;
	}
	public DescripcionOpinion getDescripcionOpinion() {
		return descripcionOpinion;
	}
	public void setDescripcionOpinion(DescripcionOpinion descripcionOpinion) {
		this.descripcionOpinion = descripcionOpinion;
	}
	
	
}
