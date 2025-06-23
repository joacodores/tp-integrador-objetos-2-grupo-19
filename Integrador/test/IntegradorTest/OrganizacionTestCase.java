package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.FuncionalidadExterna;
import integrador.Organizacion;
import integrador.Organizacion.TipoDeOrganizacion;
import integrador.Ubicacion;
import integrador.ZonaDeCobertura;

class OrganizacionTestCase {
	
	Organizacion organizacion;
	TipoDeOrganizacion tipo;
	Ubicacion ubi1;
	FuncionalidadExterna funcion1;
	FuncionalidadExterna funcion2;
	ZonaDeCobertura zona1;
	ZonaDeCobertura zona2;
	ZonaDeCobertura zona3;
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
	void testLaOrganizacionEstableceQueZonasRegistrar() {
		this.organizacion.setZonasRegistradas(zonasDeInteres);
		
		assertEquals(zonasDeInteres, this.organizacion.getZonasRegistradas());
	}
	
	@Test
	void testLaOrganizacionSabeSiEstaInteresadaONoEnUnaZona() {
		ZonaDeCobertura zonaSinInteres = mock(ZonaDeCobertura.class);
		this.organizacion.setZonasRegistradas(zonasDeInteres);
		
		assertTrue(this.organizacion.estaInteresadaEnZona(zona1));
		assertFalse(this.organizacion.estaInteresadaEnZona(zonaSinInteres));
	}
	
	@Test
	void testLaOrganizacionUsarSusFunciones() {
		this.organizacion.useFENuevaMuestra(zona1, null);
		this.organizacion.useFEMuestraVerificada(zona2, null);
		verify(funcion1, times(1)).nuevoEvento(organizacion, zona1, null);
		verify(funcion2, times(1)).nuevoEvento(organizacion, zona2, null);
	}
	
}
