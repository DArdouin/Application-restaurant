package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import controller.ControlleurOnglets;
import model.Restaurant;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;

public class PannelOnglets extends JPanel implements Observer{
	private Restaurant monModele;
	private ControlleurOnglets monControlleur;
	private JButton btnAccueil;
	private JLabel lblTable;
	private JTabbedPane tabbedPane;
	
	public PannelOnglets() {
		setLayout(new BorderLayout(0, 0));
		
		Box horizontalBox = Box.createHorizontalBox();
		add(horizontalBox, BorderLayout.NORTH);
		
		lblTable = new JLabel("Table n\u00B0");
		horizontalBox.add(lblTable);
		lblTable.setHorizontalAlignment(SwingConstants.LEFT);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		btnAccueil = new JButton("Accueil");
		btnAccueil.setActionCommand("Retour accueil");
		horizontalBox.add(btnAccueil);
		btnAccueil.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);getPreferredSize();
		add(tabbedPane, BorderLayout.CENTER);
	}

	public void setModele(Restaurant m){
		this.monModele = m;
	}
	
	public void setControlleur(ControlleurOnglets c){
		this.monControlleur = c;
		btnAccueil.addActionListener(monControlleur);
	}

	/**
	 * On modifie les informations relatives à la table sélectionnée
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		int index = monModele.getTableActive().getNumero();
		lblTable.setText("Table" + index);
	}

	/**
	 * Permet de remplacer le pannel vide (construction des onglets) via le pannel complet
	 * @param p Le pannel à ajouter
	 */
	public void ajouterOnglet(JPanel p) {
		if(p instanceof PannelEtatTable){
			tabbedPane.add("Etat des tables", p);
		}
		if(p instanceof PannelCommande){
			tabbedPane.add("Commande",p);
		}
		if(p instanceof PannelFacture){
			tabbedPane.add("Facture",p);
		}
	}
}
