import javax.swing.JFrame;

public class VentanaSecundaria extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private PanelSecundario panel2;
	
	public VentanaSecundaria(int valor, BaseDeDatos db, Balanceo blc, PanelJuego panel, Ventana vn) {
		
		setTitle("");
		setSize(400, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		panel2 = new PanelSecundario(valor, this, db, blc, panel, vn);
		add(panel2);
		setVisible(true);
			
	}
}

