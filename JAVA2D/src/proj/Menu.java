package proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Menu {
	public String nom;
	private String  Recette;
	
	
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
	
	
	
	public void addMenu(String nom,String type) {
		
	}
	
	
	/**
	 * Méthode qui permet d'obtenir le nom d'un menu à partir de son numero d'identification
	 * @param id_menu
	 * @return
	 */
	public String getNom(int id_menu) {
		 String sql = "SELECT nom FROM menu WHERE id_menu=?";
	        
	        try (Connection conn = this.connect();
	             PreparedStatement ptmt  = conn.prepareStatement(sql)){
	        	ptmt.setInt(1, id_menu);
	             ResultSet rs    = ptmt.executeQuery();
	            // loop through the result set
	            while (rs.next()) {
	                System.out.print(rs.getString("nom")+ "\n");
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
		return nom;
	}

	
	
	
	
	/**
	 * Méthode qui permet de modifier le nom d'un menu à partir de son numéro d'identification et du  nouveau nom
	 * @param id_menu
	 * @param nom
	 */
	public void setNom(int id_menu,String nom) {
		String sql = "UPDATE menu SET nom = ? WHERE id_menu=?";
  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1,nom);
            pstmt.setInt(2,id_menu);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }	
		this.nom = nom;
	}
	/**
	 * Méthode qui permet de connaitre les plats liés à un menu à partir de l'identifiant du menu
	 * @param id_menu
	 */
	public void getPlat(int id_menu) {
		String sql = "SELECT plat.nom FROM plat,menuplat WHERE menuplat.id_menu=? AND menuplat.id_plat=plat.id_plat";

        try (Connection conn = this.connect();
             PreparedStatement ptmt  = conn.prepareStatement(sql)){
        	ptmt.setInt(1, id_menu);
             ResultSet rs    = ptmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                System.out.print(rs.getString("nom")+"\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	  	
	}
	
	
	/**
	 * Méthode permettant d'ajouter un plat à un menu à partir des numero d'identification des deux entités
	 * @param plat
	 * @param menu
	 */
	public void setPlat(int plat,int menu) {
		 String sql = "INSERT INTO menuplat(id_menu,id_plat) VALUES(?,?)";
		 
	        try (Connection conn = this.connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(2, plat);
	            pstmt.setInt(1, menu);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
	}
	
 
	
}
