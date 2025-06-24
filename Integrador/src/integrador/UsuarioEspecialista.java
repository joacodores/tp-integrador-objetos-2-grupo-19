package integrador;

public class UsuarioEspecialista implements NivelConocimiento {

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
        return true;
    }
	
	@Override
	public void verificarCambioDeEstado(Usuario u) { // no hace nada
	}

}
