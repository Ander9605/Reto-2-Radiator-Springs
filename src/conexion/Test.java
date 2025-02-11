package conexion;

public class Test {
	public static void main(String[] args) {
		Conexion conexion = Conexion.getInstancia();

		if (conexion.verificarConexion()) {
			System.out.println("La conexión es válida.");
		} else {
			System.out.println("Error en la conexión.");
		}

		conexion.cerrarConexion();
	}
}
