package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class PayBillsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	DefaultComboBoxModel<Integer> comboBoxModel = new DefaultComboBoxModel<Integer>();
	/**
	 * Create the frame.
	 */
	public PayBillsGUI(MainGUI a, User user) {
		
		BLFacade facade = MainGUI.getBusinessLogic();
		PayBillsGUI b = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Elige la factura a pagar:");
		lblNewLabel.setBounds(10, 11, 141, 14);
		contentPane.add(lblNewLabel);
		
		List<Integer> activityList = facade.getBillsIDWU(user);		
        for (Integer i:activityList) comboBoxModel.addElement(i);
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setModel(comboBoxModel);
		comboBox.setBounds(161, 7, 114, 22);
		contentPane.add(comboBox);
		
		JLabel Errorlbl = new JLabel("");
		Errorlbl.setBounds(10, 51, 414, 14);
		contentPane.add(Errorlbl);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setVisible(false);
				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 82, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Pagar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Errorlbl.setText("");
				try {
					if (facade.payBill(Integer.parseInt(comboBox.getSelectedItem().toString()), user)) {
						Errorlbl.setText("Factura pagada correctamente");
						comboBoxModel.removeAllElements();
						List<Integer> activityList = facade.getBillsIDWU(user);		
				        for (Integer i:activityList) comboBoxModel.addElement(i);
					}
					else {
						Errorlbl.setText("Ha ocurrido un problema");
					}		
				} catch (Exception e1) {
					Errorlbl.setText(e1.getMessage());
				}
			}
		});
		btnNewButton_1.setBounds(335, 82, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
	}
}
