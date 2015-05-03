package com.benoitkienan.jeu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Moteur {

    Niveau lvl = new Niveau();
    Player player = new Player(lvl);
    Mob mob1 = new Mob(lvl);
    Mob mob2 = new Mob(lvl);
    BufferedImage blueBrick, redBrick, blackBrick, goldBrick, sorcier, cage,
	    poulpe, nyan;

    PanneauGame panGame;
    int x, y, d; // Pour
    // cercle
    // d'Andres
    int toolSelected = 2;
    ArrayList<Mob> MobList = new ArrayList<Mob>();
    ArrayList<Player> PlayerList = new ArrayList<Player>();
    ArrayList<Entity> EntityList = new ArrayList<Entity>();

    Thread tBalle;

    public Moteur(PanneauGame pan) {

	try {

	    sorcier = ImageIO.read(new File("Pictures/sorcier.png"));
	    cage = ImageIO.read(new File("Pictures/CAGE.png"));
	    poulpe = ImageIO.read(new File("Pictures/poulpe.png"));
	    nyan = ImageIO.read(new File("Pictures/nyan.png"));

	} catch (IOException e) {
	    e.printStackTrace();
	}

	player.setImage(nyan);
	// player2.setImage(poulpe);
	mob1.setImage(sorcier);
	mob2.setImage(cage);

	lvl.createRandomLvl();
	panGame = pan;
	PlayerList.add(player);
	// PlayerList.add(player2);
	MobList.add(mob1);
	MobList.add(mob2);

	for (Player pl : PlayerList) {
	    pl.setNiveau(lvl);
	    pl.setPanneauGame(panGame);
	    EntityList.add(pl);
	}

	for (Mob mob : MobList) {
	    mob.setNiveau(lvl);
	    mob.setPanneauGame(panGame);
	    EntityList.add(mob);
	}

    }

    public void runIA() {
	for (Mob mob : MobList) {
	    mob.spawnRandom();
	    mob.goToNearestPlayer(PlayerList, lvl.getArray());
	    mob.nuke(lvl.getArray(), 50);

	}

	while (true) {
	    for (Mob mob : MobList) {
		mob.setNiveau(lvl);
		mob.collideEntites(EntityList);
		mob.applyPhysics();

		mob.getNearestPlayer(PlayerList, lvl.getArray());
		if (mob.shortestPath != null) {
		    mob.goToNearestPlayer(PlayerList, lvl.getArray());
		    mob.followPath();
		}

	    }
	    panGame.setMobList(MobList);

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
		System.out.println("["
			+ panGame.getPointeurX()
			+ "]["
			+ panGame.getPointeurY()
			+ "]:"
			+ lvl.getArray()[panGame.getPointeurX()][panGame
				.getPointeurY()]);
		panGame.clicMiddle = false;
	    }

	    if (panGame.getClicGauche() == true) {
		try {

		    if (toolSelected == 5) { // TIR

			long new_temps = System.currentTimeMillis();

			if ((new_temps - temps) > 1000) { // Intervalle
							  // entre chaque
							  // tir : 1 sec

			    temps = System.currentTimeMillis();
			    Thread tBalle = new Thread(new RunBalle());
			    tBalle.start();
			}

		    } else {

			lvl.getArray()[panGame.getPointeurX()][panGame
				.getPointeurY()] = toolSelected;
			panGame.setNiveau(lvl);
		    }
		} catch (ArrayIndexOutOfBoundsException e) {
		    e.printStackTrace();
		}

	    }

	    if (panGame.getClicDroit() == true) {
		try {
		    lvl.getArray()[panGame.getPointeurX()][panGame
			    .getPointeurY()] = 0;

		} catch (ArrayIndexOutOfBoundsException e) {
		    e.printStackTrace();
		}
	    }

	    panGame.setPlayerList(PlayerList);
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
	panGame.setPlayerList(PlayerList);
	panGame.setMobList(MobList);

	for (Player player : PlayerList) {
	    player.setPanneauGame(panGame);
	    player.setNiveau(lvl);
	    player.spawnRandom();
	    player.nuke(lvl.getArray(), 10);

	}

	while (true) {

	    try {
		for (Player player : PlayerList) {

		    player.runPlayer();
		    player.collideEntites(EntityList);
		    player.applyPhysics();
		    player.setNiveau(lvl);
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
	    System.out.println(angle);

	    Balle balle = new Balle(posXPlayer, posYPlayer, 1, 10.0); // Nouvelle
	    // balle

	    int[][] tableau = lvl.getArray();

	    while (true) {

		try {

		    int x = (int) (balle.getPosX() / panGame.cellSizeX);
		    int y = (int) (balle.getPosY() / panGame.cellSizeY);

		    if (tableau[x][y] != 0) { // SI on a touche un mur
			System.out.println("Impact en x=" + x + " et y=" + y);
			break; // on sort de la boucle
		    }

		    balle.setPosX(balle.getPosX() + Math.cos(angle));
		    balle.setPosY(balle.getPosY() + Math.sin(angle));

		} catch (Exception e) { // Si on a un outofbound on
					// consid�re
		    // que c'est un mur
		    System.out.println("Impact en x=" + x + " et y=" + y);
		    break;
		}

	    }

	}
    }

}
