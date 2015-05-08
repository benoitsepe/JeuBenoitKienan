package com.benoitkienan.entities;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.benoitkienan.tiles.TileManager;

public class Player extends Entity implements KeyListener {
    KeyListener clavier;
    boolean keyUpPressed, keyRightPressed, keyLeftPressed, keyDownPressed, keyNukePressed, keyMinusPressed, keyPlusPressed;
    boolean callOptions;
    char keyUp = 'z';
    char keyRight = 'd';
    char keyLeft = 'q';
    char keyDown = 's';
    char keyNuke = ' ';
    char keyMinus = '-';
    char keyPlus = '+';

    int speed = 10;
    double zoom = 0.4;

    public Player(String name) {
	super(name);
    }

    public Player(String name, String imgName) {
	super(name);
	try {
	    image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/" + imgName));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // public Player(Niveau niveau, char Up, char Down, char Left, char Right,
    // char Nuke) {
    // keyUp = Up;
    // keyDown = Down;
    // keyLeft = Left;
    // keyRight = Right;
    // keyNuke = Nuke;
    // }

    public void runPlayer() {
	if (keyUpPressed == true) {
	    this.addForceY(-speed);
	}
	if (keyDownPressed) {
	    this.addForceY(speed);
	}
	if (keyLeftPressed) {
	    this.addForceX(-speed);
	}
	if (keyRightPressed) {
	    this.addForceX(speed);
	}
	if (keyNukePressed) {
	    this.nuke(niveau.getArray(), 5);
	}
	if (keyPlusPressed && zoom < 0.95) {
	    zoom = zoom + 0.01;
	}
	if (keyMinusPressed && zoom > 0.05) {
	    zoom = zoom - 0.01;
	}

	// if(Math.toDegrees(rotation)>0 && Math.toDegrees(rotation)<90){
	// try {
	// image =
	// ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/linkRight.png"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }else if(Math.toDegrees(rotation)>90 &&
	// Math.toDegrees(rotation)<180){
	// try {
	// image =
	// ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/linkBack.png"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }else if(Math.toDegrees(rotation)>-180 &&
	// Math.toDegrees(rotation)<-90){
	// try {
	// image =
	// ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/linkLeft.png"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }else if(Math.toDegrees(rotation)>-90 && Math.toDegrees(rotation)<0){
	// try {
	// image =
	// ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Pictures/linkFace.png"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	try {
	    Thread.sleep(5);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void shoot(double shootX, double shootY, ArrayList<Entity> EntityList) {
	posX = posX + (Math.cos(shootY));
	posY = posY + (Math.sin(shootY));

	for (Entity entity : EntityList) {
	    if (entity.getHitbox().contains(new Point((int) shootX, (int) shootY))) {
		System.out.println(entity.getName() + " a été touché");
	    }
	}

	if (niveau.getArray()[(int) shootX][(int) shootY].isSolid()) {
	    System.out.println("[" + (int) shootX + "][" + (int) shootY + "] a reçu un tir");
	    niveau.getArray()[(int) shootX][(int) shootY] = (new TileManager().grass);
	}

    }

    public double getRotationWithMouse(double mouseX, double mouseY) {
	double x = mouseX - posX;
	double y = mouseY - posY;
	double modZ = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	double cosTheta = x / modZ;
	double sinTheta = y / modZ;
	double theta = Math.acos(cosTheta);

	if (y < 0) {
	    rotation = -theta;
	} else {
	    rotation = theta;
	}

	return rotation;
    }

    public double getZoom() {
	return zoom;
    }

    public void keyPressed(KeyEvent e) {
	if (e.getKeyChar() == keyUp) {
	    keyUpPressed = true;
	}
	if (e.getKeyChar() == keyDown) {
	    keyDownPressed = true;
	}
	if (e.getKeyChar() == keyLeft) {
	    keyLeftPressed = true;
	}
	if (e.getKeyChar() == keyRight) {
	    keyRightPressed = true;
	}
	if (e.getKeyChar() == keyNuke) {
	    keyNukePressed = true;
	}
	if (e.getKeyChar() == keyPlus) {
	    keyPlusPressed = true;
	}
	if (e.getKeyChar() == keyMinus) {
	    keyMinusPressed = true;
	}

    }

    public void keyReleased(KeyEvent e) {
	if (e.getKeyChar() == keyUp) {
	    keyUpPressed = false;
	}
	if (e.getKeyChar() == keyDown) {
	    keyDownPressed = false;
	}
	if (e.getKeyChar() == keyLeft) {
	    keyLeftPressed = false;
	}
	if (e.getKeyChar() == keyRight) {
	    keyRightPressed = false;
	}
	if (e.getKeyChar() == keyNuke) {
	    keyNukePressed = false;
	}
	if (e.getKeyChar() == keyPlus) {
	    keyPlusPressed = false;
	}
	if (e.getKeyChar() == keyMinus) {
	    keyMinusPressed = false;
	}

    }

    public void keyTyped(KeyEvent e) {

    }

}
