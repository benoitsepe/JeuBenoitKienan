package com.benoitkienan.jeu;

import java.awt.image.BufferedImage;

import com.benoitkienan.affichage.PanneauGame;
import com.benoitkienan.entities.EntitiesManager;
import com.benoitkienan.entities.Entity;
import com.benoitkienan.entities.Mob;
import com.benoitkienan.entities.Player;
import com.benoitkienan.gun.Balle;
import com.benoitkienan.items.Item;
import com.benoitkienan.tiles.Tile;
import com.benoitkienan.tiles.TileManager;

public class Moteur {

    Niveau lvl = new Niveau();
    Player player;
    Mob mob1, mob2;
    BufferedImage blueBrick, redBrick, blackBrick, goldBrick, sorcier, cage, poulpe, nyan;

    PanneauGame panGame;
    int x, y, d; // Pour cercle d'Andres
    int toolSelected = 2;
    TileManager tileManager;
    public EntitiesManager entitiesManager;
    Item[] hudItems;
    boolean playerEscape;

    Thread tBalle;

    public Moteur(PanneauGame pan) {

	tileManager = new TileManager();
	entitiesManager = new EntitiesManager();

	lvl.createRandomLvl();
	panGame = pan;

	for (Entity ent : entitiesManager.getEntityList()) {
	    ent.setNiveau(lvl);
	    ent.setPanneauGame(panGame);
	}

    }

    public void runIA() {
	for (Mob mob : entitiesManager.getMobList()) {
	    mob.spawnRandom();
	    mob.goToNearestPlayer(entitiesManager.getPlayerList(), lvl.getArray());
	    mob.nuke(lvl.getArray(), 50);

	}

	while (true) {
	    for (Mob mob : entitiesManager.getMobList()) {
		mob.setNiveau(lvl);
		mob.collideEntites(entitiesManager.getEntityList());
		mob.applyPhysics();

		mob.getNearestPlayer(entitiesManager.getPlayerList());
		if (mob.isInSearchZone()) {
		    mob.goToNearestPlayer(entitiesManager.getPlayerList(), lvl.getArray());
		    mob.followPath();
		}

	    }
	    panGame.setMobList(entitiesManager.getMobList());

	    try {
		Thread.sleep(20);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}
    }

    public void runGame() {

	long temps = System.currentTimeMillis(); // temps pour le tir

	while (true) {

	    if (panGame.getClicMiddle() == true) {
		System.out.println("[" + panGame.getPointeurX() + "][" + panGame.getPointeurY() + "]:" + lvl.getArray()[panGame.getPointeurX()][panGame.getPointeurY()]);
		panGame.clicMiddle = false;
	    }

	    if (panGame.getClicGauche() == true) {
		try {

		    if (hudItems[toolSelected].isWeapon()) { // TIR
			//
			// long new_temps = System.currentTimeMillis();
			//
			// if ((new_temps - temps) > 1000) { // Intervalle entre
			// // chaque tir : 1 sec
			//
			// temps = System.currentTimeMillis();
			// Thread tBalle = new Thread(new RunBalle());
			// tBalle.start();
			// }

			player.shoot(panGame.getPointeurX(), panGame.getPointeurY(), entitiesManager.getEntityList());

		    } else {

			if (hudItems[toolSelected].isTile()) {
			    lvl.getArray()[panGame.getPointeurX()][panGame.getPointeurY()] = hudItems[toolSelected].getTile();
			}

			panGame.setNiveau(lvl);
		    }
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

	    panGame.setPlayerList(entitiesManager.getPlayerList());
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
	panGame.setPlayerList(entitiesManager.getPlayerList());
	panGame.setMobList(entitiesManager.getMobList());

	for (Player player : entitiesManager.getPlayerList()) {
	    player.setPanneauGame(panGame);
	    player.setNiveau(lvl);
	    player.spawnRandom();
	    player.nuke(lvl.getArray(), 10);

	}

	while (true) {

	    try {
		for (Player player : entitiesManager.getPlayerList()) {

		    player.runPlayer();
		    player.collideEntites(entitiesManager.getEntityList());
		    player.applyPhysics();
		    player.setNiveau(lvl);
		    playerEscape = player.getEscapePressed();
		}

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

    public boolean getPlayerEscape() {
	return playerEscape;
    }

    public void setHudItems(Item[] hudItems) {
	this.hudItems = hudItems;
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

    public void setToolSelected(int tool) {
	toolSelected = tool;
    }

    class RunBalle implements Runnable {
	public void run() {

	    // On r�cup�re les positions

	    double posXPlayer = player.getPosX();
	    double posYPlayer = player.getPosY();

	    double posXTir = panGame.getRealPointeurX();
	    double posYTir = panGame.getRealPointeurY();

	    double angle = player.getRotationWithMouse(posXTir, posYTir); // On
									  // prend
									  // l'angle
									  // en
									  // radian

	    Balle balle = new Balle(posXPlayer, posYPlayer, 1, 10.0); // Nouvelle
								      // balle

	    Tile[][] tableau = lvl.getArray();

	    while (true) {

		try {

		    int x = (int) (balle.getPosX() / panGame.cellSizeX);
		    int y = (int) (balle.getPosY() / panGame.cellSizeY);

		    if (tableau[x][y].isSolid()) { // SI on a touche un mur
			System.out.println("Impact en x=" + x + " et y=" + y);
			break; // on sort de la boucle
		    }

		    boolean touch = false;

		    for (Mob mob : entitiesManager.getMobList()) {
			if (mob.checkCollision(x, y, 1, 1)) {
			    System.out.println("TOCUHE ! en x=" + x + " et y=" + y);
			    touch = true;
			    break; // on sort de la boucle
			}
		    }

		    if (touch)
			break;

		    if (player.checkCollision(x, y, 1, 1)) {
			System.out.println("JOUEUR TOUCHE en x=" + x + " et y=" + y);
			break; // on sort de la boucle
		    }

		    balle.setPosX(balle.getPosX() + Math.cos(angle));
		    balle.setPosY(balle.getPosY() + Math.sin(angle));

		} catch (Exception e) { // Si on a un outofbound on consid�re
					// que c'est un mur
		    System.out.println("Impact en x=" + x + " et y=" + y);
		    break;
		}

	    }

	}
    }

}
