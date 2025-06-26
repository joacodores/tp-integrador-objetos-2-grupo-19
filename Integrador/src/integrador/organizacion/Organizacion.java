package integrador.organizacion;

import java.util.ArrayList;

import integrador.muestra.Muestra;
import integrador.ubicacion.Ubicacion;
import integrador.zonaDeCobertura.IObserverZona;
import integrador.zonaDeCobertura.ZonaDeCobertura;

public class Organizacion implements IObserverZona{
	public enum TipoDeOrganizacion {
		SALUD,
		EDUCATIVA,
		CULTURAL,
		ASISTENCIA,
	}
	private TipoDeOrganizacion tipoDeOrganizacion;
	private int cantDePersonasTrabajando;
	private Ubicacion ubicacion;
	private ArrayList<ZonaDeCobertura> zonasRegistradas;
	private FuncionalidadExterna funcionalidadExternaPorNuevaMuestra;
	private FuncionalidadExterna funcionalidadExternaPorMuestraVerificada;
	
	public Organizacion(TipoDeOrganizacion tipoDeOrganizacion, int cantDePersonasTrabajando, Ubicacion ubicacion,
			FuncionalidadExterna funcionalidadExternaPorNuevaMuestra,
			FuncionalidadExterna funcionalidadExternaPorMuestraVerificada) {
		super();
		this.tipoDeOrganizacion = tipoDeOrganizacion;
		this.cantDePersonasTrabajando = cantDePersonasTrabajando;
		this.ubicacion = ubicacion;
		this.zonasRegistradas = new ArrayList<ZonaDeCobertura>();
		this.funcionalidadExternaPorNuevaMuestra = funcionalidadExternaPorNuevaMuestra;
		this.funcionalidadExternaPorMuestraVerificada = funcionalidadExternaPorMuestraVerificada;
	}

	public TipoDeOrganizacion getTipoDeOrganizacion() {
		return tipoDeOrganizacion;
	}

	public void setTipoDeOrganizacion(TipoDeOrganizacion tipoDeOrganizacion) {
		this.tipoDeOrganizacion = tipoDeOrganizacion;
	}

	public int getCantDePersonasTrabajando() {
		return cantDePersonasTrabajando;
	}

	public void setCantDePersonasTrabajando(int cantDePersonasTrabajando) {
		this.cantDePersonasTrabajando = cantDePersonasTrabajando;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public void registrarseAZonaDeCobertura(ZonaDeCobertura z) {
		getZonasRegistradas().add(z);
		z.addObserver(this);
	}
	
	public ArrayList<ZonaDeCobertura> getZonasRegistradas() {
		return zonasRegistradas;
	}

	public FuncionalidadExterna getFuncionalidadExternaPorNuevaMuestra() {
		return funcionalidadExternaPorNuevaMuestra;
	}

	public void setFuncionalidadExternaPorNuevaMuestra(FuncionalidadExterna funcionalidadExternaPorNuevaMuestra) {
		this.funcionalidadExternaPorNuevaMuestra = funcionalidadExternaPorNuevaMuestra;
	}

	public FuncionalidadExterna getFuncionalidadExternaPorMuestraVerificada() {
		return funcionalidadExternaPorMuestraVerificada;
	}

	public void setFuncionalidadExternaPorMuestraVerificada(FuncionalidadExterna funcionalidadExternaPorMuestraVerificada) {
		this.funcionalidadExternaPorMuestraVerificada = funcionalidadExternaPorMuestraVerificada;
	}
	
	public void nuevaMuestraRegistrada(ZonaDeCobertura z, Muestra m) {
		this.useFENuevaMuestra(z, m);
	}
	public void nuevaVerificacionMuestra(ZonaDeCobertura z, Muestra m) {
		this.useFEMuestraVerificada(z, m);
	}
	
	public void useFENuevaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		getFuncionalidadExternaPorNuevaMuestra().nuevoEvento(this, zonaDeCobertura, muestra);
	}
	
	public void useFEMuestraVerificada(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		getFuncionalidadExternaPorMuestraVerificada().nuevoEvento(this, zonaDeCobertura, muestra);
	}
	
	
	
}
