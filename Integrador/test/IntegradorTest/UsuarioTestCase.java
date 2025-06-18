package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import integrador.Usuario;
import integrador.UsuarioBasico;
import integrador.UsuarioEspecialista;
import integrador.Muestra;
import integrador.Opinion;

class UsuarioTestCase {

	private Usuario usuarioNormal;
	private Usuario usuarioEspecialista;
	private Muestra muestra1;
	private Opinion op1;
	
	@BeforeEach
	void setUp() {
		this.usuarioNormal = new Usuario("Pepe Argento", false);
		this.usuarioEspecialista = new Usuario("Maria Elena", true);
		
		this.muestra1 = mock(Muestra.class);
		this.op1 = mock(Opinion.class);
		
		//op1 es una opinion que se genero para muestra1
		when(this.op1.getMuestraEvaluada()).thenReturn(muestra1);
	}
	
	@Test
	void testExisteUnUsuarioBasico() {
		assertEquals("Pepe Argento", this.usuarioNormal.getNombreUsuario());
		assertFalse(this.usuarioNormal.getEstadoUsuario() instanceof UsuarioBasico);
		assertTrue(this.usuarioNormal.getMuestrasReportadas().isEmpty());
	};
	
	@Test
	void testAlCrearseUnUsuarioNoTieneMuestrasReportadasNiOpinionesDadas() {
		assertTrue(this.usuarioNormal.getMuestrasReportadas().isEmpty());
		assertTrue(this.usuarioNormal.getOpinionesEnviadas().isEmpty());
	};
	
	@Test
	void testCuandoUnUsuarioAgregaUnaOpinionQuedaRegistradaEnUnHistorial() {
		 this.usuarioNormal.darOpinion(op1);
		 assertTrue(this.usuarioNormal.getOpinionesEnviadas().size() == 1);
		 assertTrue(this.usuarioNormal.getOpinionesEnviadas().remove(0) == op1);
	};
	/*
	@Test
	void testCuandoUnUsuarioAgregaUnaMuestraQuedaRegistradaEnUnHistorial() {
		 this.usuarioNormal.enviarMuestra(muestra1);
		 assertTrue(this.usuarioNormal.getMuestrasReportadas().size() == 1);
		 assertTrue(this.usuarioNormal.getMuestrasReportadas().remove(0) == muestra1);
	};
	
	@Test-
	void testUnUsuarioNoPuedeOpinarEnSuPropiaMuestra() {
		this.usuarioNormal.enviarMuestra(muestra1);
		// asertar que huba una exception
	}
	*/
	
	@Test
	void testUnUsuarioBasicoPuedeConvertirseEnExperto() {
		
	};
	
	
	
	@Test
	void testUnUsuarioEspecialistaNoPuedeSerBasico() {
		assertTrue(this.usuarioEspecialista.getEstadoUsuario() instanceof UsuarioEspecialista);
	};
	
	
		
	
}
