package integrador;

import java.util.ArrayList;

public class UsuarioEspecialista implements NivelConocimiento {

	@Override
	public void darOpinion(Usuario u, Opinion o) throws Exception{	
		o.getMuestraEvaluada().recibirOpinionUsuarioExperto(o);
	}
	
	@Override
	public void enviarMuestra(Usuario user, Ubicacion ubi, DescripcionOpinion especie, String foto) throws Exception {
		Muestra m = new Muestra(ubi, especie, user, foto);
		Opinion o = new Opinion(especie, m);
		m.recibirOpinionUsuarioExperto(o);
		user.addMuestraReportada(m);
		ArrayList<ZonaDeCobertura> zonasDeCoberturaDeMuestra = m.getUbicacion().getZonasDeCobertura();
		zonasDeCoberturaDeMuestra.stream()
			.forEach(zona -> zona.addMuestraEnZona(m));
		//agregar a buscador de muestras(¿)
	}
	
	@Override
	public void verificarCambioDeEstado(Usuario u) { // no hace nada
	}

}
