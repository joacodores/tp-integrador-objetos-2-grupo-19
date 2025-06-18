package integrador;

public interface EstadoMuestra {

	public DescripcionOpinion getResultadoActual(Muestra m);
	public void recibirOpinionUsuarioBasico(Opinion o);//tenia throws
	public void recibirOpinionUsuarioExperto(Opinion o); //tenia throws
	
}
