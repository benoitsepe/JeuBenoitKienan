package com.benoitkienan.jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

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
	eraserButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent a) {
		toolSelected = 0;
	    }
	});

	blueBrickButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent a) {
		toolSelected = 1;
	    }
	});

	redBrickButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent a) {
		toolSelected = 2;
	    }
	});

	blackBrickButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent a) {
		toolSelected = 3;
	    }
	});

	goldBrickButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent a) {
		toolSelected = 4;
	    }
	});

	gunButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent a) {
		toolSelected = 5;
	    }
	});
	
	this.add(gunButton);
	this.add(eraserButton);
	this.add(blueBrickButton);
	this.add(redBrickButton);
	this.add(blackBrickButton);
	this.add(goldBrickButton);
	
	this.addMouseListener(this);

    }
    
    
    public void mouseClicked(MouseEvent e) {
	System.out.println(toolSelected);
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
	
    }

    public void mousePressed(MouseEvent e) {
	
    }

    public void mouseReleased(MouseEvent e) {
	
    }
    
    
    public int getToolSelected() {
	return toolSelected;
    }
    
}
