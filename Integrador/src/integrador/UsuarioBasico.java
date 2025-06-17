package integrador;

public class UsuarioBasico implements NivelConocimiento {
	
	@Override
	public void darOpinion(Usuario u, Opinion o) throws Exception{	//agrego el throws Exception y muestra
		o.getMuestraEvaluada().recibirOpinionUsuarioBasico(o);
		verificarCambioDeEstado(u);
	}
	
	@Override
	public void enviarMuestra(Usuario u, Muestra m) {
		// enviarMuestra
		verificarCambioDeEstado(u);
	}

	@Override
	public void verificarCambioDeEstado(Usuario u) {
		if (u.muestrasDeLosUltimos30Dias().size() > 10 && 
			u.opinionesDeLosUltimos30Dias().size() > 20) {
			u.setEstadoUsuario(new UsuarioExperto());
		}
	}

}
