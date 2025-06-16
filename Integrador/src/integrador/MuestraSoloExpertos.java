package integrador;

public class MuestraSoloExpertos implements EstadoMuestra {


	@Override
	public void recibirOpinionUsuarioBasico(Opinion o) throws Exception {
		throw new IllegalStateException("Los usuarios basicos no pueden opinar más sobre esta muestra");
	}

	@Override
	public void recibirOpinionUsuarioExperto(Opinion o) {
		Muestra m = o.getMuestraEvaluada();
		m.addOpinion(o);	
		if (m.hayEmpate()) { //si hay empate, indica que no habia un voto experto sobre esta especie
			m.setEstadoMuestra(new MuestraExpertoNoDefinido());
		}
		else{//si no hay empate indica que ya habia un voto igual, por ende ahora hay 2 y se verifica
			m.verificarMuestra();
		}
	}

	@Override
	public DescripcionOpinion getResultadoActual(Muestra m) {
		return m.getOpinionMasVotada();
		
	}

}
