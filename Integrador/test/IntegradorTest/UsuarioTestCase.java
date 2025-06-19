package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import integrador.Usuario;
import integrador.UsuarioBasico;
import integrador.UsuarioEspecialista;
import integrador.UsuarioExperto;
import integrador.AppWeb;
import integrador.DescripcionOpinion;
import integrador.Muestra;
import integrador.Opinion;
import integrador.Ubicacion;

class UsuarioTestCase {

	private AppWeb app;
	private Ubicacion ubicacion;
	private Usuario usuarioNormal;
	private Usuario usuarioEspecialista;
	private Muestra muestra1;
	private Opinion op1;
	private DescripcionOpinion descripcion = DescripcionOpinion.VINCHUCA_SORDIDA;
	
	
	@BeforeEach
	void setUp() {
		this.usuarioNormal = new Usuario("Pepe Argento", false);
		this.usuarioEspecialista = new Usuario("Maria Elena", true);
		
		this.app = mock(AppWeb.class);
		this.ubicacion = mock(Ubicacion.class);
		this.muestra1 = mock(Muestra.class);
		this.op1 = mock(Opinion.class);
		
		//op1 es una opinion que se genero para muestra1
		when(this.op1.getMuestraEvaluada()).thenReturn(muestra1);
	}
	
	@Test
	void testExisteUnUsuarioBasico() {
		assertEquals("Pepe Argento", this.usuarioNormal.getNombreUsuario());
		assertTrue(this.usuarioNormal.getEstadoUsuario() instanceof UsuarioBasico);
		assertTrue(this.usuarioNormal.getMuestrasReportadas().isEmpty());
	};
	
	@Test
	void testAlCrearseUnUsuarioNoTieneMuestrasReportadasNiOpinionesDadas() {
		assertTrue(this.usuarioNormal.getMuestrasReportadas().isEmpty());
		assertTrue(this.usuarioNormal.getOpinionesEnviadas().isEmpty());
	};
	
	@Test
	void testUnUsuarioAgregaUnaOpinionQuedaRegistradaEnUnHistorial() {
		assertTrue(this.usuarioNormal.getOpinionesEnviadas().size() == 0);
		
		this.usuarioNormal.darOpinion(op1);
		 assertTrue(this.usuarioNormal.getOpinionesEnviadas().size() == 1);
		 assertTrue(this.usuarioNormal.getOpinionesEnviadas().remove(0) == op1);
	};
	
	@Test
	void testCuandoUnUsuarioAgregaUnaMuestraQuedaRegistradaEnUnHistorial() {
		 assertTrue(this.usuarioNormal.getMuestrasReportadas().size() == 0);
		 this.usuarioNormal.enviarMuestra(app, usuarioNormal, ubicacion, descripcion, "");
		 
		 assertTrue(this.usuarioNormal.getMuestrasReportadas().size() == 1);
	};
	
	@Test
	void testUnUsuarioNoPuedeOpinarDosVecesEnLaMismaMuestra() {
		usuarioNormal.darOpinion(op1); 	//muestraEvaluda == muestra1
		
		assertThrows(IllegalStateException.class, () -> {
			usuarioNormal.darOpinion(op1);	//intenta dar una opinion devuelta para muestra1
		});
	}
	
	@Test
	void testAlEnviarUnaMuestraElUsuarioTambienGeneraUnaOpinionDeDichaMuestra() {
		usuarioNormal.enviarMuestra(app, usuarioNormal, ubicacion, descripcion, "");
		
		Muestra muestraGenerada = usuarioNormal.getMuestrasReportadas().getFirst();
		
		assertEquals(1, this.usuarioNormal.getOpinionesEnviadas().size());
		assertEquals(1, muestraGenerada.getOpiniones().size());
		//evaluan si son las mismas muestras
		assertEquals(muestraGenerada, usuarioNormal.getOpinionesEnviadas().getFirst().getMuestraEvaluada());
	}
	
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
	
	
		
	
}
