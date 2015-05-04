package com.benoitkienan.editor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.benoitkienan.jeu.Player;

public class Moteur {

    Niveau lvl = new Niveau();
    BufferedImage blueBrick, redBrick, blackBrick, goldBrick, sorcier, cage, poulpe, nyan;
    PanneauGame panGame;
    int x, y, d; // Pour cercle d'Andres
    int toolSelected = 2;
    int sliderValue = 5;
    Camera camera = new Camera(lvl);

    public Moteur(PanneauGame pan) {

	try {

	    sorcier = ImageIO.read(new File("Pictures/sorcier.png"));
	    cage = ImageIO.read(new File("Pictures/CAGE.png"));
	    poulpe = ImageIO.read(new File("Pictures/poulpe.png"));
	    nyan = ImageIO.read(new File("Pictures/nyan.png"));

	} catch (IOException e) {
	    e.printStackTrace();
	}

	lvl.createEmptyLvl();
	panGame = pan;

    }

    public void runGame() {

	while (true) {

	    if (panGame.getClicMiddle() == true) {
		System.out.println("[" + panGame.getPointeurX() + "][" + panGame.getPointeurY() + "]:" + lvl.getArray()[panGame.getPointeurX()][panGame.getPointeurY()]);
		panGame.clicMiddle = false;
	    }

	    if (panGame.getClicGauche() == true) {
		try {

		    for (int r = 0; r < sliderValue; r++) {
			// Algorithme de tracé de cercle d'Andres
			x = 0;
			y = r;
			d = r - 1;
			while (y >= x) {
			    lvl.getArray()[panGame.getPointeurX() + x][panGame.getPointeurY() + y] = toolSelected;
			    lvl.getArray()[panGame.getPointeurX() + y][panGame.getPointeurY() + x] = toolSelected;
			    lvl.getArray()[panGame.getPointeurX() - x][panGame.getPointeurY() + y] = toolSelected;
			    lvl.getArray()[panGame.getPointeurX() - y][panGame.getPointeurY() + x] = toolSelected;
			    lvl.getArray()[panGame.getPointeurX() + x][panGame.getPointeurY() - y] = toolSelected;
			    lvl.getArray()[panGame.getPointeurX() + y][panGame.getPointeurY() - x] = toolSelected;
			    lvl.getArray()[panGame.getPointeurX() - x][panGame.getPointeurY() - y] = toolSelected;
			    lvl.getArray()[panGame.getPointeurX() - y][panGame.getPointeurY() - x] = toolSelected;

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
		    lvl.getArray()[panGame.getPointeurX()][panGame.getPointeurY()] = toolSelected;
		    panGame.setNiveau(lvl);
		} catch (ArrayIndexOutOfBoundsException e) {
		    e.printStackTrace();
		}

	    }

	    if (panGame.getClicDroit() == true) {
		try {
		    lvl.getArray()[panGame.getPointeurX()][panGame.getPointeurY()] = 0;

		} catch (ArrayIndexOutOfBoundsException e) {
		    e.printStackTrace();
		}
	    }

	    panGame.setNiveau(lvl);

	    try {
		Thread.sleep(5);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}
    }

    public void motApplyPhysics() {
	panGame.setNiveau(lvl);
	panGame.setCamera(camera);

	camera.setPanneauGame(panGame);
	camera.setNiveau(lvl);

	while (true) {

	    try {

		camera.runCamera();
		camera.applyPhysics();
		camera.setNiveau(lvl);

	    } catch (ArrayIndexOutOfBoundsException e) {
		e.printStackTrace();
	    }

	    try {
		Thread.sleep(5);
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

    public void setSliderValue(int value) {
	sliderValue = value;
    }

}
