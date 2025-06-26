package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.Ubicacion;
import integrador.app.AppWeb;
import integrador.muestra.Muestra;
import integrador.muestra.MuestraAbierta;
import integrador.opinion.DescripcionOpinion;
import integrador.opinion.Opinion;
import integrador.usuario.Usuario;
import integrador.usuario.UsuarioBasico;

class MuestraTestCase {
	
	Muestra muestra;
	Opinion op;
	AppWeb app;	
	Ubicacion ubi;
	//Usuario userBasico;
	//Usuario userBasico2;
	//Usuario userEspecializado;
	//Opinion op1;
	//Opinion op2;
	//Opinion op3;
	Usuario user;
	Opinion op1;
	Opinion op2;
	DescripcionOpinion descripcion1;
	DescripcionOpinion descripcion2;
	UsuarioBasico estadoBasico;
	
	@BeforeEach
	void setUp() {
	
		app = mock(AppWeb.class);
		ubi = mock(Ubicacion.class);
		user = mock(Usuario.class);
		op1 = mock(Opinion.class);
		op2 = mock(Opinion.class);
		estadoBasico = mock(UsuarioBasico.class);

		//config opinion1
		when(this.op1.getDescripcionOpinion()).thenReturn(descripcion1);
		when(this.op1.getMuestraEvaluada()).thenReturn(muestra);
		//config opinion2
		when(this.op2.getDescripcionOpinion()).thenReturn(descripcion2);
		when(this.op2.getMuestraEvaluada()).thenReturn(muestra);
		//config user basico
		when(user.getEstadoUsuario()).thenReturn(estadoBasico);
		when(user.getEstadoUsuario().esExperto()).thenReturn(false);
		
		descripcion1 = DescripcionOpinion.VINCHUCA_SORDIDA;
		descripcion2 = DescripcionOpinion.IMAGEN_POCO_CLARA;
		//userBasico = new Usuario("Pepe", false);
		//userBasico2 = new Usuario("Pepe2", false);
		//userEspecializado = new Usuario("Dardo", true);
		
		muestra = new Muestra(ubi, descripcion1, user, "foto");
		//op1 = new Opinion(descripcion1, muestra);
		//op2 = new Opinion(descripcion2, muestra);
		//op3 = new Opinion(descripcion3, muestra);
		
	}
	
	@Test
	void testMuestraConoceQueUsuarioLaGenero() {
		assertEquals(user, muestra.getIdentificacion());
	}
	
	@Test
	void testMuestraConoceSuUbicacion() {
		assertEquals(ubi, muestra.getUbicacion());
	}
	
	@Test
	void testMuestraConoceLaFotoConLaQueSeLaGenero() {
		assertEquals("foto", muestra.getFoto());
	}
	

	@Test
	void testAlGenerarseLaMuestraCuentaConUnaOpinion() {
		MuestraAbierta estadoMuestra = mock(MuestraAbierta.class);
		Muestra muestraA = new Muestra(ubi, descripcion1, user, "foto");
		when(muestraA.getEstadoMuestra()).thenReturn(estadoMuestra);
		
		verify(estadoMuestra).recibirOpinionUsuarioBasico(any(Opinion.class));
		//verify(estadoMuestra, times(1)).recibirOpinionUsuarioBasico(any(Opinion.class));
		
	}

	/*
	@Test
	void testMuestraConoceSuEspecieInicial() {
		assertEquals(descripcion, muestra.getEspecie());
	}
	*/
	/*
	@Test
	void testLaFechaDeUnaMuestraCorrespondeConElDiaEnQueSeLaCreo() {
		assertEquals(muestra.getFechaDeEnvio(), LocalDate.now());
	}
	
	@Test
	void testLaFechaDeEnvioPuedeSerModificada() {
		LocalDate unaFecha = mock(LocalDate.class);
		muestra.setFechaDeEnvio(unaFecha);
		assertEquals(muestra.getFechaDeEnvio(), unaFecha);
	}
	
	@Test
	void testEstadoInicialDeLaMuestraEsAbierto() {
		assertTrue(muestra.getEstadoMuestra() instanceof MuestraAbierta);
	}

	@Test
	void testLaMuestraPuedeCambiarDeEstado() {
		MuestraVerificada nuevoEstado = mock(MuestraVerificada.class);
		muestra.setEstadoMuestra(nuevoEstado);
		assertEquals(nuevoEstado, muestra.getEstadoMuestra());
	}

	
	 
	@Test	//modificar
	void testSiUnUsuarioBasicoGeneraLaMuestraOtroBasicoPuedeOpinarSobreElla() {
		userBasico.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		
		assertDoesNotThrow(() -> {
	        userBasico2.darOpinion(op2); 	//ESTA MAL PERO DA BIEN, DAR OPINION LO TRANSFORMA EN BASICO
	    });
	}
	
	@Test	//modificar
	void testSiUnUsuarioBasicoGeneraLaMuestraOtroExpertoPuedeOpinarSobreElla() {
		userBasico.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		userBasico2.setEstadoUsuario(new UsuarioExperto());	//ahora es experto
		assertDoesNotThrow(() -> {
	        userBasico2.darOpinion(op2);//ESTA MAL PERO DA BIEN, DAR OPINION LO TRANSFORMA EN BASICO
	    });
	}
	
	*/
	/*
	@Test
	void testSiUnUsuarioBasicoGeneraLaMuestraOtroEspecialistaPuedeOpinarSobreElla() {
		userBasico.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		assertDoesNotThrow(() -> {
			userEspecializado.darOpinion(op2);
	    });
	}
	*/
	
	/*
	@Test
	void testSiUnUsuarioExpertoGeneraLaMuestraLosUsuariosBasicosNoPuedenOpinarSobreElla() {
		userBasico2.setEstadoUsuario(new UsuarioExperto());	//ahora es experto
		userBasico2.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		assertThrows(IllegalStateException.class, () -> {
			userBasico.darOpinion(op2);
	    });
	}
	
	@Test	//???
	void testCuandoElUsuarioCreaUnaMuestraSeGeneraLaPrimeraOpinion() {
		userBasico.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		Muestra muestraGenerada = userBasico.getMuestrasReportadas().getFirst();
		
		assertEquals(1, muestraGenerada.getOpiniones().size());
	}

	
	@Test
	void testNoSePuedeOpinarSobreMuestrasVerificadas() {
			this.muestra.addOpinion(this.op);
			this.muestra.addOpinion(this.op);
			
	}
	*/
}
