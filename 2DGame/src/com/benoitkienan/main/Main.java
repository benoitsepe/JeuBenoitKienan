package com.benoitkienan.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

    public static void main( String[] args ) {
	
	JFrame mainFrame = new JFrame();
	
	JPanel mainPanel = new JPanel();
	Bouton solo = new Bouton("Solo");
	Bouton multi = new Bouton("Multijoueur");
	
	JPanel soloPanel= new JPanel();
	Bouton play = new Bouton("Jouer");
	Bouton editor = new Bouton("Editeur");
	
	
        JLabel label = new JLabel("Menu");
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(label, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy= gbc.gridy+1;
        mainPanel.add(solo, gbc);
        gbc.gridy= gbc.gridy+1;
        mainPanel.add(multi, gbc);
        
        mainFrame.setContentPane(mainPanel);
        
	mainFrame.setTitle("Ceci est un jeu super cool");
	mainFrame.setSize(200, 200);
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainFrame.setLocationRelativeTo(null);
	
	mainFrame.setContentPane(mainPanel);
	mainFrame.setVisible(true);
	
	

	
    }
}
