package integrador.filtrosBusqueda;

import java.time.LocalDate;

public interface ComparadorFechas {
	boolean comparar(LocalDate unaFecha, LocalDate otraFecha);
}
