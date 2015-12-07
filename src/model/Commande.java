package model;

import java.util.ArrayList;

import Enumerations.EtatCommande;

public class Commande {
	private ArrayList<Plat> possedeP;
	private ArrayList<Boisson> possedeB;
	
	public Commande() {
		possedeP = new ArrayList<Plat>();
		possedeB = new ArrayList<Boisson>();
	}

	public void ajouterPlat(Plat p){
		p.setEtatCommande(EtatCommande.PasPret);
		possedeP.add(p);
	}
	
	public void ajouterBoisson(Boisson b){
		b.setEtatCommande(EtatCommande.PasPret);
		possedeB.add(b);
	}
	
	/**
	 * Permet de retirer un plat d'une commande
	 * @param p Le plat à retirer
	 */
	public void retirerPlat(Plat p){
		if(p.getEtatCommande() != EtatCommande.Pret) possedeP.remove(p);
	}
	
	/**
	 * Permet de retirer une boisson d'une commande
	 * @param b La boisson à retirer
	 */
	public void retirerBoisson(Boisson b){
		possedeB.remove(b);
	}
	
	public ArrayList<Plat> getPlats(){
		return this.possedeP;
	}
	
	public ArrayList<Boisson> getBoissons(){
		return this.possedeB;
	}

	public void validerCommande() {
		for(Plat p : possedeP){
			if(p.getEtatCommande() == EtatCommande.PasPret) p.setEtatCommande(EtatCommande.Pret);
		}
		for(Boisson b : possedeB){
			if(b.getEtatCommande() == EtatCommande.PasPret) b.setEtatCommande(EtatCommande.Pret);
		}
	}
}
