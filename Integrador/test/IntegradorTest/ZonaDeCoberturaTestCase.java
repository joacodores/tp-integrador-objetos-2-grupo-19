package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.muestra.Muestra;
import integrador.ubicacion.Ubicacion;
import integrador.zonaDeCobertura.IObserverZona;
import integrador.zonaDeCobertura.ZonaDeCobertura;

class ZonaDeCoberturaTestCase {
	
	ZonaDeCobertura zona1;
	Ubicacion epicentro1;
	Muestra muestra;
	
	@BeforeEach
	void setUp() throws Exception {
		epicentro1 = mock(Ubicacion.class);
		muestra = mock(Muestra.class);
		
		zona1 = new ZonaDeCobertura(10d,"Zona 1", epicentro1);
	}

	@Test
	void testUnaZonaConoceSuNombreYPuedeCambiarlo() {
		assertEquals("Zona 1", this.zona1.getNombre());
		this.zona1.setNombre("nuevo nombre");
		assertEquals("nuevo nombre", this.zona1.getNombre());
	}
	
	@Test
	void testUnaMuestraPuedeAgregarseAUnaZona() {
		this.zona1.addMuestraEnZona(muestra);
		assertEquals(Arrays.asList(muestra), this.zona1.getMuestrasEnZona());
	}
	
	@Test
	void testUnaZonaConoceSuEpicentro() {
		assertEquals(epicentro1, zona1.getEpicentro());
	}
	
	@Test
	void testUnaZonaSabeSiUnaUbicacionPerteneceAElla() {
		//establezco una ubi que esta a 5km
		Ubicacion ubiPertenece = mock(Ubicacion.class);
		when(ubiPertenece.distanciaHastaEnKm(epicentro1)).thenReturn(5d);
		
		//establezco una ubi que esta a 30km
		Ubicacion ubiNoPertenece = mock(Ubicacion.class);
		when(ubiNoPertenece.distanciaHastaEnKm(epicentro1)).thenReturn(30d);
		
		assertTrue(zona1.perteneceUbicacion(ubiPertenece));
		assertFalse(zona1.perteneceUbicacion(ubiNoPertenece));
	}
	
	@Test
	void testUnaZonaPuedeAgregarOrganizacionesInteresadasEnElla() {
		IObserverZona observerDeZona = mock(IObserverZona.class);
		assertTrue(zona1.getObservers().isEmpty());
		zona1.addObserver(observerDeZona);
		assertTrue(zona1.getObservers().contains(observerDeZona));
	}
	
	@Test
	void testUnaZonaPuedeSettearOrganizacionesInteresadasEnElla() {
		IObserverZona observerDeZona = mock(IObserverZona.class);
		ArrayList<IObserverZona> observers = new ArrayList<>(Arrays.asList(observerDeZona));
		assertFalse(zona1.getObservers().contains(observerDeZona));
		zona1.setObservers(observers);
		assertTrue(zona1.getObservers().contains(observerDeZona));
	}
	
	@Test
	void testUnaZonaConoceLasZonasQueLaSolapan() {
		assertTrue(zona1.getZonasSolapadas().isEmpty());
	}
	
	@Test
	void testUnaZonaPuedeAgregarMasZonasASusZonasSolapadas() {
		ZonaDeCobertura nuevaZona = mock(ZonaDeCobertura.class);
		zona1.addZonaSolapada(nuevaZona);
		
		assertEquals(1, zona1.getZonasSolapadas().size());
	}
	
	@Test
	void testUnaZonaPuedeSettearZonasASusZonasSolapadas() {
		ZonaDeCobertura nuevaZona = mock(ZonaDeCobertura.class);
		Set<ZonaDeCobertura> nuevasZonasSolapadas = new HashSet<>(Arrays.asList(nuevaZona));
		
		assertFalse(zona1.getZonasSolapadas().contains(nuevaZona));
		zona1.setZonasSolapadas(nuevasZonasSolapadas);
		assertTrue(zona1.getZonasSolapadas().contains(nuevaZona));
	}
	
	@Test
	void testZonaContieneMuestrasCuyaUbicacionEsteDentroDelRango() {
		Ubicacion ubiMuestra = mock(Ubicacion.class);
		when(muestra.getUbicacion()).thenReturn(ubiMuestra);
		when(ubiMuestra.distanciaHastaEnKm(epicentro1)).thenReturn(10d);
		
		assertTrue(zona1.contieneMuestra(muestra));
	}
	
	@Test
	void testZonaNoContieneMuestrasCuyaUbicacionNoEsteDentroDelRango() {
		Ubicacion ubiMuestra = mock(Ubicacion.class);
		when(muestra.getUbicacion()).thenReturn(ubiMuestra);
		when(ubiMuestra.distanciaHastaEnKm(epicentro1)).thenReturn(15d);
		
		assertFalse(zona1.contieneMuestra(muestra));
	}
	
	@Test
	void testZonaSabeSiComparteUbicacionConOtraZonaDeCobertura() {
	    ZonaDeCobertura zona2 = mock(ZonaDeCobertura.class);
	    Ubicacion epicentro2 = mock(Ubicacion.class);

	    when(zona2.getEpicentro()).thenReturn(epicentro2);
	    when(zona2.getRadioEnKm()).thenReturn(5d);
	    // menor que 10 + 5
	    when(epicentro1.distanciaHastaEnKm(epicentro2)).thenReturn(9d); 

	    assertTrue(zona1.comparteUbicacionCon(zona2));
	}
	
	@Test
	void testZonaSabeSiNoComparteUbicacionConOtraZonaDeCobertura() {
	    ZonaDeCobertura zona2 = mock(ZonaDeCobertura.class);
	    Ubicacion epicentro2 = mock(Ubicacion.class);

	    when(zona2.getEpicentro()).thenReturn(epicentro2);
	    when(zona2.getRadioEnKm()).thenReturn(5d);
	    // mayor que 10 + 5
	    when(epicentro1.distanciaHastaEnKm(epicentro2)).thenReturn(120d); 

	    assertFalse(zona1.comparteUbicacionCon(zona2));
	}
	
	@Test
	void testZonaNotificaAlosObserversCuandoLlegaUnaNuevaMuestra() {
		IObserverZona observer = mock(IObserverZona.class);
		zona1.addObserver(observer);
		zona1.notificarNuevaMuestra(muestra);
		
		verify(observer, times(1)).nuevaMuestraRegistrada(zona1, muestra);
	}
	
	@Test
	void testZonaNotificaAlosObserversCuandoLlegaUnaMuestraVerificada() {
		IObserverZona observer = mock(IObserverZona.class);
		zona1.addObserver(observer);
		zona1.notificarMuestraVerificada(muestra);
		
		verify(observer, times(1)).nuevaVerificacionMuestra(zona1, muestra);
	}
	
	
}
