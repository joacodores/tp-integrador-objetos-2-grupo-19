package integrador.filtrosBusqueda;

import integrador.muestra.Muestra;

public class CriterioVerificacion implements CriterioDeBusqueda {
    private boolean verificada;

    public CriterioVerificacion(boolean verificada) {
        this.verificada = verificada;
    }

    public boolean seCumpleQue(Muestra muestra) {
        return muestra.estaVerificada() == verificada;
    }
}
