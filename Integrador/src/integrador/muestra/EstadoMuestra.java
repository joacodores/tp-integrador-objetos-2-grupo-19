package integrador.muestra;

import integrador.opinion.DescripcionOpinion;
import integrador.opinion.Opinion;

public interface EstadoMuestra {

	public DescripcionOpinion getResultadoActual(Muestra m);
	public void recibirOpinionUsuarioBasico(Opinion o);
	public void recibirOpinionUsuarioExperto(Opinion o); 
	
}
