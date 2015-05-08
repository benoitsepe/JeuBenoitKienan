package com.benoitkienan.particles;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import com.benoitkienan.affichage.PanneauGame;
import com.benoitkienan.entities.Entity;
import com.benoitkienan.jeu.Niveau;
import com.benoitkienan.tiles.TileManager;

public class Particle {
    Niveau niveau;
    double posX, posY;
    double vectorX, vectorY;
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
    
    public Particle(String imgName) {
	tileManager = new TileManager();
	try {
	    image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/"+imgName));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void spawnRandom() { // Place la particule aléatoirement
	do {
	    posX = (double) rand.nextInt((niveau.getArraySizeX() - 2) * (int) panGame.cellSizeX);
	    posY = (double) rand.nextInt((niveau.getArraySizeY() - 2) * (int) panGame.cellSizeY);
	} while (niveau.getArray()[(int) (posX / panGame.cellSizeX)][(int) (posY / panGame.cellSizeY)].isSolid());
	System.out.println("Spawned at x:" + posX + " y:" + posY);

    }

    public BufferedImage getImage() {
	return image;
    }

    public double getRotation() {
	return rotation;
    }

    public double getRotationWithVectors() {
	if (vectorX != 0 || vectorY != 0) {

	    modZ = Math.sqrt(Math.pow(vectorX, 2) + Math.pow(vectorY, 2));
	    rotation = Math.acos(vectorX / modZ);
	    return rotation;
	} else {
	    return 0;
	}
    }

    public void collide() { // Collisions avec les blocs seulement
	try {
	    if (niveau.getArray()[(int) ((posX + vectorX) / panGame.cellSizeX)][(int) (posY / panGame.cellSizeY)].isSolid()) {
		vectorX = 0;
	    }

	    if (niveau.getArray()[(int) (posX / panGame.cellSizeX)][(int) ((posY + vectorY) / panGame.cellSizeY)].isSolid()) {
		vectorY = 0;
	    }

	} catch (ArrayIndexOutOfBoundsException e) {
	    e.printStackTrace();
	}
    }

    public void collideEntities(ArrayList<Entity> entList) {
	for (Entity ent : entList) {
		if (checkCollision((int) ent.getPosX(), (int) ent.getPosY(), (int) (panGame.cellSizeX), (int) (panGame.cellSizeY))) {
		    vectorX = (vectorX > 0) ? (vectorX + ent.getVectorX() + 1) : (vectorX + ent.getVectorX() - 1);
		    vectorY = (vectorY > 0) ? (vectorY + ent.getVectorY() + 1) : (vectorY + ent.getVectorY() - 1);
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
	
    }

    public void addForceX(double force) {
	vectorX = vectorX + force;
    }

    public void addForceY(double force) {
	vectorY = vectorY + force;
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
}



