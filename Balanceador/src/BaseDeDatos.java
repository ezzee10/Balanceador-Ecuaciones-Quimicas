import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class BaseDeDatos {

	static Connection con = null;

	private String dts[] = new String[28];

	public BaseDeDatos() {
		
		generoBase();
		
	}

	public void generoBase() {

		try {
			con = ConexionAccess.obtenerConexion();
			Random aleatorio = new Random();
			int numero = aleatorio.nextInt(50) + 1;
			String sql = "SELECT * FROM primerReactivo R1,segundoReactivo R2,primerProducto P1,segundoProducto P2 WHERE R1.ID=R2.ID AND P1.ID=P2.ID AND R1.ID=P1.ID AND R1.ID="
					+ numero;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				for (int i = 0; i < 28; i++) {
					dts[i] = rs.getString(i + 1);
				}

				System.out.println("Primer Reactivo " + "ID: " + dts[0] + " PrimerElemento: " + dts[1]
						+ " SegundoElemento: " + dts[2] + " TercerElemento: " + dts[3] + " CantAtomo:" + dts[4]
						+ " CantAtomo2: " + dts[5] + " CantAtomo3: " + dts[6] + "\n");

				System.out.println("Segundo Reactivo " + "ID: " + dts[7] + " PrimerElemento: " + dts[8]
						+ " SegundoElemento: " + dts[9] + " TercerElemento: " + dts[10] + " CantAtomo:" + dts[11]
						+ " CantAtomo2: " + dts[12] + " CantAtomo3: " + dts[13] + "\n");

				System.out.println("Primer Producto " + "ID: " + dts[14] + " PrimerElemento: " + dts[15]
						+ " SegundoElemento: " + dts[16] + " TercerElemento: " + dts[17] + " CantAtomo:" + dts[18]
						+ " CantAtomo2: " + dts[19] + " CantAtomo3: " + dts[20] + "\n");

				System.out.println("Segundo Producto " + "ID: " + dts[21] + " PrimerElemento: " + dts[22]
						+ " SegundoElemento: " + dts[23] + " TercerElemento: " + dts[24] + " CantAtomo:" + dts[25]
						+ " CantAtomo2: " + dts[26] + " CantAtomo3: " + dts[27] + "\n");

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getDatos(int posicion) {
		return dts[posicion];
	}

	public int contarAtomos(String letra, int numero) {

		int cantidad = 0;

		switch (numero) {

		case 1:
			for (int i = 1; i < 3; i++) {
				if (letra.equals(dts[i])) {
					cantidad += Integer.parseUnsignedInt(dts[i + 3]);
				}
			}
			break;

		case 2:
			for (int i = 8; i < 11; i++) {
				if (letra.equals(dts[i])) {
					cantidad += Integer.parseUnsignedInt(dts[i + 3]);
				} else {
				}
			}
			break;

		case 3:

			for (int i = 15; i < 18; i++) {
				if (letra.equals(dts[i])) {
					cantidad += Integer.parseUnsignedInt(dts[i + 3]);
				}
			}
			break;

		case 4:

			for (int i = 22; i < 25; i++) {
				if (letra.equals(dts[i])) {
					cantidad += Integer.parseUnsignedInt(dts[i + 3]);
				}
			}
			break;

		}
		return cantidad;
	}
}
