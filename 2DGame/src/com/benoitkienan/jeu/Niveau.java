package com.benoitkienan.jeu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Niveau implements Serializable {

    public int arraySizeX = 64 * 10;
    public int arraySizeY = 36 * 10;

    Tile arrayLvl[][] = new Tile[arraySizeX][arraySizeY];
    BufferedImage blueBrickImg, redBrickImg, blackBrickImg, goldBrickImg, grassImg;

    TileManager tileManager = new TileManager();
    ArrayList<Tile> TileList;

    public Niveau() {
	TileList = tileManager.getTileList();
    }

    public void createRandomLvl() {
	for (int x = 0; x < arraySizeX; x++) {
	    for (int y = 0; y < arraySizeY; y++) {

		int item = new Random().nextInt(TileList.size());
		int i = 0;
		for (Tile tile : TileList) {
		    if (i == item)
			arrayLvl[x][y] = tile;
		    i = i + 1;
		}

	    }
	}
    }

    public void createEmptyLvl() {

	for (int x = 0; x < arraySizeX; x++) {
	    for (int y = 0; y < arraySizeY; y++) {
		arrayLvl[x][y] = tileManager.grass;
	    }
	}

    }

    public void destroyTile(int x, int y) {
	arrayLvl[x][y] = tileManager.grass;
    }

    public Tile[][] getArray() {
	return arrayLvl;
    }

    public void setArray(Tile[][] array) {
	arrayLvl = array;
    }

    public int getArraySizeX() {
	return arraySizeX;
    }

    public int getArraySizeY() {
	return arraySizeY;
    }
}
