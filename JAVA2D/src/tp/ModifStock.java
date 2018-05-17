package tp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import proj.Aliment;
/**
 * fenetre permettant de modifier un stock
 * 
 *
 */
public class ModifStock extends JFrame {
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
	public ModifStock() {
		Aliment aliment=new Aliment();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane jop = new JOptionPane();
		int id_aliment=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer le numero d'identification de l'aliment à modifier de l'aliment","Modification aliment",
		        JOptionPane.QUESTION_MESSAGE));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Modification des stocks");
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton("Nom");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							
							String nom=JOptionPane.showInputDialog(null,"Veuillez indiquer le nouveau nom de l'aliment","Modification aliment",
							        JOptionPane.QUESTION_MESSAGE);
							aliment.setNom(nom, id_aliment);
							
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

		JButton btnNewButton_1 = new JButton("Type");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						String type=JOptionPane.showInputDialog(null,"Veuillez indiquer le nouveau type de l'aliment","Modification aliment",
						        JOptionPane.QUESTION_MESSAGE);
						aliment.setType(type, id_aliment);
					}
				});
				//		Stock st= new Stock();

				//st.AddAliment(nom, ok, ok, frigo, 4);
			}
		});
		btnNewButton_1.setBounds(71, 50, 170, 20);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("conditionnement");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String conditionnement=JOptionPane.showInputDialog(null,"Veuillez indiquer le nouveau conditionnement de l'aliment","Modification aliment",
				        JOptionPane.QUESTION_MESSAGE);
				aliment.setConditionnement(conditionnement, id_aliment);
			}
		});
		btnNewButton_2.setBounds(71, 90,170, 20);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("id_emplacement");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id_emplacement=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer le nouvel identifiant de l'emplacement de l'aliment","Modification aliment",
				        JOptionPane.QUESTION_MESSAGE));
				aliment.setEmplacement(id_emplacement, id_aliment);
				
			}
		});
		btnNewButton_3.setBounds(71, 130, 170, 20);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("retour");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fenetre1 fen= new Fenetre1();
				System.out.println(fen);
			}
		});
		btnNewButton_4.setBounds(230, 200, 140, 20);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("quantite");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int quantite=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer la nouvelle quantité de l'aliment","Modification aliment",
				        JOptionPane.QUESTION_MESSAGE));
				aliment.setQuantite(quantite, id_aliment);
			}
		});
		btnNewButton_5.setBounds(71,130,140, 20);
		contentPane.add(btnNewButton_5);
	}

}

	

