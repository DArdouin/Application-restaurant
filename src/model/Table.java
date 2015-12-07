package model;

import java.util.ArrayList;
import java.util.HashMap;

import Enumerations.EtatTable;

public class Table {
	private EtatTable etat;
	private int numero;
	private ArrayList<Client> estInstalle;
	private HashMap<Client,Commande> commandeClient;
	private Client clientActif;
	private Facture factureTable;
	
	public Table(int num) {
		estInstalle = new ArrayList<Client>();
		commandeClient = new HashMap<Client,Commande>();
		factureTable = new Facture();
		etat = EtatTable.libre;
		this.numero = num;
	}
	
	public void setEtat(EtatTable etat) {
		this.etat = etat;
	}
	
	public EtatTable getEtat() {
		return etat;
	}
	
	public int getNumero(){
		return numero;
	}
	
	public Facture getFactureTable(){
		return this.factureTable;
	}
	
	/**
	 * Ajoute un client à la table, lui assigne une nouvelle commande
	 */
	public void installerClient(){
		String nomClient = "Client " + estInstalle.size();
		Client c = new Client(nomClient);
		estInstalle.add(c);
		commandeClient.put(c, new Commande());
		clientActif = c;
	}
	
	public void retirerClient(String nom){
		Client clientASupprimer = null;
		String nouveauPrenom;
		boolean trouve = false;
		int nouvelIndex;
		for(Client c : estInstalle){
			if(trouve){ //Si le client a été enlevé, on décrémente de "1" le nom de chaque client
				nouvelIndex = estInstalle.indexOf(c) - 1;
				nouveauPrenom = c.getPrenom().replace(Integer.toString(estInstalle.indexOf(c)), Integer.toString(nouvelIndex));
				c.setPrenom(nouveauPrenom);
			} else {
				if (c.getPrenom().equals(nom)){
					trouve = true;
					clientASupprimer = c;
				}
			}
		}
		//On retire les plats du client de la facture
		for(Plat p : commandeClient.get(clientASupprimer).getPlats()){
			factureTable.supprimerItem(p);
		}
		for(Boisson b : commandeClient.get(clientASupprimer).getBoissons()){
			factureTable.supprimerItem(b);
		}
		
		//On supprime le client
		commandeClient.remove(clientASupprimer);
		estInstalle.remove(clientASupprimer);
		
		//Quand on supprime un client, on revient sur le premier de la liste
		if(estInstalle.size() > 0) 
			clientActif = estInstalle.get(estInstalle.size()-1); //On récupère le dernier client de la liste
		else clientActif = null;
	}
	
	/**
	 * Supprime la liste de client, ainsi que les commandes qui leur sont associées
	 * Réinitialise la facture pour la table
	 */
	public void libererTable(){
		estInstalle.clear();
		commandeClient.clear();
		clientActif = null;
		factureTable.nouvelleFacture();
	}
	
	/**
	 * 
	 * @return la liste des clients de la table
	 */
	public ArrayList<Client> getEstInstalle() {
		return estInstalle;
	}

	public void changerClientActif(String nom) {
		for(Client c : estInstalle){
			if(c.getPrenom().equals(nom))
				this.clientActif = c;
		}
	}
	
	public boolean ajouterPlat(Plat p){
		Commande commandeActive = commandeClient.get(clientActif);
		if(commandeActive != null){
			commandeActive.ajouterPlat(p);
			factureTable.ajouterItem(p);
			return true;
		}
		return false;
	}
	
	public boolean ajouterBoisson(Boisson b){
		Commande commandeActive = commandeClient.get(clientActif);
		if(commandeActive != null){
			commandeActive.ajouterBoisson(b);
			factureTable.ajouterItem(b);
			return true;
		}
		return false;
	}

	public Client getClientActif() {
		return clientActif;
	}

	public Commande getCommandeClientActif() {		
		return commandeClient.get(clientActif);
	}

	public boolean isEmpty() {
		return estInstalle.isEmpty();
	}

	public void retirerItemClientActif(Item item) {
		Commande commandeActive = commandeClient.get(clientActif);
		Plat platASupprimer = null;
		Boisson boissonASupprimer = null;
		//On cherche l'item à supprimer (plat ou boisson)
		if(item instanceof Plat) platASupprimer = (Plat) item;
		if(item instanceof Boisson) boissonASupprimer = (Boisson) item;
		//On le supprime
		if(platASupprimer != null){
			commandeActive.retirerPlat(platASupprimer);
			factureTable.supprimerItem(item);
		}
		else if(boissonASupprimer != null){
			commandeActive.retirerBoisson(boissonASupprimer);
			factureTable.supprimerItem(item);
		}
	}

	public void validerCommandesClients() {
		for(Client c : estInstalle){
			commandeClient.get(c).validerCommande();
		}
	}
}
