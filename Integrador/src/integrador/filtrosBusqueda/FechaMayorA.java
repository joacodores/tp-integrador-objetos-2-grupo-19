package integrador.filtrosBusqueda;

import java.time.LocalDate;

public class FechaMayorA implements ComparadorFechas {
    @Override
    public boolean comparar(LocalDate unaFecha, LocalDate otraFecha) {
        return unaFecha.isAfter(otraFecha);
    }
}
