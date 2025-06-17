package integrador;

public class UsuarioExperto implements NivelConocimiento {
	
	@Override
	public void darOpinion(Usuario u, Opinion o) throws Exception { //agrego el throws Exception y muestra
		verificarCambioDeEstado(u);
		o.getMuestraEvaluada().recibirOpinionUsuarioExperto(o);
	}
	
	@Override
	public void enviarMuestra(Usuario u, Muestra m) {
		verificarCambioDeEstado(u);
		// enviarMuestra
	}
	
	@Override
	public void verificarCambioDeEstado(Usuario u) {
		if (u.muestrasDeLosUltimos30Dias().size() <= 10 && 
			u.opinionesDeLosUltimos30Dias().size() <= 20) {
			u.setEstadoUsuario(new UsuarioBasico());
		}
	}

}
