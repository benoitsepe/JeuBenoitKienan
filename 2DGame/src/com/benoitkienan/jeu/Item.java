package com.benoitkienan.jeu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Item {
    boolean isTile;
    boolean isWeapon;
    String name;
    Tile tile;
    Entity entity;
    BufferedImage itemImg;

    //
    public Item(String name, String imgPath) {
	this.name = name;

	try {
	    itemImg = ImageIO.read(new File(imgPath));
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public Item(Tile tile) {
	this.tile = tile;
	isTile = true;
	name = tile.getName();
	itemImg = tile.getImg();
    }

    public Item(Entity ent) {
	entity = ent;

	try {
	    itemImg = ImageIO.read(new File("Pictures/gun.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public boolean isTile() {
	return isTile;
    }

    public String getName() {
	return name;
    }

    public Tile getTile() throws uselessException{
	if(isTile)
	return tile;
	
	else 
	    throw new uselessException();

    }
    
    public Object use() throws uselessException {
	if (isTile) {
	    return tile;
	}

	else if (isWeapon) {
	    return entity;
	}

	else
	    throw new uselessException();
    }

    public BufferedImage getImg() {
	return itemImg;
    }

    class uselessException extends Exception {
	public uselessException() {
	    System.out.println("Cet objet est inutile");
	}
    }

}
