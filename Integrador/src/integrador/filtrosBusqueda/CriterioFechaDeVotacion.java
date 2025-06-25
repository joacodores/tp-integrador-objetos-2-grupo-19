package integrador.filtrosBusqueda;

import java.time.LocalDate;

import integrador.muestra.Muestra;

public class CriterioFechaDeVotacion implements CriterioDeBusqueda{

	private LocalDate fecha;
	private ComparadorFechas comparadorFechas;

    public CriterioFechaDeVotacion(LocalDate fecha, ComparadorFechas comparadorFechas) {
        this.fecha = fecha;
        this.comparadorFechas = comparadorFechas;
    }
    
    @Override
    public boolean seCumpleQue(Muestra muestra) {
    	LocalDate fechaVotacion = muestra.getFechaUltimaVotacion();
        return getComparadorFechas().comparar(fechaVotacion, getFecha());
    }

	private LocalDate getFecha() {
		return fecha;
	}


	public ComparadorFechas getComparadorFechas() {
		return comparadorFechas;
	}

	public void setComparadorFechas(ComparadorFechas comparadorFechas) {
		this.comparadorFechas = comparadorFechas;
	}
}
