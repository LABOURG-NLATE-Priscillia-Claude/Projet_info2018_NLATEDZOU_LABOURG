package proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stock {
	String nom;
	 private Connection connect() {
		 	/**Méthode permettant de se connecter à la base de données
		 	 * @param NULL
		 	 * @return NULL
		 	 * @author sqlite tutorial
		 	 * @version ?
	         */
	        String url = "jdbc:sqlite:STOCK";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }



	public void setAliment(int id_aliment, int id_emplacement) {
		/**Méthode permettant de changer l'emplacement d'un aliment
		 * @id_aliment numero d'identification de l'aliment à déplacer
		 * @id_emplacement numero d'identification du nouvel emplacement de l'aliment
		 * @return NULL
		 * @author Priscillia LABOURG
		 * @version 0.1
		 */
		 String sql = "UPDATE stock SET id_emplacement = ? "
		 		+ "WHERE id_aliment=?";
		 
	        try (Connection conn = this.connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, id_emplacement);
	            pstmt.setInt(2, id_aliment);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}

		
public void delAliment(int id_aliment) {
	/**Méthode permettant la suppression d'un aliment sélectionné
	 * @id_aliment Numero d'identification de l'aliment à supprimer
	 * @return NULL
	 * @author LABOURG Priscillia
	 * @version 0.1
	 */
	String sql="DELETE FROM stock WHERE id_aliment=? ";
	 try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_aliment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
}
