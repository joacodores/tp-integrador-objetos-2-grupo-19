package integrador.usuario;

import integrador.muestra.Muestra;

public class UsuarioExperto implements NivelConocimiento {
	
	@Override
    public boolean puedeDarOpinion(Usuario u, Muestra m){
		return !(m.getIdentificacion().equals(u)) && //revisar si hace falta, ya que si es su muestra tiene su propia opinion
	            !(u.getOpinionesEnviadas().stream().anyMatch(o -> o.getMuestraEvaluada().equals(m))) &&
	            !(m.estaVerificada());
    }

    @Override
    public boolean esExperto() {
        return true;
    }
	
	@Override
	public void verificarCambioDeEstado(Usuario u) {
		if (u.muestrasDeLosUltimos30Dias().size() <= 10 && 
			u.opinionesDeLosUltimos30Dias().size() <= 20) {
			u.setEstadoUsuario(new UsuarioBasico());
		}
	}

}
