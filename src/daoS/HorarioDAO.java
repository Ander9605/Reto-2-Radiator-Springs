package daoS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexion.Conexion;
import dtoS.HorarioDTO;

public class HorarioDAO implements Patron_DAO<HorarioDTO> {
	private static final String SQL_INSERT = "INSERT INTO horarios (id_empleado, fecha, hora_entrada, hora_salida) "
			+ "VALUES (?, ?, ?, ?)";

	private static final String SQL_DELETE = "DELETE FROM horarios WHERE id_horario = ?";
	private static final String SQL_UPDATE = "UPDATE horarios SET id_empleado = ?, fecha = ?, hora_entrada = ?, hora_salida = ?, horas_trabajadas = ? "
			+ "WHERE id_horario = ?";
	private static final String SQL_SELECT = "SELECT * FROM horarios WHERE id_horario = ?";
	private static final String SQL_SELECT_TODOS = "SELECT * FROM horarios";

	private Conexion con = Conexion.getInstancia();

	@Override
	public boolean insertar(HorarioDTO horario) {
		try (PreparedStatement stmt = con.getCon().prepareStatement(SQL_INSERT)) {
			stmt.setInt(1, horario.getIdEmpleado());
			stmt.setDate(2, horario.getFecha());
			stmt.setTime(3, horario.getHoraEntrada());
			stmt.setTime(4, horario.getHoraSalida());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (Exception e) {
			System.err.println("Error al insertar horario: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean borrar(Object pk) {
		try (PreparedStatement stmt = con.getCon().prepareStatement(SQL_DELETE)) {
			stmt.setInt(1, (Integer) pk);
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println("Error al borrar horario: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean actualizar(HorarioDTO horario) {
		try (PreparedStatement stmt = con.getCon().prepareStatement(SQL_UPDATE)) {
			stmt.setInt(1, horario.getIdEmpleado());
			stmt.setDate(2, horario.getFecha());
			stmt.setTime(3, horario.getHoraEntrada());
			stmt.setTime(4, horario.getHoraSalida());
			stmt.setDouble(5, horario.getHorasTrabajadas());
			stmt.setInt(6, horario.getIdHorario());
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			System.err.println("Error al actualizar horario: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public HorarioDTO buscar(Object pk) {
		try (PreparedStatement stmt = con.getCon().prepareStatement(SQL_SELECT)) {
			stmt.setInt(1, (Integer) pk);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new HorarioDTO(rs.getInt("id_horario"), rs.getInt("id_empleado"), rs.getDate("fecha"),
						rs.getTime("hora_entrada"), rs.getTime("hora_salida"), rs.getDouble("horas_trabajadas"));
			}
		} catch (Exception e) {
			System.err.println("Error al buscar horario: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<HorarioDTO> listarTodos() {
		ArrayList<HorarioDTO> horarios = new ArrayList<>();
		try (PreparedStatement stmt = con.getCon().prepareStatement(SQL_SELECT_TODOS)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				horarios.add(new HorarioDTO(rs.getInt("id_horario"), rs.getInt("id_empleado"), rs.getDate("fecha"),
						rs.getTime("hora_entrada"), rs.getTime("hora_salida"), rs.getDouble("horas_trabajadas")));
			}
		} catch (Exception e) {
			System.err.println("Error al listar horarios: " + e.getMessage());
			e.printStackTrace();
		}
		return horarios;
	}

	/**
	 * OBTENER HORAS TOTALES
	 * @param idEmpleado
	 * @return
	 */
	public double obtenerHorasTotales(int idEmpleado) {
		String query = "SELECT SUM(horas_trabajadas) FROM Horarios WHERE id_empleado = ?";
		try (PreparedStatement stmt = con.getCon().prepareStatement(query)) {
			stmt.setInt(1, idEmpleado);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
