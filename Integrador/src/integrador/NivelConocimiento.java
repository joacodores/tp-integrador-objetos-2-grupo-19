package integrador;

public interface NivelConocimiento {
	public void darOpinion(Opinion o);
	public void enviarMuestra(Muestra m);
	public void setEstadoUsuario(NivelConocimiento estadoUsuario);
}
