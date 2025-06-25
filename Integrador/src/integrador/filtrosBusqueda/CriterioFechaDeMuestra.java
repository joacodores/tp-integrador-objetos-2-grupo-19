package integrador.filtrosBusqueda;

import java.time.LocalDate;

import integrador.Muestra;

public class CriterioFechaDeMuestra implements CriterioDeBusqueda{

	private LocalDate fecha;
	private ComparadorFechas comparadorFechas;

    public CriterioFechaDeMuestra(LocalDate fecha, ComparadorFechas comparadorFechas) {
        this.fecha = fecha;
        this.comparadorFechas = comparadorFechas;
    }
    
    @Override
    public boolean seCumpleQue(Muestra muestra) {
    	LocalDate fechaCreacion = muestra.getFechaDeEnvio();
        return getComparadorFechas().comparar(fechaCreacion, getFecha());
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
