package integrador;

import java.util.ArrayList;

public class UsuarioBasico implements NivelConocimiento {
	
	@Override
	public void darOpinion(Usuario u, Opinion o) throws Exception{	//agrego el throws Exception y muestra
		o.getMuestraEvaluada().recibirOpinionUsuarioBasico(o);
		
	}
	
	@Override
	public void enviarMuestra(Usuario user, Ubicacion ubi, DescripcionOpinion especie, String foto) throws Exception {
		Muestra m = new Muestra(ubi, especie, user, foto);
		Opinion o = new Opinion(especie, m);
		m.recibirOpinionUsuarioBasico(o);
		user.addMuestraReportada(m);
		ArrayList<ZonaDeCobertura> zonasDeCoberturaDeMuestra = m.getUbicacion().getZonasDeCobertura();
		zonasDeCoberturaDeMuestra.stream()
			.forEach(zona -> zona.addMuestraEnZona(m));
		//agregar a buscador de muestras(¿)

	}

	@Override
	public void verificarCambioDeEstado(Usuario u) {
		if (u.muestrasDeLosUltimos30Dias().size() > 10 && 
			u.opinionesDeLosUltimos30Dias().size() > 20) {
			u.setEstadoUsuario(new UsuarioExperto());
		}
	}

}
