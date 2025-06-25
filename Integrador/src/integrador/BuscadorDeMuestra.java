package integrador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscadorDeMuestra { // se setea y se usa desde la appWeb
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
	
    public List<Muestra> filtrar() { 
        return muestrasAFiltrar.stream()
                .filter(m -> criterioDeBusqueda.seCumpleQue(m))
                .collect(Collectors.toList());
    }
	
	
	
}
