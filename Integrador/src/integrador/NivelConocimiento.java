package integrador;

public interface NivelConocimiento {
	boolean puedeDarOpinion(Usuario u, Muestra m) ;

	boolean puedeEnviarMuestra(Usuario u) ;

    void verificarCambioDeEstado(Usuario u);
    
    boolean esExperto();
}
