import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelJuego extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton check;
	private static JLabel etiquetasG[] = new JLabel[4];
	private JLabel etiquetas[][] = new JLabel[4][7];
	private JLabel recuadro1, recuadro2, flecha1,signo1, signo2;
	private JButton botones[][] = new JButton[4][2];
	private JLabel elementos[] = new JLabel[12];
	private JLabel atomos[] = new JLabel[12];
	private BaseDeDatos base;
	private Font fuente1 = new Font("Arial", 60, 45);
	private Font fuente2 = new Font("Arial", 60, 20);
	private Font fuente3 = new Font("Arial", 3, 20);

	public PanelJuego(BaseDeDatos db) {

		base = db;
		inicializarPanelJuego();
	}

	public void inicializarPanelJuego() {

		setLayout(null);
		setSize(1000, 1000);
		setBackground(Color.CYAN);

		// CREO EL ARRAY CON LAS ETIQUETAS Y LES ASIGNO LOS ATOMOS QUE SUBEN Y BAJAN
		// (MAXIMO 7)

		int contador1 = 0;
		for (int i = 0; i < 4; i++) {
			int contador2 = 0;
			for (int j = 0; j < 7; j++) {
				etiquetas[i][j] = new JLabel();
				etiquetas[i][j].setSize(35, 35);
				etiquetas[i][j].setLocation(40 + contador1, 505 - contador2);
				etiquetas[i][j].setIcon(new ImageIcon("imagenes/bola.jpg"));
				etiquetas[i][j].setVisible(false);
				add(etiquetas[i][j], 0);
				contador2 += 70;
			}
			if (i != 1) {
				contador1 += 310;
			} else {
				contador1 += 380;
			}
		}

		// CREO EL ARRAY CON BOTONES PARA SUBIR Y BAJAR

		int contador3 = 0;
		for (int i = 0; i < 4; i++) {
			int contador4 = 0;
			for (int j = 0; j < 2; j++) {
				botones[i][j] = new JButton();
				botones[i][j].setSize(90, 20);
				botones[i][j].setLocation(20 + contador3, 690 - contador4);
				botones[i][j].setVisible(true);
				add(botones[i][j], 0);
				contador4 += 130;
			}
			if (i != 1) {
				contador3 += 310;
			} else {
				contador3 += 380;
			}
		}

		// CREO ARRAY CON ETIQUETAS GRANDES

		int contador5 = 0;
		for (int i = 0; i < 4; i++) {
			etiquetasG[i] = new JLabel("0", SwingConstants.CENTER);
			etiquetasG[i].setOpaque(true);
			etiquetasG[i].setFont(fuente3);
			etiquetasG[i].setBackground(Color.GRAY);
			etiquetasG[i].setSize(90, 70);
			etiquetasG[i].setLocation(20 + contador5, 600);
			etiquetasG[i].setVisible(true);
			add(etiquetasG[i], 0);
			if (i != 1) {
				contador5 += 310;
			} else {
				contador5 += 380;
			}
		}

		// CREO ARRAY CON ETIQUETAS PARA LAS LETRAS DE LOS ELEMENTOS Y LOS ATOMOS DE
		// CADA ELEMENTO
		int contador6 = 0;
		for (int i = 0; i < 12; i++) {
			atomos[i] = new JLabel();
			elementos[i] = new JLabel();
			atomos[i].setFont(fuente2);
			elementos[i].setFont(fuente1);
			elementos[i].setBounds(110 + contador6, 600, 90, 70);
			atomos[i].setBounds(140 + contador6, 650, 20, 20);
			add(atomos[i], 0);
			add(elementos[i], 0);
			if (i != 2 && i != 5 && i != 8) {
				contador6 += 60;
			} else if (i == 2 || i == 8) {
				contador6 += 190;
			} else {
				contador6 += 260;
			}
		}

		botonesAlaEscucha();
		varios();
		asignarElemYatm();
	}

	// PONGO A LA ESCUCHA LOS BOTONES (NO CONSEGUI HACERLO GENERICO DEBIDO A QUE
	// ESTA ENCAPSULADO)
	public void botonesAlaEscucha() {

		botones[0][0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				if (getValorEtiqueta(0) == 1 && getValorEtiqueta(1) == 0 && getValorEtiqueta(2) == 0
						&& getValorEtiqueta(3) == 0) {
					check.setEnabled(false);
				}

				int numero = Integer.parseInt(etiquetasG[0].getText());
				if (numero != 0) {
					numero--;
					String palabra = String.valueOf(numero);
					etiquetasG[0].setText(palabra);
					etiquetas[0][numero].setVisible(false);
				}
			}
		});

		botones[0][1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				check.setEnabled(true);
				int numero = Integer.parseInt(etiquetasG[0].getText());
				if (numero != 7) {
					numero++;
					String palabra = String.valueOf(numero);
					etiquetasG[0].setText(palabra);
					etiquetas[0][numero - 1].setVisible(true);
				}
			}
		});

		botones[1][0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				if (getValorEtiqueta(0) == 0 && getValorEtiqueta(1) == 1 && getValorEtiqueta(2) == 0
						&& getValorEtiqueta(3) == 0) {
					check.setEnabled(false);
				}

				int numero = Integer.parseInt(etiquetasG[1].getText());
				if (numero != 0) {
					numero--;
					String palabra = String.valueOf(numero);
					etiquetasG[1].setText(palabra);
					etiquetas[1][numero].setVisible(false);
				}
			}
		});

		botones[1][1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				check.setEnabled(true);
				int numero = Integer.parseInt(etiquetasG[1].getText());
				if (numero != 7) {
					numero++;
					String palabra = String.valueOf(numero);
					etiquetasG[1].setText(palabra);
					etiquetas[1][numero - 1].setVisible(true);
				}
			}
		});

		botones[2][0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				if (getValorEtiqueta(0) == 0 && getValorEtiqueta(1) == 0 && getValorEtiqueta(2) == 1
						&& getValorEtiqueta(3) == 0) {
					check.setEnabled(false);
				}

				int numero = Integer.parseInt(etiquetasG[2].getText());
				if (numero != 0) {
					numero--;
					String palabra = String.valueOf(numero);
					etiquetasG[2].setText(palabra);
					etiquetas[2][numero].setVisible(false);
				}
			}
		});

		botones[2][1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				check.setEnabled(true);
				int numero = Integer.parseInt(etiquetasG[2].getText());
				if (numero != 7) {
					numero++;
					String palabra = String.valueOf(numero);
					etiquetasG[2].setText(palabra);
					etiquetas[2][numero - 1].setVisible(true);
				}
			}
		});

		botones[3][0].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				if (getValorEtiqueta(0) == 0 && getValorEtiqueta(1) == 0 && getValorEtiqueta(2) == 0
						&& getValorEtiqueta(3) == 1) {
					check.setEnabled(false);
				}

				int numero = Integer.parseInt(etiquetasG[3].getText());
				if (numero != 0) {
					numero--;
					String palabra = String.valueOf(numero);
					etiquetasG[3].setText(palabra);
					etiquetas[3][numero].setVisible(false);
				}
			}
		});

		botones[3][1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				check.setEnabled(true);
				int numero = Integer.parseInt(etiquetasG[3].getText());
				if (numero != 7) {
					numero++;
					String palabra = String.valueOf(numero);
					etiquetasG[3].setText(palabra);
					etiquetas[3][numero - 1].setVisible(true);
				}
			}
		});

	}

	// INSERTO LOS RECUADROS, FLECHAS, SIGNO + Y BOTON CHECK
	public void varios() {

		String guion = "-";
		signo1 = new JLabel();
		signo2 = new JLabel();
		recuadro1 = new JLabel();
		recuadro2 = new JLabel();
		flecha1 = new JLabel();
		check = new JButton("COMPROBAR");
		check.setEnabled(false);
		check.setBackground(Color.YELLOW);
		check.setBounds(550, 400, 120, 50);
		check.setForeground(Color.RED);
		add(check, 0);

		recuadro1.setBounds(20, 50, 467, 502);
		recuadro2.setBounds(715, 50, 467, 502);
		flecha1.setBounds(530, 250, 161, 80);
		
		signo1.setBounds(292, 600, 50, 70);
		signo2.setBounds(981, 600, 50, 70);

		if (guion.equals(base.getDatos(3))) {
			signo1.setBounds(230, 600, 50, 70);
		}
		if (guion.equals(base.getDatos(4))) {
			signo1.setBounds(260, 600, 50, 70);
		}
		if (guion.equals(base.getDatos(17))) {
			signo2.setBounds(930, 600, 50, 70);
		}if (guion.equals(base.getDatos(16))) {
			signo2.setBounds(890, 600, 50, 70);
		}

		add(recuadro1);
		add(recuadro2);
		add(flecha1, 0);
		add(signo1, 0);
		add(signo2, 0);

		recuadro1.setIcon(new ImageIcon("imagenes/Recuadro.png"));
		recuadro2.setIcon(new ImageIcon("imagenes/Recuadro.png"));
		flecha1.setIcon(new ImageIcon("imagenes/flecha.png"));
		signo1.setIcon(new ImageIcon("imagenes/signo.png"));
		signo2.setIcon(new ImageIcon("imagenes/signo.png"));

	}

	// ASIGNO LOS ELEMENTOS Y LOS ATOMOS A LAS ETIQUETAS
	public void asignarElemYatm() {

		String guion = "-";
		String cero = "0";
		String uno = "1";

		for (int i = 0; i < 3; i++) {
			if (!guion.equals(base.getDatos(i + 1))) { //ESTO LO HAGO PARA VER SI EL OBJETO NO ES IGUAL A UN "-" EN DICHO CASO LE ASIGNO EL ELEMENTO (PARA NO MOSTRAR - EN LA INTERFAZ)
				elementos[i].setText(base.getDatos(i + 1)); 
			}
				if(base.getDatos(i+1).length()>1) { //ESTO LO HAGO PARA QUE SI EL ELEMENTO TIENE 2 LETRAS ENTONCES QUE CORRA LA ETIQUETA DE LUGAR PARA QUE NO QUEDE EL ATOMO ANTES DEL ELEMENTO
					int coordenadaX = atomos[i].getX();
					atomos[i].setBounds(coordenadaX+21, 650, 20, 20);
				}
			if (!cero.equals(base.getDatos(i + 4)) /*&& !uno.equals(base.getDatos(i + 4))*/) { //ESTO LO HAGO PARA QUE SI EL ATOMO ES 0 Ó 1 NO MUESTRO NO LO MUESTRO EN LA INTERFAZ
				atomos[i].setText(base.getDatos(i + 4));
			}
		}
		
		
		for (int i = 3; i < 6; i++) {
			if (!guion.equals(base.getDatos(i + 5))) {
				elementos[i].setText(base.getDatos(i + 5));
			}
			if(base.getDatos(i+5).length()>1) { //ESTO LO HAGO PARA QUE SI EL ELEMENTO TIENE 2 LETRAS ENTONCES QUE CORRA LA ETIQUETA DE LUGAR PARA QUE NO QUEDE EL ATOMO ANTES DEL ELEMENTO
				int coordenadaX = atomos[i].getX();
				atomos[i].setBounds(coordenadaX+21, 653, 20, 20);
			}
			if (!cero.equals(base.getDatos(i + 8)) /*&& !uno.equals(base.getDatos(i + 8))*/) {
				atomos[i].setText(base.getDatos(i + 8));
			}
		}

		for (int i = 6; i < 9; i++) {
			if (!guion.equals(base.getDatos(i + 9))) {
				elementos[i].setText(base.getDatos(i + 9));
			}
			if(base.getDatos(i+9).length()>1) { //ESTO LO HAGO PARA QUE SI EL ELEMENTO TIENE 2 LETRAS ENTONCES QUE CORRA LA ETIQUETA DE LUGAR PARA QUE NO QUEDE EL ATOMO ANTES DEL ELEMENTO
				int coordenadaX = atomos[i].getX();
				atomos[i].setBounds(coordenadaX+21, 650, 20, 20);
			}		
			if (!cero.equals(base.getDatos(i + 12)) /* && !uno.equals(base.getDatos(i + 12))*/) {
				atomos[i].setText(base.getDatos(i + 12));
			}
		}
		
		
		for (int i = 9; i < 12; i++) {
			if (!guion.equals(base.getDatos(i + 13))) {
				elementos[i].setText(base.getDatos(i + 13));
			}
			if(base.getDatos(i+13).length()>1) { //ESTO LO HAGO PARA QUE SI EL ELEMENTO TIENE 2 LETRAS ENTONCES QUE CORRA LA ETIQUETA DE LUGAR PARA QUE NO QUEDE EL ATOMO ANTES DEL ELEMENTO
				int coordenadaX = atomos[i].getX();
				atomos[i].setBounds(coordenadaX+21, 650, 20, 20);
			}
			if (!cero.equals(base.getDatos(i + 16)) /*&& !uno.equals(base.getDatos(i + 16))*/) {
				atomos[i].setText(base.getDatos(i + 16));
			}
		}
	}

	// METODO PARA REINICIAR EL ARRAY, PARA LUEGO GENERAR UNA NUEVA COMBINACIÓN Y
	// ASIGNAR DE NUEVO
	public void reiniciarArrayElementosYAtomos() {
		for (int i = 0; i < elementos.length; i++) {
			elementos[i].setText(" ");
			atomos[i].setText(" ");
		}
	}

	// METODO PARA REINICIAR LOS CONTADORES Y DEJARLOS EN 0
	public void reiniciarContadores() {
		for (int i = 0; i < etiquetasG.length; i++) {
			etiquetasG[i].setText("0");
		}
	}

	public void reiniciarImagenesAtomos() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 7; j++) {
				etiquetas[i][j].setVisible(false);
			}
		}
	}

	// RETORNO EL BOTON CHECK PARA PODER USARLO EN BALANCEO
	public JButton getObtenerBoton() {
		return check;

	}

	// GETTERS DE LAS ETIQUETAS GRANDES
	public int getValorEtiqueta(int posicion) {
		return Integer.parseInt(etiquetasG[posicion].getText());
	}

}
