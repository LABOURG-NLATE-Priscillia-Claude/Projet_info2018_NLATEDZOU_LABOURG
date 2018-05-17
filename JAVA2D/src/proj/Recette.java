package proj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;


public class Recette {
  
	public int dureeCuisson;
	public String Indications; 
	public int DureePreparation;
	public String ListeIngredient;
	public int CalPArPersonne;
	public String Difficulte;
	public Recette() {
		
	}
	
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
	   
	   
	   
	   
	
	public void addRecette(String nom, String indication,int duree_preparation,int duree_cuisson,String difficulté,String soustype,int id_plat) {
		/**Méthode permettant d'enregistrer une recette dans la base de données
		 * @nom nom de la nouvelle recette
		 * @indications Description de l arealisation de la nouvelle recette
		 * @duree_preparation durée de préparation de la nouvelle recette
		 * @durée_cuisson durée de cuisson de la nouvelle recette
		 * @difficulté Difficulté de la nouvelle recette
		 * @soustype Pays d'origine de la nouvelle recette
		 * @id-plat numero d'identification de la nouvelle recette
		 * @return void
		 * @version 0.1
		 * 
		 */
		String sql ="INSERT INTO recette(nom,indication,duree_preparation,duree_cuisson,difficulte,soustype,id_plat) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, indication);
            pstmt.setInt(3,duree_preparation);
            pstmt.setInt(4,duree_cuisson);
            pstmt.setString(5, difficulté);
            pstmt.setString(6, soustype);
            pstmt.setInt(7,id_plat);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	
	
	
	/**Méthode permettant de recuperer le string des informations d'une recette à partir de son numéro d'identification
	 * @param id_recette numero d'identification de la recette
	 * @return NULL
	 * @version 0.1
	 */
	public String VoirRecette(int id_recette) {
		
		 String sql = "SELECT nom,indication,duree_preparation,duree_cuisson,difficulte,soustype FROM recette WHERE id_recette=?";
	     String lol="";
		 try (Connection conn = this.connect();
	             PreparedStatement ptmt  = conn.prepareStatement(sql)){
	        	ptmt.setInt(1, id_recette);
	             ResultSet rs    = ptmt.executeQuery();
	            // loop through the result set
	        	
	           lol="<html>"+rs.getString("nom")+ "</br>"
	           + getListeIngredient(id_recette)
	           + "</br>"+rs.getString("indication")+"</html>";
	           
	           
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		 return lol;
		
	}
	
	
	/**Permet de déduire du stock un ingrédient à partir de son numéro d'identification et de la recette auquel il appartient
	 * @id_recette numero d'identification de la recette que l'on souhaite commander
	 * 
	 */
	public void DepletionStock(int id_ingredient,int id_recette) {
		
		Aliment aliment=new Aliment();
		int quant=0;
		int frais=0;
		int id=0;
		String sql = "SELECT stock.id_aliment,stock.quantite,ingredient.quantites FROM ingredient,stock "
				+ "WHERE id_ingredient=? "
		 		+ "AND stock.nom=ingredient.nom "
		 		+ "AND stock.conditionnement=ingredient.conditionnement "
		 		+ "AND stock.quantite-ingredient.quantites>0 "
		 		+ "AND ingredient.id_recette=id_recette";
		 		
	        try (Connection conn = this.connect();
	             PreparedStatement ptmt  = conn.prepareStatement(sql)){
	        		ptmt.setInt(1, id_ingredient);
	             ResultSet rs    = ptmt.executeQuery();
	            // loop through the result set
	        	
	            	quant=rs.getInt("quantite");
	            	frais=rs.getInt("quantites");
	            	id=rs.getInt("id_aliment");
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        aliment.setQuantite(quant-frais,id);
		
	}
	
	/**Permet de commander une recette et donc de déduire du stock les ingrédients la composant à partir de son numéro d'identification
	 * @id_recette numero d'identification de la recette que l'on souhaite commander
	 * 
	 */
	public void Commander(int id_recette) {
		List<Integer> id_ingredient=new ArrayList<Integer>();
		String sql="SELECT id_ingredient FROM ingredient WHERE id_recette=?";
		  try (Connection conn = this.connect();
		             PreparedStatement ptmt  = conn.prepareStatement(sql)){
		        		ptmt.setInt(1, id_recette);
		             ResultSet rs    = ptmt.executeQuery();
		            // loop through the result set
		             while(rs.next()) {
		            	 id_ingredient.add(rs.getInt("id_ingredient"));
		             }
		             
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		  for (int i:id_ingredient) {
			        		
			  DepletionStock(i,id_recette);
		  }
	}



/**
 * Méthode permettant de voir les numeros d'identification des ingrédients composant une recette à partir de son numéro d'identification
 * @param id_recette
 */
public void getIdIngredient(int id_recette) {
	String sql = "SELECT id_ingredient FROM ingredient WHERE id_recette=?";
    try (Connection conn = this.connect();
         PreparedStatement ptmt  = conn.prepareStatement(sql)){
    		ptmt.setInt(1, id_recette);
         ResultSet rs    = ptmt.executeQuery();
        // loop through the result set
    	
        while (rs.next()) {
            System.out.print(rs.getInt("id_ingredient")+ "\n");
            
            
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
	
	
}
	
	/**
	 * Méthode permettant de définir si une recette est disponible(si tout les ingrédient nécéssaire à sa réalisation étaient bien présents)
	 * @param id_recette
	 * @return
	 */
	public boolean Disponibilite(int id_recette) {
		 String sql = "SELECT ingredient.nom,stock.quantite,ingredient.quantites FROM ingredient,stock WHERE id_recette=? "
		 		+ "AND stock.nom=ingredient.nom "
		 		+ "AND stock.conditionnement=ingredient.conditionnement";
	        try (Connection conn = this.connect();
	             PreparedStatement ptmt  = conn.prepareStatement(sql)){
	        		ptmt.setInt(1, id_recette);
	             ResultSet rs    = ptmt.executeQuery();
	            // loop through the result set
	        	
	            while (rs.next()) {
	                if(rs.getInt("quantite")-rs.getInt("quantites")<0) {
	                	return false;
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return true;
	}
	
	
	/**Méthode permettant de voir la durée d'une recette à partir de son numéro d'identification
	 * @id_recette numero d'identification de la recette concernée
	 */
	public void getDureeCuisson(int id_recette) {
		
		String sql = "SELECT duree_cuisson FROM recette WHERE id_recette=?";
        try (Connection conn = this.connect();
             PreparedStatement ptmt  = conn.prepareStatement(sql)){
        		ptmt.setInt(1, id_recette);
             ResultSet rs    = ptmt.executeQuery();
            // loop through the result set
        	
            while (rs.next()) {
                System.out.print(rs.getInt("duree_cuisson")+"\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	
	
	/**Méthode permettant de modifier la duree de cuisson d'une recette à partir de son numero d'identification
	 * @param id_recette numero d'identifictaionde la recette concernée
	 * @param dureeCuisson nouvelle duree de cuisson de la recette
	 */
	public void setDureeCuisson(int dureeCuisson,int id_recette) {
		
		String sql = "UPDATE recette SET duree_cuisson = ? WHERE id_recette=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(2,id_recette);
            pstmt.setInt(1,dureeCuisson);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }	
		this.dureeCuisson = dureeCuisson;
	}
	
	
	
	/**
	 * Méthode permettant de voir les instructions d'une recette à partir de son numéro d'identification
	 * @param id_recette
	 */
	public void getInstructions(int id_recette) {
		String sql = "SELECT indication FROM recette WHERE id_recette=?";
        try (Connection conn = this.connect();
             PreparedStatement ptmt  = conn.prepareStatement(sql)){
        		ptmt.setInt(1, id_recette);
             ResultSet rs    = ptmt.executeQuery();
            // loop through the result set
        	
            while (rs.next()) {
                System.out.print(rs.getString("indication")+"\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	/***
	 * Méthode permettant de modifier une série d'instruction propre à une recette grâce au numéro di'dentification de la recette	
	 * @param instruction
	 * @param id_recette
	 */
	public void setInstructions(String instruction,int id_recette) {
		String sql = "UPDATE recette SET indication= ? WHERE id_recette=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,instruction);
            pstmt.setInt(2,id_recette);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	
	
	
	
	
	/**
	 * Méthode permettant de modifier le nom d'une recette à partir de son numero d'identification
	 * @param nom
	 * @param id_recette
	 */
	public void setNom(String nom, int id_recette) {
		String sql = "UPDATE recette SET nom = ? WHERE id_recette=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,nom);
            pstmt.setInt(2,id_recette);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	
	/**
	 * Méthode permettant de voir la durée de préparation d'une recette à partir de son numero d'identification
	 * @param id_recette
	 */
	public void getDureePreparation(int id_recette) {
		String sql = "SELECT duree_preparation FROM recette WHERE id_recette=?";
        try (Connection conn = this.connect();
             PreparedStatement ptmt  = conn.prepareStatement(sql)){
        		ptmt.setInt(1, id_recette);
             ResultSet rs    = ptmt.executeQuery();
            // loop through the result set
        	
            while (rs.next()) {
                System.out.print(rs.getInt("duree_preparation")+"\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	
	/**
	 * Méthode permettant de récupérer le nom d'une recette à partir de son numero d'identification
	 * @param id_recette
	 */
	public void getNom(int id_recette) {
		String sql = "SELECT nom FROM recette WHERE id_recette=?";
        try (Connection conn = this.connect();
             PreparedStatement ptmt  = conn.prepareStatement(sql)){
        		ptmt.setInt(1, id_recette);
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
	 * Méthode permettant de modifier la durée de préparation d'une recette à partir du numeor d'identification de celle ci
	 * @param dureePreparation
	 * @param id_recette
	 */
	public void setDureePreparation(int dureePreparation,int id_recette) {
		String sql = "UPDATE recette SET duree_preparation = ? WHERE id_recette=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,dureePreparation);
            pstmt.setInt(2,id_recette);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		DureePreparation = dureePreparation;
	}
	
	
	
	
	
	
	
	/**
	 * Méthode qui permet de voir la liste des ingrédients d'une recette à partir de son numero d'identification
	 * @param id_recette
	 */
	public String getListeIngredient(int id_recette) {
		String listingre="";
		String sql = "SELECT nom,conditionnement,quantites FROM ingredient WHERE id_recette=?";
        try (Connection conn = this.connect();
             PreparedStatement ptmt  = conn.prepareStatement(sql)){
        		ptmt.setInt(1, id_recette);
             ResultSet rs    = ptmt.executeQuery();
            // loop through the result set
        	
            while (rs.next()) {
                listingre+=rs.getString("nom")+
                "\t" + rs.getString("conditionnement") +
                "\t" +rs.getInt("quantites")+"grammes"+"\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return listingre;
	}
	
	
	
/**
 * Méthode qui permet de récupérer l'apport en calorie de la recette à partir de son numero d'identification
 * @param id_recette
 */
	public void getCalPArPersonne(int id_recette) {
		String sql = "SELECT calorie FROM recette WHERE id_recette=?";
        try (Connection conn = this.connect();
             PreparedStatement ptmt  = conn.prepareStatement(sql)){
        		ptmt.setInt(1, id_recette);
             ResultSet rs    = ptmt.executeQuery();
            // loop through the result set
        	
            while (rs.next()) {
                System.out.print(rs.getInt("calorie")+"\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	
	
	
	/**
	 * Méthode qui permet de voir la difficulté d'une recette à partir de son numero d'identification
	 * @param id_recette
	 */
	public void getDifficulte(int id_recette) {
		String sql = "SELECT difficulte FROM recette WHERE id_recette=?";
        try (Connection conn = this.connect();
             PreparedStatement ptmt  = conn.prepareStatement(sql)){
        		ptmt.setInt(1, id_recette);
             ResultSet rs    = ptmt.executeQuery();
            // loop through the result set
        	
            while (rs.next()) {
                System.out.print(rs.getString("difficulte")+"\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	
	/**
	 * Méthode qui permet de modifier la difficulté d'une recette dont on connait le numero d'identification
	 * @param difficulte
	 * @param id_recette
	 */
	public void setDifficulte(String difficulte,int id_recette) {
		String sql = "UPDATE recette SET difficulte = ? WHERE id_recette=?";
	  	  
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,difficulte);
            pstmt.setInt(2,id_recette);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	
	
	
	public void getSoustype(int id_recette) {
		String sql = "SELECT soustype FROM recette WHERE id_recette=?";
        try (Connection conn = this.connect();
             PreparedStatement ptmt  = conn.prepareStatement(sql)){
        		ptmt.setInt(1, id_recette);
             ResultSet rs    = ptmt.executeQuery();
            // loop through the result set
        	
            while (rs.next()) {
                System.out.print(rs.getString("soustype")+"\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
			}
	
	
	
	
	
	
			public void setSoustype(String soustype,int id_recette) {
				String sql = "UPDATE recette SET soustype = ? WHERE id_recette=?";
			  	  
		        try (Connection conn = this.connect();
		                PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1,soustype);
		            pstmt.setInt(2,id_recette);
		            pstmt.executeUpdate();
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
			}
}
