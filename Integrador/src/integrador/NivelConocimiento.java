package integrador;

public interface NivelConocimiento {
	boolean puedeDarOpinion(Usuario u, Muestra m) ;

    void verificarCambioDeEstado(Usuario u);
    
    boolean esExperto();
}
