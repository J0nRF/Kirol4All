package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	private DefaultComboBoxModel<String> usuarios = new DefaultComboBoxModel<String>();
	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		
		LoginGUI a = this;
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter email:");
		lblNewLabel.setBounds(70, 30, 79, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterPassword = new JLabel("Enter password:");
		lblEnterPassword.setBounds(70, 65, 93, 14);
		contentPane.add(lblEnterPassword);
		
		textField = new JTextField();
		textField.setBounds(230, 28, 150, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(230, 63, 150, 20);
		contentPane.add(passwordField);
		usuarios.addElement("Driver");
		usuarios.addElement("Traveler");
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(70, 143, 300, 16);
		contentPane.add(lblError);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblError.setText("");
				
				BLFacade facade = MainGUI.getBusinessLogic();
				
				User u = facade.getUser(textField.getText());
				if (u != null) {	
					if (facade.Login(new String(passwordField.getPassword()), u)) {
						if (u.getType()) {
							EncargadoGUI c = new EncargadoGUI();
							c.setVisible(true);
						}
						else {
							MainGUI b = new MainGUI(u);
							b.setVisible(true);
						}
						a.setVisible(false);
					} else lblError.setText("There's an error in the data given");
				} else lblError.setText("The email given doesn't have an account");
			}
		});
		btnLogin.setBounds(168, 105, 98, 26);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("If you don't have a user, click here:");
		lblNewLabel_1.setBounds(12, 188, 200, 16);
		contentPane.add(lblNewLabel_1);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI b = new RegisterGUI(a);
				a.setVisible(false);
				b.setVisible(true);
			}
		});
		btnRegister.setBounds(230, 183, 98, 26);
		contentPane.add(btnRegister);
		
		JButton btnConsultarSesiones = new JButton("Consultar sesiones");
		btnConsultarSesiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LookSessionsGUI c = new LookSessionsGUI(a);
				a.setVisible(false);
				c.setVisible(true);
			}
		});
		btnConsultarSesiones.setBounds(146, 223, 144, 26);
		contentPane.add(btnConsultarSesiones);
		
	}
}
