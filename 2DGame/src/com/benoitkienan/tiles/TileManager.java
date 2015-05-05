package com.benoitkienan.tiles;

import java.util.ArrayList;
import java.util.Hashtable;

public class TileManager {
    ArrayList<Tile> TileList;
    Tile blueBrick, redBrick, blackBrick, goldBrick;
    public Tile grass;

    public TileManager() {

	blueBrick = new Tile("blueBrick.png", true, "blueBrick");
	redBrick = new Tile("redBrick.png", true, "redBrick");
	blackBrick = new Tile("blackBrick.png", true, "blackBrick");
	goldBrick = new Tile("goldBrick.png", true, "goldBrick");
	grass = new Tile("grass.png", false, "grass");

	TileList = new ArrayList<Tile>();

	TileList.add(blueBrick);
	TileList.add(redBrick);
	TileList.add(blackBrick);
	TileList.add(goldBrick);
	TileList.add(grass);

    }

    public ArrayList<Tile> getTileList() {
	return TileList;
    }

}
