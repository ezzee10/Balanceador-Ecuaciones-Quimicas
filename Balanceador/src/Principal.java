public class Principal {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		BaseDeDatos db = new BaseDeDatos();
		PanelJuego panel = new PanelJuego(db);
		Ventana ventana = new Ventana(panel);
		Balanceo bl = new Balanceo(panel,db,ventana);
		
		
	}
}
