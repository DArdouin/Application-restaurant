package model;

import Enumerations.EtatCommande;

public abstract class Plat extends Item{
	private int prix;
	
	public Plat(String libelle, int prix) {
		super(libelle);
		this.prix = prix;
	}
	
	public int getPrix() {
		return prix;
	}
	
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public abstract Plat clone();
}
