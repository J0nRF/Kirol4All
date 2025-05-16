package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;
import domain.User;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private User user;
	private JPanel jContentPane = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	/**
	 * This is the default constructor
	 */
	public MainGUI(User u) {
		super();
		
		user = u;
		MainGUI a = this;
		
		// this.setSize(271, 295);
		this.setSize(495, 225);
		
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		
		
		setContentPane(jContentPane);
		
		JButton btnNewButton = new JButton("Consultar Sesiones");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LookSessionsGUI b = new LookSessionsGUI(a);
				a.setVisible(false);
				b.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 11, 135, 74);
		jContentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reservar Sesion");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookSessionGUI c = new BookSessionGUI(a, user);
				a.setVisible(false);
				c.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(172, 11, 135, 74);
		jContentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancelar Reserva");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteBookingGUI d = new DeleteBookingGUI(a, user);
				a.setVisible(false);
				d.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(334, 11, 135, 74);
		jContentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Consultar Facturas");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LookBillsGUI f = new LookBillsGUI(a, user);
				a.setVisible(false);
				f.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(10, 96, 210, 74);
		jContentPane.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Realizar Pagos");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PayBillsGUI g = new PayBillsGUI(a, user);
				a.setVisible(false);
				g.setVisible(true);
			}
		});
		btnNewButton_3_1.setBounds(253, 96, 216, 74);
		jContentPane.add(btnNewButton_3_1);
		setTitle("Usuario");
	}
}

