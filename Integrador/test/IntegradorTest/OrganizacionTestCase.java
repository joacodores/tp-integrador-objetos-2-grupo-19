package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.muestra.Muestra;
import integrador.organizacion.FuncionalidadExterna;
import integrador.organizacion.Organizacion;
import integrador.organizacion.Organizacion.TipoDeOrganizacion;
import integrador.ubicacion.Ubicacion;
import integrador.zonaDeCobertura.ZonaDeCobertura;

class OrganizacionTestCase {
	
	Organizacion organizacion;
	TipoDeOrganizacion tipo;
	Ubicacion ubi1;
	FuncionalidadExterna funcion1;
	FuncionalidadExterna funcion2;
	ZonaDeCobertura zona1;
	ZonaDeCobertura zona2;
	ZonaDeCobertura zona3;
	Muestra muestra;
	ArrayList<ZonaDeCobertura> zonasDeInteres;
	
	@BeforeEach
	void setUp() throws Exception {
		tipo = TipoDeOrganizacion.SALUD;
		ubi1 = mock(Ubicacion.class);
		funcion1 = mock(FuncionalidadExterna.class);
		funcion2 = mock(FuncionalidadExterna.class);
		zona1 = mock(ZonaDeCobertura.class);
		zona2 = mock(ZonaDeCobertura.class);
		zona3 = mock(ZonaDeCobertura.class);
		muestra = mock(Muestra.class);
		zonasDeInteres = new ArrayList<ZonaDeCobertura>(Arrays.asList(zona1, zona2, zona3));
		
		organizacion = new Organizacion(tipo, 10, ubi1, funcion1, funcion2);
	}

	@Test
	void testLaOrganizacionConoceSuTipo() {
		assertEquals(TipoDeOrganizacion.SALUD, this.organizacion.getTipoDeOrganizacion());
	}
	
	@Test
	void testLaOrganizacionPuedeCambiarSuTipo() {
		this.organizacion.setTipoDeOrganizacion(TipoDeOrganizacion.CULTURAL);
		assertEquals(TipoDeOrganizacion.CULTURAL, this.organizacion.getTipoDeOrganizacion());
	}
	
	@Test
	void testLaOrganizacionConoceCuantasPersonasTrabajanEnElla() {
		assertEquals(10, this.organizacion.getCantDePersonasTrabajando());
	}
	
	@Test
	void testLaOrganizacionPuedeCambiarCuantasPersonasTrabajanEnElla() {
		this.organizacion.setCantDePersonasTrabajando(60);
		assertEquals(60, this.organizacion.getCantDePersonasTrabajando());
	}
	
	@Test
	void testLaOrganizacionConoceSuUbicacion() {
		assertEquals(ubi1, this.organizacion.getUbicacion());
	}
	
	@Test
	void testLaOrganizacionPuedeCambiarSuUbicacion() {
		Ubicacion ubi2 = mock(Ubicacion.class);
		
		this.organizacion.setUbicacion(ubi2);
		assertEquals(ubi2, this.organizacion.getUbicacion());
	}
	
	@Test
	void testLaOrganizacionConoceSusFuncionalidades() {
		assertEquals(funcion1, this.organizacion.getFuncionalidadExternaPorNuevaMuestra());
		assertEquals(funcion2, this.organizacion.getFuncionalidadExternaPorMuestraVerificada());
	}
	
	@Test
	void testLaOrganizacionPuedenCambiarSusFuncionalidades() {
		FuncionalidadExterna funcion3 = mock(FuncionalidadExterna.class);
		
		this.organizacion.setFuncionalidadExternaPorNuevaMuestra(funcion3);
		this.organizacion.setFuncionalidadExternaPorMuestraVerificada(funcion3);
		assertEquals(funcion3, this.organizacion.getFuncionalidadExternaPorNuevaMuestra());
		assertEquals(funcion3, this.organizacion.getFuncionalidadExternaPorMuestraVerificada());
	}

	@Test
	void testLaOrganizacionSabeSiEstaInteresadaONoEnUnaZona() {
		ZonaDeCobertura zonaSinInteres = mock(ZonaDeCobertura.class);
		this.organizacion.registrarseAZonaDeCobertura(zona1);
		
		assertTrue(this.organizacion.getZonasRegistradas().contains(zona1));
		assertFalse(this.organizacion.getZonasRegistradas().contains(zonaSinInteres));
	}
	
	@Test
	void testLaOrganizacionUsaUnaFuncionAlRecibirUnaNuevaMuestraRegistrada() {
		organizacion.nuevaMuestraRegistrada(zona1, muestra);
		verify(funcion1, times(1)).nuevoEvento(organizacion, zona1, muestra);
	}
	
	@Test
	void testLaOrganizacionUsaUnaFuncionAlRecibirUnaNuevaVerificacionDeMuestra() {
		organizacion.nuevaVerificacionMuestra(zona1, muestra);
		verify(funcion2, times(1)).nuevoEvento(organizacion, zona1, muestra);
	}
	
}
