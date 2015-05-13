package com.benoitkienan.tiles;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class DisplayTileManager {
    Hashtable<String, BufferedImage> DisplayList;
    BufferedImage img;
    BufferedImage tileImg;

    public DisplayTileManager() {

	DisplayList = new Hashtable<String, BufferedImage>();
	TileManager tilemanager = new TileManager();

	for (Tile tile : tilemanager.getTileList()) {

	    try {
		tileImg = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/" + tile.getName() + ".png"));
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	    System.out.println(tile);
	    DisplayList.put(tile.getName(), tileImg);
	}
    }

    public Hashtable<String, BufferedImage> getDisplayList() {
	return DisplayList;
    }

}
