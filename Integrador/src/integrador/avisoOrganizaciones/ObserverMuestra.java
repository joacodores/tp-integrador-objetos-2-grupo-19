
package integrador.avisoOrganizaciones;

import java.util.Set;

import integrador.muestra.Muestra;
import integrador.zonaDeCobertura.ZonaDeCobertura;

public class ObserverMuestra implements IObserverMuestra{
	private Set<ZonaDeCobertura> zonasDeMuestra;
	
	public ObserverMuestra(Set<ZonaDeCobertura> zonasDeMuestra) {
		this.zonasDeMuestra = zonasDeMuestra;
	}
	
	public Set<ZonaDeCobertura> getZonasDeMuestra() {
		return zonasDeMuestra;
	}

	public void setZonasDeMuestra(Set<ZonaDeCobertura> zonasDeMuestra) {
		this.zonasDeMuestra = zonasDeMuestra;
	}

	@Override
	public void nuevaMuestraVerificada(Muestra m) {
		getZonasDeMuestra().stream().forEach(z -> z.addMuestraEnZona(m));
		for(ZonaDeCobertura z : getZonasDeMuestra()) {
			z.notificarMuestraVerificada(m);
		}
	}
	
	@Override
	public void nuevaMuestraRegistrada(Muestra m) {
		getZonasDeMuestra().stream().forEach(z -> z.addMuestraEnZona(m));
		for(ZonaDeCobertura z : getZonasDeMuestra()) {
			z.notificarNuevaMuestra(m);
		}
	}
}