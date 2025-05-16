package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EncargadoGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public EncargadoGUI() {
		setTitle("Menu encargado");
		
		EncargadoGUI a = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Añadir actividad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddActivityGUI b = new AddActivityGUI(a);
				a.setVisible(false);
				b.setVisible(true);
			}
		});
		btnNewButton.setBounds(35, 35, 160, 70);
		contentPane.add(btnNewButton);
		
		JButton btnConsultarSesiones = new JButton("Consultar sesiones");
		btnConsultarSesiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LookSessionsGUI c = new LookSessionsGUI(a);
				a.setVisible(false);
				c.setVisible(true);
			}
		});
		btnConsultarSesiones.setBounds(242, 35, 160, 70);
		contentPane.add(btnConsultarSesiones);
		
		JButton btnPlanificarSesion = new JButton("Planificar sesion");
		btnPlanificarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSessionGUI d = new AddSessionGUI(a);
				a.setVisible(false);
				d.setVisible(true);
			}
		});
		btnPlanificarSesion.setBounds(35, 116, 160, 70);
		contentPane.add(btnPlanificarSesion);
		
		JButton btnEnviarFacturas = new JButton("Enviar facturas");
		btnEnviarFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendBillGUI f = new SendBillGUI(a);
				a.setVisible(false);
				f.setVisible(true);
			}
		});
		btnEnviarFacturas.setBounds(242, 116, 160, 70);
		contentPane.add(btnEnviarFacturas);
	}
}
