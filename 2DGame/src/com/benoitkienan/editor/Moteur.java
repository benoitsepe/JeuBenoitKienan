package com.benoitkienan.editor;

import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;

import com.benoitkienan.affichage.PanneauGame;
import com.benoitkienan.entities.Camera;
import com.benoitkienan.entities.EntitiesManager;
import com.benoitkienan.entities.Entity;
import com.benoitkienan.entities.Mob;
import com.benoitkienan.entities.Player;
import com.benoitkienan.gun.Balle;
import com.benoitkienan.items.Item;
import com.benoitkienan.niveau.Niveau;
import com.benoitkienan.tiles.Tile;
import com.benoitkienan.tiles.TileManager;

public class Moteur {

    Niveau lvl = new Niveau();
    public Camera camera = new Camera("Roger");
    BufferedImage blueBrick, redBrick, blackBrick, goldBrick, sorcier, cage, poulpe, nyan;
    PanneauGame panGame;
    int x, y, d; // Pour cercle d'Andres
    Tile toolSelected;
    TileManager tileManager;

    public Moteur(PanneauGame pan) {

	tileManager = new TileManager();

	lvl.createRandomLvl();
	panGame = pan;

	camera.setNiveau(lvl);
	camera.setPanneauGame(panGame);

    }

    public void runGame() {
	while (true) {

	    if (panGame.getClicMiddle() == true) {
		System.out.println("[" + panGame.getPointeurX() + "][" + panGame.getPointeurY() + "]:" + lvl.getArray()[panGame.getPointeurX()][panGame.getPointeurY()].getName());
		panGame.clicMiddle = false;
	    }

	    if (panGame.getClicGauche() == true) {
		try {
		    lvl.getArray()[panGame.getPointeurX()][panGame.getPointeurY()] = toolSelected;
		    panGame.setNiveau(lvl);

		} catch (ArrayIndexOutOfBoundsException e) {
		    e.printStackTrace();
		}

	    }

	    if (panGame.getClicDroit() == true) {
		try {
		    lvl.getArray()[panGame.getPointeurX()][panGame.getPointeurY()] = tileManager.grass;

		} catch (ArrayIndexOutOfBoundsException e) {
		    e.printStackTrace();
		}
	    }

	    panGame.setNiveau(lvl);

	    try {
		Thread.sleep(20);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}
    }

    public void motApplyPhysics() {
	panGame.setNiveau(lvl);
	panGame.setFocusEntity(camera);

	camera.setPanneauGame(panGame);
	camera.setNiveau(lvl);
	camera.spawnAt(0, 0);

	while (true) {

	    try {

		camera.runPlayer();
		camera.applyPhysics();
		camera.setNiveau(lvl);

	    } catch (ArrayIndexOutOfBoundsException e) {
		e.printStackTrace();
	    }

	    try {
		Thread.sleep(20);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}

    }

    public void setTileManager(TileManager tileManager) {
	this.lvl.setTileManager(tileManager);
    }

    public void setNiveau(Niveau niv) {
	lvl = niv;
    }

    public void setPanneau(PanneauGame pan) {
	panGame = pan;
    }

    public Niveau getNiveau() {
	return lvl;
    }

    public void setToolSelected(Tile tool) {
	toolSelected = tool;
    }

}
