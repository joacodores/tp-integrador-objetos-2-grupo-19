package integrador;

public class MuestraVerificada implements EstadoMuestra {

	@Override
	public void recibirOpinionUsuarioBasico(Opinion o){  //tenia throws
		throw new IllegalStateException("No se puede opinar más sobre esta muestra, esta verificada");
	}

	@Override
	public void recibirOpinionUsuarioExperto(Opinion o){ //tenia throws
		throw new IllegalStateException("No se puede opinar más sobre esta muestra, esta verificada");
	}

	@Override
	public DescripcionOpinion getResultadoActual(Muestra m) {
		return m.getOpinionMasVotada();

	}

}
