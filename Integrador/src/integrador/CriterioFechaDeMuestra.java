package integrador;

import java.time.LocalDate;

public class CriterioFechaDeMuestra implements CriterioDeBusqueda{

	private LocalDate fecha;

    public CriterioFechaDeMuestra(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    @Override
    public boolean seCumpleQue(Muestra muestra) {
        return muestra.getFechaDeEnvio().isAfter(fecha);
    }
}
