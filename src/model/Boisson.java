package model;

import Enumerations.EtatCommande;

public class Boisson extends Item{
	private int prix;
	private int volume;
	
	public Boisson(String libelle, int prix, int volume) {
		super(libelle);
		this.prix = prix;
		this.volume = volume;
	}
	
	public int getPrix() {
		return prix;
	}
	
	public int getVolume() {
		return volume;
	}
	
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public Boisson clone(){
		return new Boisson(this.getLibelle(),this.prix,this.volume);
	}
}
