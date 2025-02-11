package prueba;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import conexion.Conexion;
import daoS.HorarioDAO;

class HorasTotalesTest {

	private Connection con;
	private HorarioDAO horarioDAO;
	private int testEmpleadoId = 999;

	@BeforeEach
	void setUp() throws SQLException {
		con = Conexion.getInstancia().getCon();
		horarioDAO = new HorarioDAO();

		String insertEmpleado = "INSERT INTO empleados (id_empleado, nombre, apellido, email, contrasena, telefono, puesto, fecha_alta, activo, horas_totales) "
				+ "VALUES (?, 'Juan', 'Pérez', 'juan@example.com', '123456', '123456789', 'Desarrollador', NOW(), 1, 0)";
		try (PreparedStatement stmt = con.prepareStatement(insertEmpleado)) {
			stmt.setInt(1, testEmpleadoId);
			stmt.executeUpdate();
		}

		String insertHorario = "INSERT INTO horarios (id_empleado, fecha, hora_entrada, hora_salida) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = con.prepareStatement(insertHorario)) {
			stmt.setInt(1, testEmpleadoId);
			stmt.setDate(2, new Date(System.currentTimeMillis()));
			stmt.setTime(3, Time.valueOf("09:00:00"));
			stmt.setTime(4, Time.valueOf("14:00:00"));
			stmt.executeUpdate();

			stmt.setInt(1, testEmpleadoId);
			stmt.setDate(2, new Date(System.currentTimeMillis()));
			stmt.setTime(3, Time.valueOf("15:00:00"));
			stmt.setTime(4, Time.valueOf("18:30:00"));
			stmt.executeUpdate();
		}

	}

	@Test
	void testObtenerHorasTotales() {
		double horasTotales = horarioDAO.obtenerHorasTotales(testEmpleadoId);
		assertEquals(8.5, horasTotales, 0.01, "Las horas totales no coinciden");
	}

	@AfterEach
	void tearDown() throws SQLException {
		String deleteHorarios = "DELETE FROM horarios WHERE id_empleado = ?";
		try (PreparedStatement stmt = con.prepareStatement(deleteHorarios)) {
			stmt.setInt(1, testEmpleadoId);
			stmt.executeUpdate();
		}

		String deleteEmpleado = "DELETE FROM empleados WHERE id_empleado = ?";
		try (PreparedStatement stmt = con.prepareStatement(deleteEmpleado)) {
			stmt.setInt(1, testEmpleadoId);
			stmt.executeUpdate();
		}
	}
}
