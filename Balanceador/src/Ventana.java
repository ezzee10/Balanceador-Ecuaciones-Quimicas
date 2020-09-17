import javax.swing.JFrame;

public class Ventana extends JFrame{

	private static final long serialVersionUID = 1L;
	public Ventana(PanelJuego panel) {
		setTitle("Balanceo");
		setSize(1300, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		add(panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
}
