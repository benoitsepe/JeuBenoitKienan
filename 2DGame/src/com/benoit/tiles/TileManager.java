package com.benoit.tiles;

import java.util.ArrayList;
import java.util.Hashtable;

public class TileManager {
    ArrayList<Tile> TileList;
    Tile blueBrick, redBrick, blackBrick, goldBrick;
    public Tile grass;

    public TileManager() {

	blueBrick = new Tile("Pictures/blueBrick.png", true, "blueBrick");
	redBrick = new Tile("Pictures/redBrick.png", true, "redBrick");
	blackBrick = new Tile("Pictures/blackBrick.png", true, "blackBrick");
	goldBrick = new Tile("Pictures/goldBrick.png", true, "goldBrick");
	grass = new Tile("Pictures/grass.png", false, "grass");

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
