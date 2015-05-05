package com.benoitkienan.tiles;

import java.util.ArrayList;

public class TileManager {
    ArrayList<Tile> TileList;
    public Tile grass, dirtBrick, redBrick, blackBrick, goldBrick, wood1, wood2, wood3;

    public TileManager() {

	dirtBrick = new Tile("texture terre.png", false, "dirt");
	redBrick = new Tile("redBrick.png", true, "redBrick");
	blackBrick = new Tile("blackBrick.png", true, "blackBrick");
	goldBrick = new Tile("goldBrick.png", true, "goldBrick");
	grass = new Tile("texture gazon.png", false, "grass");
	wood1 = new Tile("texture bois 2.png", true, "wood");

	TileList = new ArrayList<Tile>();

	TileList.add(dirtBrick);
	TileList.add(redBrick);
	TileList.add(blackBrick);
	TileList.add(goldBrick);
	TileList.add(grass);
	TileList.add(wood1);

    }

    public ArrayList<Tile> getTileList() {
	return TileList;
    }

}
