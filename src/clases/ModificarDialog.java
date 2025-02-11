package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import daoS.EmpleadoDAO;
import dtoS.EmpleadoDTO;
import java.sql.Date;
import java.util.ArrayList;

public class ModificarDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> cmbIdEmpleado;
	private JTextField txtNombre, txtApellido, txtEmail, txtContrasena, txtTelefono, txtPuesto;
	private JCheckBox chkActivo;
	private EmpleadoDAO empleadoDAO;

	/**
	 * CONSTRUCTOR DE MODIFICACIÓNS
	 * @param gerenteDialog
	 */
	public ModificarDialog(GerenteDialog gerenteDialog) {
		super(gerenteDialog, "Modificar Empleado", true);
		empleadoDAO = new EmpleadoDAO();

		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(34, 34, 34));

		ArrayList<String[]> empleados = empleadoDAO.obtenerIdsYNombresEmpleados();

		JLabel lblIdEmpleado = new JLabel("Seleccione Empleado:");
		lblIdEmpleado.setFont(new Font("Arial", Font.PLAIN, 14));
		lblIdEmpleado.setForeground(Color.WHITE);
		lblIdEmpleado.setBounds(20, 20, 146, 25);
		getContentPane().add(lblIdEmpleado);

		cmbIdEmpleado = new JComboBox<>();
		for (String[] empleado : empleados) {
			cmbIdEmpleado.addItem("ID: " + empleado[0] + " - " + empleado[1]);
		}
		cmbIdEmpleado.setFont(new Font("Arial", Font.PLAIN, 14));
		cmbIdEmpleado.setBounds(176, 20, 200, 25);
		getContentPane().add(cmbIdEmpleado);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(20, 60, 120, 25);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNombre.setForeground(Color.WHITE);
		txtNombre.setBackground(new Color(50, 50, 50));
		txtNombre.setBounds(176, 60, 200, 25);
		txtNombre.setCaretColor(Color.WHITE);
		getContentPane().add(txtNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 14));
		lblApellido.setForeground(Color.WHITE);
		lblApellido.setBounds(20, 100, 120, 25);
		getContentPane().add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Arial", Font.PLAIN, 14));
		txtApellido.setForeground(Color.WHITE);
		txtApellido.setBackground(new Color(50, 50, 50));
		txtApellido.setBounds(176, 100, 200, 25);
		txtApellido.setCaretColor(Color.WHITE);
		getContentPane().add(txtApellido);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(20, 140, 120, 25);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setBackground(new Color(50, 50, 50));
		txtEmail.setBounds(176, 140, 200, 25);
		txtEmail.setCaretColor(Color.WHITE);
		getContentPane().add(txtEmail);

		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
		lblContrasena.setForeground(Color.WHITE);
		lblContrasena.setBounds(20, 180, 120, 25);
		getContentPane().add(lblContrasena);

		txtContrasena = new JTextField();
		txtContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
		txtContrasena.setForeground(Color.WHITE);
		txtContrasena.setBackground(new Color(50, 50, 50));
		txtContrasena.setBounds(176, 180, 200, 25);
		txtContrasena.setCaretColor(Color.WHITE);
		getContentPane().add(txtContrasena);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setBounds(20, 220, 120, 25);
		getContentPane().add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTelefono.setForeground(Color.WHITE);
		txtTelefono.setBackground(new Color(50, 50, 50));
		txtTelefono.setBounds(176, 220, 200, 25);
		txtTelefono.setCaretColor(Color.WHITE);
		getContentPane().add(txtTelefono);

		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPuesto.setForeground(Color.WHITE);
		lblPuesto.setBounds(20, 260, 120, 25);
		getContentPane().add(lblPuesto);

		txtPuesto = new JTextField();
		txtPuesto.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPuesto.setForeground(Color.WHITE);
		txtPuesto.setBackground(new Color(50, 50, 50));
		txtPuesto.setBounds(176, 260, 200, 25);
		txtPuesto.setCaretColor(Color.WHITE);
		getContentPane().add(txtPuesto);

		JLabel lblActivo = new JLabel("Activo:");
		lblActivo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblActivo.setForeground(Color.WHITE);
		lblActivo.setBounds(20, 300, 120, 25);
		getContentPane().add(lblActivo);

		chkActivo = new JCheckBox();
		chkActivo.setBounds(176, 300, 50, 25);
		chkActivo.setBackground(new Color(34, 34, 34));
		getContentPane().add(chkActivo);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
		btnGuardar.setBackground(new Color(0, 123, 255));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBounds(80, 350, 100, 30);
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBorderPainted(false);
		getContentPane().add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(200, 350, 100, 30);
		btnCancelar.setFocusPainted(false);
		btnCancelar.setBorderPainted(false);
		getContentPane().add(btnCancelar);

		cmbIdEmpleado.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selectedItem = (String) cmbIdEmpleado.getSelectedItem();
					if (selectedItem != null) {
						String idStr = selectedItem.substring(selectedItem.indexOf("ID: ") + 4,
								selectedItem.indexOf(" -"));
						int idEmpleado = Integer.parseInt(idStr);

						EmpleadoDTO empleado = empleadoDAO.buscar(idEmpleado);

						if (empleado != null) {
							txtNombre.setText(empleado.getNombre());
							txtApellido.setText(empleado.getApellido());
							txtEmail.setText(empleado.getEmail());
							txtContrasena.setText(empleado.getContrasena());
							txtTelefono.setText(empleado.getTelefono());
							txtPuesto.setText(empleado.getPuesto());
							chkActivo.setSelected(empleado.isActivo());
						} else {
							JOptionPane.showMessageDialog(ModificarDialog.this, "Empleado no encontrado.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});

		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String selectedItem = (String) cmbIdEmpleado.getSelectedItem();
					String idStr = selectedItem.substring(selectedItem.indexOf("ID: ") + 4, selectedItem.indexOf(" -"));
					int idEmpleado = Integer.parseInt(idStr);

					String nombre = txtNombre.getText();
					String apellido = txtApellido.getText();
					String email = txtEmail.getText();
					String contrasena = txtContrasena.getText();
					String telefono = txtTelefono.getText();
					String puesto = txtPuesto.getText();
					boolean activo = chkActivo.isSelected();

					double horasTotales = 0.0;

					EmpleadoDTO empleado = new EmpleadoDTO(idEmpleado, nombre, apellido, email, contrasena, telefono,
							puesto, new Date(System.currentTimeMillis()), activo, horasTotales);

					boolean actualizado = empleadoDAO.actualizar(empleado);

					if (actualizado) {
						JOptionPane.showMessageDialog(ModificarDialog.this, "Empleado actualizado correctamente.",
								"Éxito", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(ModificarDialog.this, "No se pudo actualizar el empleado.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(ModificarDialog.this,
							"Error al actualizar el empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setSize(400, 450);
		setLocationRelativeTo(gerenteDialog);
	}
}