package com.benoitkienan.tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Tile implements Serializable{
    transient BufferedImage tileImg;
    boolean isSolid;
    String name;

    public Tile(String imgName, boolean solid, String name) {
	try {
	    tileImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/" + imgName));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	isSolid = solid;
	this.name = name;
    }
    
    /**
     * @return the tileImg
     */
    public BufferedImage getImg() {
        return tileImg;
    }



    /**
     * @param tileImg the tileImg to set
     */
    public void setTileImg(BufferedImage tileImg) {
        this.tileImg = tileImg;
    }

    public boolean isSolid() {
	return isSolid;
    }

    public String getName() {
	return name;
    }

}
