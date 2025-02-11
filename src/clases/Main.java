package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import daoS.EmpleadoDAO;
import dtoS.EmpleadoDTO;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private static EmpleadoDAO empleadoDAO = new EmpleadoDAO();

	public Main() {
		setTitle("Login");
		setSize(400, 250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		getContentPane().setBackground(new Color(34, 34, 34));

		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setBounds(50, 40, 100, 30);
		getContentPane().add(emailLabel);

		emailField = new JTextField();
		emailField.setFont(new Font("Arial", Font.PLAIN, 14));
		emailField.setForeground(Color.WHITE);
		emailField.setBackground(new Color(50, 50, 50));
		emailField.setBounds(150, 40, 200, 30);
		emailField.setCaretColor(Color.WHITE);
		getContentPane().add(emailField);

		JLabel passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setBounds(50, 90, 100, 30);
		getContentPane().add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
		passwordField.setForeground(Color.WHITE);
		passwordField.setBackground(new Color(50, 50, 50));
		passwordField.setBounds(150, 90, 200, 30);
		passwordField.setCaretColor(Color.WHITE);
		getContentPane().add(passwordField);

		loginButton = new JButton("Iniciar Sesión");
		loginButton.setFont(new Font("Arial", Font.BOLD, 14));
		loginButton.setBackground(new Color(0, 123, 255));
		loginButton.setForeground(Color.WHITE);
		loginButton.setBounds(128, 142, 145, 40);
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		getContentPane().add(loginButton);

		loginButton.addActionListener(new LoginAction());

		setLocationRelativeTo(null);
	}

	private class LoginAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String email = emailField.getText().trim();
			String password = new String(passwordField.getPassword()).trim();
			EmpleadoDTO empleado = empleadoDAO.buscarPorEmail(email);
			if (empleado != null) {
				System.out.println("Empleado encontrado: " + empleado.getEmail());
				System.out.println("Contraseña almacenada: " + empleado.getContrasena());
				if (empleado.getContrasena().equals(password)) {
					if (empleado.getPuesto().equalsIgnoreCase("gerente")) {
						JOptionPane.showMessageDialog(null, "Sesión iniciada. Bienvenido, Gerente.");
						new GerenteDialog(Main.this).setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Sesión iniciada. Bienvenido, Empleado.");
						new EmpleadoDialog(Main.this, empleado).setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Email no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * MÉTODO MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
	}
}
