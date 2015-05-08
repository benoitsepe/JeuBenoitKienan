package com.benoitkienan.entities;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import com.benoitkienan.affichage.PanneauGame;
import com.benoitkienan.jeu.Niveau;
import com.benoitkienan.tiles.Tile;
import com.benoitkienan.tiles.TileManager;

public class Entity {
    Niveau niveau;
    double posX, posY;
    private double vectorX;
    private double vectorY;
    int speed;
    Random rand = new Random();
    PanneauGame panGame;
    Color couleur = Color.blue;
    double rotation = 0; // En radians
    double modZ;
    int d, x, y, r;
    BufferedImage image;
    int masse = 10;
    TileManager tileManager;
    String name;
    
    int life;

    public Entity(String name) {
	this.name=name;
	tileManager = new TileManager();
	try {
	    image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/notDefined.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public Entity(String name, String imgName) {
	this.name=name;
	tileManager = new TileManager();
	try {
	    image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/"+imgName));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void sayInfos() {
	System.out.println("--------------------------------------------------------------");
	System.out.println("posX:" + posX + " posY:" + posY);
	System.out.println("vectorX:" + getVectorX() + " vectorY:" + getVectorY());
    }

    public void spawnRandom() { // Place l'entité aléatoirement
	do {
	    posX = (double) rand.nextInt((niveau.getArraySizeX() - 2) * (int) panGame.cellSizeX);
	    posY = (double) rand.nextInt((niveau.getArraySizeY() - 2) * (int) panGame.cellSizeY);
	} while (niveau.getArray()[(int) (posX / panGame.cellSizeX)][(int) (posY / panGame.cellSizeY)].isSolid());
	System.out.println("Spawned at x:" + posX + " y:" + posY);

    }

    public Tile[][] nuke(Tile[][] array, int rayon) {
	try {
	    for (int r = 0; r < rayon; r++) {
		// Algorithme de tracé de cercle d'Andres
		x = 0;
		y = r;
		d = r - 1;
		while (y >= x) {
		    array[(int) (posX / panGame.cellSizeX) + x][(int) (posY / panGame.cellSizeY) + y] = tileManager.grass;
		    array[(int) (posX / panGame.cellSizeX) + y][(int) (posY / panGame.cellSizeY) + x] = tileManager.grass;
		    array[(int) (posX / panGame.cellSizeX) - x][(int) (posY / panGame.cellSizeY) + y] = tileManager.grass;
		    array[(int) (posX / panGame.cellSizeX) - y][(int) (posY / panGame.cellSizeY) + x] = tileManager.grass;
		    array[(int) (posX / panGame.cellSizeX) + x][(int) (posY / panGame.cellSizeY) - y] = tileManager.grass;
		    array[(int) (posX / panGame.cellSizeX) + y][(int) (posY / panGame.cellSizeY) - x] = tileManager.grass;
		    array[(int) (posX / panGame.cellSizeX) - x][(int) (posY / panGame.cellSizeY) - y] = tileManager.grass;
		    array[(int) (posX / panGame.cellSizeX) - y][(int) (posY / panGame.cellSizeY) - x] = tileManager.grass;

		    if (d >= 2 * x) {
			d = d - 2 * x - 1;
			x = x + 1;
		    } else if (d < 2 * (r - y)) {
			d = d + 2 * y - 1;
			y = y - 1;
		    } else {
			d = d + 2 * (y - x - 1);
			y = y - 1;
			x = x + 1;
		    }
		}
	    }
	} catch (ArrayIndexOutOfBoundsException e) {
	    e.printStackTrace();
	}
	return array;
    }

    public void setImage(BufferedImage img) {
	image = img;
    }

    public BufferedImage getImage() {
	return image;
    }

    public double getRotation() {
	return rotation;
    }

    public double getRotationWithVectors() {
	if (getVectorX() != 0 || getVectorY() != 0) {

	    modZ = Math.sqrt(Math.pow(getVectorX(), 2) + Math.pow(getVectorY(), 2));
	    rotation = Math.acos(getVectorX() / modZ);
	    return rotation;
	} else {
	    return 0;
	}
    }

    public void collide() { // Collisions avec les blocs seulement
	try {
	    if (niveau.getArray()[(int) ((posX + getVectorX()) / panGame.cellSizeX)][(int) (posY / panGame.cellSizeY)].isSolid()) {
		setVectorX(0);
	    }

	    if (niveau.getArray()[(int) (posX / panGame.cellSizeX)][(int) ((posY + getVectorY()) / panGame.cellSizeY)].isSolid()) {
		setVectorY(0);
	    }

	} catch (ArrayIndexOutOfBoundsException e) {
	    e.printStackTrace();
	}
    }

    public void collideEntites(ArrayList<Entity> entList) {
	for (Entity ent : entList) {
	    if (ent != this) {
		if (checkCollision((int) ent.getPosX(), (int) ent.getPosY(), (int) (panGame.cellSizeX), (int) (panGame.cellSizeY))) {
		    setVectorX((getVectorX() > 0) ? (getVectorX() + ent.getVectorX() + 1) : (getVectorX() + ent.getVectorX() - 1));
		    setVectorY((getVectorY() > 0) ? (getVectorY() + ent.getVectorY() + 1) : (getVectorY() + ent.getVectorY() - 1));
		}
	    }
	}
    }

    /**
     * 
     * x, y positions de la hitbox � tester
     * 
     * @param sx
     *            largeur hitbox � tester
     * @param sy
     *            hauteur hitbox � tester
     */
    public boolean checkCollision(int x, int y, int sx, int sy) {
	Rectangle r1 = new Rectangle((int) (posX - panGame.cellSizeX / 2), (int) (posY - panGame.cellSizeY / 2), (int) (panGame.cellSizeX), (int) (panGame.cellSizeY));
	Rectangle r2 = new Rectangle((int) (x - panGame.cellSizeX / 2), (int) (y - panGame.cellSizeY), sx, sy);

	return r1.intersects(r2);
    }

    public boolean checkCollisionEntity(Entity ent) {
	Rectangle r1 = new Rectangle((int) (posX - panGame.cellSizeX / 2), (int) (posY - panGame.cellSizeY / 2), (int) (panGame.cellSizeX), (int) (panGame.cellSizeY));
	Rectangle r2 = new Rectangle((int) (ent.getPosX() - panGame.cellSizeX / 2), (int) (ent.getPosY() - panGame.cellSizeY / 2), (int) (panGame.cellSizeX), (int) (panGame.cellSizeY));
	return r1.intersects(r2);
    }

    public void setNiveau(Niveau niv) {
	niveau = niv;
    }

    public Rectangle getHitbox() {
	Rectangle r1 = new Rectangle((int) (posX - panGame.cellSizeX / 2), (int) (posY - panGame.cellSizeY / 2), (int) (panGame.cellSizeX), (int) (panGame.cellSizeY));
	return r1;
    }

    public void setPanneauGame(PanneauGame pan) {
	panGame = pan;
    }

    public void applyPhysics() {
	collide(); // WTF
	if (niveau.getArray().length > ((posX + getVectorX() + panGame.cellSizeX) / panGame.cellSizeX) && ((posX + getVectorX()) / panGame.cellSizeX) > 0) // WAT
																		 // ?
	    posX = posX + getVectorX();
	if (niveau.getArray()[1].length > ((posY + getVectorY() + panGame.cellSizeY) / panGame.cellSizeY) && ((posY + getVectorY()) / panGame.cellSizeY) > 0) // RE-WHAT
																		    // ?
	    posY = posY + getVectorY();

	setVectorX(getVectorX() / 2); // WHY ?
	setVectorY(getVectorY() / 2);

    }

    public void addForceX(double force) {
	setVectorX(getVectorX() + force);
    }

    public void addForceY(double force) {
	setVectorY(getVectorY() + force);
    }

    public void setPosX(double pos) {
	posX = pos;
    }

    public void setPosY(double pos) {
	posY = pos;
    }

    public double getPosX() {
	return posX;
    }

    public double getPosY() {
	return posY;
    }

    public String getName(){
	return name;
    }

    public double getVectorX() {
	return vectorX;
    }

    public void setVectorX(double vectorX) {
	this.vectorX = vectorX;
    }

    public double getVectorY() {
	return vectorY;
    }

    public void setVectorY(double vectorY) {
	this.vectorY = vectorY;
    }
}