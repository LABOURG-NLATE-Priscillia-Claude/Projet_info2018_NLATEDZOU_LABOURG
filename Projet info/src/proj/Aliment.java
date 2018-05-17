package proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Aliment {
	
	public String nom;
	public String type; 
	public int calorie;
	public int id_emplacement;
	public String conditionnement;
	public int quantite;
	
	
	
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
	    * Méthode permettant de créer un aliment dans la base de données
	    * @param nom 
	    * @param type (feculent, legume, fruit,....)
	    * @param conditionnement (frais,sauce, surgele,...)
	    * @param id_emplacement numero d'identification de l'emplacement où se trouvera l'aliment
	    * @param quantite quantité en gramme de l'aliment ajouté
	    */
		public void AddAliment(String nom,String type,String conditionnement,int id_emplacement,int quantite,int calorie) {
			String sql ="INSERT INTO stock(nom,type,conditionnement,id_emplacement,quantite,calorie) VALUES(?,?,?,?,?,?)";
			        try (Connection conn = this.connect();
			                PreparedStatement pstmt = conn.prepareStatement(sql)) {
			            pstmt.setString(1, nom);
			            pstmt.setString(2, type);
			            pstmt.setString(3,conditionnement);
			            pstmt.setInt(4,id_emplacement);
			            pstmt.setInt(5, quantite);
			            pstmt.setInt(6, calorie);
			            pstmt.executeUpdate();
			        } catch (SQLException e) {
			            System.out.println(e.getMessage());
			        }
		}
	
		
		
		/**Méthode permettant de consulter les aliments présent dans tout les emplacements enregistrés
		 * @param NULL
		 * @return NULL
		 * @author Priscillia LABOURG
		 * @version 0.1	 */
		public String getAliment() {
			 String sql = "SELECT id_aliment,nom,type,conditionnement,id_emplacement,quantite FROM stock";
		      String lol="<html>";  
		        try (Connection conn = this.connect();
		             Statement stmt  = conn.createStatement();
		             ResultSet rs    = stmt.executeQuery(sql)){
		            
		            // loop through the result set
		            while (rs.next()) {
		                lol=lol+ rs.getInt("id_aliment")+ "\t"
		                		+rs.getString("nom") +"\t"
		                		+ rs.getString("type") + "\t"
		                		+ rs.getString("conditionnement")+ "\t"
		                		+ rs.getString("id_emplacement") + "\t"
		                		+rs.getInt("quantite")+ "<br/>";
		            }
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		        return lol+"</br></html>";
		}
	   
	 
		
		
		/**Méthode qui permet de récupérer le nom d'un aliment en stock à partir de son id
		 * @param id_aliment numero d'identification de l'aliment
		 * @return NULL
		 * @version 0.1
		 */	
	public String getNom(int id_aliment) {
		String sql = "SELECT nom FROM stock WHERE id_aliment=?";
		String lol="";
	    try (Connection conn = this.connect();
	         PreparedStatement ptmt  = conn.prepareStatement(sql)){
	    		ptmt.setInt(1, id_aliment);
	         ResultSet rs    = ptmt.executeQuery();
	        // loop through the result set
	    	
	        while (rs.next()) {
	            lol=rs.getString("nom")+ "\n";
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return lol;
	}
	
	
	
	
	/**
	 * Méthode permettant de modifier le nom d'un aliment de la base de données à partir de son numero d'identification
	 * @param nom nouveau nom de l'aliment
	 * @param id_aliment numero d'identification de l'aliment
	 */
	
	public void setNom(String nom,int id_aliment) {
		String sql = "UPDATE stock SET nom = ? WHERE id_aliment=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(2,id_aliment);
            pstmt.setString(1,nom);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }	
		this.nom = nom;
	}

	
	
	
	
	/**
	 * Méthode qui permet de voir le type d'un aliment à partir de son numero d'identification
	 * @param id_aliment
	 */
	public String getType(int id_aliment) {
		String sql = "SELECT type FROM stock WHERE id_aliment=?";
		String lol="";
	    try (Connection conn = this.connect();
	         PreparedStatement ptmt  = conn.prepareStatement(sql)){
	    		ptmt.setInt(1, id_aliment);
	         ResultSet rs    = ptmt.executeQuery();
	        // loop through the result set
	    	
	        while (rs.next()) {
	            lol=rs.getString("type")+ "\n";
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return lol;
	}
	
	
	
	
	/**
	 * Méthode qui permet de modifier le type d'un aliment à partir de son numéro d'identification
	 * @param type
	 * @param id_aliment
	 */
	public void setType(String type,int id_aliment) {
		String sql = "UPDATE stock SET type = ? WHERE id_aliment=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,type.toUpperCase());
            pstmt.setInt(2,id_aliment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	
		
	
		/**
		 * Méthode permettant de récupérer le nombre de calorie pour 100 grammes d'un aliment à partir de son numéro d'identification
		 * @param id_aliment
		 * @return integer calorie
		 */
	public int getCalorie(int id_aliment) {
		String sql = "SELECT calorie FROM stock WHERE id_aliment=?";
	    try (Connection conn = this.connect();
	         PreparedStatement ptmt  = conn.prepareStatement(sql)){
	    		ptmt.setInt(1, id_aliment);
	         ResultSet rs    = ptmt.executeQuery();
	        // loop through the result set
	    	
	        while (rs.next()) {
	            System.out.print(rs.getInt("calorie")+ "\n");
	            int calorie=rs.getInt("calorie");
	            
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return calorie;
	}
	
	
	
	
	
	/**Méthode permettant de modifier les calories pour 100 grammes d'un aliment
	 * @param calorie nouvelle valeur des calorie pour 100 grammes
	 * @param id_aliment numero d'identification de l'aliment pour lequel on veut modifier la valeur
	 * @return NULL
	 * @version 0.1 
	 */
	public void setCalorie(int calorie,int id_aliment) {
		String sql = "UPDATE stock SET calorie = ? WHERE id_aliment=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,calorie);
            pstmt.setInt(2,id_aliment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		this.calorie = calorie;
	}
	
	
	
	
	
	/**Méthode permettant de récupérer l'emplacement d'un aliment à partir de son numéro d'identification
	 * @param id_aliment numero d'identification de l'aliment dont on veut avoir l'emplacement
	 * @return NULL
	 * @version 0.1
	 */
	public void getEmplacement(int id_aliment) {
		
		String sql = "SELECT id_emplacement FROM stock WHERE id_aliment=?";
	    try (Connection conn = this.connect();
	         PreparedStatement ptmt  = conn.prepareStatement(sql)){
	    		ptmt.setInt(1, id_aliment);
	         ResultSet rs    = ptmt.executeQuery();
	        // loop through the result set
	    	
	        while (rs.next()) {
	            System.out.print(rs.getInt("id_emplacement")+ "\n");
	            
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	
	
	
	/**
	 * Méthode permettant de modifier l'emplacement d'un aliment grâce à son numero d'identification et le numero d'identification de son nouvel emplacement
	 * @param id_emplacement numero d'identification de son nouvel emplacement
	 * @param id_aliment numero d'identification de l'aliment
	 */
	public void setEmplacement(int id_emplacement, int id_aliment) {
		String sql = "UPDATE stock SET id_emplacement = ? WHERE id_aliment=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,id_emplacement);
            pstmt.setInt(2,id_aliment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	




/**
 * Méthode permettant d'obtenir la quantité d'un aliment à partir de son numero d'ide9ntification
 * @param id_aliment
 * @return
 */
public int getQuantite(int id_aliment) {
	String sql = "SELECT quantite FROM stock WHERE id_aliment=?";
    try (Connection conn = this.connect();
         PreparedStatement ptmt  = conn.prepareStatement(sql)){
    		ptmt.setInt(1, id_aliment);
         ResultSet rs    = ptmt.executeQuery();
        // loop through the result set
    	
        while (rs.next()) {
            System.out.print(rs.getInt("quantite")+ "\n");
            int quantite =rs.getInt("quantite");
            
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    	}
    return quantite;
	}






/** Méthode permettant de modifier l'emplacement d'un aliment à partir de son numero d'identifiaction
 * @emplacement numero d'identification du nouvel emplacement de l'objet
 * @param id_aliment numerod'identification de l'aliment dont on souhaite modifier l'emplacement
 * @return NULL
 * @version 0.1
 */
public void setQuantite(int quantite, int id_aliment) {
	
	String sql = "UPDATE stock SET quantite = ? WHERE id_aliment=?";
  	  
    try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1,quantite);
        pstmt.setInt(2,id_aliment);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}




/**Méthode permettant de récupérer l'emplacement d'un aliment à partir de son numéro d'identification
 * @param id_aliment numero d'identification de l'aliment dont on veut avoir l'emplacement
 * @return NULL
 * @version 0.1
 */
public void getConditionnement(int id_aliment) {
	
	String sql = "SELECT conditionnement FROM stock WHERE id_aliment=?";
    try (Connection conn = this.connect();
         PreparedStatement ptmt  = conn.prepareStatement(sql)){
    		ptmt.setInt(1, id_aliment);
         ResultSet rs    = ptmt.executeQuery();
        // loop through the result set
    	
        while (rs.next()) {
            System.out.print(rs.getString("conditionnement")+ "\n");
            
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    	}
	}





/** Méthode permettant de modifier l'emplacement d'un aliment à partir de son numero d'identifiaction
 * @param emplacement numero d'identification du nouvel emplacement de l'objet
 * @param id_aliment numerod'identification de l'aliment dont on souhaite modifier l'emplacement
 * @return NULL
 * @version 0.1
 */

public void setConditionnement(String conditionnement, int id_aliment) {
	String sql = "UPDATE stock SET conditionnement = ? WHERE id_aliment=?";
  	  
    try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1,conditionnement);
        pstmt.setInt(2,id_aliment);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
}
