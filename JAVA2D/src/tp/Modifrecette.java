package tp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import proj.Aliment;
import proj.Recette;

public class Modifrecette extends JFrame {
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
	public Modifrecette() {
		Recette rec=new Recette();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane jop = new JOptionPane();
		int id_recette=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer le numero d'identification de la recette à modifier ","Modification recette",
		        JOptionPane.QUESTION_MESSAGE));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Modification recette");
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton("Nom");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							
							String nom=JOptionPane.showInputDialog(null,"Veuillez indiquer le nouveau nom de la recette","Modification recette",
							        JOptionPane.QUESTION_MESSAGE);
							rec.setNom(nom.toUpperCase(), id_recette);
							
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

		JButton btnNewButton_1 = new JButton("SousType");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						String type=JOptionPane.showInputDialog(null,"Veuillez indiquer le nouveau pays d'origine de la recette","Modification recette",
						        JOptionPane.QUESTION_MESSAGE);
						rec.setSoustype(type.toUpperCase(), id_recette);
					}
				});
				//		Stock st= new Stock();

				//st.AddAliment(nom, ok, ok, frigo, 4);
			}
		});
		btnNewButton_1.setBounds(71, 50, 170, 20);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("difficulté");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String difficulte=JOptionPane.showInputDialog(null,"Veuillez indiquer la nouvelle difficulté de la recette","Modification recette",
				        JOptionPane.QUESTION_MESSAGE);
				rec.setDifficulte(difficulte, id_recette);
			}
		});
		btnNewButton_2.setBounds(71, 90,170, 20);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("id_plat");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int duree_cuisson=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer le nouveau temps de cuisson de la recette","Modification recette",
				        JOptionPane.QUESTION_MESSAGE));
				rec.setDureeCuisson(duree_cuisson, id_recette);
				
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

}
}
