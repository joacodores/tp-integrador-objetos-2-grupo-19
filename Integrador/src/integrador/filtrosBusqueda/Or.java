package integrador.filtrosBusqueda;

import integrador.OperadorLogico;

public class Or implements OperadorLogico {
	@Override
	public boolean combinarCriterios(boolean a, boolean b) {
		return a || b;
	}
}
