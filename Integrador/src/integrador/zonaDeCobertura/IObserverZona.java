package integrador.zonaDeCobertura;

import integrador.muestra.Muestra;

public interface IObserverZona {
	public void nuevaMuestraRegistrada(ZonaDeCobertura z, Muestra m);
	public void nuevaVerificacionMuestra(ZonaDeCobertura z, Muestra m); 
}
