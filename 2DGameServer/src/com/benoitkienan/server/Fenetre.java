package com.benoitkienan.server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

    JPanel  mainPan = new JPanel();
    JLabel  label   = new JLabel();

    JButton fermer  = new JButton( "STOP" );

    public Fenetre( String ip, int port ) {

        this.setTitle( "Serveur" );
        this.setSize( 400, 200 );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setLocationRelativeTo( null );

        label.setText( "IP : " + ip + ", Port : " + port );
        label.setHorizontalAlignment( 0 );

        fermer.setPreferredSize( new Dimension( 300, 50 ) );

        mainPan.setLayout( new BorderLayout() );
        mainPan.add( label, BorderLayout.CENTER );
        mainPan.add( fermer, BorderLayout.SOUTH );

        this.setContentPane( mainPan );
        this.setResizable( false );

        fermer.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent a ) {
                System.exit( 0 );
            }
        } );

        this.setVisible( true );

    }
}
