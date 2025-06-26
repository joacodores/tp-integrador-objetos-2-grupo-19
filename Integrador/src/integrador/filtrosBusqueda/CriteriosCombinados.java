package integrador.filtrosBusqueda;

import integrador.muestra.Muestra;

public class CriteriosCombinados implements CriterioDeBusqueda {
	
    private CriterioDeBusqueda criterioIzq;
    private CriterioDeBusqueda criterioDer;
    private OperadorLogico operador;

    public CriteriosCombinados(CriterioDeBusqueda izq, CriterioDeBusqueda der, OperadorLogico op) {
        this.criterioIzq = izq;
        this.criterioDer = der;
        this.operador = op;
    }

    @Override
    public boolean seCumpleQue(Muestra muestra) {
        /* De esta manera se pueden anidar ambos op logicos, mas facil que tener una lista de criterios.
         * Cada nodo de combinación (CriterioCompuesto) tiene su propio operador (And o Or) y dos criterios hijos,
         * que pueden ser: criterios simples (hojas) o otros CriterioCompuesto (se anidan de forma natural)
         */
        boolean cIzq = criterioIzq.seCumpleQue(muestra);
        boolean cDer = criterioDer.seCumpleQue(muestra);
        return operador.combinarCriterios(cIzq, cDer);
    }
}
