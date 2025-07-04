	package integrador.usuario;

import integrador.muestra.Muestra;

public class UsuarioEspecialista implements NivelConocimiento {

	@Override
    public boolean puedeDarOpinion(Usuario u, Muestra m)  {
		return !(m.getIdentificacion().equals(u)) && 
	            !(u.getOpinionesEnviadas().stream().anyMatch(o -> o.getMuestraEvaluada().equals(m))) &&
	            !(m.estaVerificada());
    }

    @Override
    public boolean esExperto() {
        return true;
    }
	
	@Override
	public void verificarCambioDeEstado(Usuario u) { // no hace nada
	}

}
