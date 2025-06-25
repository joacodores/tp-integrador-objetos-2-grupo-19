package integrador.opinion;

import java.time.LocalDate;

import integrador.muestra.Muestra;

public class Opinion {
	
	private LocalDate fechaOpinion;
	private DescripcionOpinion descripcionOpinion;
	private Muestra muestraEvaluada;
	
	public Opinion( DescripcionOpinion descripcionOpinion, Muestra muestra) {
		super();
		this.fechaOpinion = LocalDate.now();
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
