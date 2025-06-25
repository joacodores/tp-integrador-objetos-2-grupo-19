package integrador.filtrosBusqueda;

import java.time.LocalDate;

public class FechaMenorA implements ComparadorFechas {
    @Override
    public boolean comparar(LocalDate unaFecha, LocalDate otraFecha) {
        return unaFecha.isBefore(otraFecha);
    }
}
