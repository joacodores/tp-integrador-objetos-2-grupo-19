package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import integrador.app.AppWeb;
import integrador.avisoOrganizaciones.IObserverMuestra;
import integrador.muestra.EstadoMuestra;
import integrador.muestra.Muestra;
import integrador.muestra.MuestraAbierta;
import integrador.muestra.MuestraSoloExpertos;
import integrador.muestra.MuestraVerificada;
import integrador.opinion.DescripcionOpinion;
import integrador.opinion.Opinion;
import integrador.ubicacion.Ubicacion;
import integrador.usuario.Usuario;
import integrador.usuario.UsuarioBasico;
import integrador.zonaDeCobertura.ZonaDeCobertura;

class MuestraTestCase {
	Muestra muestraM;
    Opinion opM;
	Muestra muestra;
	Opinion op;
	AppWeb app;	
	Ubicacion ubi;
	Usuario user;
	Usuario user2;
	Opinion op1;
	Opinion op2;
	DescripcionOpinion descripcion1;
	DescripcionOpinion descripcion2;
	UsuarioBasico estadoBasico;
	Muestra muestraMock;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.openMocks(this);
		app = mock(AppWeb.class);
		ubi = mock(Ubicacion.class);
		user = mock(Usuario.class);
		user2 = mock(Usuario.class);
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
		
		muestra = new Muestra(ubi, descripcion1, user, "foto");
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
		assertEquals(1, muestra.getOpinionesUsuarios().size());
	}

	
	@Test
	void testMuestraConoceSuEspecieInicial() {
		assertEquals(descripcion1, muestra.getResultadoActual());
	}
	
	
	@Test
	void testLaFechaDeUnaMuestraCorrespondeConElDiaEnQueSeLaCreo() {
		assertEquals(muestra.getFechaDeEnvio(), LocalDate.now());
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

	@Test
	void testEnUnaMuestraPuedenOpinarUsuarios() {
		muestra.addOpinion(op);				//UNA OPINION ES HECHA POR UN USUARIO
		assertEquals(2, muestra.getOpinionesUsuarios().size());	
	}
	
	@Test
	void testUnaMuestraPasaAEstarVerificada() {
		muestra.seVerificaLaMuestra();
		assertTrue(muestra.getEstadoMuestra() instanceof MuestraVerificada);
		assertTrue(muestra.estaVerificada());
	}
	
	@Test
	void testMuestraConoceLaFechaDeLaUltimaOpinion() {
		when(op1.getFechaOpinion()).thenReturn(LocalDate.of(2025, 01, 01));
		when(op2.getFechaOpinion()).thenReturn(LocalDate.now());
		
		muestra.addOpinion(op1);
		muestra.addOpinion(op2);
		
		assertEquals(LocalDate.now(), muestra.getFechaUltimaVotacion());
	}
	
	@Test
	void testMuestraPuedeRecibirOpinionDeUnUsuarioBasicoSiNoOpinoNingunExperto() {
		MuestraAbierta estado = mock(MuestraAbierta.class);
		muestra.setEstadoMuestra(estado);
		
		muestra.recibirOpinionUsuarioBasico(op1);
		verify(estado, times(1)).recibirOpinionUsuarioBasico(op1);
	}
	
	@Test
	void testMuestraNoPuedeRecibirOpinionDeUnUsuarioBasicoSiOpinoUnExperto() {
		MuestraSoloExpertos estado = new MuestraSoloExpertos();
		muestra.setEstadoMuestra(estado);
		
		muestra.recibirOpinionUsuarioExperto(op1);
		//assertTrue(muestra.getOpinionesUsuarios().contains(op1));
		assertThrows(IllegalStateException.class, () -> {
		    muestra.recibirOpinionUsuarioBasico(op2);
		});
	}
	
	
	
	/*@Test
	void testUnaMuestraPuedeRecibirOpinionUsuarioBasico() {
		when(muestraM.getEstadoMuestra()).thenReturn(estadoMuestraMock);
		
		 verify(estadoMuestraMock, times(1)).recibirOpinionUsuarioBasico(op);

		 verify(muestraM, times(1)).getEstadoMuestra();
	}*/
	/*@Test
	void testSiUnUsuarioExpertoGeneraLaMuestraLosUsuariosBasicosNoPuedenOpinarSobreElla() {
		userBasico2.setEstadoUsuario(new UsuarioExperto());	//ahora es experto
		userBasico2.enviarMuestra(app, userBasico, ubi, descripcion1, "foto");
		assertThrows(IllegalStateException.class, () -> {
			userBasico.darOpinion(op2);
	    });
	}
	
	@Test
	void testNoSePuedeOpinarSobreMuestrasVerificadas() {
			this.muestra.addOpinion(this.op);
			this.muestra.addOpinion(this.op);
			
	}
	
		public void recibirOpinionUsuarioBasico(Opinion o) {
		getEstadoMuestra().recibirOpinionUsuarioBasico(o);
	}
	
	public void recibirOpinionUsuarioExperto(Opinion o) { 
		getEstadoMuestra().recibirOpinionUsuarioExperto(o);
	}
	
	public void borrarOpiniones() { // se llama cuando cambia a estado soloExpertos, para borrar las opiniones de los u.basicos
		this.opinionesUsuarios.removeAll(opinionesUsuarios);
	}

	public void setOpinionesUsuarios(ArrayList<Opinion> opinionesUsuarios) {
		this.opinionesUsuarios = opinionesUsuarios;
	}

	public void agregarObservador(ObserverMuestra observer) {
        if (!observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    public void removerObservador(ObserverMuestra observer) {
        this.observers.remove(observer);
    }
    
	public LocalDate getFechaUltimaVotacion() {
		return getOpinionesUsuarios().stream()
				 .map(o -> o.getFechaOpinion())				 
			     .max(LocalDate::compareTo)
			     .orElse(null);
	}
	
	*/
	
	
	
	/* ANTES SE HACIA DESDE APP
	 * 
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
	void testLaAppLeAvisaALasOrganizacionesCorrespondientesCuandoSeCargaUnaNuevaMuestra() {
		//añado zona y organizacion a la app
		Set<ZonaDeCobertura> zonasNuevas = new HashSet<>(List.of(zona));
		app.setZonasDeCobertura(zonasNuevas);
		app.registrarOrganizacion(organizacion);
		
		//llega la muestra a la app, entonces verifico que la organizacion ejecute FE
		app.recibirMuestra(muestra1);	
		verify(organizacion, times(1)).useFENuevaMuestra(zona, muestra1);
	}
	
	
	*/
}


