package com.benoitkienan.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame {

    JPanel       mainPan      = new JPanel();
    JPanel       boutonPan    = new JPanel();
    JPanel       connexionPan = new JPanel();
    BorderLayout bl           = new BorderLayout();
    GridLayout   gl           = new GridLayout( 2, 0 );

    JButton      solo, multi;

    public Fenetre() {
	
    }

    private void connexion() {

	mainPan.removeAll();

	mainPan.setLayout( bl );

	JPanel IPPan = new JPanel();
	JTextField formIP = new JTextField();
	JLabel labelIP = new JLabel( "IP :" );

	JPanel PortPan = new JPanel();
	JTextField formPort = new JTextField( "16540" );
	JLabel labelPort = new JLabel( "Port :" );

	JButton connexion = new JButton( "Connexion" );
	connexion.setPreferredSize( new Dimension( 100, 50 ) );

	formIP.setPreferredSize( new Dimension( 150, 30 ) );
	formIP.requestFocus();
	IPPan.add( labelIP );
	IPPan.add( formIP );

	formPort.setPreferredSize( new Dimension( 50, 30 ) );
	PortPan.add( labelPort );
	PortPan.add( formPort );

	connexionPan.add( IPPan );
	connexionPan.add( PortPan );

	mainPan.add( connexionPan, BorderLayout.CENTER );
	mainPan.add( connexion, BorderLayout.SOUTH );

	this.setContentPane( mainPan );
	this.repaint();
    }

    /**
     * @return the solo
     */
    public JButton getSolo() {
	return solo;
    }

    /**
     * @return the multi
     */
    public JButton getMulti() {
	return multi;
    }

}