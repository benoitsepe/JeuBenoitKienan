package com.benoitkienan.jeu;

import java.awt.Color;
import java.awt.Dimension;

public class PanelThread {
    PanneauGame panGame = new PanneauGame();
    Tile[][] fenArray = new Tile[panGame.lvl.getArraySizeX()][panGame.lvl.getArraySizeY()];
    Fenetre fen;
    ToolBar toolBar;
    Hud hud;

    public PanelThread(Fenetre fen, PanneauGame panGame, Hud hud) {
	this.hud = hud;
	this.panGame = panGame;
	this.fen = fen;
	this.toolBar = fen.toolBar;
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
