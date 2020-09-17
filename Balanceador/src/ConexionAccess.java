import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConexionAccess {
	
	static Connection con = null;
	static String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
	static String url = "jdbc:ucanaccess://base\\Combinaciones.accdb";
	
	//static String url = "jdbc:ucanaccess://C:\\Users\\homer\\Desktop\\Quimica\\Combinaciones.accdb";
	
	public static Connection obtenerConexion() {
		try {
			if(con == null) {
				Class.forName(driver);
				con = DriverManager.getConnection(url);
				//JOptionPane.showMessageDialog(null, "Conexion correcta");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			con = null;
		}
		return con;
	}	
}
