package com.benoitkienan.items;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.benoit.tiles.Tile;
import com.benoitkienan.jeu.Entity;

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

    public String getName() {
	return name;
    }


    public BufferedImage getImg() {
	return itemImg;
    }

}
