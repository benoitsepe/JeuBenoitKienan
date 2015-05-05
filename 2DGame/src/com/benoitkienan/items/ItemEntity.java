package com.benoitkienan.items;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.benoitkienan.jeu.Entity;

public class ItemEntity extends Item {
    Entity entity;

    public ItemEntity(Entity ent) {
	entity = ent;

	try {
	    itemImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/gun.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void use() {
    }

}
