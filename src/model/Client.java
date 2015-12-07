package model;

public class Client {
	/*
	 * L'id permet d'identifier le client dans la table
	 */
	private String prenom;
	
	public Client(String prenom) {
		this.prenom = prenom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
