package integrador;

public interface EstadoMuestra {

	public DescripcionOpinion getResultadoActual(Muestra m);
	public void recibirOpinionUsuarioBasico(Opinion o);
	public void recibirOpinionUsuarioExperto(Opinion o); 
	
}
