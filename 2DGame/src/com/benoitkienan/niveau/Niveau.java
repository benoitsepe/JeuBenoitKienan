package com.benoitkienan.niveau;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import com.benoitkienan.tiles.Tile;
import com.benoitkienan.tiles.TileManager;

public class Niveau implements Serializable {

    public int arraySizeX = 64 * 10;
    public int arraySizeY = 36 * 10;

    Tile arrayLvl[][] = new Tile[arraySizeX][arraySizeY];
    BufferedImage blueBrickImg, redBrickImg, blackBrickImg, goldBrickImg, grassImg;

    private TileManager tileManager = new TileManager();
    ArrayList<Tile> TileList;

    public Niveau() {
	TileList = getTileManager().getTileList();
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
		arrayLvl[x][y] = getTileManager().grass;
	    }
	}

    }

    public void destroyTile(int x, int y) {
	arrayLvl[x][y] = getTileManager().grass;
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

    public TileManager getTileManager() {
	return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
	this.tileManager = tileManager;
    }
}
