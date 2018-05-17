package proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class Carte {
	private List<Plat> Disponible;
	
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
	  * Methode permettant d'ajouter un plat à la carte
	  * @param id_carte 
	  * @param id_plat
	  */
	public void addPlat(int id_carte, int id_plat) {
		 String sql = "INSERT INTO carte_plat(id_carte,id_plat) VALUES(?,?)";
		 
	        try (Connection conn = this.connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, id_carte);
	            pstmt.setInt(2, id_plat);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
	
	
	/**
	 * Méthode permettant d'effacer un plat de la carte
	 * @param id_plat
	 */
	public void delPlat(int id_plat) {
		String sql = "DELETE FROM plat WHERE id_plat=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,id_plat);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }	
		
	}
	
	
	
	/**Méthode qui permet de d'afficher tout les plats de la base de données
	 * @return NULL
	 * @version 0.1
	 */
public String AfficherCarte() {
	
	 String sql = "SELECT id_plat,nom FROM plat";
     String lol="";
	 
     try (Connection conn = this.connect();
          Statement stmt  = conn.createStatement();
          ResultSet rs    = stmt.executeQuery(sql)){
         
         // loop through the result set
         while (rs.next()) {
             lol=rs.getInt("id_plat") + "\t"+" " 
         +rs.getString("nom")+ "\n" ;
         }
     } catch (SQLException e) {
         System.out.println(e.getMessage());
     }
     return lol;
		
	}

}
