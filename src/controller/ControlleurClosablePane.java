package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Item;
import model.Restaurant;
import view.ClosablePane;
import view.MainFrame;

public class ControlleurClosablePane implements MouseListener, MouseMotionListener, ActionListener
{
	private int selectedTab;
	private final int width = 8; private final int height = 8;
	private Rectangle rectangle = new Rectangle(0, 0, 8, 8);
	private ClosablePane maVue;
	private Restaurant monModele;
	private MainFrame mainFrame;

	public ControlleurClosablePane(MainFrame m) {
		this.mainFrame = m;
	}

	public void mouseEntered(MouseEvent me) {
	}

	public void mouseExited(MouseEvent me) {
	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseClicked(MouseEvent me) {
	}

	public void mouseDragged(MouseEvent me) {  
	} 

	/**
	 * C'est dans cette méthode que l'on ferme le pannel (suppression du client)
	 */
	public void mouseReleased(MouseEvent me) { 
		if (closeUnderMouse(me.getX(), me.getY())) {
			boolean isToCloseTab = maVue.tabAboutToClose(this.selectedTab);
			if ((isToCloseTab) && (this.selectedTab > -1)) {
				//Supprimer le client correspondant à l'onglet selectionné dans le modèle
				supprimerClient(maVue.getTabTitleAt(selectedTab));
			}
		}
		else monModele.changerClientActif("Client " + Integer.toString(selectedTab));
		this.selectedTab = this.maVue.getSelectedIndex();
		maVue.repaint();
	}

	public void mouseMoved(MouseEvent me)
	{
		maVue.setMeX(me.getX());
		maVue.setMeY(me.getY());
		if (maVue.mouseOverTab(maVue.getMeX(), maVue.getMeY())) {
			controlCursor();
			this.maVue.repaint();
		}
	}

	private void controlCursor() {
		if (this.maVue.getTabCount() > 0)
			if (closeUnderMouse(maVue.getMeX(), maVue.getMeY())) {
				this.maVue.setCursor(new Cursor(12));
				if (this.selectedTab > -1)
					this.maVue.setToolTipTextAt(this.selectedTab, "Close " + this.maVue.getTitleAt(this.selectedTab));
			}
			else {
				this.maVue.setCursor(new Cursor(0));
				if (this.selectedTab > -1)
					this.maVue.setToolTipTextAt(this.selectedTab, "");
			}
	}

	public void setSelectedTab(int i){
		this.selectedTab = i;
	}

	private boolean closeUnderMouse(int x, int y) {
		this.rectangle.x = maVue.getCloseX();
		this.rectangle.y = maVue.getCloseY();
		return this.rectangle.contains(x, y);
	}

	public void setVue(ClosablePane v){
		this.maVue = v;
	}

	public void setModele(Restaurant m){
		this.monModele = m;
	}

	public void supprimerClient(String nom){
		monModele.supprimerClient(nom);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Supprimer plat")){
			if(maVue.getPlatSelectionne() != null){
				for(Item item : maVue.getPlatSelectionne()){
					if(item != null) monModele.retirerItemClientActif(item);
				}
			}
		}
	}
}
