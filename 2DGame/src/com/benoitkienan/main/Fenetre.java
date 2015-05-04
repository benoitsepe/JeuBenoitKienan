package com.benoitkienan.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame implements ActionListener {

    JPanel boutonPan = new JPanel();
    JPanel connexionPan = new JPanel();
    BorderLayout bl = new BorderLayout();

    JPanel mainPanel = new JPanel();
    Bouton solo = new Bouton("Solo");
    Bouton multi = new Bouton("Multijoueur");

    JPanel soloPanel = new JPanel();
    Bouton play = new Bouton("Jouer");
    Bouton editor = new Bouton("Editeur");

    public Fenetre() {

	JLabel label = new JLabel("Menu");
	mainPanel.setLayout(new GridBagLayout());

	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridx = 0;
	gbc.gridy = 0;
	mainPanel.add(label, gbc);

	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.gridy = gbc.gridy + 1;
	mainPanel.add(solo, gbc);
	gbc.gridy = gbc.gridy + 1;
	mainPanel.add(multi, gbc);

	setContentPane(mainPanel);

	setTitle("Ceci est un jeu super cool");
	setSize(200, 200);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);

	setContentPane(mainPanel);

	solo.addActionListener(this);
	multi.addActionListener(this);
	play.addActionListener(this);
	editor.addActionListener(this);

	setVisible(true);
    }

    private void connexion() {

	mainPanel.removeAll();

	mainPanel.setLayout(bl);

	JPanel IPPan = new JPanel();
	JTextField formIP = new JTextField();
	JLabel labelIP = new JLabel("IP :");

	JPanel PortPan = new JPanel();
	JTextField formPort = new JTextField("16540");
	JLabel labelPort = new JLabel("Port :");

	JButton connexion = new JButton("Connexion");
	connexion.setPreferredSize(new Dimension(100, 50));

	formIP.setPreferredSize(new Dimension(150, 30));
	formIP.requestFocus();
	IPPan.add(labelIP);
	IPPan.add(formIP);

	formPort.setPreferredSize(new Dimension(50, 30));
	PortPan.add(labelPort);
	PortPan.add(formPort);

	connexionPan.add(IPPan);
	connexionPan.add(PortPan);

	mainPanel.add(connexionPan, BorderLayout.CENTER);
	mainPanel.add(connexion, BorderLayout.SOUTH);

	this.setContentPane(mainPanel);
	this.repaint();
    }

    private void solo() {
	mainPanel.removeAll();

	JLabel label = new JLabel("Mode Solo");
	mainPanel.setLayout(new GridBagLayout());

	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridx = 1;
	gbc.gridy = 1;
	mainPanel.add(label, gbc);

	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.gridy = gbc.gridy + 1;
	mainPanel.add(play, gbc);
	gbc.gridy = gbc.gridy + 1;
	mainPanel.add(editor, gbc);

	setContentPane(mainPanel);

	setContentPane(mainPanel);
	this.repaint();
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == solo) {
	    solo();
	}

	if (e.getSource() == play) {
	    this.dispose();
	    com.benoitkienan.jeu.Fenetre fen = new com.benoitkienan.jeu.Fenetre("Pouet");
	}

	if (e.getSource() == editor) {
	    com.benoitkienan.editor.Fenetre fen = new com.benoitkienan.editor.Fenetre("Pouet");

	}

	if (e.getSource() == multi) {
	    connexion();
	}

    }

}