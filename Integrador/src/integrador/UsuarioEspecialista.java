package integrador;

public class UsuarioEspecialista implements NivelConocimiento {

	@Override
	public void darOpinion(Usuario u, Opinion o) throws Exception{	//agrego el throws Exception y muestra
		o.getMuestraEvaluada().recibirOpinionUsuarioExperto(o);
	}
	
	@Override
	public void enviarMuestra(Usuario u, Muestra m) {
		// enviarMuestra
	}
	
	@Override
	public void verificarCambioDeEstado(Usuario u) {
		//nunca deberia cambiar de estado.
	}

}
