package com.benoitkienan.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Bouton extends JButton implements MouseListener {
    String name;

    public Bouton(String str) {
	super(str);
	JLabel label = new JLabel(str, SwingConstants.CENTER);
	this.add(label);
    }

    public void paintComponent(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.white);
	g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	g2d.setColor(Color.black);
    }

    public void mouseClicked(MouseEvent arg0) {

    }

    public void mouseEntered(MouseEvent arg0) {

    }

    public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
    }

    public void mousePressed(MouseEvent arg0) {

    }

    public void mouseReleased(MouseEvent arg0) {

    }

}
