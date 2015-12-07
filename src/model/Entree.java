package model;

public class Entree extends Plat{

	public Entree(String libelle, int prix) {
		super(libelle, prix);
	}

	public Plat clone(){
		return new Entree(this.getLibelle(),this.getPrix());
	}
}
