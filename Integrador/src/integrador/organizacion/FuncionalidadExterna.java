package integrador.organizacion;

import integrador.muestra.Muestra;
import integrador.zonaDeCobertura.ZonaDeCobertura;

public interface FuncionalidadExterna {
	public void nuevoEvento(Organizacion o, ZonaDeCobertura z, Muestra m);
}
