package dtoS;

import java.util.Objects;

public class EmpleadoDTO {
	private int idEmpleado;
	private String nombre;
	private String apellido;
	private String email;
	private String contrasena;
	private String telefono;
	private String puesto;
	private java.sql.Date fechaAlta;
	private boolean activo;
	private double horasTotales;

	/**
	 * CONSTRUCTOR DE EMPLEADO
	 * @param idEmpleado
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param contrasena
	 * @param telefono
	 * @param puesto
	 * @param fechaAlta
	 * @param activo
	 * @param horasTotales
	 */
	public EmpleadoDTO(int idEmpleado, String nombre, String apellido, String email, String contrasena, String telefono,
			String puesto, java.sql.Date fechaAlta, boolean activo, double horasTotales) {
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasena = contrasena;
		this.telefono = telefono;
		this.puesto = puesto;
		this.fechaAlta = fechaAlta;
		this.activo = activo;
		this.horasTotales = horasTotales;
	}

	public double getHorasTotales() {
		return horasTotales;
	}

	public void setHorasTotales(double horasTotales) {
		this.horasTotales = horasTotales;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public java.sql.Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(java.sql.Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "EmpleadoDTO{" + "idEmpleado=" + idEmpleado + ", nombre='" + nombre + '\'' + ", apellido='" + apellido
				+ '\'' + ", email='" + email + '\'' + ", contrasena='" + contrasena + '\'' + ", telefono='" + telefono
				+ '\'' + ", puesto='" + puesto + '\'' + ", fechaAlta=" + fechaAlta + ", activo=" + activo
				+ ", horasTotales=" + horasTotales + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EmpleadoDTO that = (EmpleadoDTO) o;
		return idEmpleado == that.idEmpleado && activo == that.activo
				&& Double.compare(that.horasTotales, horasTotales) == 0 && Objects.equals(nombre, that.nombre)
				&& Objects.equals(apellido, that.apellido) && Objects.equals(email, that.email)
				&& Objects.equals(contrasena, that.contrasena) && Objects.equals(telefono, that.telefono)
				&& Objects.equals(puesto, that.puesto) && Objects.equals(fechaAlta, that.fechaAlta);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEmpleado, nombre, apellido, email, contrasena, telefono, puesto, fechaAlta, activo,
				horasTotales);
	}
}
