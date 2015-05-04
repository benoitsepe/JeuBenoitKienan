package com.benoitkienan.jeu;

import java.util.Hashtable;

public class ItemManager {
    Hashtable<String, Item> ItemList = new Hashtable<String, Item>();

    public ItemManager() {
	TileManager tileManager = new TileManager();

	for (Tile tile : tileManager.getTileList()) {
	    System.out.println("Tile:" + tile.getName());
	    ItemList.put(tile.getName(), new Item(tile));
	}

	Item spawner = new Item(new Entity());
	ItemList.put("spawner", spawner);
    }

    public Hashtable<String, Item> getItemList() {
	return ItemList;
    }

}
