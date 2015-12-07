package view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import controller.ControlleurConnexion;
import model.Restaurant;

import java.awt.SystemColor;
import java.awt.Color;

public class PannelConnexion extends JPanel implements Observer{
	private JTextField fieldNom;
	private JTextField fieldPrenom;
	private JPasswordField fieldPassword;
	private JPanel panel;
	private JButton btnConnexion;
	private Restaurant monModele;
	private ControlleurConnexion monControlleur;
	public PannelConnexion() {
		setBackground(SystemColor.activeCaption);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setForeground(SystemColor.activeCaption);
		add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{265, 144, 107, 0};
		gbl_panel.rowHeights = new int[]{216, 0, 0, 0, 10, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNom = new JLabel("Nom");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.anchor = GridBagConstraints.EAST;
		gbc_lblNom.fill = GridBagConstraints.VERTICAL;
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 0;
		gbc_lblNom.gridy = 1;
		panel.add(lblNom, gbc_lblNom);
		
		fieldNom = new JTextField();
		GridBagConstraints gbc_fieldNom = new GridBagConstraints();
		gbc_fieldNom.fill = GridBagConstraints.BOTH;
		gbc_fieldNom.gridwidth = 2;
		gbc_fieldNom.insets = new Insets(0, 0, 5, 0);
		gbc_fieldNom.gridx = 1;
		gbc_fieldNom.gridy = 1;
		panel.add(fieldNom, gbc_fieldNom);
		fieldNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Pr\u00E9nom");
		GridBagConstraints gbc_lblPrenom = new GridBagConstraints();
		gbc_lblPrenom.anchor = GridBagConstraints.EAST;
		gbc_lblPrenom.fill = GridBagConstraints.VERTICAL;
		gbc_lblPrenom.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrenom.gridx = 0;
		gbc_lblPrenom.gridy = 2;
		panel.add(lblPrenom, gbc_lblPrenom);
		
		fieldPrenom = new JTextField();
		GridBagConstraints gbc_fieldPrenom = new GridBagConstraints();
		gbc_fieldPrenom.fill = GridBagConstraints.BOTH;
		gbc_fieldPrenom.gridwidth = 2;
		gbc_fieldPrenom.insets = new Insets(0, 0, 5, 0);
		gbc_fieldPrenom.gridx = 1;
		gbc_fieldPrenom.gridy = 2;
		panel.add(fieldPrenom, gbc_fieldPrenom);
		fieldPrenom.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.anchor = GridBagConstraints.EAST;
		gbc_lblMotDePasse.fill = GridBagConstraints.VERTICAL;
		gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse.gridx = 0;
		gbc_lblMotDePasse.gridy = 3;
		panel.add(lblMotDePasse, gbc_lblMotDePasse);
		
		fieldPassword = new JPasswordField();
		GridBagConstraints gbc_fieldPassword = new GridBagConstraints();
		gbc_fieldPassword.insets = new Insets(0, 0, 5, 0);
		gbc_fieldPassword.fill = GridBagConstraints.BOTH;
		gbc_fieldPassword.gridwidth = 2;
		gbc_fieldPassword.gridx = 1;
		gbc_fieldPassword.gridy = 3;
		panel.add(fieldPassword, gbc_fieldPassword);
		
		btnConnexion = new JButton("Connexion");
		GridBagConstraints gbc_btnConnexion = new GridBagConstraints();
		gbc_btnConnexion.gridx = 2;
		gbc_btnConnexion.gridy = 4;
		panel.add(btnConnexion, gbc_btnConnexion);
		btnConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		/**
		 * TODO ajoute d'un listener
		 */
	}
	
	public String getNom(){
		return fieldNom.getText();
	}
	
	public String getPrenom(){
		return fieldPrenom.getText();
	}
	
	public String getPassword(){
		return new String(fieldPassword.getPassword());
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setModele(Restaurant m){
		this.monModele = m;
	}
	
	public void setControlleur(ControlleurConnexion c){
		this.monControlleur = c;
		btnConnexion.setActionCommand("Connexion");
		btnConnexion.addActionListener(c);
	}
}
