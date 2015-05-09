package com.benoitkienan.affichage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import CDIO.pathFinder.Node;

import com.benoitkienan.entities.Entity;
import com.benoitkienan.entities.Mob;
import com.benoitkienan.entities.Player;
import com.benoitkienan.niveau.Niveau;

public class PanneauGame extends JPanel implements MouseListener {

    Color color = Color.white;
    Niveau lvl = new Niveau();
    int pointeurX, pointeurY;
    double realPointeurX, realPointeurY;
    public double cellSizeX;
    public double cellSizeY;
    boolean clicGauche, clicDroit;
    public boolean clicMiddle;
    BufferedImage blueBrick, redBrick, blackBrick, goldBrick;
    ArrayList<Mob> MobList = new ArrayList<Mob>();
    ArrayList<Player> PlayerList = new ArrayList<Player>();

    double rotation = 0;
    AffineTransform rot = new AffineTransform();
    AffineTransform tx;
    AffineTransformOp op;
    Image img;
    Node node;
    Graphics2D g2;
    double zoom = 1;
    int width, height;
    int xMin, xMax, yMin, yMax;
    BufferedImage blueBrickImg, redBrickImg, blackBrickImg, goldBrickImg, grassImg;
    int toolSelected = 0;
    int mouseX, mouseY;

    Color exterior = Color.gray.darker();

    public PanneauGame() {

	this.addMouseListener(this);
	this.addMouseWheelListener(new MouseWheelListener() {

	    public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0) {
		    toolSelected = (toolSelected >= 7) ? toolSelected = 0 : toolSelected + 1;
		}

		if (e.getWheelRotation() > 0) {
		    toolSelected = (toolSelected <= 0) ? toolSelected = 7 : toolSelected - 1;
		}
	    }

	});
	this.addMouseMotionListener(new MouseMotionListener() {
	    public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	    }

	    public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	    }

	});

	cellSizeX = (1920 / lvl.getArraySizeX()) * 50;
	cellSizeY = (1080 / lvl.getArraySizeY()) * 50;
    }

    public void paintComponent(Graphics g) {
	realPointeurX = (((PlayerList.get(0).getPosX() - width) + ((mouseX) * 2)));
	realPointeurY = (((PlayerList.get(0).getPosY() - height) + ((mouseY) * 2)));
	pointeurX = (int) (((PlayerList.get(0).getPosX() - width / 2 / zoom) / cellSizeX) + ((mouseX / zoom) / cellSizeX));
	pointeurY = (int) (((PlayerList.get(0).getPosY() - height / 2 / zoom) / cellSizeY) + ((mouseY / zoom) / cellSizeY));

	zoom = PlayerList.get(0).getZoom();

	width = this.getWidth();
	height = this.getHeight();

	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(exterior);
	g2.fillRect(0, 0, this.getWidth(), this.getHeight());

	g2.translate((-PlayerList.get(0).getPosX() * zoom) + this.getWidth() / 2, (-PlayerList.get(0).getPosY() * zoom) + this.getHeight() / 2);
	g2.scale(zoom, zoom);

	// Création quadrillage
	/*
	 * 
	 * g.setColor(Color.black); for(int y=0; y<this.getHeight();
	 * y=y+(this.getHeight()/lvl.getArraySizeY())){ g.drawLine(0, y,
	 * this.getWidth(), y); }
	 * 
	 * for(int x=0;
	 * x<this.getWidth();x=x+(this.getWidth()/lvl.getArraySizeX())){
	 * g.drawLine(x,0,x,this.getHeight()); }
	 */
	// Fin création quadrillage

	if ((PlayerList.get(0).getPosX() - this.getWidth() / 2 / zoom) / cellSizeX < 0) {
	    xMin = 0;
	} else {
	    xMin = (int) ((PlayerList.get(0).getPosX() - this.getWidth() / 2 / zoom) / cellSizeX);
	}

	if (((PlayerList.get(0).getPosX() + this.getWidth() / 2 / zoom) / cellSizeX) + 5 > lvl.getArraySizeX()) {
	    xMax = lvl.getArraySizeX();
	} else {
	    xMax = (int) ((PlayerList.get(0).getPosX() + this.getWidth() / 2 / zoom) / cellSizeX) + 5;
	}

	if ((PlayerList.get(0).getPosY() - this.getHeight() / 2 / zoom) / cellSizeY < 0) {
	    yMin = 0;
	} else {
	    yMin = (int) ((PlayerList.get(0).getPosY() - this.getHeight() / 2 / zoom) / cellSizeY);
	}

	if (((PlayerList.get(0).getPosY() + this.getHeight() / 2 / zoom) / cellSizeY) + 5 > lvl.getArraySizeY()) {
	    yMax = (lvl.getArraySizeY());
	} else {
	    yMax = (int) ((PlayerList.get(0).getPosY() + this.getHeight() / 2 / zoom) / cellSizeY) + 5;
	}

	for (int x = xMin; x < xMax; x++) {
	    for (int y = yMin; y < yMax; y++) {
		g2.drawImage(lvl.getArray()[x][y].getImg(), x * (int) cellSizeX, y * (int) cellSizeY, (int) cellSizeX, (int) cellSizeY, this);
	    }
	}

	g2.setColor(Color.green);
	for (Mob mob : MobList) {
	    // Dessin du path
	    /*
	     * if (mob.getPath() != null) { if (mob.shortestPath.size() > 0) {
	     * for (int i = 0; i < mob.shortestPath.size(); i++) {
	     * g2.fillRect((int) (mob.getPath().get(i).getX() * cellSizeX),
	     * (int) (mob.getPath().get(i).getY() * cellSizeY), (int) cellSizeX
	     * / 2, (int) cellSizeY / 2); } } }
	     */
	    // Fin dessin path

	    g2.drawImage(rotate(mob.getImage(), (int) cellSizeX, (int) cellSizeY, mob.getRotationWithVectors()), (int) (mob.getPosX() - cellSizeX / 2), (int) (mob.getPosY() - cellSizeY / 2), (int) cellSizeX, (int) cellSizeY, this);
	    g2.draw(mob.getHitbox());
	}

	for (Player player : PlayerList) {
	    g2.draw(player.getHitbox());
	    g2.drawImage(rotate(player.getImage(), (int) cellSizeX, (int) cellSizeY, player.getRotationWithMouse(realPointeurX, realPointeurY)), (int) (player.getPosX() - cellSizeX / 2), (int) (player.getPosY() - cellSizeY / 2), (int) cellSizeX, (int) cellSizeY, this);
	    // player.getRotationWithMouse(realPointeurX, realPointeurY);
	    // g2.drawImage(player.getImage(), (int) (player.getPosX() -
	    // cellSizeX / 2), (int) (player.getPosY() - cellSizeY / 2), (int)
	    // cellSizeX, (int) cellSizeY, this);

	}

	// Dessin de vecteurs
	for (Player ent : PlayerList) {
	    if ((int) ent.getVectorX() != 0 || (int) ent.getVectorY() != 0) {
		((Graphics2D) g2).setStroke(new BasicStroke(10));
		g2.setColor(Color.WHITE);
		g2.drawLine((int) (ent.getPosX()), (int) (ent.getPosY()), (int) ((ent.getPosX()) + ent.getVectorX() * 10), (int) ((ent.getPosY()) + ent.getVectorY() * 10));
	    }
	}

	for (Mob ent : MobList) {
	    if ((int) ent.getVectorX() != 0 || (int) ent.getVectorY() != 0) {
		((Graphics2D) g2).setStroke(new BasicStroke(10));
		g2.setColor(Color.WHITE);
		g2.drawLine((int) (ent.getPosX()), (int) (ent.getPosY()), (int) ((ent.getPosX()) + ent.getVectorX() * 10), (int) ((ent.getPosY()) + ent.getVectorY() * 10));
	    }
	}

	g2.setColor(Color.RED);
	g2.setStroke(new BasicStroke(10));
	g2.drawRect((int) (pointeurX * cellSizeX), (int) (pointeurY * cellSizeY), (int) cellSizeX, (int) cellSizeY);

    }

    public static BufferedImage rotate(BufferedImage img, int cellSizeX, int cellSizeY, double rotation) {
	int w = cellSizeX;
	int h = cellSizeY;
	BufferedImage newImage = new BufferedImage(cellSizeX, cellSizeY, img.getType());
	Graphics2D g2 = newImage.createGraphics();
	g2.rotate(rotation, h / 2, w / 2);
	g2.drawImage(img, 0, 0, cellSizeX, cellSizeY, null);
	return newImage;
    }

    public void setNiveau(Niveau niv) {
	lvl = niv;
    }

    public void setMobList(ArrayList<Mob> mo) {
	MobList = mo;
    }

    public void setPlayerList(ArrayList<Player> play) {
	PlayerList = play;
    }

    public double getRealPointeurX() {
	return realPointeurX;
    }

    public double getRealPointeurY() {
	return realPointeurY;
    }

    public int getPointeurX() {
	return pointeurX;
    }

    public int getPointeurY() {
	return pointeurY;
    }

    public boolean getClicGauche() {
	return clicGauche;
    }

    public boolean getClicDroit() {
	return clicDroit;
    }

    public boolean getClicMiddle() {
	return clicMiddle;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
	clicGauche = false;
	clicDroit = false;
	clicMiddle = false;
    }

    public void mousePressed(MouseEvent e) {

	if (e.getButton() == 1)
	    clicGauche = true;

	if (e.getButton() == 3)
	    clicDroit = true;

	if (e.getButton() == 2)
	    clicMiddle = true;

    }

    public void mouseReleased(MouseEvent arg0) {
	clicGauche = false;
	clicDroit = false;
	clicMiddle = false;
    }

    public int getToolSelected() {
	return toolSelected;
    }

}
