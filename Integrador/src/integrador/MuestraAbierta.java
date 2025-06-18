package integrador;

public class MuestraAbierta implements EstadoMuestra {
	
	public void recibirOpinionUsuarioBasico(Opinion o) {
		Muestra m = o.getMuestraEvaluada(); 
		m.addOpinion(o);
		if (m.hayEmpate()) { // si agregando esta opinion hay empate, pasa a ser no definido
			m.setEstadoMuestra(new MuestraAbiertaNoDefinido());
		}
	}
	
	public void recibirOpinionUsuarioExperto(Opinion o) {
		Muestra m = o.getMuestraEvaluada();
		m.addOpinion(o);	
		m.setEstadoMuestra(new MuestraSoloExpertos()); 
	}// si esta en muestra abierta quiere decir que todavia no opino ningun experto


	public DescripcionOpinion getResultadoActual(Muestra m) {
		return m.getOpinionMasVotada();
	}
}

