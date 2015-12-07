package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;

import Enumerations.EtatTable;
import controller.ControlleurAccueil;
import model.Restaurant;

import javax.swing.JLabel;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class PannelAccueil extends JPanel implements Observer{
	private Restaurant monModele;
	private ControlleurAccueil monControlleur;
	/**
	 * La liste de boutons permet d'ajouter plus facilement le controlleur en tant qu'actionListener sur nos boutons
	 */
	private ArrayList<JButton> listeBoutons;
	
	public PannelAccueil() {
		listeBoutons = new ArrayList<JButton>();
		setBackground(SystemColor.activeCaption);
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblPageDaccueil = new JLabel("        Page d'accueil");
		lblPageDaccueil.setBackground(SystemColor.inactiveCaption);
		add(lblPageDaccueil, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("   ");
		lblNewLabel.setBackground(SystemColor.inactiveCaption);
		add(lblNewLabel, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnTable1 = new JButton("T 1");
		GridBagConstraints gbc_btnTable1 = new GridBagConstraints();
		gbc_btnTable1.fill = GridBagConstraints.BOTH;
		gbc_btnTable1.insets = new Insets(0, 0, 5, 5);
		gbc_btnTable1.gridx = 1;
		gbc_btnTable1.gridy = 0;
		panel.add(btnTable1, gbc_btnTable1);
		btnTable1.setActionCommand("Table1");
		listeBoutons.add(btnTable1);
		
		JButton btnTable2 = new JButton("T 2");
		GridBagConstraints gbc_btnTable2 = new GridBagConstraints();
		gbc_btnTable2.insets = new Insets(0, 0, 5, 5);
		gbc_btnTable2.fill = GridBagConstraints.BOTH;
		gbc_btnTable2.gridx = 3;
		gbc_btnTable2.gridy = 0;
		panel.add(btnTable2, gbc_btnTable2);
		btnTable2.setActionCommand("Table2");
		listeBoutons.add(btnTable2);
		
		JButton btnTable3 = new JButton("T 3");
		GridBagConstraints gbc_btnTable3 = new GridBagConstraints();
		gbc_btnTable3.insets = new Insets(0, 0, 5, 5);
		gbc_btnTable3.fill = GridBagConstraints.BOTH;
		gbc_btnTable3.gridx = 5;
		gbc_btnTable3.gridy = 0;
		panel.add(btnTable3, gbc_btnTable3);
		btnTable3.setActionCommand("Table3");
		listeBoutons.add(btnTable3);
		
		JButton btnTable4 = new JButton("T 4");
		GridBagConstraints gbc_btnTable4 = new GridBagConstraints();
		gbc_btnTable4.fill = GridBagConstraints.BOTH;
		gbc_btnTable4.insets = new Insets(0, 0, 5, 5);
		gbc_btnTable4.gridx = 7;
		gbc_btnTable4.gridy = 0;
		panel.add(btnTable4, gbc_btnTable4);
		btnTable4.setActionCommand("Table4");
		listeBoutons.add(btnTable4);
		
		JButton btnTable5 = new JButton("T 5");
		GridBagConstraints gbc_btnTable5 = new GridBagConstraints();
		gbc_btnTable5.fill = GridBagConstraints.BOTH;
		gbc_btnTable5.insets = new Insets(0, 0, 5, 0);
		gbc_btnTable5.gridx = 9;
		gbc_btnTable5.gridy = 0;
		panel.add(btnTable5, gbc_btnTable5);
		btnTable5.setActionCommand("Table5");
		listeBoutons.add(btnTable5);
		
		JButton btnNewButton_5 = new JButton("");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.gridwidth = 2;
		gbc_btnNewButton_5.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 2;
		panel.add(btnNewButton_5, gbc_btnNewButton_5);
		btnNewButton_5.setEnabled(false);
		
		JButton btnTable6 = new JButton("T 6");
		GridBagConstraints gbc_btnTable6 = new GridBagConstraints();
		gbc_btnTable6.fill = GridBagConstraints.BOTH;
		gbc_btnTable6.insets = new Insets(0, 0, 5, 5);
		gbc_btnTable6.gridx = 5;
		gbc_btnTable6.gridy = 2;
		panel.add(btnTable6, gbc_btnTable6);
		btnTable6.setActionCommand("Table6");
		listeBoutons.add(btnTable6);
		
		JButton btnTable7 = new JButton("T 7");
		GridBagConstraints gbc_btnTable7 = new GridBagConstraints();
		gbc_btnTable7.fill = GridBagConstraints.BOTH;
		gbc_btnTable7.insets = new Insets(0, 0, 5, 5);
		gbc_btnTable7.gridx = 7;
		gbc_btnTable7.gridy = 2;
		panel.add(btnTable7, gbc_btnTable7);
		btnTable7.setActionCommand("Table7");
		listeBoutons.add(btnTable7);
		
		JButton btnTable8 = new JButton("T 8");
		GridBagConstraints gbc_btnTable8 = new GridBagConstraints();
		gbc_btnTable8.fill = GridBagConstraints.BOTH;
		gbc_btnTable8.gridwidth = 3;
		gbc_btnTable8.insets = new Insets(0, 0, 0, 5);
		gbc_btnTable8.gridx = 1;
		gbc_btnTable8.gridy = 4;
		panel.add(btnTable8, gbc_btnTable8);
		btnTable8.setActionCommand("Table8");
		listeBoutons.add(btnTable8);	
	}

	public void setModele(Restaurant m) {
		this.monModele = m;
		miseAJour();
	}

	public void setControlleur(ControlleurAccueil c){
		this.monControlleur = c;
		for(JButton b : listeBoutons){
			b.addActionListener(monControlleur);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		miseAJour();
	}

	/**
	 * Gère la mise à jour de cet affichage
	 */
	public void miseAJour(){
		//C'est ici que l'on va gérer le changement de couleur des tables
		int index = 1;
		EtatTable monEtat;
		for(JButton b : listeBoutons){ //Pour chacun des boutons, on va chercher l'état de la table correspondante
			index = Integer.parseInt(b.getActionCommand().replace("Table","")); //Le numéro sur le bouton
			monEtat = monModele.getTable(index).getEtat();
			miseAJourCouleurs(b,monEtat);
		}
	}
	
	/**
	 * Permet de changer la couleur d'un bouton (table) en fonction de son Etat
	 * @param b Le bouton pour lequel la couleur va changer
	 * @param monEtat L'état de la table correspondant au bouton
	 */
	public void miseAJourCouleurs(JButton b, EtatTable monEtat) {
		if(monEtat == EtatTable.libre){
			b.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
		}else if(monEtat == EtatTable.aNettoyer){
			b.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		}else if(monEtat == EtatTable.enService){
			b.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		}
	}
}
