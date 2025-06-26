package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.filtrosBusqueda.BuscadorDeMuestra;
import integrador.filtrosBusqueda.CriterioDeBusqueda;
import integrador.filtrosBusqueda.CriterioFechaDeVotacion;
import integrador.filtrosBusqueda.CriterioTipoDeInsecto;
import integrador.muestra.Muestra;

class BuscadorDeMuestraTestCase {
	
	BuscadorDeMuestra buscador;
	CriterioDeBusqueda filtro;
	Muestra muestra1;
	Muestra muestra2;
	Muestra muestra3;
	ArrayList<Muestra> listaDeMuestras; 
	
	@BeforeEach
	void setUp() throws Exception {
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		
		filtro = mock(CriterioTipoDeInsecto.class);
		listaDeMuestras = new ArrayList<>(Arrays.asList(muestra1, muestra2, muestra3));
		
		buscador = new BuscadorDeMuestra(listaDeMuestras, filtro);
	}

	@Test
	void testElBuscadorTieneMuestrasALasQueConoce() {
		assertEquals(listaDeMuestras, buscador.getMuestrasAFiltrar());
	}
	
	@Test
	void testElBuscadorConoceElFiltroQueEstaAplicando() {
		assertTrue(buscador.getCriterioDeBusqueda() instanceof CriterioTipoDeInsecto);
	}
	
	@Test
	void testSePuedenAgregarNuevasMuestrasAlBuscador() {
		Muestra nuevaMuestra = mock(Muestra.class);
		buscador.addMuestra(nuevaMuestra);
		assertEquals(4, buscador.getMuestrasAFiltrar().size());
		assertTrue(buscador.getMuestrasAFiltrar().contains(nuevaMuestra));
	}
	
	@Test
	void testSePuedenQuitarMuestrasDelBuscador() {
		buscador.removeMuestra(muestra3);
		assertEquals(2, buscador.getMuestrasAFiltrar().size());
		assertFalse(buscador.getMuestrasAFiltrar().contains(muestra3));
	}
	
	@Test
	void testSePuedenSettearNuevasMuestrasEnElBuscador() {
		ArrayList<Muestra> NuevasMuestras;
		NuevasMuestras= new ArrayList<>(Arrays.asList(muestra3));
		
		assertEquals(3, buscador.getMuestrasAFiltrar().size());
		buscador.setMuestrasAFiltrar(NuevasMuestras);
		assertEquals(1, buscador.getMuestrasAFiltrar().size());
	}
	
	@Test
	void testSePuedenCambiarElFiltroBuscador() {
		CriterioDeBusqueda nuevoFiltro = mock(CriterioFechaDeVotacion.class);
		
		assertTrue(buscador.getCriterioDeBusqueda() instanceof CriterioTipoDeInsecto);
		buscador.setCriterioDeBusqueda(nuevoFiltro);
		assertTrue(buscador.getCriterioDeBusqueda() instanceof CriterioFechaDeVotacion);
	}
	
	@Test
	void testBuscadorPuedeFiltrarMuestrasSegunSuCriterioDeBusqueda() {
		when(filtro.seCumpleQue(muestra1)).thenReturn(true);
		when(filtro.seCumpleQue(muestra2)).thenReturn(true);
		when(filtro.seCumpleQue(muestra3)).thenReturn(false);
		
		ArrayList<Muestra> resultadoFinal = new ArrayList<>(Arrays.asList(muestra1, muestra2)); 
		assertEquals(resultadoFinal, buscador.filtrar());
	}
	
	

}
