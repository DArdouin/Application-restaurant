package model;

import javax.swing.text.PlainDocument;

import Enumerations.Cuisson;

public class PlatPrincipal extends Plat{
	/**
	 * A préciser lorsque l'on ajoute le plat dans la commande du client
	 */
	private Cuisson cuisson;
	
	public PlatPrincipal(String nom, int prix) {
		super(nom, prix);
	}
	
	public Cuisson getCuisson() {
		return cuisson;
	}
	
	public void setCuisson(Cuisson cuisson) {
		this.cuisson = cuisson;
	}
	
	public Plat clone(){
		return new PlatPrincipal(this.getLibelle(), this.getPrix());
	}
}
