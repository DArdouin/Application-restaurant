package view;

import java.awt.CardLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.ControlleurAccueil;
import controller.ControlleurClosablePane;
import controller.ControlleurCommande;
import controller.ControlleurConnexion;
import controller.ControlleurEtatTable;
import controller.ControlleurFacture;
import controller.ControlleurOnglets;
import model.Restaurant;
import model.Table;


public class MainFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar barreDeMenu;
	private JMenuItem aide;
	private JPanel cardsPanel ;
	private Restaurant monModele;
	private PannelConnexion vueConnexion;
	private PannelAccueil vueAccueil;
	private PannelOnglets ongletsMenu;
	private PannelEtatTable vueEtatTable;
	private PannelCommande vueCommande;
	private ClosablePane ongletsClients;
	private PannelFacture vueFacture;
	private ControlleurConnexion controlleurConnexion;
	private ControlleurAccueil controlleurAccueil;
	private ControlleurOnglets controlleurOnglets;
	private ControlleurEtatTable controlleurEtatTable;
	private ControlleurCommande controlleurCommande;
	private ControlleurClosablePane controlleurClosablePane;
	private ControlleurFacture controlleurFacture;
	private String currentPanelName;
	
	public MainFrame(String nomLogiciel) {
		super(nomLogiciel);
		cardsPanel = new JPanel(new CardLayout());
		this.add(cardsPanel);
		barreDeMenu = new JMenuBar();
		aide = new JMenuItem("?");
		aide.setActionCommand("Aide");
		aide.addActionListener(this);
		barreDeMenu.add(aide);
		this.setJMenuBar(barreDeMenu);
		
		//Modèle MVC :
		//--On définit le modèle
		monModele = new Restaurant();

		//--On définit les controlleurs
		controlleurConnexion = new ControlleurConnexion(this);
		controlleurAccueil = new ControlleurAccueil(this);
		controlleurOnglets = new ControlleurOnglets(this);
		controlleurEtatTable = new ControlleurEtatTable(this);
		controlleurCommande = new ControlleurCommande(this);
		controlleurClosablePane = new ControlleurClosablePane(this);
		controlleurFacture = new ControlleurFacture(this);
		
		//--On définit les vues
		vueConnexion = new PannelConnexion();
		vueAccueil = new PannelAccueil();
		ongletsMenu = new PannelOnglets();
		vueEtatTable = new PannelEtatTable();
		vueCommande = new PannelCommande();
		ongletsClients = new ClosablePane(); //Onglet inclus dans la vue commande, possède son propre controlleur
		vueFacture = new PannelFacture();
		
		//----On abonne les vues aux modèles et aux controlleurs
		vueConnexion.setModele(monModele);
		vueConnexion.setControlleur(controlleurConnexion);
		vueAccueil.setModele(monModele);
		vueAccueil.setControlleur(controlleurAccueil);
		ongletsMenu.setModele(monModele);
		ongletsMenu.setControlleur(controlleurOnglets);
		vueEtatTable.setModele(monModele);
		vueEtatTable.setControlleur(controlleurEtatTable);
		vueCommande.setModele(monModele);
		vueCommande.setControlleur(controlleurCommande);
		ongletsClients.setModele(monModele);
		ongletsClients.setControlleur(controlleurClosablePane);
		vueFacture.setModele(monModele);
		vueFacture.setControlleur(controlleurFacture);
		
		//--On indique les vues & les modèles au controlleur
		controlleurConnexion.setModele(monModele);
		controlleurConnexion.setVue(vueConnexion);
		controlleurAccueil.setModele(monModele);
		controlleurAccueil.setVue(vueAccueil);
		controlleurOnglets.setModele(monModele);
		controlleurOnglets.setVue(ongletsMenu);
		controlleurEtatTable.setModele(monModele);
		controlleurEtatTable.setVue(vueEtatTable);
		controlleurCommande.setModele(monModele);
		controlleurCommande.setVue(vueCommande);
		controlleurClosablePane.setModele(monModele);
		controlleurClosablePane.setVue(ongletsClients);
		controlleurFacture.setModele(monModele);
		controlleurFacture.setVue(vueFacture);
		
		//--On ajoute les observer au modèle
		monModele.addObserver(vueConnexion);
		monModele.addObserver(vueAccueil);
		monModele.addObserver(ongletsMenu);
		monModele.addObserver(vueEtatTable);
		monModele.addObserver(vueCommande);
		monModele.addObserver(ongletsClients);
		monModele.addObserver(vueFacture);
		
		//--On ajoute les vues (les cards) à la mainFrame
		cardsPanel.add(vueConnexion,"PanelConnexion");
		cardsPanel.add(vueAccueil,"PannelAccueil");
		cardsPanel.add(ongletsMenu,"PannelOnglets");
		ongletsMenu.ajouterOnglet(vueEtatTable);
		ongletsMenu.ajouterOnglet(vueCommande);
		ongletsMenu.ajouterOnglet(vueFacture);
		vueCommande.addOngletClients(ongletsClients); //Ajoute le tabbedPan fermable à la vue
	}
	
	//Méthode permettant d'afficher le panel souhaité
	public void showCurrentPanel(String panelToShow) {
		CardLayout currentLayout = (CardLayout) (cardsPanel.getLayout());
		currentPanelName = new String(panelToShow);
		currentLayout.show(cardsPanel, panelToShow);
	}
	
	public String getCurrentPanel(){
		return currentPanelName;
	}

	/**
	 * Bien que le modèle MVC voudrait que les listener ne soient pas dans la vue,
	 * on ne gère dans la mainFrame que le clic sur le bouton d'aide
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Aide"))
			try {
				Runtime.getRuntime().exec("hh.exe Application restaurant.chm");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}