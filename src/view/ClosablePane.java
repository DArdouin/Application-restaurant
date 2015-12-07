package view;

/**
 * Classe importée depuis internet
 * Modification d'un JTabbedPane afin d'ajouter une "croix" pour les fermer
 * Code source initial provenant de : http://paste.ubuntu.com/12045641/
 * Modifié le 28/11 par ARDOUIN Damien :
 *  - Modification de l'orientation
 * 	- Ajout d'un onglet "d'ajout"
 *  - Demande avant suppression d'un onglet
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import Enumerations.EtatCommande;
import controller.ControlleurClosablePane;
import model.Boisson;
import model.Client;
import model.Item;
import model.Plat;
import model.Restaurant;

public class ClosablePane extends JTabbedPane implements Observer
{
	private int closeX = 0; 
	private int closeY = 0; 
	private int meX = 0; 
	private int meY = 0;
	private static final long serialVersionUID = 1L;
	private ControlleurClosablePane monControlleur;
	private Restaurant monModele;
	private DefaultListModel listeCommande;
	private JList jListeCommande;
	private JScrollPane panelScrolable;

	public ClosablePane() {
		listeCommande = new DefaultListModel<Item>();
		jListeCommande = new JList(listeCommande);
		panelScrolable = new JScrollPane(jListeCommande);
	}

	public void paint(Graphics g) {
		super.paint(g);
		this.paintCroix(g);
	}

	public String getTabTitleAt(int index)
	{
		return super.getTitleAt(index).trim();
	}

	public boolean tabAboutToClose(int tabIndex)
	{
		return true;
	}

	public void paintCroix(Graphics g)
	{
		int tabCount = getTabCount();
		for (int j = 0; j < tabCount; j++){ //Pas possible de fermer le dernier pannel
			if (getComponent(j).isShowing()) {
				int x = getBoundsAt(j).x + getBoundsAt(j).width - 8 - 5;
				int y = getBoundsAt(j).y + 5;
				drawClose(g, x, y);
				break;
			}
		}
		if (mouseOverTab(meX, meY))
			drawClose(g, this.closeX, this.closeY);
	}

	private void drawClose(Graphics g, int x, int y)
	{
		if ((this != null) && (getTabCount() > 0)) {
			Graphics2D g2 = (Graphics2D)g;
			drawColored(g2, isUnderMouse(x, y) ? Color.RED : Color.WHITE, x, y);
		}
	}

	public boolean isUnderMouse(int x, int y)
	{
		if ((Math.abs(x - getMeX()) < 8) && (Math.abs(y - getMeY()) < 8))
			return true;
		return false;
	}

	private void drawColored(Graphics2D g2, Color color, int x, int y) {
		g2.setStroke(new BasicStroke(5.0F, 1, 1));
		g2.setColor(Color.BLACK);
		g2.drawLine(x, y, x + 8, y + 8);
		g2.drawLine(x + 8, y, x, y + 8);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(3.0F, 1, 1));
		g2.drawLine(x, y, x + 8, y + 8);
		g2.drawLine(x + 8, y, x, y + 8);
	}

	public boolean mouseOverTab(int x, int y) {
		int tabCount = getTabCount();
		for (int j = 0; j < tabCount; j++)
			if (getBoundsAt(j).contains(getMeX(), getMeY())) {
				if(monControlleur != null) monControlleur.setSelectedTab(j);
				setCloseX((getBoundsAt(j).x + getBoundsAt(j).width - 8 - 5));
				setCloseY((getBoundsAt(j).y + 5));
				return true;
			}
		return false;
	}

	public int getCloseX() {
		return closeX;
	}

	public int getCloseY() {
		return closeY;
	}

	public int getMeX() {
		return meX;
	}

	public int getMeY() {
		return meY;
	}

	public void setModele(Restaurant m){
		this.monModele = m;
	}

	public void setControlleur(ControlleurClosablePane c){
		this.monControlleur = c;
		addMouseListener(monControlleur);
		addMouseMotionListener(monControlleur);
	}

	public void setCloseX(int i) {
		this.closeX = i;		
	}

	public void setCloseY(int closeY) {
		this.closeY = closeY;
	}

	public void setMeX(int x) {
		this.meX = x;		
	}

	public void setMeY(int y){
		this.meY = y;
	}

	public List<Item> getPlatSelectionne(){
		if(!jListeCommande.isSelectionEmpty()){
			return jListeCommande.getSelectedValuesList();
		}
		else return null;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//On supprime les données relatives à la commande
		listeCommande.clear();
		jListeCommande.removeAll();
		jListeCommande.setCellRenderer(new ItemCellRenderer());
		jListeCommande.setSelectionModel(new DisabledItemSelectionModel());

		//On récupère le contenu de la commande
		if(!monModele.getTableActive().isEmpty()){
			for(Plat p : monModele.getTableActive().getCommandeClientActif().getPlats()){
				listeCommande.addElement(p);
			}
			for(Boisson b : monModele.getTableActive().getCommandeClientActif().getBoissons()){
				listeCommande.addElement(b);
			}
		}

		//Si on a changé le nombre de panel, on change le pannel selectionné
		if(monModele.getTableActive().getEstInstalle().size() != getTabCount()){	
			//On commence par supprimer tous les onglets, tous les plats de la liste
			this.removeAll();
			int i = -1;
			boolean trouve = false;
			for(Client c : monModele.getTableActive().getEstInstalle()){
				this.addTab(c.getPrenom() + "  ", new JPanel()); //On ajoute des panel vide dans les autres onglets
				if(c.getPrenom().equals(monModele.getTableActive().getClientActif().getPrenom())) trouve = true;
				if(!trouve) i++;
			}
			//On sélectionne le pannel correspondant au clientActif
			if(i != -1) this.setSelectedIndex(i+1);
		}	

		//On ajoute la liste des plats de la commande, s'il y a au moins un client
		if(monModele.getTableActive().getClientActif() != null){
			JPanel monPanel = (JPanel) this.getSelectedComponent();
			monPanel.removeAll();
			monPanel.setLayout(new BoxLayout(monPanel, BoxLayout.Y_AXIS));
			monPanel.add(panelScrolable);
			JButton monBouton = new JButton("Supprimer plat");
			monBouton.addActionListener(monControlleur);
			monBouton.setActionCommand("Supprimer plat");
			monPanel.add(monBouton);
		}
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
			txt += " .... ";
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
			
			//Gestion de l'état
			if(item.getEtatCommande() == EtatCommande.Pret){
				this.setEnabled(false);
			} else {
				this.setEnabled(true);
			}
			
			return this;
		}

	}
	
	/**
	 * Cette classe est définie en interne car elle ne sert qu'à param la sélection dans notre liste
	 * Elle empêche la sélection des Item qui ont déjà été validés
	 * @author Damien
	 *
	 */
	private class DisabledItemSelectionModel extends DefaultListSelectionModel {

        private static final long serialVersionUID = 1L;

        @Override
        public void setSelectionInterval(int index0, int index1) {
        	Item itemIndex0 = (Item) listeCommande.get(index0);
            if (itemIndex0.getEtatCommande() == EtatCommande.PasPret) {
                super.setSelectionInterval(index0, index0);
            } else {
                /*
                 * The previously selected index is before this one,
                 * so walk forward to find the next selectable item.
                 */
                if (getAnchorSelectionIndex() < index0) {
                    for (int i = index0; i < listeCommande.size(); i++) {
                    	Item itemIndexI = (Item) listeCommande.get(i);
                        if (itemIndexI.getEtatCommande() == EtatCommande.PasPret) {
                            super.setSelectionInterval(i, i);
                            return;
                        }
                    }
                } /*
                 * Otherwise, walk backward to find the next selectable item.
                 */ else {
                    for (int i = index0; i >= 0; i--) {
                    	Item itemIndexI = (Item) listeCommande.get(i);
                        if (itemIndexI.getEtatCommande() == EtatCommande.PasPret) {
                            super.setSelectionInterval(i, i);
                            return;
                        }
                    }
                }
            }
        }
    }
}

