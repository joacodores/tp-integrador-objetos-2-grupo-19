package integrador;

import java.util.ArrayList;

public class BuscadorDeMuestra {
	private ArrayList<Muestra> muestrasAFiltrar;
	private CriterioDeBusqueda criterioDeBusqueda;
	
	public BuscadorDeMuestra(ArrayList<Muestra> muestrasAFiltrar, CriterioDeBusqueda criterioDeBusqueda) {
		super();
		this.muestrasAFiltrar = muestrasAFiltrar;
		this.criterioDeBusqueda = criterioDeBusqueda;
	}
	
	public void addMuestra(Muestra m) {
		this.muestrasAFiltrar.add(m);
	}
	
	public void removeMuestra(Muestra m) {
		this.muestrasAFiltrar.remove(m);
	}

	public ArrayList<Muestra> getMuestrasAFiltrar() {
		return muestrasAFiltrar;
	}

	public void setMuestrasAFiltrar(ArrayList<Muestra> muestrasAFiltrar) {
		this.muestrasAFiltrar = muestrasAFiltrar;
	}

	public CriterioDeBusqueda getCriterioDeBusqueda() {
		return criterioDeBusqueda;
	}

	public void setCriterioDeBusqueda(CriterioDeBusqueda criterioDeBusqueda) {
		this.criterioDeBusqueda = criterioDeBusqueda;
	}
	
	
	
}
