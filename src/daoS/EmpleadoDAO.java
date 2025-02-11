package daoS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexion.Conexion;
import dtoS.EmpleadoDTO;

public class EmpleadoDAO implements Patron_DAO<EmpleadoDTO> {
	private static final String SQL_INSERT = "INSERT INTO empleados (id_empleado, nombre, apellido, email, contrasena, telefono, puesto, fecha_alta, activo, horas_totales) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM empleados WHERE id_empleado = ?";
	private static final String SQL_UPDATE = "UPDATE empleados SET nombre = ?, apellido = ?, email = ?, contrasena = ?, telefono = ?, puesto = ?, fecha_alta = ?, activo = ?, horas_totales = ? "
			+ "WHERE id_empleado = ?";
	private static final String SQL_SELECT = "SELECT * FROM empleados WHERE id_empleado = ?";
	private static final String SQL_SELECT_TODOS = "SELECT * FROM empleados";

	private Conexion con = Conexion.getInstancia();

	@Override
	public boolean insertar(EmpleadoDTO empleado) {
		try (PreparedStatement stmt = con.getCon().prepareStatement(SQL_INSERT)) {
			stmt.setInt(1, empleado.getIdEmpleado());
			stmt.setString(2, empleado.getNombre());
			stmt.setString(3, empleado.getApellido());
			stmt.setString(4, empleado.getEmail());
			stmt.setString(5, empleado.getContrasena());
			stmt.setString(6, empleado.getTelefono());
			stmt.setString(7, empleado.getPuesto());
			stmt.setDate(8, empleado.getFechaAlta());
			stmt.setBoolean(9, empleado.isActivo());
			stmt.setDouble(10, empleado.getHorasTotales());
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
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
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean actualizar(EmpleadoDTO empleado) {
		try (PreparedStatement stmt = con.getCon().prepareStatement(SQL_UPDATE)) {
			stmt.setString(1, empleado.getNombre());
			stmt.setString(2, empleado.getApellido());
			stmt.setString(3, empleado.getEmail());
			stmt.setString(4, empleado.getContrasena());
			stmt.setString(5, empleado.getTelefono());
			stmt.setString(6, empleado.getPuesto());
			stmt.setDate(7, empleado.getFechaAlta());
			stmt.setBoolean(8, empleado.isActivo());
			stmt.setDouble(9, empleado.getHorasTotales());
			stmt.setInt(10, empleado.getIdEmpleado());
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public EmpleadoDTO buscar(Object pk) {
		try (PreparedStatement stmt = con.getCon().prepareStatement(SQL_SELECT)) {
			stmt.setInt(1, (Integer) pk);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new EmpleadoDTO(rs.getInt("id_empleado"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("email"), rs.getString("contrasena"), rs.getString("telefono"),
						rs.getString("puesto"), rs.getDate("fecha_alta"), rs.getBoolean("activo"),
						rs.getDouble("horas_totales"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<EmpleadoDTO> listarTodos() {
		ArrayList<EmpleadoDTO> empleados = new ArrayList<>();
		try (PreparedStatement stmt = con.getCon().prepareStatement(SQL_SELECT_TODOS)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				empleados.add(new EmpleadoDTO(rs.getInt("id_empleado"), rs.getString("nombre"),
						rs.getString("apellido"), rs.getString("email"), rs.getString("contrasena"),
						rs.getString("telefono"), rs.getString("puesto"), rs.getDate("fecha_alta"),
						rs.getBoolean("activo"), rs.getDouble("horas_totales")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empleados;
	}

	/**
	 * OBTENER ID EMPLEADO
	 * @return
	 */
	public ArrayList<Integer> obtenerIdsEmpleados() {
		ArrayList<Integer> ids = new ArrayList<>();
		String query = "SELECT id_empleado FROM empleados ORDER BY id_empleado ASC";
		try (PreparedStatement stmt = con.getCon().prepareStatement(query)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ids.add(rs.getInt("id_empleado"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	/**
	 * OBTENER ID Y NOMBRE EMPLEADO
	 * @return
	 */
	public ArrayList<String[]> obtenerIdsYNombresEmpleados() {
	    ArrayList<String[]> empleados = new ArrayList<>();
	    String query = "SELECT id_empleado, nombre FROM empleados ORDER BY id_empleado ASC";
	    try (PreparedStatement stmt = con.getCon().prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String[] empleado = new String[2];
	            empleado[0] = String.valueOf(rs.getInt("id_empleado"));
	            empleado[1] = rs.getString("nombre");
	            empleados.add(empleado);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return empleados;
	}

	/**
	 * BUSCAR POR EMAIL
	 * @param email
	 * @return
	 */
	public EmpleadoDTO buscarPorEmail(String email) {
		String query = "SELECT * FROM empleados WHERE email = ?";
		try (PreparedStatement stmt = con.getCon().prepareStatement(query)) {
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new EmpleadoDTO(rs.getInt("id_empleado"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("email"), rs.getString("contrasena"), rs.getString("telefono"),
						rs.getString("puesto"), rs.getDate("fecha_alta"), rs.getBoolean("activo"),
						rs.getDouble("horas_totales"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * OBTENER SIGUIENTE ID
	 * @return
	 */
	public int obtenerSiguienteId() {
	    int siguienteId = 1;
	    String query = "SELECT MAX(id_empleado) + 1 FROM empleados";
	    try (PreparedStatement stmt = con.getCon().prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next() && rs.getObject(1) != null) {
	            siguienteId = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return siguienteId;
	}
}
