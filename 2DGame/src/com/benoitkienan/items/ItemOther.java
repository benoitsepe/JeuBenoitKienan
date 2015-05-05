package com.benoitkienan.items;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ItemOther extends Item {

    public ItemOther(String name, String imgPath) {
	this.name = name;

	try {
	    itemImg = ImageIO.read(new File(imgPath));
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public void use() {

    }

}
