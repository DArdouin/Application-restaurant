package model;

import java.util.ArrayList;

public class Facture {
	private ArrayList<Item> itemFacturable;
	private double totalHt,Tvq,Tps,total;
	
	public Facture() {
		this.itemFacturable = new ArrayList<Item>();
	}
	
	public void ajouterItem(Item i){
		this.itemFacturable.add(i);
		Plat p;
		Boisson b;
		if(i instanceof Plat){
			p = (Plat)i;
			totalHt += p.getPrix();
		} else if(i instanceof Boisson){
			b = (Boisson)i;
			totalHt += b.getPrix();
		}
		actualiserPrix();
	}
	
	public void supprimerItem(Item i){
		this.itemFacturable.remove(i);
		Plat p;
		Boisson b;
		if(i instanceof Plat){
			p = (Plat)i;
			totalHt -= p.getPrix();
		} else if(i instanceof Boisson){
			b = (Boisson)i;
			totalHt -= b.getPrix();
		}
		actualiserPrix();
	}
	
	private void actualiserPrix() {
		Tvq = (9.975*totalHt)/100;
		Tps = (5*totalHt)/100;
		total = totalHt + Tvq + Tps;
	}

	public ArrayList<Item> getItemFacturable(){
		return this.itemFacturable;
	}
	
	public void nouvelleFacture(){
		this.itemFacturable.clear();
	}
	
	public double getTotal() {
		return total;
	}
	
	public double getTotalHt() {
		return totalHt;
	}
	
	public double getTps() {
		return Tps;
	}
	
	public double getTvq() {
		return Tvq;
	}
}
