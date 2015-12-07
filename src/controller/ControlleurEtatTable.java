package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Enumerations.EtatTable;
import model.Restaurant;
import view.MainFrame;
import view.PannelEtatTable;

public class ControlleurEtatTable implements ActionListener{
	private MainFrame mainFrame;
	private Restaurant monModele;
	private PannelEtatTable maVue;
	
	public ControlleurEtatTable(MainFrame m) {
		this.mainFrame = m;
	}

	/**
	 * En fonction du radioButton que l'on va sélectionner
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		EtatTable monEtat = monModele.getTableActive().getEtat();
		if(e.getActionCommand().equals("Table libre")) monEtat = EtatTable.libre;
		if(e.getActionCommand().equals("Table a nettoyer")){
			//On passe la table dans cet état après le paiement. Dans ce cas, on supprime les commandes actuelles
			monEtat = EtatTable.aNettoyer;
			monModele.libererTable(monModele.getTableActive().getNumero());
		}
		if(e.getActionCommand().equals("En cours de service")) monEtat = EtatTable.enService;
		monModele.modifierEtatTableActive(monEtat);
	}
	
	public void setModele(Restaurant m){
		this.monModele = m;
	}

	public void setVue(PannelEtatTable v){
		this.maVue = v;
	}
}
