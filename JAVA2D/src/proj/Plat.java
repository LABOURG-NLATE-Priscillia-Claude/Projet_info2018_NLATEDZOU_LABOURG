package proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Plat {
	public String nomPlat;
	public Type type;
	
	
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
	   * Méthode permettant de créer un plat
	   * @param nom
	   * @param type
	   */
	public void addPLat(String nom,String type) {
		String sql ="INSERT INTO plat(nom,type) VALUES(?,?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, type);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	
	/**
	 * Méthode permettant d'obtenir le nom d'un plat à partir de son numero d'identification
	 * @param id_plat
	 */
	public void getNom(int id_plat) {
		String sql = "SELECT nom FROM plat WHERE id_plat=?";
        try (Connection conn = this.connect();
             PreparedStatement ptmt  = conn.prepareStatement(sql)){
        		ptmt.setInt(1, id_plat);
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
	 * Méthode permettant de modifier le nom d'un plat àpartir de son numéro d'identification et de son nouveau nom
	 * @param nomPlat
	 * @param id_plat
	 */
	public void setNom(String nomPlat, int id_plat) {
		String sql = "UPDATE plat SET nom = ? WHERE id_plat=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,nomPlat);
            pstmt.setInt(2,id_plat);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		this.nomPlat = nomPlat;
	}
	
	
	
	
	
	
	/**
	 * Méthode permettant de voir le type d'un plat à partir de son numero d'identification
	 * @param id_plat
	 */
	public void getType(int id_plat) {
		String sql = "SELECT type FROM plat WHERE id_plat=?";
        try (Connection conn = this.connect();
             PreparedStatement ptmt  = conn.prepareStatement(sql)){
        		ptmt.setInt(1, id_plat);
             ResultSet rs    = ptmt.executeQuery();
            // loop through the result set
        	
            while (rs.next()) {
                System.out.print(rs.getString("type")+"\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
	}
	
	
	
	
	
	
	
	/**
	 * Méthode permettant de modifier le type d'une fonction à partir de son numerod'identification
	 * @param type
	 * @param id_plat
	 */
	public void setType(String type,int id_plat) {
		String sql = "UPDATE plat SET type = ? WHERE id_plat=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,type);
            pstmt.setInt(2,id_plat);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
        
}
