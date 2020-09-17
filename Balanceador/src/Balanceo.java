import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Balanceo {

	private BaseDeDatos db;
	private PanelJuego panel;
	private int balanceado = -1;
	private int[] valor = new int[21];
	private String[] letras = new String[6];
	private Ventana vn;

	public Balanceo(PanelJuego panel, BaseDeDatos db, Ventana vn) {

		this.panel = panel;
		this.db = db;
		this.vn = vn;
		ponerEscuchaBotonCheck();
		inicializarArrayLetras();
		almacenarValores();

	}
	
	public Balanceo getBalanceo() { // devuelvo la instancia de la clase para poder usarla en el nuevo panel
		return this;
	}

	public void ponerEscuchaBotonCheck() { //pongo a la escucha el boton check, el cual comprueba el balance y da paso a las nuevas ventanas
		panel.getObtenerBoton().addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			public void mousePressed(MouseEvent e) {

				comprobarBalanceo();
				if (balanceado == 0) {
					VentanaSecundaria ventana = new VentanaSecundaria(balanceado, db, getBalanceo(), panel, vn);
					vn.setEnabled(false);
					
				}else if(balanceado == 1) {
					VentanaSecundaria ventana = new VentanaSecundaria(balanceado, db, getBalanceo(), panel, vn);
					vn.setEnabled(false);
				}
			}
		});
	}	
	
	public int getBalanceado() { //devuelvo el valor 1 si esta balanceado 0 si no esta balanceado y -1 si todo esta en 0
		return balanceado;
	}

	public void inicializarArrayLetras() { //para que no queden las posiciones en null
		for (int i = 0; i < letras.length; i++) {
			letras[i] = ".";
		}
	}

	public void almacenarValores() { //almaceno los valores de las combinaciones para luego poder comparar los balanceos

		if (db.getDatos(1) != "-") {
			letras[0] = db.getDatos(1);
			valor[0] = db.contarAtomos(letras[0], 1);
			valor[1] = db.contarAtomos(letras[0], 2);
			valor[2] = db.contarAtomos(letras[0], 3);
			valor[3] = db.contarAtomos(letras[0], 4);
		}
		if (db.getDatos(2) != "-") {
			letras[1] = (db.getDatos(2));
			valor[4] = db.contarAtomos(letras[1], 1);
			valor[5] = db.contarAtomos(letras[1], 2);
			valor[6] = db.contarAtomos(letras[1], 3);
			valor[7] = db.contarAtomos(letras[1], 4);

		}
		if (db.getDatos(3) != "-") {
			letras[2] = (db.getDatos(3));
			valor[8] = db.contarAtomos(letras[2], 1);
			valor[9] = db.contarAtomos(letras[2], 2);
			valor[10] = db.contarAtomos(letras[2], 3);
			valor[11] = db.contarAtomos(letras[2], 4);
		}
		if (db.getDatos(8) != "-" && !letras[0].equals(db.getDatos(8)) && !letras[1].equals(db.getDatos(8))
				&& !letras[2].equals(db.getDatos(8))) {
			letras[3] = (db.getDatos(8));
			valor[12] = db.contarAtomos(letras[3], 2);
			valor[13] = db.contarAtomos(letras[3], 3);
			valor[14] = db.contarAtomos(letras[3], 4);
		}
		if (db.getDatos(9) != "-" && !letras[0].equals(db.getDatos(9)) && !letras[1].equals(db.getDatos(9))
				&& !letras[2].equals(db.getDatos(9)) && !letras[3].equals(db.getDatos(9))) {
			letras[4] = (db.getDatos(9));
			valor[15] = db.contarAtomos(letras[4], 2);
			valor[16] = db.contarAtomos(letras[4], 3);
			valor[17] = db.contarAtomos(letras[4], 4);
		}
		if (db.getDatos(10) != "-" && !letras[0].equals(db.getDatos(10)) && !letras[1].equals(db.getDatos(10))
				&& !letras[2].equals(db.getDatos(10)) && !letras[3].equals(db.getDatos(10))
				&& !letras[4].equals(db.getDatos(10))) {
			letras[5] = (db.getDatos(10));
			valor[18] = db.contarAtomos(letras[5], 2);
			valor[19] = db.contarAtomos(letras[5], 3);
			valor[20] = db.contarAtomos(letras[5], 4);
		}
	}

	public void comprobarBalanceo() { //compruebo los balanceo

		while (true) {
			if (panel.getValorEtiqueta(0) * valor[0]
					+ panel.getValorEtiqueta(1) * valor[1] != panel.getValorEtiqueta(2) * valor[2]
							+ panel.getValorEtiqueta(3) * valor[3]) {
				balanceado = 0;
				break;
			} else if (panel.getValorEtiqueta(0) * valor[4]
					+ panel.getValorEtiqueta(1) * valor[5] != panel.getValorEtiqueta(2) * valor[6]
							+ panel.getValorEtiqueta(3) * valor[7]) {
				balanceado = 0;
				break;
			} else if (panel.getValorEtiqueta(0) * valor[8]
					+ panel.getValorEtiqueta(1) * valor[9] != panel.getValorEtiqueta(2) * valor[10]
							+ panel.getValorEtiqueta(3) * valor[11]) {
				balanceado = 0;
				break;
			} else if (panel.getValorEtiqueta(1) * valor[12] != panel.getValorEtiqueta(2) * valor[13]
					+ panel.getValorEtiqueta(3) * valor[14]) {
				balanceado = 0;
				break;
			} else if (panel.getValorEtiqueta(1) * valor[15] != panel.getValorEtiqueta(2) * valor[16]
					+ panel.getValorEtiqueta(3) * valor[17]) {
				balanceado = 0;
				break;
			} else if (panel.getValorEtiqueta(1) * valor[18] != panel.getValorEtiqueta(2) * valor[19]
					+ panel.getValorEtiqueta(3) * valor[20]) {
				balanceado = 0;
				break;
			} else if (panel.getValorEtiqueta(0) == 0 && panel.getValorEtiqueta(1) == 0
					&& panel.getValorEtiqueta(2) == 0 && panel.getValorEtiqueta(3) == 0) {
				balanceado = -1;
				break;
			}

			balanceado = 1;
			break;
		}
	}
	
	public void reiniciarArrayValor() { //metodo para reiniciar array luego de apretar el boton "SIGUIENTE" y poder volver a llenar con datos nuevos
		for(int i=0; i<valor.length; i++) {
			valor[i] = 0;
		}
	}
}
