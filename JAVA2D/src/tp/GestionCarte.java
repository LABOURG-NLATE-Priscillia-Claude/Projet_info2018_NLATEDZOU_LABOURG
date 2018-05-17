package tp;
import proj.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * fenetre qui affiche les differentes mise à jour qu'on peut faire sur une carte
 * 
 *
 */
public class GestionCarte extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionCarte frame = new GestionCarte();
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
	public GestionCarte() {
		JOptionPane jop = new JOptionPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Gestion de la carte");
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Ajouter un plat a la carte");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Plat plat=new Plat();
				String nom = jop.showInputDialog(null, "Veuillez indiquer le nom du plat", "Création plat",
						JOptionPane.QUESTION_MESSAGE);
				String type = jop.showInputDialog(null, "Veuillez indiquer le type du plat(entree,plat,dessert)", "Création plat",
						JOptionPane.QUESTION_MESSAGE);
				plat.addPLat(nom, type);
			}
		});
		btnNewButton.setBounds(71, 10, 200, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Retirer un plat de la carte");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Carte carte=new Carte();
				int id_plat=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer l'identifiant du plat","Suppression plat",
				        JOptionPane.QUESTION_MESSAGE));
				carte.delPlat(id_plat);
			}
		});
		btnNewButton_1.setBounds(71, 50, 200, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Consulter la carte");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		});
		btnNewButton_2.setBounds(71, 90,200, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Afficher une recette");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id_recette=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer l'identifiant du plat","Suppression plat",
				        JOptionPane.QUESTION_MESSAGE));
				Recette rec=new Recette();
				rec.VoirRecette( id_recette);
			}
		});
		btnNewButton_3.setBounds(71, 130, 200, 25);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Modifier une recette");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnNewButton_4.setBounds(71, 170, 200, 25);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Ajouter une recette");
		btnNewButton_5.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Ajoutrecette rec=new Ajoutrecette();
						rec.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		});
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_5.setBounds(71, 210, 200, 25);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Retour");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_6.setBounds(230, 250, 120, 20);
		contentPane.add(btnNewButton_6);
	}
	}


