package tp;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import proj.Aliment;
import proj.Carte;

import java.awt.Panel;
import javax.swing.JTable;
/**
 * fenetre permettant d'afficher la carte
 * @author 
 *
 */

public class ConsultCard extends JFrame {

	private JPanel contentPane;
	Carte rs= new Carte();
	
	JLabel pan=new JLabel(rs.AfficherCarte());
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultCard frame = new ConsultCard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultCard() {
		
		setTitle("La CARTE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(pan);
		
	
		
	}

}
