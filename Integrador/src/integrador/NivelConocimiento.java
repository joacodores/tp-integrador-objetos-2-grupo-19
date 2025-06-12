package integrador;

public interface NivelConocimiento {
	public void darOpinion(Muestra m, Opinion o);
	public void enviarMuestra(Muestra m);
	public void setEstadoUsuario(NivelConocimiento estadoUsuario);
}
