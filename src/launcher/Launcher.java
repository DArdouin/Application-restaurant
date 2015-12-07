package launcher;

import java.awt.Dimension;

import javax.swing.JFrame;

import view.MainFrame;

public class Launcher {

	/**
	 * Création et affichage de la fenêtre principale.
     */
    private static void createAndShowGUI() {
        //Création et paramétrage de la fenêtre principale.
    	MainFrame mainFrame = new MainFrame("Programme de gestion des commandes - Eggs île");
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    	mainFrame.setPreferredSize(new Dimension(800,600));
    	
    	//Méthode pour afficher le panneau courant 
    	mainFrame.showCurrentPanel("PanelConnexion");
         
        //Affichage de la fenêtre principale
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
	
	public static void main(String[] args) {
			
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
}
