import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelSecundario extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel etiq, correcta, incorrecta;
	private JButton boton1;
	private JButton boton2;
	private VentanaSecundaria window;
	private BaseDeDatos db;
	private Balanceo blc;
	private PanelJuego pnl;
	private Ventana vn;
	private Font fuente = new Font("Arial", 3, 15);
	
	public PanelSecundario(int balanceado, VentanaSecundaria window, BaseDeDatos db, Balanceo blc, PanelJuego pnl, Ventana vn) {

		inicioPanel2(balanceado);
		this.window = window;
		this.db = db;
		this.blc = blc;
		this.pnl = pnl;
		this.vn = vn;
	}
	
	public void inicioPanel2(int valor) {
		
		setVisible(true);
		setLayout(null);
		setSize(400, 400);
		setBackground(Color.YELLOW);
		etiq = new JLabel();
		
		
		if(valor == 0) {
			boton1 = new JButton("REINTENTAR");
			boton2 = new JButton("SALIR");
			incorrecta = new JLabel("ECUACION BALANCEADA INCORRECTAMENTE");
			incorrecta.setFont(fuente);
			incorrecta.setBounds(20,10,400,20);
			add(incorrecta,0);
			etiq.setBounds(50, 40, 300, 158);
			etiq.setIcon(new ImageIcon("imagenes/emoji.png"));
		}if(valor == 1) {
			boton1 = new JButton("SIGUIENTE");
			boton2 = new JButton("SALIR");
			correcta = new JLabel("ECUACION BALANCEADA CORRECTAMENTE");
			correcta.setBounds(20,10,400,20);
			correcta.setFont(fuente);
			add(correcta,0);
			etiq.setBounds(40, 30, 300, 158);
			etiq.setIcon(new ImageIcon("imagenes/emoji2.jpg"));
		}

		boton1.setBounds(100, 200, 180, 50);
		boton2.setBounds(100, 270, 180, 50);
		add(etiq,0);
		add(boton1,0);
		add(boton2,0);	
		
		boton1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
			
			if(valor ==1) {
				db.generoBase(); //genero una nueva combinacion
				blc.inicializarArrayLetras(); //reinicio el array que tengo almacenado los elementos viejos
				blc.reiniciarArrayValor(); //reinicio el array con los valores viejos
				pnl.reiniciarArrayElementosYAtomos(); //establezco un setText(" ") para los elementos viejos y atomos viejos (LO REINICIO) 
				pnl.reiniciarContadores(); //reinicio los contadores los establezco de nuevo en 0
				pnl.reiniciarImagenesAtomos(); //hago que las bolitas que suben y bajan queden en 0 nuevamente
				blc.almacenarValores(); //almaceno los nuevos elementos
				pnl.asignarElemYatm(); //asigno los elementos de nuevo al panel
				
			}
			vn.setEnabled(true);
			window.setVisible(false);
		
			}
		});	
		
		boton2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
			System.exit(0);
		
			}
		});	
		
	}
}
