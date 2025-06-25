package integrador.filtrosBusqueda;

import java.time.LocalDate;

public class FechaIgualA implements ComparadorFechas {
    @Override
    public boolean comparar(LocalDate unaFecha, LocalDate otraFecha) {
        return unaFecha.equals(otraFecha);
    }
}