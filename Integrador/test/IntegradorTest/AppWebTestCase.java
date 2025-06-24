package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.AppWeb;
import integrador.BuscadorDeMuestra;
import integrador.Muestra;
import integrador.Organizacion;
import integrador.Usuario;
import integrador.ZonaDeCobertura;

class AppWebTestCase {
	
	AppWeb app;
	Muestra muestra1;
	Muestra muestra2;
	BuscadorDeMuestra filtros;
	Organizacion organizacion;
	Usuario user;
	
	Set<Muestra> muestrasRecibidas;
	Set<ZonaDeCobertura> zonasDeCobertura;
	Set<Usuario> usuarios;
	ArrayList<Organizacion> organizaciones;
	ZonaDeCobertura zona;
	
	
	@BeforeEach
	void setUp() throws Exception {
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		filtros = mock(BuscadorDeMuestra.class);
		organizacion = mock(Organizacion.class);
		user = mock(Usuario.class);
		zona = mock(ZonaDeCobertura.class);
		
		muestrasRecibidas = new HashSet<>();	//vacio
		zonasDeCobertura = new HashSet<>();		//vacio
		usuarios = new HashSet<>();				//vacio
		organizaciones = new ArrayList<>();		//vacio
		
		
		app = new AppWeb(muestrasRecibidas, zonasDeCobertura, filtros, usuarios, organizaciones);
	}

	@Test
	void testLaAppConoceALasOrganizacionesRegistradas() {
		assertTrue(app.getOrganizaciones().isEmpty());
	}
	
	@Test
	void testLaAppPuedeSettearALasOrganizaciones() {
		assertEquals(0, app.getOrganizaciones().size());
		
		ArrayList<Organizacion> nuevasOrganizaciones = new ArrayList<>();	
		nuevasOrganizaciones.add(organizacion);
		app.setOrganizaciones(nuevasOrganizaciones);
		
		assertEquals(1, app.getOrganizaciones().size());
	}
	
	@Test
	void testLaAppPuedeSRegistrarOrganizacionesNuevas() {
		assertFalse(app.getOrganizaciones().contains(organizacion));
		app.registrarOrganizacion(organizacion);
		assertTrue(app.getOrganizaciones().contains(organizacion));
	}
	
	@Test
	void testLaAppConoceALosUsuarioRegistrados() {
		assertFalse(app.getUsuarios().contains(user));
		app.addUsuario(user);
		assertTrue(app.getUsuarios().contains(user));
	}
	
	@Test
	void testLaAppConoceALasMuestrasRecibidas() {
		assertFalse(app.getMuestrasRecibidas().contains(muestra1));
		app.addMuestra(muestra1);
		assertTrue(app.getMuestrasRecibidas().contains(muestra1));
	}
	
	@Test
	void testLaAppConoceALasZonasDeCobertura() {
		assertFalse(app.getZonasDeCobertura().contains(zona));
		Set<ZonaDeCobertura> zonasNuevas = new HashSet<>(List.of(zona));
		app.setZonasDeCobertura(zonasNuevas);
		assertTrue(app.getZonasDeCobertura().contains(zona));
	}
	
	@Test
	void testLaAppConoceAlFiltroDeBusquedaUtilizado() {
		assertEquals(this.filtros, app.getFiltroDeMuestras());
	}
	
	@Test
	void testLaAppPuedeCambiarSuFiltroDeBusquedaUtilizado() {
		BuscadorDeMuestra nuevoFiltro = mock(BuscadorDeMuestra.class);
		app.setFiltroDeMuestras(nuevoFiltro);
		assertEquals(nuevoFiltro, app.getFiltroDeMuestras());
	}
}
