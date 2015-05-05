package com.benoitkienan.items;

import java.util.Hashtable;

import com.benoitkienan.gun.Arme;
import com.benoitkienan.gun.ArmeManager;
import com.benoitkienan.tiles.Tile;
import com.benoitkienan.tiles.TileManager;

public class ItemManager {
    Hashtable<String, Item> ItemList = new Hashtable<String, Item>();

    public ItemManager() {

	/* AJOUT DES TILES */
	TileManager tileManager = new TileManager();
	for (Tile tile : tileManager.getTileList()) {
	    ItemList.put(tile.getName(), new Item(tile));
	}

	/* AJOUT DES ARMES */
	ArmeManager armeManager = new ArmeManager();
	for (Arme arme : armeManager.getArmeList()) {
	    ItemList.put(arme.getNom(), new Item(arme));
	}

    }

    public Hashtable<String, Item> getItemList() {
	return ItemList;
    }

}
