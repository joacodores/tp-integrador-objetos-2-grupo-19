package integrador;

public interface NivelConocimiento {
	//agrego el parametro usuario a todos
	public void darOpinion(Usuario u, Opinion o) throws Exception; //agrego el throws Exception
	
	public void enviarMuestra(Usuario u, Muestra m);
	
	public void verificarCambioDeEstado(Usuario u);
	//remplaza a 
	//public void setEstadoUsuario(Usuario u, NivelConocimiento estadoUsuario);
	
}
