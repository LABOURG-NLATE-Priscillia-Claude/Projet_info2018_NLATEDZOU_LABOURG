package tp;
import proj.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * fenetre affiche une boite de dialogue permettant de supprimer un element du stock
 *
 */
public class RemoveAliment extends JFrame {
	public RemoveAliment() {
	Stock stock=new Stock();
	JOptionPane jop = new JOptionPane();
	int id_aliment=Integer.parseInt( JOptionPane.showInputDialog(null,"Veuillez indiquer l'identifiant de l'aliment à supprimer","Suppression aliment",
	        JOptionPane.QUESTION_MESSAGE));
	stock.delAliment(id_aliment);
}
}
