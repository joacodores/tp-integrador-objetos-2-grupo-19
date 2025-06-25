package integrador.organizacion;

import integrador.ZonaDeCobertura;
import integrador.muestra.Muestra;

public interface FuncionalidadExterna {
	public void nuevoEvento(Organizacion o, ZonaDeCobertura z, Muestra m);
}
