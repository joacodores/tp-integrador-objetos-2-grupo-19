package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.Usuario;

class usuarioTest {

	Usuario usuarioBasico;
	Usuario usuarioEspecialista;
	
	
	@BeforeEach
	void setUp() {
		usuarioBasico = new Usuario("Pepe Argento", false);
		usuarioEspecialista = new Usuario("Moni Argento", true);
	}
	
	@Test
	void existeUnUsuarioBasico() {
		assertEquals("Pepe Argento", usuarioBasico.getNombreUsuario());
		assertFalse(usuarioBasico.getEsEspecialista());
		assertTrue(usuarioBasico.getMuestrasReportadas().isEmpty());
	};
	
		
	
}
