package com.benoitkienan.jeu;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Hud extends JPanel implements MouseListener{
    JButton gunButton = new JButton(new ImageIcon("Pictures/gun.png"));
    JButton eraserButton = new JButton(new ImageIcon("Pictures/eraser.png"));
    JButton blueBrickButton = new JButton(new ImageIcon("Pictures/blueBrick.png"));
    JButton redBrickButton = new JButton(new ImageIcon("Pictures/redBrick.png"));
    JButton blackBrickButton = new JButton(new ImageIcon("Pictures/blackBrick.png"));
    JButton goldBrickButton = new JButton(new ImageIcon("Pictures/goldBrick.png"));
    JButton rainbow = new JButton(new ImageIcon("Pictures/rainbow.png"));
    int toolSelected;
    
    public Hud(){
	
	this.setOpaque(true);
	
	this.addMouseListener(this);

    }
    
    public void paintComponent(Graphics g) {
	
    }
    
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
	
    }

    public void mousePressed(MouseEvent e) {
	
    }

    public void mouseReleased(MouseEvent e) {
	
    }
    
    
    public void setToolSelected(int tool) {
	toolSelected=tool;
    }
    
}
