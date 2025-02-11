package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	private static Conexion instancia = null;
	private static Connection con;

	private Conexion() {
		String host = "127.0.0.1";
		String user = "root";
		String pass = "";
		String dtbs = "reto2";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs + "?user=" + user + "&password=" + pass;
			con = DriverManager.getConnection(newConnectionURL);

			if (con != null) {
				System.out.println("Conexión exitosa a la base de datos.");
			}
		} catch (Exception e) {
			System.out.println("Error al abrir la conexión: " + e.getMessage());
		}
	}

	public static Conexion getInstancia() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getCon() {
		return con;
	}

	public void cerrarConexion() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
				System.out.println("Conexión cerrada exitosamente.");
			}
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión: " + e.getMessage());
		}
	}

	public boolean verificarConexion() {
		try {
			if (con != null && !con.isClosed()) {
				Statement stmt = con.createStatement();
				stmt.executeQuery("SELECT 1");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error al verificar la conexión: " + e.getMessage());
		}
		return false;
	}
}
