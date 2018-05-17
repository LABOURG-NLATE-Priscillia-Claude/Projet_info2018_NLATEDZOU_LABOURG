package tp;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import proj.Aliment;
/**
 * fenetre  pourl'ajout d'un aliment dans le stock
 * @author 
 *
 */
public class AjoutStock extends JFrame {
	
	public AjoutStock() {
		Aliment aliment=new Aliment();
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();//créer une boite de dialogue avec une question
		String nom = jop.showInputDialog(null, "Veuillez indiquer le nom de l'aliment", "Création aliment",
		JOptionPane.QUESTION_MESSAGE);
		String type=jop.showInputDialog(null, "Veuillez indiquer le type de l'aliment", "Création aliment",
		JOptionPane.QUESTION_MESSAGE);
		String conditionnement=jop.showInputDialog(null, "Veuillez indiquer le conditionnement de l'aliment", "Création aliment",
				JOptionPane.QUESTION_MESSAGE);
		int id_emplacement=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer l'identifiant de l'emplacement de l'aliment","Création aliment",
		        JOptionPane.QUESTION_MESSAGE));
		int quantite=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer la quantité en gramme d'aliment ajouté","Création aliment",
		        JOptionPane.QUESTION_MESSAGE));
		int calorie=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer les calories pour 100 gramme d'aliment","Création aliment",
		        JOptionPane.QUESTION_MESSAGE));
		aliment.AddAliment(nom.toUpperCase(), type.toUpperCase(), conditionnement.toUpperCase(), id_emplacement, quantite,calorie);
		}

}

