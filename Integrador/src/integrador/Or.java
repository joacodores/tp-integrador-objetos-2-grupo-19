package integrador;

public class Or implements OperadorLogico {
	@Override
	public boolean combinarCriterios(boolean a, boolean b) {
		return a || b;
	}
}
