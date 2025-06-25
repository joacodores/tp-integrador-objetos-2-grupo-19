package integrador.muestra;

import integrador.opinion.DescripcionOpinion;
import integrador.opinion.Opinion;

public abstract class MuestraNoDefinido implements EstadoMuestra {

	@Override
	public abstract void recibirOpinionUsuarioBasico(Opinion o); //tenia throws

	@Override
	public abstract void recibirOpinionUsuarioExperto(Opinion o);
	
	@Override
	public DescripcionOpinion getResultadoActual(Muestra m) {
		return DescripcionOpinion.NO_DEFINIDO;
	}

}
