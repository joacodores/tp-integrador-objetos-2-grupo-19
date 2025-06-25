package integrador.filtrosBusqueda;

import integrador.DescripcionOpinion;
import integrador.Muestra;

public class CriterioTipoDeInsecto implements CriterioDeBusqueda {
	private DescripcionOpinion tipo;
	
	public CriterioTipoDeInsecto(DescripcionOpinion tipo) {
		this.tipo = tipo;
	}
	
	public boolean seCumpleQue(Muestra muestra) {
		return muestra.getResultadoActual().equals(tipo);
	}
}
