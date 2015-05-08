package com.benoitkienan.items;

import java.awt.image.BufferedImage;

import com.benoitkienan.entities.Entity;
import com.benoitkienan.gun.Arme;
import com.benoitkienan.tiles.Tile;

public class Item {
    boolean isTile = false;
    boolean isWeapon = false;
    boolean isSpawner = false;
    String name;
    Tile tile;
    Arme arme;
    BufferedImage itemImg;

    public Item() {

    }

    public Item(Tile tile) {
	this.tile = tile;
	isTile = true;
	name = tile.getName();
	itemImg = tile.getImg();
    }

    public Item(Entity entity) {
	name = entity.getName();
	isSpawner = true;
    }

    public Item(Arme arme) {
	this.arme = arme;
	isWeapon = true;
	name = arme.getNom();
	arme.getImg();
    }

    public boolean isTile() {
	return isTile;
    }

    public boolean isWeapon() {
	return isWeapon;
    }

    public Tile getTile() {
	return tile;
    }

    public Arme getArme() {
	return arme;
    }

    public String getName() {
	return name;
    }

    public BufferedImage getImg() {
	return itemImg;
    }

}
