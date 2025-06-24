package integrador;

public interface NivelConocimiento {
	boolean puedeDarOpinion(Usuario u, Muestra m) throws Exception;

	boolean puedeEnviarMuestra(Usuario u) throws Exception;

    void verificarCambioDeEstado(Usuario u);
    
    boolean esExperto();
}
