package gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import businessLogic.BLFacade;
import domain.Activity;
import domain.Room;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddSessionGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	DefaultComboBoxModel<String> rooms = new DefaultComboBoxModel<String>();
	
	DefaultComboBoxModel<String> activities = new DefaultComboBoxModel<String>();

	/**
	 * Create the frame.
	 */
	public AddSessionGUI(EncargadoGUI a) {
		
		BLFacade facade = MainGUI.getBusinessLogic();
		AddSessionGUI b = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	     setTitle("Añadir Sesion");
	     setSize(350, 200);

	        JDateChooser dateChooser = new JDateChooser();
	        dateChooser.setBounds(138, 5, 70, 20);

	        SpinnerNumberModel hourModel = new SpinnerNumberModel(12, 0, 23, 1);
	        JSpinner hourSpinner = new JSpinner(hourModel);
	        hourSpinner.setBounds(245, 5, 39, 20);
	        contentPane.setLayout(null);

	        JLabel lblFecha = new JLabel("Fecha:");
	        lblFecha.setBounds(49, 8, 84, 14);
	        contentPane.add(lblFecha);
	        contentPane.add(dateChooser);
	        JLabel label_1 = new JLabel("Hora:");
	        label_1.setBounds(213, 8, 27, 14);
	        contentPane.add(label_1);
	        contentPane.add(hourSpinner);
	        
	        List<String> roomList = facade.getRooms();
	        
	        for (String i:roomList) rooms.addElement(i);
	        
	        JComboBox<String> comboBox = new JComboBox<String>();
	        comboBox.setModel(rooms);
	        comboBox.setBounds(138, 36, 147, 22);
	        contentPane.add(comboBox);
	        
	        List<String> activityList = facade.getActivities();
	        
	        for (String i:activityList) activities.addElement(i);
	        
	        JComboBox<String> comboBox_1 = new JComboBox<String>();
	        comboBox_1.setModel(activities);
	        comboBox_1.setBounds(138, 69, 146, 22);
	        contentPane.add(comboBox_1);
	        
	        JLabel lblNewLabel = new JLabel("Sala:");
	        lblNewLabel.setBounds(49, 40, 46, 14);
	        contentPane.add(lblNewLabel);
	        
	        JLabel lblNewLabel_1 = new JLabel("Actividad:");
	        lblNewLabel_1.setBounds(49, 73, 70, 14);
	        contentPane.add(lblNewLabel_1);
	        
	        JLabel Errorlbl = new JLabel("");
	        Errorlbl.setBounds(114, 136, 186, 14);
	        contentPane.add(Errorlbl);
	        
	        JButton btnNewButton = new JButton("Añadir sesion");
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		Errorlbl.setText("");
	        		
	        		try {
	        			
	        			Date date = dateChooser.getDate();
	       	            date.setHours((int) hourSpinner.getValue());
	        			
	        			facade.addSession(comboBox.getSelectedItem().toString(), date, comboBox_1.getSelectedItem().toString());
	        			
	        		} catch (Exception e1) {
	        			Errorlbl.setText(e1.getMessage());
	        		}     		
	        	}
	        });
	        btnNewButton.setBounds(114, 102, 102, 23);
	        contentPane.add(btnNewButton);
	        
	        JButton btnNewButton_1 = new JButton("Volver");
	        btnNewButton_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		b.setVisible(false);
					a.setVisible(true);
	        	}
	        });
	        btnNewButton_1.setBounds(10, 136, 63, 23);
	        contentPane.add(btnNewButton_1);
	}
}
