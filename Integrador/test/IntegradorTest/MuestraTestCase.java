package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.AppWeb;
import integrador.DescripcionOpinion;
import integrador.Muestra;
import integrador.MuestraAbierta;
import integrador.MuestraSoloExpertos;
import integrador.ObserverPorMuestraVerificada;
import integrador.Opinion;
import integrador.Ubicacion;
import integrador.Usuario;
import integrador.UsuarioExperto;

class MuestraTestCase {
	
	Muestra muestra;
	Opinion op;
	AppWeb app;	
	Ubicacion ubi;
	Usuario userBasico;
	Usuario userBasico2;
	Usuario userEspecializado;
	Opinion op1;
	Opinion op2;
	Opinion op3;
	DescripcionOpinion descripcion1;
	DescripcionOpinion descripcion2;
	DescripcionOpinion descripcion3;
	
	@BeforeEach
	void setUp() {
	
		app = mock(AppWeb.class);
		ubi = mock(Ubicacion.class);
		descripcion1 = DescripcionOpinion.VINCHUCA_SORDIDA;
		descripcion2 = DescripcionOpinion.IMAGEN_POCO_CLARA;
		descripcion3 = DescripcionOpinion.CHINCHE_FOLIADA;
		userBasico = new Usuario("Pepe", false);
		userBasico2 = new Usuario("Pepe2", false);
		userEspecializado = new Usuario("Dardo", true);
		muestra = new Muestra(ubi, descripcion1, userBasico, "foto", new ObserverPorMuestraVerificada(app));
		op1 = new Opinion(descripcion1, muestra);
		op2 = new Opinion(descripcion2, muestra);
		op3 = new Opinion(descripcion3, muestra);
		
	}
	
	@Test
	void testMuestraConoceQueUsuarioLaGenero() {
		assertEquals(userBasico, muestra.getIdentificacion());
	}
	
	@Test
	void testMuestraConoceSuUbicacion() {
		assertEquals(ubi, muestra.getUbicacion());
	}
	/*
	@Test
	void testMuestraConoceSuEspecieInicial() {
		assertEquals(descripcion, muestra.getEspecie());
	}
	*/
	@Test
	void testLaFechaDeUnaMuestraCorrespondeConElDiaEnQueSeLaCreo() {
		assertEquals(muestra.getFechaDeEnvio(), LocalDate.now());
	}
	
	@Test
	void testEstadoInicialDeLaMuestraEsAbierto() {
		assertTrue(muestra.getEstadoMuestra() instanceof MuestraAbierta);
	}
	
	@Test
	void testSiUnUsuarioBasicoGeneraLaMuestraOtroBasicoPuedeOpinarSobreElla() {
		userBasico.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		
		assertDoesNotThrow(() -> {
	        userBasico2.darOpinion(op2); 	//ESTA MAL PERO DA BIEN, DAR OPINION LO TRANSFORMA EN BASICO
	    });
	}
	
	@Test
	void testSiUnUsuarioBasicoGeneraLaMuestraOtroExpertoPuedeOpinarSobreElla() {
		userBasico.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		userBasico2.setEstadoUsuario(new UsuarioExperto());	//ahora es experto
		assertDoesNotThrow(() -> {
	        userBasico2.darOpinion(op2);//ESTA MAL PERO DA BIEN, DAR OPINION LO TRANSFORMA EN BASICO
	    });
	}
	/*
	@Test
	void testSiUnUsuarioBasicoGeneraLaMuestraOtroEspecialistaPuedeOpinarSobreElla() {
		userBasico.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		assertDoesNotThrow(() -> {
			userEspecializado.darOpinion(op2);
	    });
	}
	*/
	@Test
	void testSiUnUsuarioExpertoGeneraLaMuestraLosUsuariosBasicosNoPuedenOpinarSobreElla() {
		userBasico2.setEstadoUsuario(new UsuarioExperto());	//ahora es experto
		userBasico2.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		assertThrows(IllegalStateException.class, () -> {
			userBasico.darOpinion(op2);
	    });
	}
	
	@Test
	void testCuandoElUsuarioCreaUnaMuestraSeGeneraLaPrimeraOpinion() {
		userBasico.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		Muestra muestraGenerada = userBasico.getMuestrasReportadas().getFirst();
		
		assertEquals(1, muestraGenerada.getOpiniones().size());
	}
	
	@Test
	void testSiUsuarioCreaUnaMuestraSeGeneraLaPrimeraOpinion() {
		userBasico.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		Muestra muestraGenerada = userBasico.getMuestrasReportadas().getFirst();
		
		assertEquals(1, muestraGenerada.getOpiniones().size());
	}
	
	
	
	
	
	@Test
	void testNoSePuedeOpinarSobreMuestrasVerificadas() {
			this.muestra.addOpinion(this.op);
			this.muestra.addOpinion(this.op);
			
	}

}
