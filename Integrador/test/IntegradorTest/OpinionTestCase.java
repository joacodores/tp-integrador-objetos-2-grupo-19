package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.muestra.Muestra;
import integrador.opinion.DescripcionOpinion;
import integrador.opinion.Opinion;

class OpinionTestCase {
	
	Opinion opinion;
	DescripcionOpinion descripcion;
	Muestra muestra;
	
	@BeforeEach
	void setUp() throws Exception {
		descripcion = DescripcionOpinion.VINCHUCA_INFESTANS;
		muestra = mock(Muestra.class);
		opinion = new Opinion(descripcion, muestra);
	}

	@Test
	void testOpinionConoceSuMuestraEvaluada() {
		assertEquals(muestra, opinion.getMuestraEvaluada());
	}
	
	@Test
	void testOpinionConoceSuDescripcion() {
		assertEquals(descripcion, opinion.getDescripcionOpinion());
	}
	
	@Test
	void testOpinionConoceSuFechaDeOpinion() {
		assertEquals(LocalDate.now(), opinion.getFechaOpinion());
	}
	
	@Test
	void testOpinionPuedeSettearSuMuestraEvaluada() {
		Muestra nuevaMuestra = mock(Muestra.class);
		opinion.setMuestraEvaluada(nuevaMuestra);
		assertEquals(nuevaMuestra, opinion.getMuestraEvaluada());
	}
	
	@Test
	void testOpinionPuedeSettearSuDescripcion() {
		DescripcionOpinion nuevaDescripcion = DescripcionOpinion.IMAGEN_POCO_CLARA;
		opinion.setDescripcionOpinion(nuevaDescripcion);
		assertEquals(nuevaDescripcion, opinion.getDescripcionOpinion());
	}
	
	@Test
	void testOpinionPuedeSettearSuFechaDeOpinion() {
		LocalDate nuevaFecha = LocalDate.of(2025, 06, 1);
		opinion.setFechaOpinion(nuevaFecha);
		assertEquals(nuevaFecha, opinion.getFechaOpinion());
	}

}
