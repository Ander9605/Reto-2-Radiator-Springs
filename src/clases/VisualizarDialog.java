package clases;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import daoS.EmpleadoDAO;
import daoS.HorarioDAO;
import dtoS.EmpleadoDTO;
import dtoS.HorarioDTO;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class VisualizarDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable empleadosTable;
	private JTable horariosTable;
	private EmpleadoDAO empleadoDAO;
	private HorarioDAO horarioDAO;
	private ArrayList<EmpleadoDTO> empleados;
	private ArrayList<HorarioDTO> horarios;

	private static final Color BACKGROUND_COLOR = new Color(25, 25, 25);
	private static final Color PANEL_COLOR = new Color(35, 35, 35);
	private static final Color TEXT_COLOR = new Color(255, 255, 255);
	private static final Color ACCENT_COLOR = new Color(100, 149, 237);
	private static final Color TABLE_HEADER_COLOR = new Color(45, 45, 45);
	@SuppressWarnings("unused")
	private static final Color TABLE_ALTERNATE_COLOR = new Color(40, 40, 40);
	private static final Color BORDER_COLOR = new Color(70, 70, 70);

	/**
	 * CONSTRUCTOR DE VISUALIZACIÓN
	 * @param gerenteDialog
	 */
	public VisualizarDialog(GerenteDialog gerenteDialog) {
		super(gerenteDialog, "Lista de Empleados y Horarios", true);
		empleadoDAO = new EmpleadoDAO();
		horarioDAO = new HorarioDAO();
		empleados = empleadoDAO.listarTodos();
		horarios = horarioDAO.listarTodos();

		setResizable(false);
		setupUIManager();
		initComponents(gerenteDialog);
	}

	private void setupUIManager() {
		UIManager.put("Panel.background", BACKGROUND_COLOR);
		UIManager.put("ComboBox.background", PANEL_COLOR);
		UIManager.put("ComboBox.foreground", TEXT_COLOR);
		UIManager.put("ComboBox.selectionBackground", ACCENT_COLOR);
		UIManager.put("ComboBox.selectionForeground", TEXT_COLOR);
		UIManager.put("ScrollPane.background", PANEL_COLOR);
		UIManager.put("Table.background", PANEL_COLOR);
		UIManager.put("Table.foreground", TEXT_COLOR);
		UIManager.put("Table.selectionBackground", ACCENT_COLOR);
		UIManager.put("Table.selectionForeground", TEXT_COLOR);
		UIManager.put("TableHeader.background", TABLE_HEADER_COLOR);
		UIManager.put("TableHeader.foreground", TEXT_COLOR);
	}

	/**
	 * COMPONENTES
	 * @param gerenteDialog
	 */
	public void initComponents(GerenteDialog gerenteDialog) {
		getContentPane().setBackground(BACKGROUND_COLOR);
		getContentPane().setLayout(new BorderLayout(10, 10));

		JLabel lblTitulo = new JLabel("Lista de Empleados y Horarios", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitulo.setForeground(TEXT_COLOR);
		lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		getContentPane().add(lblTitulo, BorderLayout.NORTH);

		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(BACKGROUND_COLOR);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JComboBox<String> comboBoxOrdenarEmpleados = createComboBox(
				new String[] { "ID Ascendente", "ID Descendente", "Nombre Ascendente", "Nombre Descendente",
						"Puesto Ascendente", "Puesto Descendente", "Activo Ascendente", "Activo Descendente" });
		comboBoxOrdenarEmpleados
				.addActionListener(e -> ordenarEmpleados((String) comboBoxOrdenarEmpleados.getSelectedItem()));
		comboBoxOrdenarEmpleados.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(comboBoxOrdenarEmpleados);
		panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));

		empleadosTable = createTable();
		actualizarTablaEmpleados();
		JScrollPane empleadosScrollPane = createScrollPane(empleadosTable, "Empleados");
		panelCentral.add(empleadosScrollPane);
		panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));

		JComboBox<String> comboBoxOrdenarHorarios = createComboBox(
				new String[] { "ID Horario Ascendente", "ID Horario Descendente", "ID Empleado Ascendente",
						"ID Empleado Descendente", "Más Reciente", "Menos Reciente" });
		comboBoxOrdenarHorarios
				.addActionListener(e -> ordenarHorarios((String) comboBoxOrdenarHorarios.getSelectedItem()));
		comboBoxOrdenarHorarios.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(comboBoxOrdenarHorarios);
		panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));

		horariosTable = createTable();
		actualizarTablaHorarios();
		JScrollPane horariosScrollPane = createScrollPane(horariosTable, "Horarios");
		panelCentral.add(horariosScrollPane);

		getContentPane().add(panelCentral, BorderLayout.CENTER);

		JButton btnCerrar = createButton("Cerrar");
		btnCerrar.addActionListener(e -> dispose());

		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(BACKGROUND_COLOR);
		panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		panelInferior.add(btnCerrar);
		getContentPane().add(panelInferior, BorderLayout.SOUTH);

		setSize(800, 600);
		setLocationRelativeTo(gerenteDialog);
	}

	/**
	 * COMBOBOX
	 * @param items
	 * @return
	 */
	public JComboBox<String> createComboBox(String[] items) {
		JComboBox<String> comboBox = new JComboBox<>(items);
		comboBox.setBackground(PANEL_COLOR);
		comboBox.setForeground(TEXT_COLOR);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 13));
		comboBox.setMaximumSize(new Dimension(300, 30));
		return comboBox;
	}

	/**
	 * TABLA
	 * @return
	 */
	public JTable createTable() {
		JTable table = new JTable();
		table.setBackground(PANEL_COLOR);
		table.setForeground(TEXT_COLOR);
		table.setGridColor(BORDER_COLOR);
		table.setSelectionBackground(ACCENT_COLOR);
		table.setSelectionForeground(TEXT_COLOR);
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		table.setRowHeight(28);

		JTableHeader header = table.getTableHeader();
		header.setBackground(TABLE_HEADER_COLOR);
		header.setForeground(TEXT_COLOR);
		header.setFont(new Font("Arial", Font.BOLD, 13));

		return table;
	}

	/**
	 * SCROLLPANEL
	 * @param table
	 * @param title
	 * @return
	 */
	public JScrollPane createScrollPane(JTable table, String title) {
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(PANEL_COLOR);
		scrollPane.getViewport().setBackground(PANEL_COLOR);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ACCENT_COLOR, 1), title,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new Font("Arial", Font.BOLD, 14), TEXT_COLOR));
		return scrollPane;
	}

	/**
	 * CREAR BOTÓN
	 * @param text
	 * @return
	 */
	public JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setBackground(ACCENT_COLOR);
		button.setForeground(TEXT_COLOR);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setFont(new Font("Arial", Font.BOLD, 13));
		button.setPreferredSize(new Dimension(100, 35));
		return button;
	}

	/**
	 * ACTUALIZAR TABLA EMPLEADOS
	 */
	public void actualizarTablaEmpleados() {
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] { "ID", "Nombre", "Apellido", "Email", "Teléfono", "Puesto",
				"Fecha Alta", "Activo", "Horas Totales" });

		for (EmpleadoDTO empleado : empleados) {
			double horasTotales = horarioDAO.obtenerHorasTotales(empleado.getIdEmpleado());

			model.addRow(new Object[] { empleado.getIdEmpleado(), empleado.getNombre(), empleado.getApellido(),
					empleado.getEmail(), empleado.getTelefono(), empleado.getPuesto(), empleado.getFechaAlta(),
					empleado.isActivo() ? "Sí" : "No", horasTotales });
		}

		empleadosTable.setModel(model);
	}

	/**
	 * ACTUALIZAR TABLA HORARIOS
	 */
	public void actualizarTablaHorarios() {
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] { "ID Horario", "ID Empleado", "Fecha", "Hora Entrada", "Hora Salida",
				"Horas Trabajadas" });

		for (HorarioDTO horario : horarios) {
			model.addRow(new Object[] { horario.getIdHorario(), horario.getIdEmpleado(), horario.getFecha(),
					horario.getHoraEntrada(), horario.getHoraSalida(), horario.getHorasTrabajadas() });
		}

		horariosTable.setModel(model);
	}

	/**
	 * ORDENAR EMPLEADOS
	 * @param criterio
	 */
	public void ordenarEmpleados(String criterio) {
		switch (criterio) {
		case "ID Ascendente":
			empleados.sort(Comparator.comparingInt(EmpleadoDTO::getIdEmpleado));
			break;
		case "ID Descendente":
			empleados.sort(Comparator.comparingInt(EmpleadoDTO::getIdEmpleado).reversed());
			break;
		case "Nombre Ascendente":
			empleados.sort(Comparator.comparing(EmpleadoDTO::getNombre));
			break;
		case "Nombre Descendente":
			empleados.sort(Comparator.comparing(EmpleadoDTO::getNombre).reversed());
			break;
		case "Puesto Ascendente":
			empleados.sort(Comparator.comparing(EmpleadoDTO::getPuesto));
			break;
		case "Puesto Descendente":
			empleados.sort(Comparator.comparing(EmpleadoDTO::getPuesto).reversed());
			break;
		case "Activo Ascendente":
			empleados.sort(Comparator.comparing(EmpleadoDTO::isActivo));
			break;
		case "Activo Descendente":
			empleados.sort(Comparator.comparing(EmpleadoDTO::isActivo).reversed());
			break;
		}
		actualizarTablaEmpleados();
	}

	/**
	 * ORDENAR HORARIOS
	 * @param criterio
	 */
	public void ordenarHorarios(String criterio) {
		switch (criterio) {
		case "ID Horario Ascendente":
			horarios.sort(Comparator.comparingInt(HorarioDTO::getIdHorario));
			break;
		case "ID Horario Descendente":
			horarios.sort(Comparator.comparingInt(HorarioDTO::getIdHorario).reversed());
			break;
		case "ID Empleado Ascendente":
			horarios.sort(Comparator.comparingInt(HorarioDTO::getIdEmpleado));
			break;
		case "ID Empleado Descendente":
			horarios.sort(Comparator.comparingInt(HorarioDTO::getIdEmpleado).reversed());
			break;
		case "Más Reciente":
			horarios.sort(Comparator.comparing(HorarioDTO::getFecha).reversed());
			break;
		case "Menos Reciente":
			horarios.sort(Comparator.comparing(HorarioDTO::getFecha));
			break;
		}
		actualizarTablaHorarios();
	}
}