package integrador;

public abstract class MuestraNoDefinido implements EstadoMuestra {

	@Override
	public abstract void recibirOpinionUsuarioBasico(Opinion o) throws Exception;

	@Override
	public abstract void recibirOpinionUsuarioExperto(Opinion o);
	
	@Override
	public DescripcionOpinion getResultadoActual(Muestra m) {
		return DescripcionOpinion.NO_DEFINIDO;
	}

}
