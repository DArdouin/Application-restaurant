package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import controller.ControlleurCommande;
import model.Boisson;
import model.Entree;
import model.Plat;
import model.PlatPrincipal;
import model.Restaurant;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.GridLayout;

public class PannelCommande extends JPanel implements Observer{
	private Restaurant monModele;
	private ControlleurCommande monControlleur;
	private JPanel panelClients, panelEntree, panelPlatPrincipal, panelBoisson;
	private JButton btnAjoutClients;
	private JTabbedPane ongletClients, ongletPlats;
	private ArrayList<JButton> listeBoutons;
	private JButton btnValiderCommandeTable;
	
	public PannelCommande() {
		listeBoutons = new ArrayList<JButton>();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{250, 202, 364};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		setLayout(gridBagLayout);
		
		//La gestion des plats
		ongletPlats = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane_1 = new GridBagConstraints();
		gbc_tabbedPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane_1.gridwidth = 2;
		gbc_tabbedPane_1.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_1.gridx = 1;
		gbc_tabbedPane_1.gridy = 0;
		add(ongletPlats, gbc_tabbedPane_1);
			//Entrée
		panelEntree = new JPanel();
		ongletPlats.add("Entrées", panelEntree);
		panelEntree.setLayout(new GridLayout(0, 3, 0, 0));
			//Plat principal
		panelPlatPrincipal = new JPanel();
		ongletPlats.add("Plats", panelPlatPrincipal);
		panelPlatPrincipal.setLayout(new GridLayout(0, 3, 0, 0));
			//Boisson
		panelBoisson = new JPanel();
		ongletPlats.add("Boissons", panelBoisson);
		panelBoisson.setLayout(new GridLayout(0, 3, 0, 0));
		
		
		//La gestion des clients
		panelClients = new JPanel();
		btnAjoutClients = new JButton("Ajouter un client");
		btnAjoutClients.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		add(panelClients, gbc_tabbedPane);
		panelClients.setLayout(new BoxLayout(panelClients,BoxLayout.Y_AXIS));
		panelClients.add(btnAjoutClients);
		
		btnValiderCommandeTable = new JButton("Valider commande table");
		btnValiderCommandeTable.setActionCommand("Valider commande");
		listeBoutons.add(btnValiderCommandeTable);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		add(btnValiderCommandeTable, gbc_btnNewButton);
	}

	//Méthode permettant l'ajout du TabbedPane client dans notre panel
	//Ce TabbedPane un peu particulier est ajouté à part, puisqu'il possède son propre controlleur
	public void addOngletClients(ClosablePane tabbedPane){
		ongletClients = tabbedPane;
		ongletClients.setTabPlacement(SwingConstants.LEFT);
		panelClients.add(ongletClients);
	}
	
	public void setModele(Restaurant m){
		this.monModele = m;
		JButton bouton = null;
		//On ajoute les boutons correspondants aux plats
		for(Plat p : monModele.getCarte()){
			bouton = new JButton(p.getLibelle());
			bouton.setActionCommand("Ajouter plat");
			listeBoutons.add(bouton);
			//Entrée
			if(p instanceof Entree)
				panelEntree.add(bouton);
			//Plat principal
			else
				panelPlatPrincipal.add(bouton);			
		}
		//On ajoute les boutons correspondant aux boisons
		for(Boisson b : monModele.getCarteBoissons()){
			bouton = new JButton(b.getLibelle());
			bouton.setActionCommand("Ajouter boisson");
			listeBoutons.add(bouton);
			panelBoisson.add(bouton);
		}
	}

	public void setControlleur(ControlleurCommande c){
		this.monControlleur = c;
		btnAjoutClients.setActionCommand("Ajouter client");
		btnAjoutClients.addActionListener(monControlleur);
		for(JButton b : listeBoutons)
			b.addActionListener(monControlleur);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	}

	/**
	 * Permet d'indiquer à l'utilisateur qu'il n'est pas possible d'ajouter un client si
	 * la table est dans cet état
	 */
	public void alerteEtatTable() {
		JOptionPane.showMessageDialog(new JFrame(),
			    "Impossible d'ajouter un client tant que la table n'a pas été nettoyée",
			    "Erreur",
			    JOptionPane.ERROR_MESSAGE);
	}

	public void alerterManqueClient() {
		JOptionPane.showMessageDialog(new JFrame(),
			    "Impossible d'ajouter un plat tant qu'un client n'est pas installé. Merci d'ajouter un client à cette table",
			    "Erreur",
			    JOptionPane.ERROR_MESSAGE);
	}
}
