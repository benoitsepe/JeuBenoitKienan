package com.benoitkienan.entities;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import com.benoitkienan.affichage.PanneauGame;
import com.benoitkienan.niveau.Niveau;
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
	this.name = name;
	tileManager = new TileManager();
	try {
	    image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/notDefined.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public Entity(String name, String imgName) {
	this.name = name;
	tileManager = new TileManager();
	try {
	    image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/" + imgName));
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

    public void spawnAt(int posX, int posY) {
	this.posX = posX;
	this.posY = posY;
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

    public void setImage(String imgName) {
	try {
	    image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/" + imgName));
	} catch (IOException e) {
	    e.printStackTrace();
	}
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
		if (checkCollision((ent.getPosX() + ent.getVectorX()), (ent.getPosY() + ent.getVectorY()), (panGame.cellSizeX), (panGame.cellSizeY))) {

		    this.addForceX((getVectorX() > 0) ? ent.getVectorX() : ent.getVectorX());
		    this.addForceY((getVectorY() > 0) ? ent.getVectorY() : ent.getVectorY());

		    // setVectorX((getVectorX() > 0) ? (getVectorX() +
		    // ent.getVectorX() + 0.1) : (getVectorX() +
		    // ent.getVectorX() - 0.1));
		    // setVectorY((getVectorY() > 0) ? (getVectorY() +
		    // ent.getVectorY() + 0.1) : (getVectorY() +
		    // ent.getVectorY() - 0.1));

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
    public boolean checkCollision(double x, double y, double sx, double sy) {
	Rectangle2D.Double r1 = new Rectangle2D.Double((posX - panGame.cellSizeX / 2), (posY - panGame.cellSizeY / 2), (panGame.cellSizeX), (panGame.cellSizeY));
	Rectangle2D.Double r2 = new Rectangle2D.Double((x - panGame.cellSizeX / 2), (y - panGame.cellSizeY / 2), sx, sy);

	return r1.intersects(r2);
    }

    public boolean checkCollisionEntity(Entity ent) {
	Rectangle2D.Double r1 = new Rectangle2D.Double((posX - panGame.cellSizeX / 2), (posY - panGame.cellSizeY / 2), (panGame.cellSizeX), (panGame.cellSizeY));
	Rectangle2D.Double r2 = new Rectangle2D.Double((ent.getPosX() - panGame.cellSizeX / 2), (ent.getPosY() - panGame.cellSizeY / 2), (panGame.cellSizeX), (panGame.cellSizeY));
	return r1.intersects(r2);
    }

    public void setNiveau(Niveau niv) {
	niveau = niv;
    }

    public Rectangle2D.Double getHitbox() {
	Rectangle2D.Double r1 = new Rectangle2D.Double((posX - panGame.cellSizeX / 2), (int) (posY - panGame.cellSizeY / 2), (panGame.cellSizeX), (panGame.cellSizeY));
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

    public String getName() {
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
