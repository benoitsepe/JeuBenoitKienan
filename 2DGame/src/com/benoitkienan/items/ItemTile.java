package com.benoitkienan.items;

import com.benoitkienan.tiles.Tile;

public class ItemTile extends Item {

    public ItemTile(Tile tile) {
	this.tile = tile;
	isTile = true;
	name = tile.getName();
	itemImg = tile.getImg();
    }

    public void use(Tile[][] array, int x, int y) {
	array[x][y] = tile;
    }

    public Tile getTile() {
	return tile;
    }

}
