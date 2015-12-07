package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.ShutdownChannelGroupException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Restaurant;
import view.MainFrame;
import view.PannelEtatTable;
import view.PannelFacture;

public class ControlleurFacture implements ActionListener{
	private MainFrame mainFrame;
	private Restaurant monModele;
	private PannelFacture maVue;
	
	public ControlleurFacture(MainFrame m) {
		this.mainFrame = m;
	}
	
	public void setVue(PannelFacture v){
		this.maVue = v;
	}
	
	public void setModele(Restaurant m){
		this.monModele = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Imprimer facture")){
			monModele.libererTable(monModele.getTableActive().getNumero());
			JOptionPane.showMessageDialog(new JFrame(),
				    "La facture a été imprimée avec succès. La table a été libérée.");
		}
	}
}
