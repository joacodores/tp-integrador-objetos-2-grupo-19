package integrador.usuario;

import integrador.muestra.Muestra;

public interface NivelConocimiento {
	boolean puedeDarOpinion(Usuario u, Muestra m) ;

    void verificarCambioDeEstado(Usuario u);
    
    boolean esExperto();
}
