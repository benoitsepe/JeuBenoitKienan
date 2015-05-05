package com.benoitkienan.items;

import java.awt.image.BufferedImage;

<<<<<<< HEAD
import com.benoitkienan.jeu.Entity;
import com.benoitkienan.tiles.Tile;
=======
import com.benoit.tiles.Tile;
>>>>>>> branch 'master' of https://github.com/mister-benoit/JeuBenoitKienan.git

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
