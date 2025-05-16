package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

import domain.User;
import exceptions.UserAlreadyExistException;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JTextField textFieldName;
	private JTextField textFieldPassword;
	
	private DefaultComboBoxModel<String> usuarios = new DefaultComboBoxModel<String>();
	private JTextField textFieldMaxWR;
	private JTextField textFieldPaymentNumber;
	/**
	 * Create the frame.
	 */
	public RegisterGUI(LoginGUI a) {
		
		RegisterGUI b = this;
		
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter email:");
		lblNewLabel.setBounds(70, 30, 120, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterName = new JLabel("Enter name:");
		lblEnterName.setBounds(70, 65, 120, 16);
		contentPane.add(lblEnterName);
		
		JLabel lblEnterPassword = new JLabel("Enter password:");
		lblEnterPassword.setBounds(70, 100, 120, 16);
		contentPane.add(lblEnterPassword);
		
		JLabel lblSelectPaymentType = new JLabel("Select payment type:");
		lblSelectPaymentType.setBounds(70, 170, 120, 16);
		contentPane.add(lblSelectPaymentType);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(230, 28, 150, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(230, 63, 150, 20);
		contentPane.add(textFieldName);
		
		textFieldPassword = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldPassword.setBounds(230, 98, 150, 20);
		contentPane.add(textFieldPassword);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(usuarios);
		usuarios.addElement("Credit Card");
		usuarios.addElement("Account");
		comboBox.setBounds(230, 168, 150, 20);
		contentPane.add(comboBox);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(125, 283, 200, 16);
		contentPane.add(lblError);
		
		JLabel lblEnterMaxRS = new JLabel("Enter max weekly reservation:");
		lblEnterMaxRS.setBounds(70, 140, 190, 16);
		contentPane.add(lblEnterMaxRS);
		
		textFieldMaxWR = new JTextField();
		textFieldMaxWR.setBounds(290, 138, 35, 20);
		contentPane.add(textFieldMaxWR);
		
		JLabel lblEnterPaymentNumber = new JLabel("Enter payment number:");
		lblEnterPaymentNumber.setBounds(70, 200, 142, 16);
		contentPane.add(lblEnterPaymentNumber);
		
		textFieldPaymentNumber = new JTextField();
		textFieldPaymentNumber.setBounds(230, 198, 150, 20);
		contentPane.add(textFieldPaymentNumber);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblError.setText("");
				try {
					BLFacade facade = MainGUI.getBusinessLogic();
					boolean type;
					if (comboBox.getSelectedItem()=="Account") type = true;
					else type = false;
					
					User u = facade.createUser(textFieldName.getText(), textFieldPassword.getText(), textFieldEmail.getText(),  Integer.parseInt(textFieldMaxWR.getText()), type, Integer.parseInt(textFieldPaymentNumber.getText()));
					lblError.setText("User Created");
				} catch (UserAlreadyExistException  e1) {
					lblError.setText(e1.getMessage());
				}
			}
		});
		btnNewButton.setBounds(173, 238, 98, 26);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Go back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.setVisible(true);
				b.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(9, 273, 80, 26);
		contentPane.add(btnNewButton_1);
	}
}
