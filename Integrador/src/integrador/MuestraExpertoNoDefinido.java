package integrador;

public class MuestraExpertoNoDefinido extends MuestraNoDefinido{
	/*Si la muestra esta en estado ExpertoNoDefinido, quiere decir que hay empate, 
	donde las posibles opiniones mas votadas, tienen 1 voto experto, ya que si 
	tendrian 2 estarian en estado verificadas */
	
	
	@Override
	public void recibirOpinionUsuarioBasico(Opinion o) { //tenia throws
		throw new IllegalStateException("Los usuarios basicos no pueden opinar mas sobre esta muestra");
	}

	@Override
	public void recibirOpinionUsuarioExperto(Opinion o) {
		Muestra m = o.getMuestraEvaluada();
		m.addOpinion(o);	
		if (!m.hayEmpate()) { // puede llegar a seguir estando en empate
			//si no hay mas empate, quiere decir que esta opinion hizo que haya 2 votos 
			//expertos hacia la misma especie, o sea que es la mas votada, por ende se verifica la muestra.
			m.verificarMuestra();
		}
	}
}
