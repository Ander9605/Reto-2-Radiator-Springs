package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import daoS.EmpleadoDAO;
import dtoS.EmpleadoDTO;

public class AltaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * CONSTRUCTOR DE ALTA
	 * @param gerenteDialog
	 */
	public AltaDialog(GerenteDialog gerenteDialog) {
		super(gerenteDialog, "Alta de Empleado", true);

		EmpleadoDAO empleadoDAO = new EmpleadoDAO();

		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(34, 34, 34));

		Font labelFont = new Font("Arial", Font.PLAIN, 14);
		Color textColor = Color.WHITE;
		Color backgroundColor = new Color(50, 50, 50);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(labelFont);
		lblNombre.setForeground(textColor);
		lblNombre.setBounds(10, 20, 180, 31);
		JTextField txtNombre = new JTextField();
		txtNombre.setFont(labelFont);
		txtNombre.setForeground(textColor);
		txtNombre.setBackground(backgroundColor);
		txtNombre.setBounds(195, 20, 181, 31);
		txtNombre.setCaretColor(textColor);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(labelFont);
		lblApellido.setForeground(textColor);
		lblApellido.setBounds(10, 60, 180, 31);
		JTextField txtApellido = new JTextField();
		txtApellido.setFont(labelFont);
		txtApellido.setForeground(textColor);
		txtApellido.setBackground(backgroundColor);
		txtApellido.setBounds(195, 60, 181, 31);
		txtApellido.setCaretColor(textColor);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(labelFont);
		lblEmail.setForeground(textColor);
		lblEmail.setBounds(10, 100, 180, 31);
		JTextField txtEmail = new JTextField();
		txtEmail.setFont(labelFont);
		txtEmail.setForeground(textColor);
		txtEmail.setBackground(backgroundColor);
		txtEmail.setBounds(195, 100, 181, 31);
		txtEmail.setCaretColor(textColor);

		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(labelFont);
		lblContrasena.setForeground(textColor);
		lblContrasena.setBounds(10, 140, 180, 31);
		JTextField txtContrasena = new JTextField();
		txtContrasena.setFont(labelFont);
		txtContrasena.setForeground(textColor);
		txtContrasena.setBackground(backgroundColor);
		txtContrasena.setBounds(195, 140, 181, 31);
		txtContrasena.setCaretColor(textColor);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setFont(labelFont);
		lblTelefono.setForeground(textColor);
		lblTelefono.setBounds(10, 180, 180, 31);
		JTextField txtTelefono = new JTextField();
		txtTelefono.setFont(labelFont);
		txtTelefono.setForeground(textColor);
		txtTelefono.setBackground(backgroundColor);
		txtTelefono.setBounds(195, 180, 181, 31);
		txtTelefono.setCaretColor(textColor);

		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setFont(labelFont);
		lblPuesto.setForeground(textColor);
		lblPuesto.setBounds(10, 220, 180, 31);
		JTextField txtPuesto = new JTextField();
		txtPuesto.setFont(labelFont);
		txtPuesto.setForeground(textColor);
		txtPuesto.setBackground(backgroundColor);
		txtPuesto.setBounds(195, 220, 181, 31);
		txtPuesto.setCaretColor(textColor);

		JLabel lblActivo = new JLabel("Activo:");
		lblActivo.setFont(labelFont);
		lblActivo.setForeground(textColor);
		lblActivo.setBounds(10, 260, 180, 31);
		JCheckBox chkActivo = new JCheckBox();
		chkActivo.setBackground(new Color(34, 34, 34));
		chkActivo.setForeground(textColor);
		chkActivo.setBounds(195, 260, 190, 31);
		chkActivo.setSelected(true);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
		btnGuardar.setBackground(new Color(0, 123, 255));
		btnGuardar.setForeground(textColor);
		btnGuardar.setBounds(20, 300, 170, 25);
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBorderPainted(false);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancelar.setBackground(new Color(255, 69, 58));
		btnCancelar.setForeground(textColor);
		btnCancelar.setBounds(195, 300, 181, 25);
		btnCancelar.setFocusPainted(false);
		btnCancelar.setBorderPainted(false);

		getContentPane().add(lblNombre);
		getContentPane().add(txtNombre);
		getContentPane().add(lblApellido);
		getContentPane().add(txtApellido);
		getContentPane().add(lblEmail);
		getContentPane().add(txtEmail);
		getContentPane().add(lblContrasena);
		getContentPane().add(txtContrasena);
		getContentPane().add(lblTelefono);
		getContentPane().add(txtTelefono);
		getContentPane().add(lblPuesto);
		getContentPane().add(txtPuesto);
		getContentPane().add(lblActivo);
		getContentPane().add(chkActivo);
		getContentPane().add(btnGuardar);
		getContentPane().add(btnCancelar);

		btnGuardar.addActionListener(new ActionListener() {
			/**
			 * 
			 * @param e
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = txtNombre.getText().trim();
					String apellido = txtApellido.getText().trim();
					String email = txtEmail.getText().trim();
					String contrasena = txtContrasena.getText().trim();
					String telefono = txtTelefono.getText().trim();
					String puesto = txtPuesto.getText().trim();
					boolean activo = chkActivo.isSelected();

					if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || contrasena.isEmpty()
							|| telefono.isEmpty() || puesto.isEmpty()) {
						throw new Exception("Todos los campos son obligatorios");
					}

					int id = empleadoDAO.obtenerSiguienteId();
					double horasTotales = 0.0;
					Date fechaAlta = new Date(System.currentTimeMillis());

					EmpleadoDTO nuevoEmpleado = new EmpleadoDTO(id, nombre, apellido, email, contrasena, telefono,
							puesto, fechaAlta, activo, horasTotales);

					if (empleadoDAO.insertar(nuevoEmpleado)) {
						JOptionPane.showMessageDialog(AltaDialog.this, "Empleado agregado exitosamente.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(AltaDialog.this, "Error al agregar empleado.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(AltaDialog.this, "Datos inválidos: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setSize(400, 380);
		setLocationRelativeTo(gerenteDialog);
	}
}