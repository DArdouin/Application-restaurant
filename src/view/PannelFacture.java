package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.ControlleurFacture;
import model.Boisson;
import model.Client;
import model.Item;
import model.Plat;
import model.Restaurant;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Insets;
import java.text.DecimalFormat;

import javax.swing.SwingConstants;

import Enumerations.EtatCommande;

import javax.swing.JList;

public class PannelFacture extends JPanel implements Observer{
	private Restaurant monModele;
	private ControlleurFacture monControlleur;
	private JScrollPane scrollPane;
	JLabel lblPrix,lblPrixVariable,lblTvq,lblTvqVariable,lblTps,lblTpsVariable,lblTotal,lblTotalVariable;
	JButton btnPayer;
	private JList jListe;
	private DefaultListModel<Item> liste;
	
	public PannelFacture() {
		liste = new DefaultListModel<Item>();
		jListe = new JList(liste);
		jListe.setCellRenderer(new ItemCellRenderer());
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{601, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		scrollPane = new JScrollPane(jListe);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		lblPrix = new JLabel("Prix HT");
		lblPrix.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPrix = new GridBagConstraints();
		gbc_lblPrix.anchor = GridBagConstraints.EAST;
		gbc_lblPrix.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrix.gridx = 0;
		gbc_lblPrix.gridy = 1;
		add(lblPrix, gbc_lblPrix);
		
		lblPrixVariable = new JLabel("0");
		GridBagConstraints gbc_lblPrixVariable = new GridBagConstraints();
		gbc_lblPrixVariable.gridx = 1;
		gbc_lblPrixVariable.gridy = 1;
		add(lblPrixVariable, gbc_lblPrixVariable);
		
		lblTvq = new JLabel("Tvq");
		GridBagConstraints gbc_lblTvq = new GridBagConstraints();
		gbc_lblTvq.anchor = GridBagConstraints.EAST;
		gbc_lblTvq.gridx = 0;
		gbc_lblTvq.gridy = 2;
		add(lblTvq, gbc_lblTvq);
		
		lblTvqVariable = new JLabel("0");
		GridBagConstraints gbc_lblTvqVariable = new GridBagConstraints();
		gbc_lblTvqVariable.gridx = 1;
		gbc_lblTvqVariable.gridy = 2;
		add(lblTvqVariable, gbc_lblTvqVariable);
		
		lblTps = new JLabel("Tps");
		GridBagConstraints gbc_lblTps = new GridBagConstraints();
		gbc_lblTps.anchor = GridBagConstraints.EAST;
		gbc_lblTps.gridx = 0;
		gbc_lblTps.gridy = 3;
		add(lblTps, gbc_lblTps);
		
		lblTpsVariable = new JLabel("0");
		GridBagConstraints gbc_lblTpsVariable = new GridBagConstraints();
		gbc_lblTpsVariable.gridx = 1;
		gbc_lblTpsVariable.gridy = 3;
		add(lblTpsVariable, gbc_lblTpsVariable);
		
		lblTotal = new JLabel("Total");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.EAST;
		gbc_lblTotal.gridx = 0;
		gbc_lblTotal.gridy = 4;
		add(lblTotal, gbc_lblTotal);
		
		lblTotalVariable = new JLabel("0");
		GridBagConstraints gbc_lblTotalVariable = new GridBagConstraints();
		gbc_lblTotalVariable.gridx = 1;
		gbc_lblTotalVariable.gridy = 4;
		add(lblTotalVariable, gbc_lblTotalVariable);
		
		btnPayer = new JButton("Facture");
		btnPayer.setActionCommand("Imprimer facture");
		GridBagConstraints gbc_btnPayer = new GridBagConstraints();
		gbc_btnPayer.gridx = 1;
		gbc_btnPayer.gridy = 5;
		add(btnPayer, gbc_btnPayer);
	}
	 
	@Override 
	public void update(Observable o, Object arg) {
		liste.clear();
		jListe.removeAll();
		for(Item i : monModele.getTableActive().getFactureTable().getItemFacturable()){
			liste.addElement(i);
		}
		lblPrixVariable.setText(new DecimalFormat("##.##").format(monModele.getTableActive().getFactureTable().getTotalHt()) + "$");
		lblTpsVariable.setText(new DecimalFormat("##.##").format(monModele.getTableActive().getFactureTable().getTps())+ "$");
		lblTvqVariable.setText(new DecimalFormat("##.##").format(monModele.getTableActive().getFactureTable().getTvq()) + "$");
		lblTotalVariable.setText(new DecimalFormat("##.##").format(monModele.getTableActive().getFactureTable().getTotal()) + "$");
	}
	
	public void setModele(Restaurant m){
		this.monModele = m;
	}
	
	public void setControlleur(ControlleurFacture c){
		this.monControlleur = c;
		btnPayer.addActionListener(monControlleur);
	}
	
	/**
	 * Cette classe est définie en interne car elle sert uniquement à la mise en forme de la liste de Plats
	 * @author Damien
	 *
	 */
	private class ItemCellRenderer extends JLabel implements ListCellRenderer{
		public ItemCellRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			Item item = (Item) value;
			String txt = value.toString();
			txt += "....................................  ";
			if(item instanceof Plat) txt += new DecimalFormat("##.##").format(((Plat) item).getPrix());
			if(item instanceof Boisson) txt += new DecimalFormat("##.##").format(((Boisson) item).getPrix());
			txt += " $";
			setText(txt);
			
			//Gestion de la sélection
			if (isSelected) {
					setBackground(new Color(0, 0, 128));
					setForeground(Color.white);
			} else {
					setBackground(Color.white);
					setForeground(Color.black);
			}
			
			return this;
		}

	}

}
