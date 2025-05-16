package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class SendBillGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
	DefaultListModel<String> listModel = new DefaultListModel<String>();

	/**
	 * Create the frame.
	 */
	public SendBillGUI(EncargadoGUI a) {
		setTitle("Enviar factura");
		
		BLFacade facade = MainGUI.getBusinessLogic();
		SendBillGUI b = this;
		List<Integer> idReservas = new ArrayList<Integer>(); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		List<String> activityList = facade.getUsers();		
        for (String i:activityList) comboBoxModel.addElement(i);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(comboBoxModel);
		comboBox.setBounds(10, 11, 100, 22);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem()!=null) {
					idReservas.clear();
					listModel.removeAllElements();
					List<String> reservas = facade.getBookingsWU(comboBox.getSelectedItem().toString());
					if (reservas.size()>4) {
						for (int i=4; i<reservas.size(); i++) {
							listModel.addElement(reservas.get(i));
							String [] s = reservas.get(i).split(", ");
							idReservas.add(Integer.parseInt(s[0]));
						}
					}	
				}
			}			
		});
		
		JList<String> list = new JList<String>();
		list.setModel(listModel);
		list.setBounds(10, 44, 414, 172);
		contentPane.add(list);
		
		JLabel Errorlbl = new JLabel("");
		Errorlbl.setBounds(109, 231, 216, 14);
		contentPane.add(Errorlbl);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setVisible(false);
				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Enviar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Errorlbl.setText("");
				try {
					if (facade.sendBill(comboBox.getSelectedItem().toString(), idReservas)) {
						Errorlbl.setText("Factura enviada");
					}
					else Errorlbl.setText("Ha ocurrido un error");
				} catch (Exception e1) {
					Errorlbl.setText(e1.getMessage());
					System.out.println(e1.getMessage());
				}
			}
		});
		btnNewButton_1.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton_1);
		

	}
}
