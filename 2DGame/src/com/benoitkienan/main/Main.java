package com.benoitkienan.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Main {

    public static void main( String[] args ) {

        final com.benoitkienan.selectionMode.Fenetre selection = new com.benoitkienan.selectionMode.Fenetre();

        JButton solo = selection.getSolo();
        JButton multi = selection.getMulti();

        solo.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent a ) {
                selection.setVisible( false );
                selection.dispose();
                com.benoitkienan.jeu.vue.Fenetre fen = new com.benoitkienan.jeu.vue.Fenetre( "Pouet" );
            }
        } );

    }
}
