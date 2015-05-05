package com.benoit.tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
    BufferedImage tileImg;
    boolean isSolid;
    String name;

    public Tile(String imgPath, boolean solid, String name) {
	try {
	    tileImg = ImageIO.read(new File(imgPath));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	isSolid = solid;
	this.name = name;
    }

    public BufferedImage getImg() {
	return tileImg;
    }

    public boolean isSolid() {
	return isSolid;
    }

    public String getName() {
	return name;
    }

}
