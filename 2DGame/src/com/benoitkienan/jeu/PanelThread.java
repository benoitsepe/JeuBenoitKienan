package com.benoitkienan.jeu;

import java.awt.Color;
import java.awt.Dimension;

public class PanelThread {
    PanneauGame panGame = new PanneauGame();
    Hud hud = new Hud();
    int[][] fenArray = new int[panGame.lvl.getArraySizeX()][panGame.lvl.getArraySizeY()];
    Fenetre fen;
    ToolBar toolBar;

    public PanelThread(Fenetre f, PanneauGame p, Hud h) {
	hud=h;
	panGame = p;
	fen = f;
	toolBar = fen.toolBar;
    }

    public void goPanel() {

	while (true) {
	    hud.repaint();
	    panGame.repaint();

	    try {
		Thread.sleep(5);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}
    }

}
