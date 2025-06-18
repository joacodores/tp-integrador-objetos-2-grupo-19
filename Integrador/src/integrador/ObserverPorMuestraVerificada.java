package integrador;

public class ObserverPorMuestraVerificada implements ObserverMuestra {
	///Una muestra tiene un observer de estos y les manda el msj muestraVerificada cuando se verifica y asi sabe la app
	private AppWeb app;
	
	public ObserverPorMuestraVerificada(AppWeb app) {
	        this.app = app;
	}
	

	public AppWeb getApp() {
		return app;
	}

	public void setApp(AppWeb app) {
		this.app = app;
	}
	public void muestraVerficada(Muestra m) {
		getApp().nuevaMuestraVerificada(m);
	}

}
