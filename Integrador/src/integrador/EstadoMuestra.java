package integrador;

public interface EstadoMuestra {

	public DescripcionOpinion getResultadoActual(Muestra m);
	public void recibirOpinionUsuarioBasico(Opinion o) throws Exception;
	public void recibirOpinionUsuarioExperto(Opinion o) throws Exception;
	
}
