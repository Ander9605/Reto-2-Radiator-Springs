package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import daoS.EmpleadoDAO;

public class EliminarDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBoxIdEmpleado;
	private EmpleadoDAO empleadoDAO;

	/**
	 * CONSTRUCTOR DE BAJA
	 * @param gerenteDialog
	 */
	public EliminarDialog(GerenteDialog gerenteDialog) {
		super(gerenteDialog, "Eliminar Empleado", true);
		empleadoDAO = new EmpleadoDAO();

		setSize(500, 100);
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(34, 34, 34));

		JLabel lblIdEmpleado = new JLabel("Seleccione Empleado:");
		lblIdEmpleado.setFont(new Font("Arial", Font.PLAIN, 14));
		lblIdEmpleado.setForeground(Color.WHITE);
		lblIdEmpleado.setBounds(20, 20, 146, 25);
		getContentPane().add(lblIdEmpleado);

		comboBoxIdEmpleado = new JComboBox<>();
		comboBoxIdEmpleado.setFont(new Font("Arial", Font.PLAIN, 14));
		comboBoxIdEmpleado.setBounds(176, 20, 200, 25);

		List<String[]> empleados = empleadoDAO.obtenerIdsYNombresEmpleados();
		for (String[] empleado : empleados) {
			comboBoxIdEmpleado.addItem("ID: " + empleado[0] + " - " + empleado[1]);
		}

		getContentPane().add(comboBoxIdEmpleado);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(0, 123, 255));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBounds(80, 70, 100, 30);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setBorderPainted(false);
		getContentPane().add(btnEliminar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancelar.setBackground(new Color(200, 0, 0));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(200, 70, 100, 30);
		btnCancelar.setFocusPainted(false);
		btnCancelar.setBorderPainted(false);
		getContentPane().add(btnCancelar);

		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) comboBoxIdEmpleado.getSelectedItem();
				if (selectedItem == null) {
					JOptionPane.showMessageDialog(EliminarDialog.this, "Debe seleccionar un empleado.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String idStr = selectedItem.substring(selectedItem.indexOf("ID: ") + 4, selectedItem.indexOf(" -"));
				int idEmpleado = Integer.parseInt(idStr);

				int confirmacion = JOptionPane.showConfirmDialog(EliminarDialog.this,
						"¿Está seguro de que desea eliminar este empleado?", "Confirmar eliminación",
						JOptionPane.YES_NO_OPTION);

				if (confirmacion == JOptionPane.YES_OPTION) {
					boolean eliminado = empleadoDAO.borrar(idEmpleado);

					if (eliminado) {
						JOptionPane.showMessageDialog(EliminarDialog.this, "Empleado eliminado correctamente.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(EliminarDialog.this,
								"Empleado no encontrado o no pudo ser eliminado.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setSize(400, 150);
		setLocationRelativeTo(gerenteDialog);
	}
}