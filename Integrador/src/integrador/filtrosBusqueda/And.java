package integrador.filtrosBusqueda;

import integrador.OperadorLogico;

public class And implements OperadorLogico{
	@Override
	public boolean combinarCriterios(boolean a, boolean b) {
		return a && b;
	}
}
