package view;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import Enumerations.EtatTable;
import controller.ControlleurEtatTable;
import model.Restaurant;
import model.Table;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

public class PannelEtatTable extends JPanel implements Observer{
	private ButtonGroup bg;
	private JRadioButton rdbtnLibre;
	private JRadioButton rdbtnService;
	private JRadioButton rdbtnNettoyer;
	private Box verticalBox;
	private Component horizontalGlue, horizontalGlue2;
	private Restaurant monModele;
	private ControlleurEtatTable monControlleur;
	
	public PannelEtatTable() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		bg = new ButtonGroup();
		
		horizontalGlue = Box.createHorizontalGlue();
		add(horizontalGlue);
		
		verticalBox = Box.createVerticalBox();
		verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(verticalBox);
		
		horizontalGlue2 = Box.createHorizontalGlue();
		add(horizontalGlue2);
		
		rdbtnLibre = new JRadioButton("Table libre");
		verticalBox.add(rdbtnLibre);
		rdbtnLibre.setAlignmentX(LEFT_ALIGNMENT);
		rdbtnLibre.setAlignmentY(CENTER_ALIGNMENT);
		rdbtnLibre.setActionCommand("Table libre");
		bg.add(rdbtnLibre);
		
		rdbtnNettoyer = new JRadioButton("Table a nettoyer");
		verticalBox.add(rdbtnNettoyer);
		rdbtnNettoyer.setAlignmentX(LEFT_ALIGNMENT);
		rdbtnNettoyer.setAlignmentY(CENTER_ALIGNMENT);
		rdbtnNettoyer.setActionCommand("Table a nettoyer");
		bg.add(rdbtnNettoyer);
		
		rdbtnService = new JRadioButton("En cours de service");
		verticalBox.add(rdbtnService);
		rdbtnService.setAlignmentX(LEFT_ALIGNMENT);
		rdbtnService.setAlignmentY(CENTER_ALIGNMENT);
		rdbtnService.setActionCommand("En cours de service");
		bg.add(rdbtnService);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		miseAJour();
	}
	
	public void setControlleur(ControlleurEtatTable c){
		this.monControlleur = c;
		rdbtnLibre.addActionListener(monControlleur);
		rdbtnNettoyer.addActionListener(monControlleur);
		rdbtnService.addActionListener(monControlleur);
	}

	public void setModele(Restaurant m){
		this.monModele = m;
	}
	
	/**
	 * Permet de sélectionner le radioButton en rapport avec l'état de la table
	 */
	public void miseAJour(){
		EtatTable monEtat = monModele.getTableActive().getEtat();
		if(monEtat == EtatTable.aNettoyer) rdbtnNettoyer.setSelected(true);
		if(monEtat == EtatTable.enService) rdbtnService.setSelected(true);
		if(monEtat == EtatTable.libre) rdbtnLibre.setSelected(true);
	}
}
