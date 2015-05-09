package com.benoitkienan.entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera extends Player implements KeyListener {
    KeyListener clavier;
    boolean keyUpPressed, keyRightPressed, keyLeftPressed, keyDownPressed, keyMinusPressed, keyPlusPressed;
    boolean callOptions;
    char keyUp = 'z';
    char keyRight = 'd';
    char keyLeft = 'q';
    char keyDown = 's';
    char keyNuke = ' ';
    char keyMinus = '-';
    char keyPlus = '+';

    int speed = 20;
    double zoom = 0.4;

    public Camera(String name) {
	super(name);
    }

    public void applyPhysics() {
	if (niveau.getArray().length > ((posX + getVectorX() + panGame.cellSizeX) / panGame.cellSizeX) && ((posX + getVectorX()) / panGame.cellSizeX) > 0)
	    posX = posX + getVectorX();
	if (niveau.getArray()[1].length > ((posY + getVectorY() + panGame.cellSizeY) / panGame.cellSizeY) && ((posY + getVectorY()) / panGame.cellSizeY) > 0)
	    posY = posY + getVectorY();

	setVectorX(getVectorX() / 2);
	setVectorY(getVectorY() / 2);

    }
    
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
	if (keyPlusPressed && zoom < 0.95) {
	    zoom = zoom + 0.01;
	}
	if (keyMinusPressed && zoom > 0.04) {
	    zoom = zoom - 0.01;
	}

	try {
	    Thread.sleep(5);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
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
