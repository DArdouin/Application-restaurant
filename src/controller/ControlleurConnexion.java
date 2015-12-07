package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import model.Restaurant;
import view.MainFrame;
import view.PannelConnexion;

public class ControlleurConnexion implements ActionListener{
	private PannelConnexion vueConnexion;
	private Restaurant monModele;
	private MainFrame mainFrame;
	
	public ControlleurConnexion(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	/**
	 * Permet d'authoriser ou non la connexion de l'utilisateur
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Connexion")) connect();
	}
	
	public void setModele(Restaurant m){
		this.monModele = m;
	}

	public void setVue(PannelConnexion v){
		this.vueConnexion = v;
	}
	
	public void connect(){
		String nom = vueConnexion.getNom();
		String prenom = vueConnexion.getPrenom();
		String password = vueConnexion.getPassword();
		
		boolean isConnected = monModele.connexion(nom,prenom,password);
		if(isConnected){
			mainFrame.showCurrentPanel("PannelAccueil");
		}else{
			JOptionPane.showMessageDialog(mainFrame,
				    "Le nom de l'utilisateur ou le mot de passe est invalide.",
				    "Erreur de connexion",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
}
