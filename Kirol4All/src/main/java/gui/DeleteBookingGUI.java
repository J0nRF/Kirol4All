package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Booking;
import domain.User;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class DeleteBookingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultListModel<String> listModel = new DefaultListModel<String>();

	/**
	 * Create the frame.
	 */
	public DeleteBookingGUI(MainGUI a, User u) {
		setTitle("Cancelar reserva");
		
		BLFacade facade = MainGUI.getBusinessLogic();
		DeleteBookingGUI b = this;
		
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
		btnNewButton.setBounds(10, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		List<String> activityList = facade.getBookingsFromUser(u);		
        for (String i:activityList) listModel.addElement(i);
		
		JList<String> list = new JList<String>();
		list.setModel(listModel);
		list.setBounds(10, 11, 414, 205);
		contentPane.add(list);
		
		JLabel Errorlbl = new JLabel("");
		Errorlbl.setBounds(109, 231, 216, 14);
		contentPane.add(Errorlbl);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Errorlbl.setText("");
				if (list.getSelectedValue()!=null) {
					
					String[] s = list.getSelectedValue().toString().split(", ");
					
					try {
						String res =facade.deleteBooking(Integer.parseInt(s[0]));
						Errorlbl.setText(res);
						
						listModel.removeAllElements();
						List<String> activityList = facade.getBookingsFromUser(u);		
				        for (String i:activityList) listModel.addElement(i);
					} catch (Exception e1) {
						Errorlbl.setText(e1.getMessage());
					}
				}
				else {
					Errorlbl.setText("No has seleccionado ninguna sesion");
				}
			}
		});
		btnNewButton_1.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
