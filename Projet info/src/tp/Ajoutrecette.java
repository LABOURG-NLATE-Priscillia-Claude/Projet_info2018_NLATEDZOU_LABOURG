package tp;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import proj.Aliment;
import proj.Recette;

public class Ajoutrecette extends JFrame{
	public Ajoutrecette() {
		Recette rec=new Recette();
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();//créer une boite de dialogue avec une question
		String nom = jop.showInputDialog(null, "Veuillez indiquer le nom de la recette", "Création recette",
		JOptionPane.QUESTION_MESSAGE);
		String indication=jop.showInputDialog(null, "Veuillez indiquer les indications de la recette", "Création recette",
		JOptionPane.QUESTION_MESSAGE);
		int duree_cuisson=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer la durée de cuisson de la recette","Création recette",
		        JOptionPane.QUESTION_MESSAGE));
		int duree_preparation=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer la durée de préparation de la recette","Création recette",
		        JOptionPane.QUESTION_MESSAGE));
		int calorie=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer le nombre de calorie apportés par la préparation","Création recette",
		        JOptionPane.QUESTION_MESSAGE));
		String difficulté=jop.showInputDialog(null, "Veuillez indiquer la difficulté(facile,moyen,difficile) de la recette", "Création recette",
				JOptionPane.QUESTION_MESSAGE);
		String soustype=jop.showInputDialog(null, "Veuillez indiquer le pays de provenance de la recette", "Création recette",
				JOptionPane.QUESTION_MESSAGE);
		int id_plat=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer le numéro d'identification du plat auquel est lié la recette","Création recette",
		        JOptionPane.QUESTION_MESSAGE));
		
		rec.addRecette(nom, indication, duree_preparation, duree_cuisson, difficulté, soustype, id_plat);
	}

}
