package com.benoitkienan.items;

import java.util.Hashtable;

import com.benoitkienan.jeu.Mob;
import com.benoitkienan.tiles.Tile;
import com.benoitkienan.tiles.TileManager;

public class ItemManager {
    Hashtable<String, Item> ItemList = new Hashtable<String, Item>();

    public ItemManager() {
	TileManager tileManager = new TileManager();

	for (Tile tile : tileManager.getTileList()) {
	    ItemList.put(tile.getName(), new ItemTile(tile));
	}
	
	
	
    }

    public Hashtable<String, Item> getItemList() {
	return ItemList;
    }

}
