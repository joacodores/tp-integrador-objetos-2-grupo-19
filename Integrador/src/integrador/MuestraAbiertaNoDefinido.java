package integrador;

public class MuestraAbiertaNoDefinido extends MuestraNoDefinido{
	
	@Override
	public void recibirOpinionUsuarioBasico(Opinion o) {
		Muestra m = o.getMuestraEvaluada(); 
		m.addOpinion(o);
		if (!m.hayEmpate()) { // puede llegar a seguir estando en empate
			m.setEstadoMuestra(new MuestraAbierta());
		}
	}
	
	@Override
	public void recibirOpinionUsuarioExperto(Opinion o) {
		Muestra m = o.getMuestraEvaluada();
		m.addOpinion(o);
		m.borrarOpiniones();
		m.setEstadoMuestra(new MuestraSoloExpertos()); 
	}// si esta en muestra abierta quiere decir que todavia no opino ningun experto

}
