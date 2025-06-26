package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.ubicacion.Ubicacion;

class UbicacionTestCase {
	
	Ubicacion ubi1;
	Ubicacion ubi2;
	Ubicacion ubi3;
	Ubicacion ubi4;
	
	@BeforeEach
	void setUp() throws Exception {
		
		ubi1 = new Ubicacion(10d, 20d);	
		ubi2 = new Ubicacion(0d, 0d);		//distancia de ubi1 = 2476.17
		ubi3 = new Ubicacion(10d, 10d);		//distancia de ubi1 = 1095.01
		ubi4 = new Ubicacion(100d, 100d);	//distancia de ubi1 = 9104.21
	}

	@Test
	void testUnaUbicacionConoceSuLatitud() {
		assertEquals(10d, this.ubi1.getLatitud());
	}
	
	@Test
	void testUnaUbicacionPuedeCambiarSuLatitud() {
		this.ubi1.setLatitud(100d);
		assertEquals(100d, this.ubi1.getLatitud());
	}
	
	@Test
	void testUnaUbicacionConoceSuLongitud() {
		assertEquals(20d, this.ubi1.getLongitud());
	}
	
	@Test
	void testUnaUbicacionPuedeCambiarSuLongitud() {
		this.ubi1.setLongitud(100d);
		assertEquals(100d, this.ubi1.getLongitud());
	}
    
	@Test
	void testLaDistanciaEntreUnaUbicacionYEllaMismaEsDe0() {
		assertEquals(0, this.ubi1.distanciaHastaEnKm(ubi1));
	}
	
	@Test
	void testUnaUbicacionCalculaLaDistanciaEnKMEntreEllaYOtraUbicacion() {
		assertEquals(2476.17, this.ubi1.distanciaHastaEnKm(ubi2));
	}
	
	@Test
	void testUnaUbicacionSabeQueOtrasUbicacionesEstanAMenosDe5000KM() {
		//todas las ubicaciones que quiero revisar
		ArrayList<Ubicacion> ubicaciones = new ArrayList<>(Arrays.asList(ubi2, ubi3, ubi4));
		ArrayList<Ubicacion> ubicacionesFiltradas = new ArrayList<>(Arrays.asList(ubi2, ubi3));
		
		assertEquals(ubicacionesFiltradas, this.ubi1.ubicacionesAMenosDe(ubicaciones, 5000d));
	}
}
