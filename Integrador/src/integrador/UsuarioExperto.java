package integrador;

import java.util.ArrayList;

public class UsuarioExperto implements NivelConocimiento {
	
	@Override
	public void darOpinion(Usuario u, Opinion o){ //tenia throws
		o.getMuestraEvaluada().recibirOpinionUsuarioExperto(o);
	}

	@Override
	public void enviarMuestra(AppWeb app, Usuario user, Ubicacion ubi, DescripcionOpinion especie, String foto){ //tenia throws
		Muestra m = new Muestra(ubi, especie, user, foto, new ObserverPorMuestraVerificada(app));
		Opinion o = new Opinion(especie, m);
		m.recibirOpinionUsuarioExperto(o);
		user.addMuestraReportada(m);
		app.recibirMuestra(m);
		user.addOpinion(o);		//la muestra cuenta como opinion inicial
	}
	
	@Override
	public void verificarCambioDeEstado(Usuario u) {
		if (u.muestrasDeLosUltimos30Dias().size() <= 10 && 
			u.opinionesDeLosUltimos30Dias().size() <= 20) {
			u.setEstadoUsuario(new UsuarioBasico());
		}
	}

}
