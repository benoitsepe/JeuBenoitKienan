package com.benoitkienan.items;

import java.awt.image.BufferedImage;

import com.benoitkienan.jeu.Entity;
import com.benoitkienan.tiles.Tile;

public class Item {
    boolean isTile;
    boolean isWeapon;
    String name;
    Tile tile;
    BufferedImage itemImg;

    public Item() {

    }

    public Item(Tile tile) {
	this.tile = tile;
	isTile = true;
	name = tile.getName();
	itemImg = tile.getImg();
    }
    
    public Item(Entity entity){
	name=entity.getName();
    }

    public boolean isTile() {
	return isTile;
    }

    public Tile getTile() {
	return tile;
    }

    public String getName() {
	return name;
    }

    public BufferedImage getImg() {
	return itemImg;
    }

}
