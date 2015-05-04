package com.benoitkienan.jeu;

import java.io.Serializable;
import java.util.Random;

public class Niveau implements Serializable {

    public int arraySizeX = 64 * 10;
    public int arraySizeY = 36 * 10;
    Random rand = new Random();
    Tile arrayLvl[][] = new Tile[arraySizeX][arraySizeY];
    

    public Niveau() {

    }

    public void createRandomLvl() {
	for (int x = 0; x < arraySizeX; x++) {
	    for (int y = 0; y < arraySizeY; y++) {
		arrayLvl[x][y] = rand.nextInt(5);
	    }
	}
    }

    public void createEmptyLvl() {

	for (int x = 0; x < arraySizeX; x++) {
	    for (int y = 0; y < arraySizeY; y++) {
		arrayLvl[x][y] = 0;
	    }
	}

    }

    public void destroyTile(int x, int y) {
	arrayLvl[x][y] = 0;
    }

    public int[][] getArray() {
	return arrayLvl;
    }

    public void setArray(int[][] array) {
	arrayLvl = array;
    }

    public int getArraySizeX() {
	return arraySizeX;
    }

    public int getArraySizeY() {
	return arraySizeY;
    }
}
