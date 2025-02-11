package clases;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;
import conexion.Conexion;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.awt.Desktop;
import net.sf.jasperreports.export.*;

@SuppressWarnings("unused")
public class GerenteDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * CONSTRUCTOR DE LA CLASE GERENTE
	 * @param parent
	 */
	public GerenteDialog(JFrame parent) {
		super(parent, "Opciones de Gerente", true);

		getContentPane().setLayout(null);
		setSize(500, 400);
		setResizable(false);

		getContentPane().setBackground(new Color(34, 34, 34));

		JButton btnAlta = new JButton("Alta");
		btnAlta.setFont(new Font("Arial", Font.BOLD, 14));
		btnAlta.setBackground(new Color(0, 123, 255));
		btnAlta.setForeground(Color.WHITE);
		btnAlta.setBounds(10, 83, 230, 105);
		btnAlta.setFocusPainted(false);
		btnAlta.setBorderPainted(false);
		btnAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AltaDialog altaDialog = new AltaDialog(GerenteDialog.this);
				altaDialog.setVisible(true);
			}
		});

		JButton btnBaja = new JButton("Baja");
		btnBaja.setFont(new Font("Arial", Font.BOLD, 14));
		btnBaja.setBackground(new Color(0, 123, 255));
		btnBaja.setForeground(Color.WHITE);
		btnBaja.setBounds(10, 198, 230, 105);
		btnBaja.setFocusPainted(false);
		btnBaja.setBorderPainted(false);
		btnBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EliminarDialog eliminarDialog = new EliminarDialog(GerenteDialog.this);
				eliminarDialog.setVisible(true);
			}
		});

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
		btnModificar.setBackground(new Color(0, 123, 255));
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBounds(250, 83, 226, 105);
		btnModificar.setFocusPainted(false);
		btnModificar.setBorderPainted(false);
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModificarDialog modificarDialog = new ModificarDialog(GerenteDialog.this);
				modificarDialog.setVisible(true);
			}
		});

		JButton btnListar = new JButton("Listar");
		btnListar.setFont(new Font("Arial", Font.BOLD, 14));
		btnListar.setBackground(new Color(0, 123, 255));
		btnListar.setForeground(Color.WHITE);
		btnListar.setBounds(250, 198, 226, 105);
		btnListar.setFocusPainted(false);
		btnListar.setBorderPainted(false);
		btnListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VisualizarDialog visualizarDialog = new VisualizarDialog(GerenteDialog.this);
				visualizarDialog.setVisible(true);
			}
		});

		JButton btnJasperPDF = new JButton("JasperPDF");
		btnJasperPDF.setFont(new Font("Arial", Font.BOLD, 14));
		btnJasperPDF.setBackground(new Color(255, 69, 0));
		btnJasperPDF.setForeground(Color.WHITE);
		btnJasperPDF.setBounds(10, 313, 230, 40);
		btnJasperPDF.setFocusPainted(false);
		btnJasperPDF.setBorderPainted(false);
		btnJasperPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField yearField = new JTextField(4);
				yearField.setFont(new Font("Arial", Font.PLAIN, 14));
				yearField.setForeground(Color.WHITE);
				yearField.setBackground(new Color(45, 45, 45));
				yearField.setCaretColor(Color.WHITE);
				yearField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255)));

				JComboBox<String> monthBox = new JComboBox<>(
						new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" });
				monthBox.setFont(new Font("Arial", Font.PLAIN, 14));
				monthBox.setForeground(Color.WHITE);
				monthBox.setBackground(new Color(45, 45, 45));
				((JTextField) monthBox.getEditor().getEditorComponent()).setCaretColor(Color.WHITE);
				monthBox.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255)));

				JLabel yearLabel = new JLabel("Año:");
				yearLabel.setFont(new Font("Arial", Font.BOLD, 14));
				yearLabel.setForeground(Color.WHITE);

				JLabel monthLabel = new JLabel("Mes:");
				monthLabel.setFont(new Font("Arial", Font.BOLD, 14));
				monthLabel.setForeground(Color.WHITE);

				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
				panel.setBackground(new Color(34, 34, 34));
				panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

				panel.add(yearLabel);
				panel.add(yearField);
				panel.add(monthLabel);
				panel.add(monthBox);

				UIManager.put("OptionPane.background", new Color(34, 34, 34));
				UIManager.put("Panel.background", new Color(34, 34, 34));
				UIManager.put("OptionPane.messageForeground", Color.WHITE);
				UIManager.put("Button.background", new Color(0, 123, 255));
				UIManager.put("Button.foreground", Color.WHITE);
				UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));
				UIManager.put("Button.focus", false);
				UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 15, 5, 15));

				int result = JOptionPane.showConfirmDialog(null, panel, "Seleccione Año y Mes",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				UIManager.put("OptionPane.background", null);
				UIManager.put("Panel.background", null);
				UIManager.put("OptionPane.messageForeground", null);
				UIManager.put("Button.background", null);
				UIManager.put("Button.foreground", null);
				UIManager.put("Button.font", null);
				UIManager.put("Button.focus", null);
				UIManager.put("Button.border", null);

				if (result == JOptionPane.OK_OPTION) {
					try {
						int year = Integer.parseInt(yearField.getText());
						int month = Integer.parseInt((String) monthBox.getSelectedItem());

						HashMap<String, Object> parameters = new HashMap<>();
						parameters.put("year", year);
						parameters.put("month", month);

						JasperPrint jasperPrint = JasperFillManager.fillReport("src\\clases\\Empleados.jasper",
								parameters, Conexion.getInstancia().getCon());

						JRPdfExporter exp = new JRPdfExporter();
						exp.setExporterInput(new SimpleExporterInput(jasperPrint));
						String outputPath = "InformeEmpleados_" + year + "_" + month + ".pdf";
						exp.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath));

						exp.exportReport();

						JOptionPane.showMessageDialog(null, "Informe generado y almacenado", "PDF Guardado",
								JOptionPane.PLAIN_MESSAGE);

						File pdfFile = new File(outputPath);
						if (pdfFile.exists() && Desktop.isDesktopSupported()) {
							Desktop.getDesktop().open(pdfFile);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Por favor, ingrese un año válido", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (JRException | IOException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error al generar el informe: " + ex.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		JButton btnVolverMain = new JButton("Volver");
		btnVolverMain.setFont(new Font("Arial", Font.BOLD, 14));
		btnVolverMain.setBackground(new Color(255, 69, 0));
		btnVolverMain.setForeground(Color.WHITE);
		btnVolverMain.setBounds(250, 313, 226, 40);
		btnVolverMain.setFocusPainted(false);
		btnVolverMain.setBorderPainted(false);
		btnVolverMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main mainFrame = new Main();
				mainFrame.setVisible(true);
			}
		});

		getContentPane().add(btnAlta);
		getContentPane().add(btnBaja);
		getContentPane().add(btnModificar);
		getContentPane().add(btnListar);
		getContentPane().add(btnJasperPDF);
		getContentPane().add(btnVolverMain);
		
		JLabel lblTitulo = new JLabel("RADIATOR SPRINGS");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(142, 35, 197, 20);
		getContentPane().add(lblTitulo);

		setLocationRelativeTo(parent);
	}
}
