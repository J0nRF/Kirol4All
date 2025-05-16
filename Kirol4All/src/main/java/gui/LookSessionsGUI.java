package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Session;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;

public class LookSessionsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
	DefaultListModel<String> listModel = new DefaultListModel<String>();

	/**
	 * Create the frame.
	 */
	public LookSessionsGUI(JFrame a) {
		
		BLFacade facade = MainGUI.getBusinessLogic();
		LookSessionsGUI b = this;
		
		setTitle("Consultar sesiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Grado de exigencia");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(96, 11, 123, 23);
		contentPane.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxModel.removeAllElements();
				List<String> activityList = facade.getGradosExigencia();
				
		        for (String i:activityList) comboBoxModel.addElement(i);
			}
		});
		
		
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Actividad");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(6, 11, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxModel.removeAllElements();
				List<String> activityList = facade.getActivities();
				
		        for (String i:activityList) comboBoxModel.addElement(i);
			}
		});
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(comboBoxModel);
		comboBox.setBounds(250, 11, 152, 22);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem()!=null) {
					if (rdbtnNewRadioButton.isSelected()==false) {
						listModel.removeAllElements();
						List<String> sesiones = facade.getSessionsWA(comboBox.getSelectedItem().toString());
						for (String i:sesiones) {
							listModel.addElement(i);
						}
					} 
					else {
						listModel.removeAllElements();
						List<String> sesiones = facade.getSessionsWGe(comboBox.getSelectedItem().toString());
						for (String i:sesiones) {
							listModel.addElement(i);
						}
					}
				}	
			}
		});

		
		JList list = new JList();
		list.setModel(listModel);
		list.setBounds(10, 65, 414, 160);
		contentPane.add(list);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setVisible(false);
				a.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 236, 63, 23);
		contentPane.add(btnNewButton_1);
	}
}
