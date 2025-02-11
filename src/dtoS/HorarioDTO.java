package dtoS;

import java.sql.Time;
import java.sql.Date;
import java.util.Objects;

public class HorarioDTO {
	private int idHorario;
	private int idEmpleado;
	private Date fecha;
	private Time horaEntrada;
	private Time horaSalida;
	private double horasTrabajadas;

	/**
	 * CONSTRUCTOR DE HORARIO
	 * @param idHorario
	 * @param idEmpleado
	 * @param fecha
	 * @param horaEntrada
	 * @param horaSalida
	 * @param horasTrabajadas
	 */
	public HorarioDTO(int idHorario, int idEmpleado, Date fecha, Time horaEntrada, Time horaSalida,
			double horasTrabajadas) {
		this.idHorario = idHorario;
		this.idEmpleado = idEmpleado;
		this.fecha = fecha;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.horasTrabajadas = horasTrabajadas;
	}

	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Time horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Time getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Time horaSalida) {
		this.horaSalida = horaSalida;
	}

	public double getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(double horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	@Override
	public String toString() {
		return "HorarioDTO{" + "idHorario=" + idHorario + ", idEmpleado=" + idEmpleado + ", fecha=" + fecha
				+ ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", horasTrabajadas=" + horasTrabajadas
				+ '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		HorarioDTO that = (HorarioDTO) o;
		return idHorario == that.idHorario && idEmpleado == that.idEmpleado
				&& Double.compare(that.horasTrabajadas, horasTrabajadas) == 0 && Objects.equals(fecha, that.fecha)
				&& Objects.equals(horaEntrada, that.horaEntrada) && Objects.equals(horaSalida, that.horaSalida);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idHorario, idEmpleado, fecha, horaEntrada, horaSalida, horasTrabajadas);
	}
}
