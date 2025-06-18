package integrador;

import java.util.ArrayList;

public class UsuarioEspecialista implements NivelConocimiento {

	@Override
	public void darOpinion(Usuario u, Opinion o) throws Exception{	
		o.getMuestraEvaluada().recibirOpinionUsuarioExperto(o);
	}
	
	@Override
	public void enviarMuestra(AppWeb app, Usuario user, Ubicacion ubi, DescripcionOpinion especie, String foto) throws Exception {
		Muestra m = new Muestra(ubi, especie, user, foto);
		Opinion o = new Opinion(especie, m);
		m.recibirOpinionUsuarioExperto(o);
		user.addMuestraReportada(m);
		app.recibirMuestra(m);
	}
	
	@Override
	public void verificarCambioDeEstado(Usuario u) { // no hace nada
	}

}
