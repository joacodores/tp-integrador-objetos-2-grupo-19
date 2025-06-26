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

import integrador.app.AppWeb;
import integrador.filtrosBusqueda.BuscadorDeMuestra;
import integrador.filtrosBusqueda.CriterioDeBusqueda;
import integrador.filtrosBusqueda.CriterioTipoDeInsecto;
import integrador.muestra.Muestra;
import integrador.muestra.MuestraVerificada;
import integrador.opinion.DescripcionOpinion;
import integrador.organizacion.FuncionalidadExterna;
import integrador.organizacion.Organizacion;
import integrador.ubicacion.Ubicacion;
import integrador.usuario.Usuario;
import integrador.zonaDeCobertura.ZonaDeCobertura;

class AppWebTestCase {
	
	AppWeb app;
	Muestra muestra1;
	Muestra muestra2;
	BuscadorDeMuestra filtro;
	Organizacion organizacion;
	Usuario user;
	Ubicacion ubiEnZona;
	
	Set<Muestra> muestrasRecibidas;
	Set<ZonaDeCobertura> zonasDeCobertura;
	Set<Usuario> usuarios;
	ArrayList<Organizacion> organizaciones;
	ZonaDeCobertura zona;
	
	
	@BeforeEach
	void setUp() throws Exception {
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		filtro = mock(BuscadorDeMuestra.class);
		organizacion = mock(Organizacion.class);
		user = mock(Usuario.class);
		zona = mock(ZonaDeCobertura.class);
		ubiEnZona = mock(Ubicacion.class);
		
		muestrasRecibidas = new HashSet<>();	//vacio
		zonasDeCobertura = new HashSet<>();		//vacio
		usuarios = new HashSet<>();				//vacio
		organizaciones = new ArrayList<>();		//vacio
		
		
		app = new AppWeb(muestrasRecibidas, zonasDeCobertura, filtro, usuarios, organizaciones);
		
		//COMPORTAMIENTOS
		//la muestra tiene como ubicacion aquella en la que este interesada la zona.
		when(muestra1.getUbicacion()).thenReturn(ubiEnZona);
		when(muestra1.getIdentificacion()).thenReturn(user);
		//la organizacion esta interesada en la zona
		when(organizacion.estaInteresadaEnZona(zona)).thenReturn(true);
		//digo que la ubicacion se encuentra dentro de la zona
		when(zona.perteneceUbicacion(ubiEnZona)).thenReturn(true);
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
	void testLaAppPuedeSettearALosUsuarios() {
		assertFalse(app.getUsuarios().contains(user));
		
		Set<Usuario> nuevosUsuarios = Set.of(user);
		app.setUsuarios(nuevosUsuarios);
		
		assertTrue(app.getUsuarios().contains(user));
	}
	
	@Test
	void testLaAppConoceALasMuestrasRecibidas() {
		assertFalse(app.getMuestrasRecibidas().contains(muestra1));
		app.addMuestra(muestra1);
		assertTrue(app.getMuestrasRecibidas().contains(muestra1));
	}
	
	@Test
	void testLaAppPuedeSettearALasUMuestrasRecibidas() {
		assertFalse(app.getMuestrasRecibidas().contains(muestra1));
		
		Set<Muestra> nuevasMuestras = Set.of(muestra1);
		app.setMuestrasRecibidas(nuevasMuestras);
		
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
	void testLaAppPuedeAgregarNuevasZonasDeCobertura() {
		assertFalse(app.getZonasDeCobertura().contains(zona));
		app.addZonaDeCobertura(zona);
		assertTrue(app.getZonasDeCobertura().contains(zona));
	}
	
	@Test
	void testLaAppConoceAlFiltroDeBusquedaUtilizado() {
		assertEquals(this.filtro, app.getFiltroDeMuestras());
	}
	
	@Test
	void testLaAppPuedeCambiarSuFiltroDeBusquedaUtilizado() {
		BuscadorDeMuestra nuevoFiltro = mock(BuscadorDeMuestra.class);
		app.setFiltroDeMuestras(nuevoFiltro);
		assertEquals(nuevoFiltro, app.getFiltroDeMuestras());
	}
	
	@Test
	void testLaAppLeAvisaALasOrganizacionesCorrespondientesCuandoSeCargaUnaNuevaMuestra() {
		//añado zona y organizacion a la app
		Set<ZonaDeCobertura> zonasNuevas = new HashSet<>(List.of(zona));
		app.setZonasDeCobertura(zonasNuevas);
		app.registrarOrganizacion(organizacion);
		
		//llega la muestra a la app, entonces verifico que la organizacion ejecute FE
		app.recibirMuestra(muestra1);	
		verify(organizacion, times(1)).useFENuevaMuestra(zona, muestra1);
		
	}
	
	@Test
	void testLaAppLeAvisaALasOrganizacionesCorrespondientesCuandoSeValidaUnaNuevaMuestra() {
		//añado zona y organizacion a la app
		Set<ZonaDeCobertura> zonasNuevas = new HashSet<>(List.of(zona));
		app.setZonasDeCobertura(zonasNuevas);
		app.registrarOrganizacion(organizacion);
		
		//creo el estado de muestra validada y una funcion externa cualquiera
		MuestraVerificada estadoValidado = mock(MuestraVerificada.class);
		FuncionalidadExterna unaFuncionalidad = mock(FuncionalidadExterna.class);
		
		//establezco el getter de funcionalidadExternaPorMuestraVerificada
		when(organizacion.getFuncionalidadExternaPorMuestraVerificada()).thenReturn(unaFuncionalidad);
		//establezco que la muestra esta en la zona y esta validada
		when(muestra1.getEstadoMuestra()).thenReturn(estadoValidado);
		when(zona.contieneMuestra(muestra1)).thenReturn(true);
		
		//llega la muestra a la app, entonces verifico que la organizacion ejecute FE
		app.nuevaMuestraVerificada(muestra1);	
		verify(organizacion, times(1)).useFEMuestraVerificada(zona, muestra1);
	}
	
	@Test
	void testLaAppPuedeFiltrarMuestrasSegunCriteriosDeFiltrado() {
		ArrayList<Muestra> muestras = new ArrayList<>(Arrays.asList(muestra1, muestra2));
		CriterioDeBusqueda filtrarPorResultado = mock(CriterioTipoDeInsecto.class);
		app.filtrarMuestrasSegunCriterio(muestras, filtrarPorResultado);
		
		verify(filtro, times(1)).filtrar();
	}
	
	
	
}
