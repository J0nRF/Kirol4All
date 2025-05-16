package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class LookBillsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	DefaultListModel<String> listModel = new DefaultListModel<String>();

	/**
	 * Create the frame.
	 */
	public LookBillsGUI(MainGUI a, User user) {
		setTitle("Consultar facturas");
		
		BLFacade facade = MainGUI.getBusinessLogic();
		LookBillsGUI b = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setVisible(false);
				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(169, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		List<String> sesiones = facade.getBillsWU(user);
		for (String i:sesiones) listModel.addElement(i);
		
		
		JList<String> list = new JList<String>();
		list.setModel(listModel);
		list.setBounds(10, 11, 414, 213);
		contentPane.add(list);
	}
}
