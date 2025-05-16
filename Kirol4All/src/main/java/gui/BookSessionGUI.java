package gui;

import java.awt.EventQueue;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Booking;
import domain.User;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class BookSessionGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
	DefaultListModel<String> listModel = new DefaultListModel<String>();
	
	/**
	 * Create the frame.
	 */
	public BookSessionGUI(MainGUI a, User u) {
		
		BLFacade facade = MainGUI.getBusinessLogic();
		BookSessionGUI b = this;
		
		setTitle("Reservar sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		List<String> activityList = facade.getActivities();		
        for (String i:activityList) comboBoxModel.addElement(i);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(comboBoxModel);
		comboBox.setBounds(10, 11, 137, 22);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem()!=null) {
					listModel.removeAllElements();
					List<String> sesiones = facade.getSessionsWA(comboBox.getSelectedItem().toString());
					for (String i:sesiones) {
						listModel.addElement(i);
					}	
				}
			}			
		});

		JLabel Errorlbl = new JLabel("");
		Errorlbl.setBounds(182, 231, 242, 14);
		contentPane.add(Errorlbl);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setVisible(false);
				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 227, 69, 23);
		contentPane.add(btnNewButton);
		
		JList<String> list = new JList<String>();
		list.setModel(listModel);
		list.setBounds(10, 44, 414, 172);
		contentPane.add(list);
		
		JButton btnNewButton_1 = new JButton("Reservar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Errorlbl.setText("");
				if (list.getSelectedValue()!=null) {
					
					String[] s = list.getSelectedValue().toString().split(", ");
					SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
					
					try {
						
						Booking b =facade.bookSession(s[0], formatter.parse(s[1]), u);
						if (b!=null) {
							Errorlbl.setText("Reserva realizada");
						}
					} catch (Exception e1) {
						Errorlbl.setText(e1.getMessage());
					}
				}
				else {
					Errorlbl.setText("No has seleccionado ninguna sesion");
				}
			}
		});
		btnNewButton_1.setBounds(83, 227, 89, 23);
		contentPane.add(btnNewButton_1);
		
	}
}
