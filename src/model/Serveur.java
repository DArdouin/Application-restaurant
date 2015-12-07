package model;

/**
 * Permet l'authentification d'un serveur
 * @author Damien
 *
 */
public class Serveur {
	private String nom;
	private String prenom;
	private String password;
	
	public Serveur(String nom, String prenom, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Permet de comparer deux Serveurs 
	 * @param s un serveur
	 * @return vrai si les serveur on le même nom/password/prenom
	 */
	public boolean equals(Object o){
		Serveur s = (Serveur)o;
		return(this.nom.equals(s.getNom()) &&
			   this.prenom.equals(s.getPrenom())&&
			   this.password.equals(s.getPassword()));
	}
}
