package tp;

import proj.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * fenetre qui affiche les differentes mise à jour qu'on peut faire dans un stock
 * 
 *
 */
public class GestionStock extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionStock frame= new GestionStock();
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
	public GestionStock() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Gestion des stock");
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("consulter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ConsultStock frame = new ConsultStock();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				//Aliment st= new Aliment();
				//st.getAliment();

			}
		});
		btnNewButton.setBounds(71, 10, 153, 40);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Ajouter aliment");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AjoutStock ms=new AjoutStock();
							ms.setVisible(true);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
				//		Stock st= new Stock();

				//st.AddAliment(nom, ok, ok, frigo, 4);
			}
		});
		btnNewButton_1.setBounds(71, 50, 153, 40);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("retirer aliment");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveAliment rem=new RemoveAliment();
				rem.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(71, 90,153, 40);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("modifier  aliment");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModifStock bn=new ModifStock(); 
				bn.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(71, 130, 153, 40);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("retour");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fenetre1 fen= new Fenetre1();
				System.out.println(fen);
			}
		});
		btnNewButton_4.setBounds(230, 200, 140, 20);
		contentPane.add(btnNewButton_4);
	}

}
