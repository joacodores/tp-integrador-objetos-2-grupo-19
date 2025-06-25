package integrador.filtrosBusqueda;

import integrador.muestra.Muestra;
import integrador.opinion.DescripcionOpinion;

public class CriterioTipoDeInsecto implements CriterioDeBusqueda {
	private DescripcionOpinion tipo;
	
	public CriterioTipoDeInsecto(DescripcionOpinion tipo) {
		this.tipo = tipo;
	}
	
	public boolean seCumpleQue(Muestra muestra) {
		return muestra.getResultadoActual().equals(tipo);
	}
}
