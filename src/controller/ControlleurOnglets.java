package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Restaurant;
import model.Table;
import view.MainFrame;
import view.PannelOnglets;

public class ControlleurOnglets implements ActionListener{
	private MainFrame mainFrame;
	private Restaurant monModele;
	private PannelOnglets maVue;
	
	public ControlleurOnglets(MainFrame m) {
		this.mainFrame = m;
	}
	
	public void setModele(Restaurant m){
		this.monModele = m;
	}
	
	public void setVue(PannelOnglets v){
		this.maVue = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Retour accueil")) mainFrame.showCurrentPanel("PannelAccueil");
	}
}
