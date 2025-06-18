package integrador;

import java.util.ArrayList;

public class Organizacion {
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

	public ArrayList<ZonaDeCobertura> getZonasRegistradas() {
		return zonasRegistradas;
	}

	public void setZonasRegistradas(ArrayList<ZonaDeCobertura> zonasRegistradas) {
		this.zonasRegistradas = zonasRegistradas;
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

	public void useFENuevaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		getFuncionalidadExternaPorNuevaMuestra().nuevoEvento(this, zonaDeCobertura, muestra);

	}

	public void useFEMuestraVerificada(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		getFuncionalidadExternaPorMuestraVerificada().nuevoEvento(this, zonaDeCobertura, muestra);
	}

	public boolean estaInteresadaEnZona(ZonaDeCobertura z) {
		return getZonasRegistradas().contains(z);
	}
	
	
	
}
