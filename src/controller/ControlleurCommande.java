package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Restaurant;
import view.MainFrame;
import view.PannelCommande;

public class ControlleurCommande implements ActionListener{
	private MainFrame mainFrame;
	private Restaurant monModele;
	private PannelCommande maVue;
	
	public ControlleurCommande(MainFrame m) {
		this.mainFrame = m;
	}
	
	/**
	 * Demande de fermerture d'un onglet (suppression d'un client)
	 * Ajout d'un onglet
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Ajouter client")){
			if(!monModele.ajouterClientTable()) 
				maVue.alerteEtatTable();
		}
		if(e.getActionCommand().equals("Ajouter plat")){
			JButton boutonClique = (JButton) e.getSource();
			if(!monModele.ajouterPlatClient(boutonClique.getText()))
				maVue.alerterManqueClient();
		}
		if(e.getActionCommand().equals("Ajouter boisson")){
			JButton boutonClique = (JButton) e.getSource();
			if(!monModele.ajouterBoissonClient(boutonClique.getText()))
				maVue.alerterManqueClient();
		}
		if(e.getActionCommand().equals("Valider commande")){
			monModele.validerCommandesClients();
		}
	}

	public void setModele(Restaurant m){
		this.monModele = m;
	}
	
	public void setVue(PannelCommande v){
		this.maVue = v;
	}
}
