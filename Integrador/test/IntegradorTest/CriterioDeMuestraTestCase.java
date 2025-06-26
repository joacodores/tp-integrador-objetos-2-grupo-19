package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.filtrosBusqueda.And;
import integrador.filtrosBusqueda.ComparadorFechas;
import integrador.filtrosBusqueda.CriterioFechaDeMuestra;
import integrador.filtrosBusqueda.CriterioFechaDeVotacion;
import integrador.filtrosBusqueda.CriterioTipoDeInsecto;
import integrador.filtrosBusqueda.CriterioVerificacion;
import integrador.filtrosBusqueda.CriteriosCombinados;
import integrador.filtrosBusqueda.FechaIgualA;
import integrador.filtrosBusqueda.FechaMayorA;
import integrador.filtrosBusqueda.FechaMenorA;
import integrador.filtrosBusqueda.Or;
import integrador.muestra.Muestra;
import integrador.opinion.DescripcionOpinion;

class CriterioDeMuestraTestCase {
	
	CriterioFechaDeMuestra filtroFecha;
	CriterioTipoDeInsecto filtroInsecto;
	CriterioVerificacion filtroVerificacion;
	CriterioFechaDeVotacion filtroFechaDeVotacion;
	
	ComparadorFechas mayorA = new FechaMayorA();
	ComparadorFechas igualA = new FechaIgualA();
	ComparadorFechas menorA = new FechaMenorA();
	And and = new And();
	Or or = new Or();
	
	Muestra muestra1;
	Muestra muestra2;
	Muestra muestra3;
	
	LocalDate comienzoDeMes;
	LocalDate mediadosDeMes;
	LocalDate finalDeMes;
	DescripcionOpinion tipo;
	
	@BeforeEach
	void setUp() throws Exception {
		tipo = DescripcionOpinion.VINCHUCA_SORDIDA;
		
		comienzoDeMes = LocalDate.of(2025, 01, 1);
		mediadosDeMes = LocalDate.of(2025, 01, 16);
		finalDeMes 	= LocalDate.of(2025, 01, 31);
		
		muestra1 = mock(Muestra.class);		//  01/01/25
		muestra2 = mock(Muestra.class);		//  16/01/25
		muestra3 = mock(Muestra.class);		//  31/01/25
		
		when(muestra1.getFechaDeEnvio()).thenReturn(comienzoDeMes);
		when(muestra2.getFechaDeEnvio()).thenReturn(mediadosDeMes);
		when(muestra3.getFechaDeEnvio()).thenReturn(finalDeMes);
		
		when(muestra1.getFechaUltimaVotacion()).thenReturn(comienzoDeMes);
		when(muestra2.getFechaUltimaVotacion()).thenReturn(mediadosDeMes);
		when(muestra3.getFechaUltimaVotacion()).thenReturn(finalDeMes);
		
		filtroInsecto = new CriterioTipoDeInsecto(tipo);
		filtroVerificacion = new CriterioVerificacion(true);
		filtroFecha = new CriterioFechaDeMuestra(mediadosDeMes, mayorA);
		filtroFechaDeVotacion = new CriterioFechaDeVotacion(mediadosDeMes, mayorA);
	}

	@Test
	void testElCriterioPorTipoDeInsectoSabeSiUnaMuestraPasaElFiltro() {
		when(muestra1.getResultadoActual()).thenReturn(DescripcionOpinion.VINCHUCA_SORDIDA);
		when(muestra2.getResultadoActual()).thenReturn(DescripcionOpinion.CHINCHE_FOLIADA);
		
		assertTrue(filtroInsecto.seCumpleQue(muestra1));
		assertFalse(filtroInsecto.seCumpleQue(muestra2));
	}

	@Test
	void testElCriterioPorVerificacionSabeSiUnaMuestraPasaElFiltro() {
		when(muestra1.estaVerificada()).thenReturn(true);
		when(muestra2.estaVerificada()).thenReturn(false);
		
		assertTrue(filtroVerificacion.seCumpleQue(muestra1));
		assertFalse(filtroVerificacion.seCumpleQue(muestra2));
	}
	
	@Test
	void testElCriterioPorFechaSabeSiUnaMuestraPasaElFiltroParaFechasMayoresALaBuscada() {
		assertFalse(filtroFecha.seCumpleQue(muestra1));
		assertFalse(filtroFecha.seCumpleQue(muestra2));
		assertTrue(filtroFecha.seCumpleQue(muestra3));
	}
	
	@Test
	void testElCriterioPorFechaSabeSiUnaMuestraPasaElFiltroParaFechasMenoresALaBuscada() {
		filtroFecha.setComparadorFechas(menorA);
		
		assertTrue(filtroFecha.seCumpleQue(muestra1));
		assertFalse(filtroFecha.seCumpleQue(muestra2));
		assertFalse(filtroFecha.seCumpleQue(muestra3));
	}
	
	@Test
	void testElCriterioPorFechaSabeSiUnaMuestraPasaElFiltroParaFechasIgualesALaBuscada() {
		filtroFecha.setComparadorFechas(igualA);
		
		assertFalse(filtroFecha.seCumpleQue(muestra1));
		assertTrue(filtroFecha.seCumpleQue(muestra2));
		assertFalse(filtroFecha.seCumpleQue(muestra3));
	}
	
	@Test
	void testElCriterioPorFechaVotacionSabeSiUnaMuestraPasaElFiltroParaFechasMayoresALaBuscada() {
		assertFalse(filtroFechaDeVotacion.seCumpleQue(muestra1));
		assertFalse(filtroFechaDeVotacion.seCumpleQue(muestra2));
		assertTrue(filtroFechaDeVotacion.seCumpleQue(muestra3));
	}
	
	@Test
	void testElCriterioPorFechaVotacionSabeSiUnaMuestraPasaElFiltroParaFechasMenoresALaBuscada() {
		filtroFechaDeVotacion.setComparadorFechas(menorA);
		
		assertTrue(filtroFechaDeVotacion.seCumpleQue(muestra1));
		assertFalse(filtroFechaDeVotacion.seCumpleQue(muestra2));
		assertFalse(filtroFechaDeVotacion.seCumpleQue(muestra3));
	}
	
	@Test
	void testElCriterioPorFechaVotacionSabeSiUnaMuestraPasaElFiltroParaFechasIgualesALaBuscada() {
		filtroFechaDeVotacion.setComparadorFechas(igualA);
		
		assertFalse(filtroFechaDeVotacion.seCumpleQue(muestra1));
		assertTrue(filtroFechaDeVotacion.seCumpleQue(muestra2));
		assertFalse(filtroFechaDeVotacion.seCumpleQue(muestra3));
	}
	
	@Test
	void testOperadorLogicoAndFiltraMuestrasQueCumplanAmbasCondiciones() {
		CriterioFechaDeMuestra c1 = filtroFecha;
		CriterioTipoDeInsecto c2 = filtroInsecto;
		CriteriosCombinados filtroCombinado = new CriteriosCombinados(c1, c2, and);
		
		when(muestra1.getResultadoActual()).thenReturn(DescripcionOpinion.VINCHUCA_SORDIDA);
		when(muestra2.getResultadoActual()).thenReturn(DescripcionOpinion.VINCHUCA_SORDIDA);
		when(muestra3.getResultadoActual()).thenReturn(DescripcionOpinion.VINCHUCA_SORDIDA);
		
		assertFalse(filtroCombinado.seCumpleQue(muestra1));
		assertFalse(filtroCombinado.seCumpleQue(muestra2));
		assertTrue(filtroCombinado.seCumpleQue(muestra3));
	}
	
	@Test
	void testOperadorLogicoAndFiltraMuestrasQueCumplanAlMenosUnaCondicion() {
		CriterioFechaDeMuestra c1 = filtroFecha;
		CriterioTipoDeInsecto c2 = filtroInsecto;
		CriteriosCombinados filtroCombinado = new CriteriosCombinados(c1, c2, or);
		
		when(muestra1.getResultadoActual()).thenReturn(DescripcionOpinion.IMAGEN_POCO_CLARA);
		when(muestra2.getResultadoActual()).thenReturn(DescripcionOpinion.VINCHUCA_SORDIDA);
		when(muestra3.getResultadoActual()).thenReturn(DescripcionOpinion.VINCHUCA_SORDIDA);
		
		assertFalse(filtroCombinado.seCumpleQue(muestra1));
		assertTrue(filtroCombinado.seCumpleQue(muestra2));
		assertTrue(filtroCombinado.seCumpleQue(muestra3));
	}
	
}
