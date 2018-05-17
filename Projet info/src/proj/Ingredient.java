package proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ingredient {
	
	
	/**Méthode permettant de se connecter à la base de données
 	 * @param NULL
 	 * @return NULL
 	 * @author sqlite tutorial
 	 * @version ?
     */
	 private Connection connect() {
		  
	        // SQLite connection string
	        String url = "jdbc:sqlite:STOCK";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	 
	 
	 
	 	/**
	 	 * Permet d'ajouter un ingrédient et de l'assigner à une recette
	 	 * @param id_recette
	 	 * @param nom
	 	 * @param quantite
	 	 * @param conditionnement
	 	 */
		public void setIngredient(int id_recette, String nom,int quantite,String conditionnement) {
			String sql ="INSERT INTO ingredients(id_recette,nom,quantite,conditionnement) VALUES(?,?,?,?)";
	        try (Connection conn = this.connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, nom);
	            pstmt.setString(2, nom);
	            pstmt.setInt(3,quantite);
	            pstmt.setString(4,conditionnement);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		}
	 
	 
	


}
