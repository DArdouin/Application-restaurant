package model;

import Enumerations.EtatCommande;

/**
 * Classe vide, permet simplement de mettre les plats & boissons dans une même liste
 * @author Damien
 *
 */
public abstract class Item {
	private String libelle;
	private EtatCommande etatCommande;

	public Item(String libelle) {
		this.libelle = libelle;
		this.etatCommande = EtatCommande.PasPret;
	}
	
	public EtatCommande getEtatCommande() {
		return etatCommande;
	}
	
	public void setEtatCommande(EtatCommande etatCommande) {
		this.etatCommande = etatCommande;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public String toString(){
		return this.libelle;
	}
}
