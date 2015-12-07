package launcher;

import java.awt.Dimension;

import javax.swing.JFrame;

import view.MainFrame;

public class Launcher {

	/**
	 * Cr�ation et affichage de la fen�tre principale.
     */
    private static void createAndShowGUI() {
        //Cr�ation et param�trage de la fen�tre principale.
    	MainFrame mainFrame = new MainFrame("Programme de gestion des commandes - Eggs �le");
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    	mainFrame.setPreferredSize(new Dimension(800,600));
    	
    	//M�thode pour afficher le panneau courant 
    	mainFrame.showCurrentPanel("PanelConnexion");
         
        //Affichage de la fen�tre principale
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
