package integrador;

public class UsuarioBasico implements NivelConocimiento {
	
	@Override
    public boolean puedeDarOpinion(Usuario u, Muestra m) throws Exception {
		if (m.getIdentificacion().equals(u) ||
	            u.getOpinionesEnviadas().stream().anyMatch(o -> o.getMuestraEvaluada().equals(m)) ||
	            m.estaVerificada()) {
	            return false;
	        }
	        return true;
    }

    @Override
    public boolean puedeEnviarMuestra(Usuario u) throws Exception {
        return true;
    }

    @Override
    public boolean esExperto() {
        return false;
    }

	@Override
	public void verificarCambioDeEstado(Usuario u) {
		if (u.muestrasDeLosUltimos30Dias().size() > 10 && 
			u.opinionesDeLosUltimos30Dias().size() > 20) {
			u.setEstadoUsuario(new UsuarioExperto());
		}
	}

}
