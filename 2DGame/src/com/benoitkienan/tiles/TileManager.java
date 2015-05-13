package com.benoitkienan.tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class TileManager implements Serializable {
    ArrayList<Tile> TileList;
    public Tile grass, dirtBrick, redBrick, blackBrick, goldBrick, wood1, wood2, wood3;

    public TileManager() {

	dirtBrick = new Tile("dirt.png", false, "dirt");
	blackBrick = new Tile("blackBrick.png", true, "blackBrick");
	goldBrick = new Tile("goldBrick.png", true, "goldBrick");
	grass = new Tile("grass.png", false, "grass");

	TileList = new ArrayList<Tile>();

	TileList.add(dirtBrick);
	TileList.add(blackBrick);
	TileList.add(goldBrick);
	TileList.add(grass);

	for (Tile tile : TileList) {
	    try {
		tile.setTileImg(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/" + tile.getName() + ".png")));
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

    }

    public ArrayList<Tile> getTileList() {
	return TileList;
    }

}
