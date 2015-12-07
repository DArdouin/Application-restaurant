package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Restaurant;
import view.MainFrame;
import view.PannelAccueil;

public class ControlleurAccueil implements ActionListener{
	private PannelAccueil vueAcceuil;
	private Restaurant monModele;
	private MainFrame mainFrame;
	
	public ControlleurAccueil(MainFrame f) {
		this.mainFrame = f;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = new String(e.getActionCommand());
		s = s.replace("Table", "");
		int indiceTable = Integer.parseInt(s);
		
		//On modifie la table active (sélectionnée)
		monModele.changerTableActive(indiceTable);
		//On affiche les données relatives à la table
		mainFrame.showCurrentPanel("PannelOnglets");
	}
	
	public void setModele(Restaurant m){
		this.monModele = m;
	}
	
	public void setVue(PannelAccueil v){
		this.vueAcceuil = v;
	}

}
