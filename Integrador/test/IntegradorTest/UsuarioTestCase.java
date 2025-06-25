package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

<<<<<<< HEAD
import integrador.Usuario;
import integrador.UsuarioBasico;
import integrador.UsuarioEspecialista;
import integrador.UsuarioExperto;
import integrador.Muestra;
import integrador.Opinion;
=======
import integrador.usuario.Usuario;
import integrador.usuario.UsuarioBasico;
import integrador.usuario.UsuarioEspecialista;
import integrador.usuario.UsuarioExperto;
import integrador.Ubicacion;
import integrador.app.AppWeb;
import integrador.muestra.Muestra;
import integrador.opinion.DescripcionOpinion;
import integrador.opinion.Opinion;
>>>>>>> branch 'main' of https://github.com/joacodores/tp-integrador-objetos-2-grupo-19.git

class UsuarioTestCase {

	private Usuario usuarioNormal;
	private Usuario usuarioEspecialista;
	private Muestra muestra1;
	private Muestra muestra2;
	private Opinion op1;
	private Opinion op2;
	
	
	@BeforeEach
	void setUp() {
		this.usuarioNormal = new Usuario("Pepe Argento", false);
		this.usuarioEspecialista = new Usuario("Maria Elena", true);
		
		this.muestra1 = mock(Muestra.class);
		this.muestra2 = mock(Muestra.class);
		this.op1 = mock(Opinion.class);
		this.op2 = mock(Opinion.class);
		
		//op1 es una opinion que se genero para muestra1
		when(this.op1.getMuestraEvaluada()).thenReturn(muestra1);
		when(this.op2.getMuestraEvaluada()).thenReturn(muestra1);
	}
	
	@Test
	void testExisteUnUsuarioBasico() {
		assertEquals("Pepe Argento", this.usuarioNormal.getNombreUsuario());
		assertTrue(this.usuarioNormal.getEstadoUsuario() instanceof UsuarioBasico);
		assertTrue(this.usuarioNormal.getMuestrasReportadas().isEmpty());
	};
	
	@Test
	void testElUsuarioPuedeCambiarSuNombre() {
		assertEquals("Pepe Argento", this.usuarioNormal.getNombreUsuario());
		usuarioNormal.setNombreUsuario("Nuevo Nombre");
		assertEquals("Nuevo Nombre", this.usuarioNormal.getNombreUsuario());
	};
	
	@Test
	void testExisteUnUsuarioEspecialista() {
		assertEquals("Maria Elena", this.usuarioEspecialista.getNombreUsuario());
		assertTrue(this.usuarioEspecialista.getEstadoUsuario() instanceof UsuarioEspecialista);
	};
	
	@Test
	void testAlCrearseUnUsuarioNoTieneMuestrasReportadasNiOpinionesDadas() {
		assertTrue(this.usuarioNormal.getMuestrasReportadas().isEmpty());
		assertTrue(this.usuarioNormal.getOpinionesEnviadas().isEmpty());
	};
	
	@Test
	void testUnUsuarioTieneUnHistorialDeOpinionesDadas() {
		assertTrue(this.usuarioNormal.getOpinionesEnviadas().size() == 0);
		usuarioNormal.addOpinion(op1);
		usuarioNormal.addOpinion(op2);

		assertTrue(this.usuarioNormal.getOpinionesEnviadas().size() == 2);
	};
	
	@Test
	void testCuandoUnUsuarioTieneSuHistorialDeMuestrasEnviadas() {
		 assertTrue(this.usuarioNormal.getMuestrasReportadas().isEmpty());
		 usuarioNormal.addMuestraReportada(muestra1);
		 
		 assertEquals(1, this.usuarioNormal.getMuestrasReportadas().size());
		 assertTrue(this.usuarioNormal.getMuestrasReportadas().contains(muestra1));
	};
	
	@Test
	void testUsuarioSabeQueMuestrasSonDeLosUltimos30Dias() {
		 assertTrue(this.usuarioNormal.muestrasDeLosUltimos30Dias().isEmpty());
		 LocalDate hoy = LocalDate.now();
		 LocalDate haceTiempo = LocalDate.of(2020, 10, 1);
		 usuarioNormal.addMuestraReportada(muestra1);
		 usuarioNormal.addMuestraReportada(muestra2);	
		 
		 when(this.muestra1.getFechaDeEnvio()).thenReturn(hoy);
		 when(this.muestra2.getFechaDeEnvio()).thenReturn(haceTiempo);
		 
		 assertTrue(this.usuarioNormal.muestrasDeLosUltimos30Dias().contains(muestra1));
		 assertFalse(this.usuarioNormal.muestrasDeLosUltimos30Dias().contains(muestra2));
	};
	
	@Test
	void testUsuarioSabeQueOpinionesSonDeLosUltimos30Dias() {
		 assertTrue(this.usuarioNormal.opinionesDeLosUltimos30Dias().isEmpty());
		 LocalDate hoy = LocalDate.now();
		 LocalDate haceTiempo = LocalDate.of(2020, 10, 1);
		 usuarioNormal.addOpinion(op1);
		 usuarioNormal.addOpinion(op2);	
		 
		 when(this.op1.getFechaOpinion()).thenReturn(hoy);
		 when(this.op2.getFechaOpinion()).thenReturn(haceTiempo);
		 
		 assertTrue(this.usuarioNormal.opinionesDeLosUltimos30Dias().contains(op1));
		 assertFalse(this.usuarioNormal.opinionesDeLosUltimos30Dias().contains(op2));
	};
	
	@Test
	void testUnUsuarioBasicoPuedeConvertirseEnExperto() {
		assertTrue(this.usuarioNormal.getEstadoUsuario() instanceof UsuarioBasico);
		
		when(this.op1.getFechaOpinion()).thenReturn(LocalDate.now());
		when(this.muestra1.getFechaDeEnvio()).thenReturn(LocalDate.now());
		ArrayList<Opinion> opiniones = new ArrayList<>(Collections.nCopies(21, this.op1));
		ArrayList<Muestra> muestras = new ArrayList<>(Collections.nCopies(11, this.muestra1));

		this.usuarioNormal.setOpinionesEnviadas(opiniones);	//21 opiniones de hoy
		this.usuarioNormal.setMuestrasReportadas(muestras); //11 muestras de hoy
		
		assertTrue(this.usuarioNormal.getEstadoUsuario() instanceof UsuarioExperto);
	};

	@Test
	void testUnUsuarioExpertoPuedeConvertirseEnBasico() {
		//establezco que cumplio con los requisitos para experto pero hace 30 dias
		LocalDate fechaDeEnvios = LocalDate.now().minusDays(31);	
		when(this.op1.getFechaOpinion()).thenReturn(fechaDeEnvios);
		when(this.muestra1.getFechaDeEnvio()).thenReturn(fechaDeEnvios);
		ArrayList<Opinion> opiniones = new ArrayList<>(Collections.nCopies(21, this.op1));
		ArrayList<Muestra> muestras = new ArrayList<>(Collections.nCopies(11, this.muestra1));
		
		this.usuarioNormal.setOpinionesEnviadas(opiniones);	//21 opiniones de hace 30 dias
		this.usuarioNormal.setMuestrasReportadas(muestras); //11 muestras de hace 30 dias
		
		assertTrue(this.usuarioNormal.getEstadoUsuario() instanceof UsuarioBasico);
	};
	
	@Test
	void testUnUsuarioEspecialistaNoPuedeSerBasico() {
		//usuarioEspecialista no cumple la verificacion de mas de  opiniones y mas de 10 muestras
		assertTrue(this.usuarioEspecialista.getEstadoUsuario() instanceof UsuarioEspecialista);
		assertFalse(this.usuarioEspecialista.getEstadoUsuario() instanceof UsuarioBasico);
	};
	
	/*
	@Test	//hacerlo desde el lado de muestra
	void testUnUsuarioNoPuedeOpinarDosVecesEnLaMismaMuestra() {
		usuarioNormal.darOpinion(op1); 	//muestraEvaluda == muestra1
		
		assertThrows(IllegalStateException.class, () -> {
			usuarioNormal.darOpinion(op1);	//intenta dar una opinion devuelta para muestra1
		});
	}
	*/
		
	
}
