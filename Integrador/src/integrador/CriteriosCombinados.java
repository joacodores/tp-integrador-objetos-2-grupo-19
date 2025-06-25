package integrador;

import java.util.ArrayList;

public class CriteriosCombinados implements CriterioDeBusqueda {
	
    private ArrayList<CriterioDeBusqueda> criterios;
    private OperadorLogico operador;

    public CriteriosCombinados(ArrayList<CriterioDeBusqueda> criterios, OperadorLogico operador) {
        this.criterios = criterios;
        this.operador = operador;
    }

    @Override
    public boolean seCumpleQue(Muestra muestra) {
        return criterios.stream()
            .map(c -> c.seCumpleQue(muestra))
            .reduce((a, b) -> operador.combinarCriterios(a, b))
            .orElse(true); // true si no hay criterios
    }
}
