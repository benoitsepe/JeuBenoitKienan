package com.benoitkienan.affichage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.benoitkienan.items.Item;
import com.benoitkienan.main.Bouton;

public class OptionsMenu extends JPanel {
    Item[] hudItems = new Item[8];
    int toolSelected;
    int caseSize;
    boolean needToQuit = false;
    Bouton play = new Bouton("Reprendre");
    Bouton control = new Bouton("Contrôles");
    Bouton quit = new Bouton("Quitter");

    public OptionsMenu() {

	this.removeAll();

	JLabel label = new JLabel("Options");
	this.setLayout(new GridBagLayout());

	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridx = 1;
	gbc.gridy = 1;
	this.add(label, gbc);

	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.gridy = gbc.gridy + 1;
	this.add(play, gbc);
	gbc.gridy = gbc.gridy + 1;
	this.add(control, gbc);
	gbc.gridy = gbc.gridy + 1;
	this.add(quit, gbc);
	this.repaint();

	this.setBackground(Color.black);
	this.setOpaque(false);

	play.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent a) {
		closeMenu();
	    }
	});

	control.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent a) {
		System.out.println("Menu contrôle");
	    }
	});

	quit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent a) {
		needToQuit = true;
	    }
	});

    }

    public void closeMenu() {
	this.setVisible(false);
    }

    public void openMenu() {
	this.setVisible(true);
    }

    /**
     * @return the needToQuit
     */
    public boolean isNeedToQuit() {
	return needToQuit;
    }

}
