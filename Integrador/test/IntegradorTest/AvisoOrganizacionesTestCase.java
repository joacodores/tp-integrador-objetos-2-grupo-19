package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.avisoOrganizaciones.ObserverMuestra;
import integrador.muestra.Muestra;
import integrador.zonaDeCobertura.ZonaDeCobertura;

class AvisoOrganizacionesTestCase {
	
	private ObserverMuestra obM;
	private Set<ZonaDeCobertura> zonasDeMuestra;
	private ZonaDeCobertura zona;
	private Muestra mV;
	
	@BeforeEach
	void setUp() throws Exception {
		zona = mock(ZonaDeCobertura.class);
		zonasDeMuestra = new HashSet<>();
		obM = new ObserverMuestra(zonasDeMuestra);		
		mV = mock(Muestra.class);
	}
	
	@Test
	void testGetZonasDeMuestra(){
		assertEquals(0, this.obM.getZonasDeMuestra().size());
	}
	
	@Test
	void testSetZonasDeMuestra() {
		zonasDeMuestra.add(zona);
		obM.setZonasDeMuestra(zonasDeMuestra);
	}
	
	/*public void setZonasDeMuestra(Set<ZonaDeCobertura> zonasDeMuestra) {
		this.zonasDeMuestra = zonasDeMuestra;
	}*/
}