package model;

import java.util.ArrayList;
import java.util.Observable;

import Enumerations.EtatTable;

/**
 * Classe permettant d'initialiser nos différents objets
 * @author Damien
 *
 */
public class Restaurant extends Observable{
	private ArrayList<Serveur> emploie;
	private ArrayList<Table> compose;
	private ArrayList<Plat> carte;
	private ArrayList<Boisson> carteBoissons;
	private Table tableActive;
	
	public Restaurant() {
		emploie = new ArrayList<Serveur>();
		compose = new ArrayList<Table>();
		carte = new ArrayList<Plat>();
		carteBoissons = new ArrayList<Boisson>();
		
		loadData();
	}
	
	public void loadData(){
		//On commence par définir les tables
		for(int i=1;i<=8;i++){
			compose.add(new Table(i));
		}
		
		//On définit nos serveur
		Serveur jeanBernard = new Serveur("Bernard","Jean","0000");
		Serveur michelPaillet = new Serveur("Paillet","Michel","1234");
		emploie.add(jeanBernard);
		emploie.add(michelPaillet);
		
		//On définit nos plats
			//Les entrées
		carte.add(new Entree("Fondue parmesan",4));
		carte.add(new Entree("Ecargots à l'ail",5));
		carte.add(new Entree("Crevettes à l'ail",5));
		carte.add(new Entree("Feuilleté aux epinards",5));
		carte.add(new Entree("Salade césar",4));
		
			//Les plats
		carte.add(new PlatPrincipal("Brochette de poulet", 16));
		carte.add(new PlatPrincipal("Brochette de filet mignon",27));
		carte.add(new PlatPrincipal("Brochette de porc",15));
		carte.add(new PlatPrincipal("Duo de brochettes",26));
		carte.add(new PlatPrincipal("Filet mignon", 27));
		
			//Les boissons
		carteBoissons.add(new Boisson("Boisson gaseuze",2,20));
		carteBoissons.add(new Boisson("Jus pomme",2,12));
		carteBoissons.add(new Boisson("Lait",2,12));
		carteBoissons.add(new Boisson("Bière blonde",7,20));
		carteBoissons.add(new Boisson("Bière rousse",6,20));
	}
	
	/**
	 * Permet de retourner la table correspondant à notre index
	 * @param i le numéro de la table
	 * @return la table correspondant à notre index
	 */
	public Table getTable(int index){
		return(compose.get(index-1)); //-1 car la première table est à l'index 0, et non 1
	}
	
	/**
	 * Permet d'avoir la table sélectionnée sur la page d'acceuil
	 * @return la table sélectionnée
	 */
	public Table getTableActive(){
		return tableActive;
	}
	
	/**
	 * 
	 * @return la liste des serveurs 
	 */
	public ArrayList<Serveur> getServeurList(){
		return emploie;
	}

	/**
	 * Test pour la connexion d'un employé
	 * @param nom
	 * @param prenom
	 * @param password
	 * @return
	 */
	public boolean connexion(String nom, String prenom, String password) {
		boolean bResult = false;		
		Serveur newServeur = new Serveur(nom,prenom,password);
		for(Serveur s : emploie){
			bResult = bResult | s.equals(newServeur);
		}		
		return bResult;
	}
	
	/**
	 * Permet de changer la table active, notifie les observers
	 * @param index de la table sélectionnée
	 */
	public void changerTableActive(int index){
		tableActive = getTable(index);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Permet de libérer une table (supprimer les occupants, les commandes)
	 * Libérer une table la passe automatiquement en l'état "attente de nettoyage"
	 * @param index Le numéro de la table à libérer
	 */
	public void libererTable(int index){
		getTable(index).libererTable();
		getTable(index).setEtat(EtatTable.aNettoyer);
		setChanged();
		notifyObservers();
	}

	/**
	 * Permet de modifier l'état de la table active
	 * @param monEtat Le nouvel état de la table
	 */
	public void modifierEtatTableActive(EtatTable monEtat) {
		tableActive.setEtat(monEtat);
		setChanged();
		notifyObservers();
	}

	/**
	 * Si on ajoute des clients, c'est que la table est en cours de service
	 */
	public boolean ajouterClientTable() {
		if(tableActive.getEtat() != EtatTable.aNettoyer){
			tableActive.installerClient();
			tableActive.setEtat(EtatTable.enService);
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	public void supprimerClient(String nom) {
		tableActive.retirerClient(nom);
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Plat> getCarte() {
		return carte;
	}
	
	public ArrayList<Boisson> getCarteBoissons() {
		return carteBoissons;
	}
	
	public void changerClientActif(String nom){
		tableActive.changerClientActif(nom);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Dans la table active, au client actif, on ajoute le plat
	 * @param libelle Le nom du plat que l'on veut ajouter
	 */
	public boolean ajouterPlatClient(String libelle){
		Plat platAAjouter = null;
		for(Plat p : carte){
			if(p.getLibelle().equals(libelle)) platAAjouter = p.clone();
		}
		boolean b = tableActive.ajouterPlat(platAAjouter);
		setChanged();
		notifyObservers();
		return b;
	}
	
	public boolean ajouterBoissonClient(String libelle){
		Boisson boissonAAjouter = null;
		for(Boisson b : carteBoissons){
			if(b.getLibelle().equals(libelle)) boissonAAjouter = b.clone();
		}
		boolean b = tableActive.ajouterBoisson(boissonAAjouter);
		setChanged();
		notifyObservers();
		return b;
	}
	
	public void retirerItemClientActif(Item item){
		tableActive.retirerItemClientActif(item);
		setChanged();
		notifyObservers();
	}

	public void validerCommandesClients() {
		tableActive.validerCommandesClients();
		setChanged();
		notifyObservers();
	}
} 

