package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddActivityGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the frame.
	 */
	public AddActivityGUI(EncargadoGUI a) {
		setTitle("Añadir actividad");
		
		AddActivityGUI b = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(211, 22, 150, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(328, 53, 33, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(315, 84, 46, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(77, 25, 63, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Grado de exigencia:");
		lblNewLabel_1.setBounds(77, 56, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setBounds(77, 87, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel Errorlbl_3 = new JLabel("");
		Errorlbl_3.setBounds(115, 155, 190, 14);
		contentPane.add(Errorlbl_3);
		
		JButton btnNewButton = new JButton("Añadir actividad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Errorlbl_3.setText("");
				
				BLFacade facade = MainGUI.getBusinessLogic();
				try {
					
					facade.addActivity(textField.getText(), Integer.parseInt(textField_1.getText()), Float.parseFloat(textField_2.getText()));
				} catch (Exception e1) {
					Errorlbl_3.setText(e1.getMessage());
				}
			}
		});
		btnNewButton.setBounds(148, 121, 125, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setVisible(false);
				a.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 155, 76, 23);
		contentPane.add(btnNewButton_1);
	}
}
