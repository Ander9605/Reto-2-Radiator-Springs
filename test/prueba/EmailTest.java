package prueba;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import conexion.Conexion;
import daoS.EmpleadoDAO;
import dtoS.EmpleadoDTO;

class EmailTest {

	private Connection con;
	private EmpleadoDAO empleadoDAO;
	private String testEmail = "test@email.com";

	@BeforeEach
	void setUp() throws SQLException {
		con = Conexion.getInstancia().getCon();
		empleadoDAO = new EmpleadoDAO();

		String insertQuery = "INSERT INTO empleados (id_empleado, nombre, apellido, email, contrasena, telefono, puesto, fecha_alta, activo, horas_totales) "
				+ "VALUES (999, 'Juan', 'Pérez', ?, '123456', '123456789', 'Desarrollador', NOW(), 1, 40.0)";
		try (PreparedStatement stmt = con.prepareStatement(insertQuery)) {
			stmt.setString(1, testEmail);
			stmt.executeUpdate();
		}
	}

	@Test
	void testBuscarPorEmail() {
		EmpleadoDTO resultado = empleadoDAO.buscarPorEmail(testEmail);

		assertNotNull(resultado, "El empleado no debería ser null");
		assertEquals(999, resultado.getIdEmpleado());
		assertEquals("Juan", resultado.getNombre());
		assertEquals("Pérez", resultado.getApellido());
		assertEquals(testEmail, resultado.getEmail());
	}

	@AfterEach
	void tearDown() throws SQLException {
		String deleteQuery = "DELETE FROM empleados WHERE id_empleado = 999";
		try (PreparedStatement stmt = con.prepareStatement(deleteQuery)) {
			stmt.executeUpdate();
		}
	}
}
