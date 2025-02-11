package clases;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.sql.Time;
import java.time.LocalDate;
import javax.swing.border.LineBorder;
import daoS.HorarioDAO;
import dtoS.HorarioDTO;
import dtoS.EmpleadoDTO;

public class EmpleadoDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private HorarioDAO horarioDAO = new HorarioDAO();
	private LocalTime horaEntrada;
	private JPanel statusPanel;
	private JLabel statusLabel;
	private JLabel tiempoLabel;
	private Timer timer;
	private JButton ficharButton;
	private JButton salirButton;

	/**
	 * CONSTRUCTOR DE LA CLASE EMPLEADO
	 * @param parent
	 * @param empleado
	 */
	public EmpleadoDialog(JFrame parent, EmpleadoDTO empleado) {
		super(parent, "Empleado - " + empleado.getNombre(), true);
		setSize(400, 400);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(34, 34, 34));

		JLabel welcomeLabel = new JLabel("Bienvenido, " + empleado.getNombre());
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setBounds(0, 20, 386, 40);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(welcomeLabel);

		statusPanel = new JPanel();
		statusPanel.setLayout(null);
		statusPanel.setBounds(50, 80, 300, 100);
		statusPanel.setBackground(new Color(45, 45, 45));
		statusPanel.setBorder(new LineBorder(new Color(60, 60, 60), 1, true));

		statusLabel = new JLabel("NO EN SERVICIO");
		statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
		statusLabel.setForeground(new Color(255, 69, 0));
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(0, 20, 300, 30);
		statusPanel.add(statusLabel);

		tiempoLabel = new JLabel("--:--:--");
		tiempoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		tiempoLabel.setForeground(Color.WHITE);
		tiempoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tiempoLabel.setBounds(0, 50, 300, 30);
		statusPanel.add(tiempoLabel);

		getContentPane().add(statusPanel);

		ficharButton = new JButton("Fichar");
		ficharButton.setFont(new Font("Arial", Font.BOLD, 14));
		ficharButton.setBackground(new Color(0, 123, 255));
		ficharButton.setForeground(Color.WHITE);
		ficharButton.setBounds(46, 200, 120, 40);
		ficharButton.setFocusPainted(false);
		ficharButton.setBorderPainted(false);
		ficharButton.addActionListener(e -> fichar(empleado));
		getContentPane().add(ficharButton);

		salirButton = new JButton("Salir");
		salirButton.setFont(new Font("Arial", Font.BOLD, 14));
		salirButton.setBackground(new Color(0, 123, 255));
		salirButton.setForeground(Color.WHITE);
		salirButton.setBounds(223, 200, 120, 40);
		salirButton.setFocusPainted(false);
		salirButton.setBorderPainted(false);
		salirButton.setEnabled(false);
		salirButton.addActionListener(e -> salir(empleado));
		getContentPane().add(salirButton);

		JButton closeButton = new JButton("Cerrar");
		closeButton.setFont(new Font("Arial", Font.BOLD, 14));
		closeButton.setBackground(new Color(255, 69, 0));
		closeButton.setForeground(Color.WHITE);
		closeButton.setBounds(129, 260, 131, 40);
		closeButton.setFocusPainted(false);
		closeButton.setBorderPainted(false);
		closeButton.addActionListener(e -> {
			if (timer != null)
				timer.stop();
			dispose();
		});
		getContentPane().add(closeButton);

		setLocationRelativeTo(parent);
	}

	/**
	 * MÉTODO FICHAR
	 * @param empleado
	 */
	public void fichar(EmpleadoDTO empleado) {
		horaEntrada = LocalTime.now();

		statusLabel.setText("EN SERVICIO");
		statusLabel.setForeground(new Color(46, 204, 113));
		statusPanel.setBackground(new Color(45, 45, 45));
		ficharButton.setEnabled(false);
		salirButton.setEnabled(true);

		timer = new Timer(1000, e -> actualizarTiempoTranscurrido());
		timer.start();

		JOptionPane.showMessageDialog(this, "Has fichado a las: " + horaEntrada);
	}

	/**
	 * MÉTODO ACTUALIZAR TIEMPO
	 */
	public void actualizarTiempoTranscurrido() {
		if (horaEntrada != null) {
			LocalTime ahora = LocalTime.now();
			long segundos = ChronoUnit.SECONDS.between(horaEntrada, ahora);
			long horas = segundos / 3600;
			long minutos = (segundos % 3600) / 60;
			long segs = segundos % 60;
			tiempoLabel.setText(String.format("Tiempo: %02d:%02d:%02d", horas, minutos, segs));
		}
	}

	/**
	 * MÉTODO SALIR
	 * @param empleado
	 */
	public void salir(EmpleadoDTO empleado) {
		if (horaEntrada == null) {
			JOptionPane.showMessageDialog(this, "Debes fichar antes de registrar tu salida.");
			return;
		}

		if (timer != null) {
			timer.stop();
		}

		LocalTime horaSalida = LocalTime.now();
		long minutosTrabajados = ChronoUnit.MINUTES.between(horaEntrada, horaSalida);
		double horasTrabajadas = minutosTrabajados / 60.0;

		java.sql.Date fechaActual = java.sql.Date.valueOf(LocalDate.now());
		Time sqlHoraEntrada = Time.valueOf(horaEntrada);
		Time sqlHoraSalida = Time.valueOf(horaSalida);

		HorarioDTO nuevoHorario = new HorarioDTO(0, empleado.getIdEmpleado(), fechaActual, sqlHoraEntrada,
				sqlHoraSalida, horasTrabajadas);

		boolean exito = horarioDAO.insertar(nuevoHorario);
		if (exito) {
			double horasTotales = horarioDAO.obtenerHorasTotales(empleado.getIdEmpleado());
			empleado.setHorasTotales(horasTotales);
			JOptionPane.showMessageDialog(this,
					"Has salido a las: " + horaSalida + "\nHoras trabajadas en esta jornada: " + horasTrabajadas
							+ "\nTotal de horas trabajadas hasta ahora: " + horasTotales);
		} else {
			JOptionPane.showMessageDialog(this, "Error al registrar el horario. Intenta nuevamente.");
		}

		horaEntrada = null;
		statusLabel.setText("NO EN SERVICIO");
		statusLabel.setForeground(new Color(255, 69, 0));
		tiempoLabel.setText("--:--:--");
		ficharButton.setEnabled(true);
		salirButton.setEnabled(false);
	}
}