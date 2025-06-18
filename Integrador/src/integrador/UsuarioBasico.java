package integrador;

import java.util.ArrayList;

public class UsuarioBasico implements NivelConocimiento {
	
	@Override
	public void darOpinion(Usuario u, Opinion o) throws Exception{	//agrego el throws Exception y muestra
		o.getMuestraEvaluada().recibirOpinionUsuarioBasico(o);
	}
	
	@Override
	public void enviarMuestra(AppWeb app, Usuario user, Ubicacion ubi, DescripcionOpinion especie, String foto) throws Exception {
		Muestra m = new Muestra(ubi, especie, user, foto, new ObserverPorMuestraVerificada(app));
		Opinion o = new Opinion(especie, m);
		m.recibirOpinionUsuarioBasico(o);
		user.addMuestraReportada(m);
		app.recibirMuestra(m);
	}

	@Override
	public void verificarCambioDeEstado(Usuario u) {
		if (u.muestrasDeLosUltimos30Dias().size() > 10 && 
			u.opinionesDeLosUltimos30Dias().size() > 20) {
			u.setEstadoUsuario(new UsuarioExperto());
		}
	}

}
