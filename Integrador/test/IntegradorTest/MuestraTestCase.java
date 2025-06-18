package IntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integrador.DescripcionOpinion;
import integrador.Muestra;
import integrador.MuestraSoloExpertos;
import integrador.Opinion;
import integrador.Ubicacion;
import integrador.Usuario;

class MuestraTestCase {
	
	Muestra muestra;
	Opinion op;
	
	@BeforeEach
	void setUp() {
		Usuario user = mock(Usuario.class);
		Ubicacion ubi = mock(Ubicacion.class);
		DescripcionOpinion descripcion = DescripcionOpinion.VINCHUCA_SORDIDA;
		muestra = new Muestra(ubi, descripcion, user, "foto");
		
	}

	@Test
	void noSePuedeOpinarSobreMuestrasVerificadas() {
			this.muestra.addOpinion(this.op);
			this.muestra.addOpinion(this.op);
			
	}

}
