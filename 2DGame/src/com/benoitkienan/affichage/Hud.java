package com.benoitkienan.affichage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.benoitkienan.items.Item;
import com.benoitkienan.items.ItemManager;

public class Hud extends JPanel implements MouseListener {
    Item[] hudItems = new Item[8];
    int toolSelected;
    int caseSize;

    public Hud() {
	ItemManager itemManager = new ItemManager();

	hudItems[0] = (itemManager.getItemList().get("spawner"));
	hudItems[1] = (itemManager.getItemList().get("blackBrick"));
	hudItems[2] = (itemManager.getItemList().get("redBrick"));
	hudItems[3] = (itemManager.getItemList().get("goldBrick"));
	hudItems[4] = (itemManager.getItemList().get("blueBrick"));
	hudItems[5] = (itemManager.getItemList().get("grass"));

	caseSize = this.getWidth() / hudItems.length;

	this.setBackground(Color.black);
	this.setOpaque(true);
	this.addMouseListener(this);

    }

    public void paintComponent(Graphics g) {
	caseSize = this.getWidth() / hudItems.length;

	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(Color.black);
	g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	for (int i = 0; i < hudItems.length; i++) {
	    if (hudItems[i] != null) {
		g2.setColor(Color.white);
		g2.fillRect((i*caseSize)+5, 5, caseSize-10, this.getHeight()-10);
		g2.drawImage(hudItems[i].getImg(), (i * caseSize) + 10, 10, caseSize - 20, this.getHeight()-20, this);
	    }
	    
	    g2.setColor(Color.blue);
	    g2.setStroke(new BasicStroke(5));
	    g2.drawRect((toolSelected*caseSize)+3, 3, caseSize-8, this.getHeight()-8);
	}

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

    public void setToolSelected(int tool){
	toolSelected=tool;
    }
    
    public Item[] getHudItems(){
	return hudItems;
    }

}
